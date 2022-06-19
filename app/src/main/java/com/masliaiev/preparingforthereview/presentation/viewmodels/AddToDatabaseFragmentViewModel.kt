package com.masliaiev.preparingforthereview.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masliaiev.preparingforthereview.domain.entity.Automobile
import com.masliaiev.preparingforthereview.domain.entity.Employee
import com.masliaiev.preparingforthereview.domain.entity.EmployeeJobTitle
import com.masliaiev.preparingforthereview.domain.entity.PhoneNumber
import com.masliaiev.preparingforthereview.domain.usecases.AddEmployeeUseCase
import com.masliaiev.preparingforthereview.domain.usecases.GetAutomobilesListUseCase
import com.masliaiev.preparingforthereview.domain.usecases.GetEmployeesListUseCase
import com.masliaiev.preparingforthereview.domain.usecases.GetPhoneNumbersListUseCase
import com.masliaiev.preparingforthereview.helpers.responces.DatabaseResponse
import kotlinx.coroutines.launch

class AddToDatabaseFragmentViewModel(
    private val addEmployeeUseCase: AddEmployeeUseCase,
    private val getEmployeesListUseCase: GetEmployeesListUseCase,
    private val getAutomobilesListUseCase: GetAutomobilesListUseCase,
    private val getPhoneNumbersListUseCase: GetPhoneNumbersListUseCase
) : ViewModel() {

    private val _addResultLiveDada = MutableLiveData<DatabaseResponse>()
    val addResultLiveDada: LiveData<DatabaseResponse>
        get() = _addResultLiveDada

    private val _newUserIdLiveData = MutableLiveData<Int>()
    private val newUserIdLiveData: LiveData<Int>
        get() = _newUserIdLiveData

    private val _automobileNumberErrorLiveData = MutableLiveData<Boolean>()
    val automobileNumberErrorLiveData: LiveData<Boolean>
        get() = _automobileNumberErrorLiveData

    private val _phoneNumberErrorLiveData = MutableLiveData<Boolean>()
    val phoneNumberErrorLiveData: LiveData<Boolean>
        get() = _phoneNumberErrorLiveData

    init {
        generateNewUserId()
    }

    fun addEmployee(
        employee: Employee,
        automobile: Automobile,
        phoneNumbers: List<PhoneNumber>,
        employeeJobTitles: List<EmployeeJobTitle>
    ) {
        viewModelScope.launch {
            val response = addEmployeeUseCase.addEmployee(
                employee,
                automobile,
                phoneNumbers,
                employeeJobTitles
            )
            _addResultLiveDada.value = response
        }
    }

    fun createRequest(
        firstName: String,
        lastName: String,
        address: String,
        automobileNumber: Int,
        brand: String,
        phoneNumbers: List<String>,
        jobTitlesId: List<Int>
    ) {
        viewModelScope.launch {
            val automobiles = getAutomobilesListUseCase.getAutomobilesList()
            val phones = getPhoneNumbersListUseCase.getPhoneNumbersList()
            var isAutomobileExist = false
            var isPhoneExist = false
            for (automobile in automobiles) {
                if (automobile.automobileId == automobileNumber) {
                    isAutomobileExist = true
                    _automobileNumberErrorLiveData.value = true
                }
            }
            for (phoneNumber in phoneNumbers) {
                for (phone in phones) {
                    if (phone.number == phoneNumber) {
                        isPhoneExist = true
                        _phoneNumberErrorLiveData.value = true
                    }
                }
            }
            if (!isAutomobileExist && !isPhoneExist) {

                newUserIdLiveData.value?.let { userId ->
                    val employee = Employee(userId, firstName, lastName, address, automobileNumber)
                    val automobile = Automobile(automobileNumber, brand, employee.id)

                    val phonesList = mutableListOf<PhoneNumber>()
                    for (phoneNumber in phoneNumbers){
                        phonesList.add(PhoneNumber(phoneNumber, employee.id))
                    }

                    val employeeJobTitlesList = mutableListOf<EmployeeJobTitle>()
                    for (jobTitleId in jobTitlesId){
                        employeeJobTitlesList.add(EmployeeJobTitle(employee.id, jobTitleId))
                    }

                    addEmployee(employee, automobile, phonesList, employeeJobTitlesList)
                }
            }
        }
    }

    private fun generateNewUserId() {
        viewModelScope.launch {
            val employees = getEmployeesListUseCase.getEmployeesList()
            val randomUserId = (1..100).random()
            var isTheSame = false
            if (employees.isNotEmpty()) {
                for (employee in employees) {
                    if (employee.id == randomUserId) {
                        isTheSame = true
                    }
                }
                if (!isTheSame) {
                    _newUserIdLiveData.value = randomUserId
                } else {
                    generateNewUserId()
                }
            } else {
                _newUserIdLiveData.value = randomUserId
            }
        }
    }

}