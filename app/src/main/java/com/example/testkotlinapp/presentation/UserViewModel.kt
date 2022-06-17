package com.example.testkotlinapp.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.domain.usecases.GetUserUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.domain.common.Result
import com.example.domain.entities.User
import com.example.domain.entities.UserInfo


class UserViewModel(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var _users = MutableLiveData<List<UserInfo>>()
    val users = _users

    fun getUsers(page: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val usersResult = getUserUseCase.invoke(page)) {
                is Result.Success -> {
                    _users.value = usersResult.data.userInfo
                    _dataLoading.postValue(false)
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(usersResult.exception.message)
                }
            }
        }
    }


    class UserViewModelFactory(
        private val getUserUseCase: GetUserUseCase,
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(
                getUserUseCase
            ) as T
        }
    }
}