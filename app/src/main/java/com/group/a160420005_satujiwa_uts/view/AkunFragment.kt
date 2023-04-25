package com.group.a160420005_satujiwa_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.util.loadImage
import com.group.a160420005_satujiwa_uts.viewmodel.ListViewUser

class AkunFragment : Fragment() {
    private lateinit var viewModel: ListViewUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewUser::class.java)
        viewModel.fetch()
        observeViewModel(view)

        view.findViewById<Button>(R.id.btnGalangDanaSayaAkun).setOnClickListener{
            val action = AkunFragmentDirections.actionToGalangDana()
            Navigation.findNavController(it).navigate(action)
        }
        view.findViewById<Button>(R.id.btnTentangAkun).setOnClickListener{
            val action = AkunFragmentDirections.actionToTentangSatuJiwa()
            Navigation.findNavController(it).navigate(action)
        }
        view.findViewById<Button>(R.id.btnEditProfileAkun).setOnClickListener{
            val action = AkunFragmentDirections.actionToEditAkun()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel(view:View) {
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                view.findViewById<ProgressBar>(R.id.progressBar6).visibility = View.VISIBLE
            }
            else {
                view.findViewById<ProgressBar>(R.id.progressBar6).visibility = View.GONE
            }
        })
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtSapaanAkun).setText("Selamat datang, "+it.get(0).username.toString())
            view.findViewById<TextView>(R.id.txtSaldoAkun).setText("Saldo Saat Ini: "+it.get(0).saldo.toString())
            view.findViewById<TextView>(R.id.txtTotalDonasiAkun).setText("Total Donasimu: "+it.get(0).totalGalangDana.toString())

            var imageView = view.findViewById<ImageView>(R.id.imgProfileAkun)
            var progressBar = view.findViewById<ProgressBar>(R.id.progressBar6)
            imageView.loadImage(it.get(0).foto.toString(), progressBar)
        })
    }
}