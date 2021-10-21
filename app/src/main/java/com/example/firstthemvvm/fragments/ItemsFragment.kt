package com.example.firstthemvvm.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProviders
import com.example.firstthemvvm.R
import com.example.firstthemvvm.constants.Constants.ID_KEY
import com.example.firstthemvvm.databinding.FragmentItemListBinding
import com.example.firstthemvvm.model.adapter.ItemAdapter
import com.example.firstthemvvm.model.entity.Item
import com.example.firstthemvvm.viewmodel.ItemsViewModel

class ItemsFragment : Fragment() {

    private lateinit var binding: FragmentItemListBinding
    private var itemAdapter = ItemAdapter{
            item: Item ->
        val position = bundleOf(ID_KEY to  item.id)
        Log.d("MyApp","$position")
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SomeItemFragment>(R.id.container,args = position)
            addToBackStack(null)
        }
    }
    private val itemsViewModel by lazy { ViewModelProviders.of(this).get(ItemsViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        binding = FragmentItemListBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = itemAdapter
        itemsViewModel.itemsList.observe(viewLifecycleOwner, {
            it.let {
                itemAdapter.submitList(it)
            }
        })
        itemsViewModel.getListItems()
    }
}