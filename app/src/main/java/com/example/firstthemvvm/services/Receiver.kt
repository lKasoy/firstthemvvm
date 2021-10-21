package com.example.firstthemvvm.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.firstthemvvm.MainActivity
import com.example.firstthemvvm.constants.Constants.ID_KEY
import com.example.firstthemvvm.constants.Constants.ITEMS_PREFS_KEY
import com.example.firstthemvvm.constants.Constants.ITEM_ID_KEY

class Receiver: BroadcastReceiver() {

    override fun onReceive(context: Context, p1: Intent?) {
        val sharedPreferences = context.getSharedPreferences(ITEMS_PREFS_KEY,-1)
        val itemId = sharedPreferences.getInt(ITEM_ID_KEY, -1)
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(ID_KEY, itemId)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Log.d("MyApp","$itemId putInt in intent")
        }
        context.startActivity(intent)
    }
}