package com.group.a160420005_satujiwa_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.model.Aktivitas
import com.group.a160420005_satujiwa_uts.model.GalangSaya
import com.group.a160420005_satujiwa_uts.util.loadImage

class GalangDanaSayaListAdapter(val galangDanaSayaList:ArrayList<GalangSaya>)
    : RecyclerView.Adapter<GalangDanaSayaListAdapter.GalangDanaSayaViewHolder>() {
    class GalangDanaSayaViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateGalangDanaSayaList(newGalangDanaSayaList: ArrayList<GalangSaya>) {
        galangDanaSayaList.clear()
        galangDanaSayaList.addAll(newGalangDanaSayaList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalangDanaSayaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.galang_dana_saya_item, parent, false)
        return GalangDanaSayaViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalangDanaSayaViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtNamaAktivitasGalangDanaSaya).text = galangDanaSayaList[position].namaAktivitas
        holder.view.findViewById<TextView>(R.id.txtNominalGalangDanaSaya).text = "Rp " + galangDanaSayaList[position].nominal

//        holder.view.findViewById<Button>(R.id.btnDetailList).setOnClickListener {
//            val action = ActivityListFragmentDirections.activityToDetail(aktivitasList[position].nama.toString())
//            Navigation.findNavController(it).navigate(action)
//        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imgGalangDanaSaya)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar2)
        imageView.loadImage(galangDanaSayaList[position].foto, progressBar)
    }

    override fun getItemCount(): Int {
        return galangDanaSayaList.size
    }
}