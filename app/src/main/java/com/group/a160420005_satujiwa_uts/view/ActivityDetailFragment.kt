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
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.util.loadImage
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewActivityDetail
import java.util.concurrent.TimeUnit


class ActivityDetailFragment : Fragment() {
    private lateinit var viewModel: ListViewActivityDetail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val id = ActivityDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(ListViewActivityDetail::class.java)
            viewModel.fetch(id)
        }

        observeViewModel(view)
    }

    fun observeViewModel(view:View) {
        viewModel.activityDetailLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtJudulAktivitasDetail).setText(it.nama)
            view.findViewById<TextView>(R.id.txtJumlahTerkumpul).setText("Rp "+it.uangTerkumpul)
            view.findViewById<TextView>(R.id.txtJumlahDiminta).setText("Rp "+it.jumlahDiminta)
            view.findViewById<TextView>(R.id.txtJumlahMendonasi).setText(it.jumlahOrang + " telah mendonasi")
            view.findViewById<TextView>(R.id.txtSisaHariDetail).setText("tersisa "+ it.hariTersisa + " hari")
            view.findViewById<TextView>(R.id.txtNamaPenggalangDetail).setText(it.penggalangDana)
            view.findViewById<TextView>(R.id.txtCeritaDetail).setText(it.cerita)
            view.findViewById<ImageView>(R.id.imgPenggalangDetail).loadImage(it.fotoPenggalang,
                view.findViewById<ProgressBar>(R.id.progressBar8))
            view.findViewById<ImageView>(R.id.imgActivityDetail).loadImage(it.fotoAktivitas,
                view.findViewById<ProgressBar>(R.id.progressBar7))
        })
    }
}