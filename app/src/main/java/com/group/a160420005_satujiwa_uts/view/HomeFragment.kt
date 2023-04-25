package com.group.a160420005_satujiwa_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.group.a160420005_satujiwa_uts.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnLangsungGalangDana).setOnClickListener{
            val action = HomeFragmentDirections.actionToItemActivity()
            Navigation.findNavController(it).navigate(action)
        }
        view.findViewById<Button>(R.id.btnBerita).setOnClickListener{
            val action = HomeFragmentDirections.actionItemHomeToBeritaTerkini()
            Navigation.findNavController(it).navigate(action)
        }
    }
}