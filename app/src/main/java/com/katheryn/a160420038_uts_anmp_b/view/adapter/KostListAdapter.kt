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
        val kost = kostList[position]
        holder.view.txtName.text = kost.name
        holder.view.txtAddress.text = kost.address
        holder.view.txtPrice.text = kost.price
        var imageView = holder.view.findViewById<ImageView>(R.id.imageKostList)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(kostList[position].photoUrl, progressBar)

        holder.view.btnDetail.setOnClickListener {
            var action: NavDirections
            if(Global.fragment == "KostListFragment"){
                action = KostListFragmentDirections.actionKostDetail(kost.id.toString())
            } else {
                action = FavoriteFragmentDirections.actionDetailFragment(kost.id.toString())
            }
            Navigation.findNavController(it).navigate(action)
        }
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