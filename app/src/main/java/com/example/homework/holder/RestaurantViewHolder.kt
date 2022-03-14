package com.example.homework.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.entity.Restaurant
import com.example.homework.utils.FrescoLoader
import kotlinx.android.synthetic.main.item_restaurant_list.view.*
import java.math.RoundingMode

class RestaurantViewHolder(view: View, private val ibridge: IRestaurantBridge? = null) :
    RecyclerView.ViewHolder(view) {

    var data: Restaurant? = null

    init {
        itemView.setOnClickListener { ibridge?.onRestaurantClick(restaurant = data) }
    }

    /**
     * bind data from adapter
     */
    fun bindData(restaurant: Restaurant?) {
        data = restaurant
        FrescoLoader.load(restaurant?.image_url, itemView.sdv_logo)
        itemView.tv_title.text = restaurant?.name ?: ""
        itemView.tv_rating.text = restaurant?.rating ?: ""
        itemView.tv_address.text =
            restaurant?.distance?.substringBefore(".")
                .toString() + "m  " + restaurant?.location?.getAddress() ?: ""
    }
}

interface IRestaurantBridge {
    fun onRestaurantClick(restaurant: Restaurant?)
}