package com.masliaiev.preparingforthereview.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.masliaiev.preparingforthereview.databinding.FragmentEmployeeInfoBinding

class EmployeeInfoFragment : Fragment() {

    private var _binding: FragmentEmployeeInfoBinding? = null
    private val binding: FragmentEmployeeInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentEmployeeInfoBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val employeeId = requireArguments().getInt(EMPLOYEE_ID)
        binding.tvId.text = employeeId.toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val EMPLOYEE_ID = "id"

        fun newInstance(employeeId: Int): EmployeeInfoFragment {
            return EmployeeInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(EMPLOYEE_ID, employeeId)
                }
            }
        }
    }

}