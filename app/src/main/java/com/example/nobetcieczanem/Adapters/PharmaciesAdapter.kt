package com.example.nobetcieczanem.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nobetcieczanem.Models.Result
import com.example.nobetcieczanem.R

class PharmaciesAdapter(private val context: Context) :
    RecyclerView.Adapter<PharmaciesAdapter.PharmacyViewHolder>() {
    private lateinit var pharmacyList: List<Result>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        return PharmacyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pharmacyList.size
    }

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        val pharmacyModel = pharmacyList[position]
        holder.txtPharmacyName.text = pharmacyModel.name
        holder.txtPharmacyDist.text = pharmacyModel.dist
        holder.txtPharmacyAddress.text = pharmacyModel.address
        holder.txtPharmacyPhone.text = reFormatNumber(pharmacyModel.phone)

        holder.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${pharmacyModel.phone}")
            context.startActivity(intent)
        }

        holder.btnShowLocation.setOnClickListener {
            val locs = pharmacyModel.loc.split(",")
            showLocation(locs[0], locs[1])
        }
    }

    private fun showLocation(latitude: String, longitude: String) {
        val uri = "https://www.google.com.tw/maps/place/$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        context.startActivity(intent)
    }

    private fun reFormatNumber(phone: String): String {
        return phone.substring(0, 3) + " " + phone.substring(3, 6) + " " + phone.substring(
            6,
            8
        ) + " " + phone.substring(8, 10)
    }

    fun setPharmacyList(pharmacyList: List<Result>) {
        this.pharmacyList = pharmacyList
    }

    class PharmacyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtPharmacyName: TextView = itemView.findViewById(R.id.txtPharmacyName)
        val txtPharmacyDist: TextView = itemView.findViewById(R.id.txtPharmacyDist)
        val txtPharmacyAddress: TextView = itemView.findViewById(R.id.txtPharmacyAddress)
        val txtPharmacyPhone: TextView = itemView.findViewById(R.id.txtPharmacyPhone)
        val btnCall: Button = itemView.findViewById(R.id.btnCall)
        val btnShowLocation: Button = itemView.findViewById(R.id.btnShowLocation)
    }
}