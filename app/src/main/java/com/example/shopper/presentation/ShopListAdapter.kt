package com.example.shopper.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.shopper.R
import com.example.shopper.databinding.ItemShopDisabledBinding
import com.example.shopper.databinding.ItemShopEnabledBinding
import com.example.shopper.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    //в этой переменной может лежать
    //функция которая принимате ShopItem и ничего не возвращает
    //сама переменная может принимать null, для этого тут есть знак вопроса (?)

    var onShopClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw java.lang.RuntimeException("Unknown ViewType: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding

        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            //invoke и знак вопроса перед точкой
            // обезателен только если переменная onShopItemLongClickListener
            //может быть null
            // иначе можно использовать такой вызов onShopItemLongClickListener(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopClickListener?.invoke(shopItem)
        }
        when (binding) {
            is ItemShopDisabledBinding -> {
                binding.itemName.text = shopItem.name
                binding.itemCount.text = shopItem.count.toString()
            }
            is ItemShopEnabledBinding -> {
                binding.itemName.text = shopItem.name
                binding.itemCount.text = shopItem.count.toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }

    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0

        const val MAX_POOL_SIZE = 11
    }
}
