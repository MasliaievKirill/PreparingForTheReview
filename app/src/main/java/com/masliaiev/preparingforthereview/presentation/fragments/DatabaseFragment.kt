package com.masliaiev.preparingforthereview.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masliaiev.preparingforthereview.R
import com.masliaiev.preparingforthereview.databinding.FragmentDatabaseBinding
import com.masliaiev.preparingforthereview.helpers.eventbus.Events
import com.masliaiev.preparingforthereview.presentation.adapters.EmployeeAdapter
import com.masliaiev.preparingforthereview.presentation.viewmodels.DatabaseFragmentViewModel
import org.greenrobot.eventbus.EventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

class DatabaseFragment : Fragment() {

    private var _binding: FragmentDatabaseBinding? = null
    private val binding: FragmentDatabaseBinding
        get() = _binding ?: throw RuntimeException("FragmentDatabaseBinding is null")

    private val viewModel by viewModel<DatabaseFragmentViewModel>()

    private val adapter by lazy { EmployeeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDatabaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEmployees.adapter = adapter
        binding.rvEmployees.layoutManager = LinearLayoutManager(requireContext())

        binding.fabAddItem.setOnClickListener {
            EventBus.getDefault().post(Events.AddItemToDatabase)
        }

        viewModel.employeesLiveData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        setupSwipeListener(binding.rvEmployees)

        adapter.onItemClickListener = {
            val fragment = EmployeeInfoFragment.newInstance(it.id)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                parentFragmentManager.popBackStack()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteEmployee(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

}