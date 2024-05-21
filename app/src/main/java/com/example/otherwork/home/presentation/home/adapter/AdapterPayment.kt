package com.example.otherwork.home.presentation.home.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.otherwork.R
import com.example.otherwork.databinding.LayoutGasolineItemBinding
import com.example.otherwork.databinding.LayoutPaymentItemBinding
import com.example.otherwork.extention.layoutInflater
import com.example.otherwork.home.presentation.home.model.GasolineModel

class AdapterPayment(private val itemSelected:(GasolineModel)->Unit) :
    ListAdapter<GasolineModel, RecyclerView.ViewHolder>(
        diffCallback
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val binding =
            LayoutPaymentItemBinding.inflate(parent.layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {

        getItem(position)?.let {
            (holder as ViewHolder).bind(it)
        }
    }

    private inner class ViewHolder(private val binding: LayoutPaymentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(item: GasolineModel) = with(binding) {

            tvPayment.text = item.title

            binding.layoutPaymentItem.setOnClickListener {
                for(items in currentList){
                    items.selected = false
                }
                item.selected = true
                itemSelected(item)
                notifyDataSetChanged()
            }

            if(item.selected){
                val colorInt = Color.parseColor("#303925")
                layoutPaymentItem.backgroundTintList = ColorStateList.valueOf(colorInt)
            }else{
                val colorInt = Color.parseColor("#B7F470")
                layoutPaymentItem.backgroundTintList = ColorStateList.valueOf(colorInt)
            }

        }

    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<GasolineModel>() {
            override fun areItemsTheSame(
                oldItem: GasolineModel,
                newItem: GasolineModel,
            ): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: GasolineModel,
                newItem: GasolineModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}