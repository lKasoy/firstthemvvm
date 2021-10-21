package com.example.firstthemvvm.model.placeholder

import com.example.firstthemvvm.model.entity.Item

object ItemsHolder {

    val items: List<Item> by lazy {
        val mutableListOfItems = mutableListOf<Item>()
        for (i in 0 until 20){
            mutableListOfItems.add(Item(i,"name $i", "description $i"))
        }
        mutableListOfItems
    }

    fun getItemsList() = items

    fun getById(id: Int): Item {
        return items.first {
            it.id == id
        }
    }
}