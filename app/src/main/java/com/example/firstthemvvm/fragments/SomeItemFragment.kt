package com.example.firstthemvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firstthemvvm.R
import com.example.firstthemvvm.constants.Constants.ID_KEY
import com.example.firstthemvvm.constants.Constants.ITEMS_PREFS_KEY
import com.example.firstthemvvm.databinding.FragmentSomeItemBinding
import com.example.firstthemvvm.model.entity.Item
import com.example.firstthemvvm.viewmodel.SomeItemViewModel

class SomeItemFragment : Fragment() {

    private lateinit var binding: FragmentSomeItemBinding
    private val someItemViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return context?.let {
                    SomeItemViewModel(
                        it.getSharedPreferences(
                            ITEMS_PREFS_KEY,
                            -1
                        )
                    )
                } as T
            }
        }).get(SomeItemViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_some_item, container, false)
        binding = FragmentSomeItemBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = requireArguments().getInt(ID_KEY)
        someItemViewModel.saveLastElementId(position)
        someItemViewModel.selectedItem.observe(viewLifecycleOwner, {
            it.let { item: Item ->
                binding.apply {
                    id.text = item.id.toString()
                    name.text = item.name
                    description.text = item.description
                }
            }
        })
        someItemViewModel.getSelectedItem(position)
    }
}