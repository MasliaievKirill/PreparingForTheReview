package com.masliaiev.preparingforthereview.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.masliaiev.preparingforthereview.databinding.FragmentAddToDatabaseBinding
import com.masliaiev.preparingforthereview.domain.entity.Automobile
import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.entity.EmployeeJobTitle
import com.masliaiev.preparingforthereview.domain.entity.PhoneNumber
import com.masliaiev.preparingforthereview.helpers.responces.DatabaseResponse
import com.masliaiev.preparingforthereview.presentation.viewmodels.AddToDatabaseFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddToDatabaseFragment : Fragment() {

    private var _binding: FragmentAddToDatabaseBinding? = null
    private val binding: FragmentAddToDatabaseBinding
        get() = _binding ?: throw RuntimeException("FragmentAddToDatabaseBinding is null")

    private val viewModel by viewModel<AddToDatabaseFragmentViewModel>()

    private val checkedJobTitles = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddToDatabaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cbDirector.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                checkedJobTitles.add(1)
            } else {
                checkedJobTitles.remove(1)
            }
        }
        binding.cbSoftwareEngineer.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                checkedJobTitles.add(2)
            } else {
                checkedJobTitles.remove(2)
            }
        }

        binding.cbMechanicalEngineer.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                checkedJobTitles.add(3)
            } else {
                checkedJobTitles.remove(3)
            }
        }

        binding.cbQa.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                checkedJobTitles.add(4)
            } else {
                checkedJobTitles.remove(4)
            }
        }

        binding.btnAddEmployee.setOnClickListener {

            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val address = binding.etAddress.text.toString()
            val firstPhone = binding.etFirstNumber.text.toString()
            val secondPhone = binding.etSecondNumber.text.toString()
            val automobileNumber = binding.etAutomobileNumber.text.toString().toInt()
            val automobileBrand = binding.etAutomobileBrand.text.toString()

            val phones = mutableListOf<String>()
            if (firstPhone.isNotEmpty()){
                phones.add(firstPhone)
            }
            if (secondPhone.isNotEmpty()){
                phones.add(secondPhone)
            }


            if(checkedJobTitles.isNotEmpty()){
                viewModel.createRequest(
                    firstName = firstName,
                    lastName = lastName,
                    address = address,
                    automobileNumber = automobileNumber,
                    brand = automobileBrand,
                    phoneNumbers = phones,
                    jobTitlesId = checkedJobTitles
                )
            }
        }

        viewModel.addResultLiveDada.observe(viewLifecycleOwner){
            when(it) {
                DatabaseResponse.SUCCESS -> Log.d("Result", "Success")
                DatabaseResponse.ERROR -> Log.d("Result", "Error")
                else -> Log.d("Result", "Error")
            }
        }

        viewModel.automobileNumberErrorLiveData.observe(viewLifecycleOwner){
            binding.tfAutomobileNumber.error = "Is already exist"
        }

        viewModel.phoneNumberErrorLiveData.observe(viewLifecycleOwner){
            binding.tfFirstNumber.error = "Is already exist"
            binding.tfSecondNumber.error = "Is already exist"
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): AddToDatabaseFragment {
            return AddToDatabaseFragment()
        }
    }
}