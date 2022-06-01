package com.podmev.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.podmev.shoppinglist.R
import com.podmev.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var count = 0
    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener: ((ShopItem)->Unit)? = null
    var onShopItemClickListener: ((ShopItem)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder: count=${++count}")
        val layout = when(viewType){
            ENABLED_VIEW -> R.layout.item_shop_enabled
            DISABLED_VIEW -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent,false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
            true
        }
    }

    /*перед переиспользованием view что делаем*/
    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvCount.text = ""
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = shopList[position]
        return if(shopItem.enabled) ENABLED_VIEW else DISABLED_VIEW
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    interface OnShopItemLongClickListener{
        fun onShopItemLongClick(shopItem: ShopItem)
    }

    companion object{
        const val ENABLED_VIEW = 101
        const val DISABLED_VIEW = 102

        const val MAX_POOL_SIZE = 15
    }
}