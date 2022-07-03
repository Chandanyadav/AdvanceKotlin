package com.example.testkotlinapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.domain.usecases.GetUserUseCase
import kotlinx.coroutines.launch
import com.example.domain.common.Result
import com.example.domain.entities.UserInfo
import com.example.domain.usecases.GetLocalUserUseCase
import com.example.domain.usecases.SaveUserUseCase


class UserViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val getLocalUserUseCase: GetLocalUserUseCase,
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var _users = MutableLiveData<List<UserInfo>>()
    val users = _users

    private var userInfoLocal = UserInfo("aaa", "vvvv", "sdfsdfs", "aaa@aa.com", "adasdsad")


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

  //  viewModelScope.launch {
//                        val saveUser = saveUserUseCase.invoke(userInfoLocal)
//                    }

    class UserViewModelFactory(
        private val getUserUseCase: GetUserUseCase,
        private val saveUserUseCase: SaveUserUseCase,
        private val getLocalUserUseCase: GetLocalUserUseCase,
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(
                getUserUseCase,
                saveUserUseCase,
                getLocalUserUseCase
            ) as T
        }
    }
}