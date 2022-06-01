package com.podmev.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.podmev.shoppinglist.R
import com.podmev.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ENABLED_VIEW -> R.layout.item_shop_enabled
            DISABLED_VIEW -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
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

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if(shopItem.enabled) ENABLED_VIEW else DISABLED_VIEW
    }

    companion object{
        const val ENABLED_VIEW = 101
        const val DISABLED_VIEW = 102

        const val MAX_POOL_SIZE = 15
    }
}