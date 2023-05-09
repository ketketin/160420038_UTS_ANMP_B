package com.katheryn.a160420038_uts_anmp_b.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.katheryn.a160420038_uts_anmp_b.Global
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.model.Kost
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
import com.katheryn.a160420038_uts_anmp_b.view.fragment.FavoriteFragmentDirections
import com.katheryn.a160420038_uts_anmp_b.view.fragment.KostListFragmentDirections
import com.katheryn.a160420038_uts_anmp_b.view.fragment.MyKostFragmentDirections
import kotlinx.android.synthetic.main.kost_list_item.view.*
import kotlinx.android.synthetic.main.mykost_item.view.*

class myKostAdapter(
    val myKostList:ArrayList<Kost>
) : RecyclerView.Adapter<myKostAdapter.MyKostViewHolder>() {
    class MyKostViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyKostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.mykost_item, parent, false)
        return MyKostViewHolder(view)
    }

    override fun onBindViewHolder(holder: myKostAdapter.MyKostViewHolder, position: Int) {
        val kost = myKostList[position]
        holder.view.txtkostNameMyKost.text = kost.name
        holder.view.txtPriceMyKost.text = kost.price
        var imageView = holder.view.findViewById<ImageView>(R.id.imageMyKost)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarMyKostitem)
        imageView.loadImage(myKostList[position].photoUrl, progressBar)

        holder.view.btnCheckout.setOnClickListener {
            var action = MyKostFragmentDirections.actionDetailCheckout(kost.id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return myKostList.size
    }

    fun updateMyKostList(newKostList: ArrayList<Kost>) {
        myKostList.clear()
        myKostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}