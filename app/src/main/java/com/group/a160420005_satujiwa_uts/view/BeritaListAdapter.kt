package com.group.a160420005_satujiwa_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.model.Berita
import com.group.a160420005_satujiwa_uts.model.GalangSaya
import com.group.a160420005_satujiwa_uts.util.loadImage

class BeritaListAdapter(val beritaList:ArrayList<Berita>)
    : RecyclerView.Adapter<BeritaListAdapter.BeritaViewHolder>() {
    class BeritaViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateBeritaList(newBeritaList: ArrayList<Berita>) {
        beritaList.clear()
        beritaList.addAll(newBeritaList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_list_berita_item, parent, false)
        return BeritaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtJudulBerita).text = beritaList[position].judul
        holder.view.findViewById<TextView>(R.id.txtDetailBerita).text = beritaList[position].detail


        var imageView = holder.view.findViewById<ImageView>(R.id.imgBerita)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar5)
        imageView.loadImage(beritaList[position].foto, progressBar)
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }
}