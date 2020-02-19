package me.abulazm.chamatask

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.abulazm.chamatask.data.database.HeritageItemInsertImpl
import me.abulazm.chamatask.data.local.JsonHelper
import me.abulazm.chamatask.data.model.HeritageItem
import me.abulazm.chamatask.features.heritagelist.HeritageListFragment
import me.abulazm.chamatask.utils.isDatabaseInitialized
import me.abulazm.chamatask.utils.readDataFromRawFile

class MainActivity : AppCompatActivity() {

    private val viewSwitcher: ViewSwitcher by lazy { findViewById<ViewSwitcher>(R.id.mainActivityViewSwitcher) }
    private val errorTextView: TextView by lazy { findViewById<TextView>(R.id.errorTextView) }
    private val progressView: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (isDatabaseInitialized()) {
            onDatabaseInitialized()
        } else {
            isDatabaseInitialized(true)
            initializeDatabase()
        }
    }

    private fun initializeDatabase() {
        showProgress()
        MainScope().launch {
            val content = this@MainActivity.readDataFromRawFile(R.raw.world_heritage)
            if (content == null) {
                onError(getString(R.string.error_reading_data))
                return@launch
            }
            val items: List<HeritageItem>? = JsonHelper().parseHeritageItemsFromJson(content)
            if (items.isNullOrEmpty()) {
                onError(getString(R.string.error_reading_data))
                return@launch
            }
            HeritageItemInsertImpl(this@MainActivity).insertItems(items)
            hideProgress()
            onDatabaseInitialized()
        }

    }

    private fun onError(errorMessage: String) {
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = errorMessage
        hideProgress()
    }

    private fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressView.visibility = View.GONE
    }

    private fun onDatabaseInitialized() {
        viewSwitcher.showNext()
        replaceFragment(HeritageListFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        val tag = fragment.javaClass.simpleName
        val fragmentByTag = supportFragmentManager.findFragmentByTag(tag)
        if (fragmentByTag != null && fragmentByTag.isVisible) return
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, tag).commit()
    }
}
