//package com.katheryn.a160420038_uts_anmp_b.view.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.ProgressBar
//import androidx.navigation.Navigation
//import androidx.recyclerview.widget.RecyclerView
//import com.katheryn.a160420038_uts_anmp_b.R
//import com.katheryn.a160420038_uts_anmp_b.model.Fasilitas
//import com.katheryn.a160420038_uts_anmp_b.model.Kost
//import com.katheryn.a160420038_uts_anmp_b.util.loadImage
//import com.katheryn.a160420038_uts_anmp_b.view.fragment.KostDetailFragment
//import com.katheryn.a160420038_uts_anmp_b.view.fragment.KostDetailFragmentDirections
//import kotlinx.android.synthetic.main.fragment_kost_detail.view.*
//
//class KostDetailAdapter(private val kostList: ArrayList<Kost>,
//                        val fasilitas: ArrayList<Fasilitas>
//): RecyclerView.Adapter<KostDetailAdapter.KostViewHolder>() {
//    class KostViewHolder(val view: View): RecyclerView.ViewHolder(view)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.fragment_kost_detail, parent, false)
//        return KostViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
//        val kost = kostList[position]
//
//        var imageView = holder.view.findViewById<ImageView>(R.id.imgKostDetail)
//        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarKostDetail)
//
//        imageView.loadImage(kost.photoUrl, progressBar)
//
//        holder.view.txtAddressKostDetail.text = kost.address.toString()
//        holder.view.txtDescriptionKostDetail.text = kost.description.toString()
//        holder.view.txtNameKostDetail.text = kost.name.toString()
//
//        holder.view.btnPhotos.setOnClickListener {
//            val action = KostDetailFragmentDirections.actionPhotosFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        holder.view.btnFasilitas.setOnClickListener {
//            val action = KostDetailFragmentDirections.actionFacilitiesFragment(kost.id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        holder.view.btnCheckout.setOnClickListener {
//            val action = KostDetailFragmentDirections.actionCheckoutFragment(kost.id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return kostList.size
//    }
//
//    fun updateKostList(newKostList: ArrayList<Kost>) {
//        kostList.clear()
//        kostList.addAll(newKostList)
//        notifyDataSetChanged()
//    }
//
//}