package me.abulazm.chamatask.features.heritagelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.abulazm.chamatask.R
import me.abulazm.chamatask.data.database.HeritageDao
import me.abulazm.chamatask.data.database.HeritageDatabase
import me.abulazm.chamatask.data.model.HeritageItem


class HeritageListFragment : Fragment(), HeritageListAdapter.OnHeritageItemClickListener {

    override fun onHeritageItemClick(item: HeritageItem) {
        val uri = Uri.parse("geo:${item.lat},${item.lng}?z=14")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        activity?.let {
            if (intent.resolveActivity(it.packageManager) != null) {
                it.startActivity(intent)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_heritage_list, container, false)
        val heritageDao = getHeritageDao()
        val viewModel = HeritageListViewModel(heritageDao)
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.heritageRecyclerView)
        val adapter = HeritageListAdapter(this)
        viewModel.itemsLiveData.observe(viewLifecycleOwner, getObserver(adapter))
        recyclerView.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        return fragmentView
    }

    private fun getObserver(adapter: HeritageListAdapter): Observer<PagedList<HeritageItem>> {
        return Observer {
            adapter.submitList(it)
        }
    }

    private fun getHeritageDao(): HeritageDao {
        return HeritageDatabase.invoke(activity!!).heritageDao()
    }

    companion object {
        @JvmStatic
        fun newInstance(): HeritageListFragment = HeritageListFragment()
    }

}