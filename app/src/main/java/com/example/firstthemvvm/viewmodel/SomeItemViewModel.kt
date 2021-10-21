package com.example.firstthemvvm.viewmodel

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstthemvvm.constants.Constants.ITEM_ID_KEY
import com.example.firstthemvvm.model.entity.Item
import com.example.firstthemvvm.model.placeholder.ItemsHolder

class SomeItemViewModel(private val savedPref: SharedPreferences) : ViewModel() {

    private val _selectedItem: MutableLiveData<Item> = MutableLiveData()
    val selectedItem: LiveData<Item> = _selectedItem

    fun getSelectedItem(id: Int) {
        _selectedItem.value = ItemsHolder.getById(id)
    }

    fun saveLastElementId(position: Int) {
        savedPref.edit {
            putInt(ITEM_ID_KEY, position)
        }
    }
}