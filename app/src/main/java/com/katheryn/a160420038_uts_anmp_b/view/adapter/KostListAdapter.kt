package com.katheryn.a160420038_uts_anmp_b.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.model.Kost
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
import com.katheryn.a160420038_uts_anmp_b.view.fragment.KostListFragmentDirections
import kotlinx.android.synthetic.main.fragment_kost_detail.*
import kotlinx.android.synthetic.main.fragment_kost_detail.view.*
import kotlinx.android.synthetic.main.kost_list_item.view.*

class KostListAdapter(
    val kostList:ArrayList<Kost>
) :RecyclerView.Adapter<KostListAdapter.KostViewHolder>() {
    class KostViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
        return KostViewHolder(view)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        holder.view.txtName.text = kostList[position].name.toString()
        holder.view.txtAddress.text = kostList[position].address.toString()
        holder.view.txtPrice.text = kostList[position].price.toString()

        holder.view.btnDetail.setOnClickListener {
            val action = KostListFragmentDirections.actionKostDetail(kostList[position].id!!)
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageKostList)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(kostList[position].photoUrl, progressBar)
    }

    override fun getItemCount(): Int {
        return kostList.size
    }

    fun updateKostList(newKostList: ArrayList<Kost>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}