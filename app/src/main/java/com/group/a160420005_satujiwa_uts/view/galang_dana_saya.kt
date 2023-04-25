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
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewGalangDana


class galang_dana_saya : Fragment() {

    private lateinit var viewModel: ListViewGalangDana
    private val galangDanaSayaListAdapter = GalangDanaSayaListAdapter(arrayListOf())

    fun observeViewModel(view:View) {
        viewModel.galangDanaLD.observe(viewLifecycleOwner, Observer {
            galangDanaSayaListAdapter.updateGalangDanaSayaList(it)
        })
        viewModel.galangDanaLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<TextView>(R.id.txtErrorGalang).visibility = View.VISIBLE
            } else {
                view.findViewById<TextView>(R.id.txtErrorGalang).visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<RecyclerView>(R.id.recViewGalang).visibility = View.GONE
                view.findViewById<ProgressBar>(R.id.progrssload2).visibility = View.VISIBLE
            }
            else {
                view.findViewById<RecyclerView>(R.id.recViewGalang).visibility = View.VISIBLE
                view.findViewById<ProgressBar>(R.id.progrssload2).visibility = View.GONE
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_galang_dana_saya, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewGalangDana::class.java)
        viewModel.refresh()

        view.findViewById<RecyclerView>(R.id.recViewGalang).layoutManager = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.recViewGalang).adapter = galangDanaSayaListAdapter
        observeViewModel(view)

        view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutGalang).setOnRefreshListener {
            view.findViewById<RecyclerView>(R.id.recViewGalang).visibility = View.GONE
            view.findViewById<TextView>(R.id.txtErrorGalang).visibility = View.GONE
            view.findViewById<RecyclerView>(R.id.recViewGalang).visibility = View.VISIBLE
            viewModel.refresh()
            view.findViewById<SwipeRefreshLayout>(R.id.refreshLayoutGalang).isRefreshing = false
        }
    }

}