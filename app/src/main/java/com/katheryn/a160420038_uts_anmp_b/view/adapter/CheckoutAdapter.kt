package com.katheryn.a160420038_uts_anmp_b.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.katheryn.a160420038_uts_anmp_b.R
import com.katheryn.a160420038_uts_anmp_b.model.Checkout
import com.katheryn.a160420038_uts_anmp_b.util.loadImage
import com.katheryn.a160420038_uts_anmp_b.view.fragment.CheckOutKostFragment
import com.katheryn.a160420038_uts_anmp_b.view.fragment.CheckOutKostFragmentDirections
import com.katheryn.a160420038_uts_anmp_b.view.fragment.MyKostFragmentDirections
import kotlinx.android.synthetic.main.mykost_item.view.*

class CheckoutAdapter(
    val checkoutList:ArrayList<Checkout>
) :RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder>(){
    class CheckoutViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.mykost_item, parent, false)
        return  CheckoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.view.txtkostNameMyKost.text = checkoutList[position].kost_name
        holder.view.txtPriceMyKost.text = checkoutList[position].totalPrice

        holder.view.btnCheckout.setOnClickListener {
            val action = MyKostFragmentDirections.actionCheckoutFragment(checkoutList[position].kostID!!)
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageMyKost)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarMyKost)
        imageView.loadImage(checkoutList[position].photoUrl, progressBar)
    }

    override fun getItemCount(): Int {
        return  checkoutList.size
    }

    fun updateCheckoutList(newCheckoutList: ArrayList<Checkout>){
        checkoutList.clear()
        checkoutList.addAll(newCheckoutList)
        notifyDataSetChanged()
    }
}