package com.example.firstthemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.firstthemvvm.constants.Constants.ID_KEY
import com.example.firstthemvvm.databinding.ActivityMainBinding
import com.example.firstthemvvm.fragments.ItemsFragment
import com.example.firstthemvvm.fragments.SomeItemFragment
import com.example.firstthemvvm.services.ForegroundService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        ForegroundService.startService(this)
        initFragment(savedInstanceState)
        startItemFragmentFromReceiver()
    }

    private fun startItemFragmentFromReceiver() {
        if(intent.hasExtra(ID_KEY)){
            val id = intent.extras?.getInt(ID_KEY) ?: -1
            if(id != -1) {
                val bundle = bundleOf(ID_KEY to id)
                supportFragmentManager.commit {
                    replace<SomeItemFragment>(R.id.container,args = bundle)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager.commit {
                replace<ItemsFragment>(R.id.container)
                setReorderingAllowed(true)
            }
        }
    }
}