package com.group.a160420005_satujiwa_uts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.group.a160420005_satujiwa_uts.R
import com.group.a160420005_satujiwa_uts.model.Aktivitas
import com.group.a160420005_satujiwa_uts.util.loadImage

class AktivitasListAdapter(val aktivitasList:ArrayList<Aktivitas>)
    : RecyclerView.Adapter<AktivitasListAdapter.AktivitasViewHolder>()  {
    class AktivitasViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    fun updateAktivitasList(newAktivitasList: ArrayList<Aktivitas>) {
        aktivitasList.clear()
        aktivitasList.addAll(newAktivitasList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AktivitasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_list_item, parent, false)
        return AktivitasViewHolder(view)
    }

    override fun onBindViewHolder(holder: AktivitasViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtJudulAktivitasList).text = aktivitasList[position].nama
        holder.view.findViewById<TextView>(R.id.txtTerkumpulList).text = "Rp " + aktivitasList[position].uangTerkumpul
        holder.view.findViewById<TextView>(R.id.txtHariList).text = aktivitasList[position].hariTersisa + " hari lagi"

        holder.view.findViewById<Button>(R.id.btnDetailList).setOnClickListener {
            val action = ActivityListFragmentDirections.activityToDetail(aktivitasList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imgActivityList)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(aktivitasList[position].fotoAktivitas, progressBar)
    }

    override fun getItemCount(): Int {
        return aktivitasList.size
    }

}