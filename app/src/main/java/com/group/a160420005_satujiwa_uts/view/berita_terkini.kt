package com.group.a160420005_satujiwa_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewAktivitas
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewBerita

class berita_terkini : Fragment() {
    private lateinit var viewModel: ListViewBerita
    private val beritaListAdapter = BeritaListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_berita_terkini, container, false)
    }


    fun observeViewModel(view:View) {
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            beritaListAdapter.updateBeritaList(it)
        })
        viewModel.beritaLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<TextView>(R.id.txtErrorBerita).visibility = View.VISIBLE
            } else {
                view.findViewById<TextView>(R.id.txtErrorBerita).visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<RecyclerView>(R.id.recViewBerita).visibility = View.GONE
                view.findViewById<ProgressBar>(R.id.progressLoadBerita).visibility = View.VISIBLE
            }
            else {
                view.findViewById<RecyclerView>(R.id.recViewBerita).visibility = View.VISIBLE
                view.findViewById<ProgressBar>(R.id.progressLoadBerita).visibility = View.GONE
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewBerita::class.java)
        viewModel.refresh()

        view.findViewById<RecyclerView>(R.id.recViewBerita).layoutManager = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.recViewBerita).adapter = beritaListAdapter
        observeViewModel(view)

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutBerita).setOnRefreshListener {
            view.findViewById<RecyclerView>(R.id.recViewBerita).visibility = View.GONE
            view.findViewById<TextView>(R.id.txtErrorBerita).visibility = View.GONE
            view.findViewById<RecyclerView>(R.id.recViewBerita).visibility = View.VISIBLE
            viewModel.refresh()
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutBerita).isRefreshing = false
        }
    }
}