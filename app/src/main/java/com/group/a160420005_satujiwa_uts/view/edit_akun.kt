package com.group.a160420005_satujiwa_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.util.loadImage
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewTentang
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewUser

class edit_akun : Fragment() {

    private lateinit var viewModel: ListViewUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_akun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewUser::class.java)
        viewModel.fetch()
        observeViewModel(view)
    }

    fun observeViewModel(view:View) {
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<ProgressBar>(R.id.progressBar4).visibility = View.VISIBLE
            }
            else {
                view.findViewById<ProgressBar>(R.id.progressBar4).visibility = View.GONE
            }
        })
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtUsername).setText(it.get(0).username.toString())
            view.findViewById<TextView>(R.id.txtNamaLengkap).setText(it.get(0).nama.toString())
            view.findViewById<TextView>(R.id.txtNoHandphone).setText(it.get(0).noHandphone.toString())
            view.findViewById<TextView>(R.id.txtAlamat).setText(it.get(0).alamat.toString())

            var imageView = view.findViewById<ImageView>(R.id.imgProfileEditAkun)
            var progressBar = view.findViewById<ProgressBar>(R.id.progressBar4)
            imageView.loadImage(it.get(0).foto.toString(), progressBar)
        })
    }

}