package com.example.firstthemvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstthemvvm.model.placeholder.ItemsHolder
import com.example.firstthemvvm.model.entity.Item

class ItemsViewModel: ViewModel() {

    private val _itemsList: MutableLiveData<List<Item>> = MutableLiveData()
    val itemsList: LiveData<List<Item>> = _itemsList

    fun getListItems() {
        _itemsList.value = ItemsHolder.getItemsList()
    }
}