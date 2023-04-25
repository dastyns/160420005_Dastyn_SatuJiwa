package com.group.a160420005_satujiwa_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewAktivitas

class ActivityListFragment : Fragment() {

    private lateinit var viewModel:ListViewAktivitas
    private val aktivitasListAdapter = AktivitasListAdapter(arrayListOf())

    fun observeViewModel(view:View) {
        viewModel.aktivitasLD.observe(viewLifecycleOwner, Observer {
            aktivitasListAdapter.updateAktivitasList(it)
        })
        viewModel.aktivitasLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<TextView>(R.id.txtError).visibility = View.VISIBLE
            } else {
                view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<RecyclerView>(R.id.recView).visibility = View.GONE
                view.findViewById<ProgressBar>(R.id.progressLoad).visibility = View.VISIBLE
            }
            else {
                view.findViewById<RecyclerView>(R.id.recView).visibility = View.VISIBLE
                view.findViewById<ProgressBar>(R.id.progressLoad).visibility = View.GONE
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewAktivitas::class.java)
        viewModel.refresh()

        view.findViewById<RecyclerView>(R.id.recView).layoutManager = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.recView).adapter = aktivitasListAdapter
        observeViewModel(view)

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).setOnRefreshListener {
            view.findViewById<RecyclerView>(R.id.recView).visibility = View.GONE
            view.findViewById<TextView>(R.id.txtError).visibility = View.GONE
            view.findViewById<RecyclerView>(R.id.recView).visibility = View.VISIBLE
            viewModel.refresh()
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout).isRefreshing = false
        }
    }
}