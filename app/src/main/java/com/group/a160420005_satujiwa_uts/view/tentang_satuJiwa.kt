package com.group.a160420005_satujiwa_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewTentang
import java.util.concurrent.TimeUnit

class tentang_satuJiwa : Fragment() {
    private lateinit var viewModel: ListViewTentang

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tentang_satu_jiwa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewTentang::class.java)
        viewModel.fetch()
        observeViewModel(view)
    }

    fun observeViewModel(view:View) {
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<ProgressBar>(R.id.progressBar3).visibility = View.VISIBLE
            }
            else {
                view.findViewById<ProgressBar>(R.id.progressBar3).visibility = View.GONE
            }
        })
        viewModel.tentangLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtTentang).setText(it.get(0).tentang.toString())
        })
    }

}