package com.example.testkotlinapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.domain.usecases.GetUserUseCase
import kotlinx.coroutines.launch
import com.example.domain.common.Result
import com.example.domain.entities.UserInfo
import com.example.domain.usecases.GetLocalUserUseCase
import com.example.domain.usecases.SaveUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


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

    fun getUsers(page: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val usersResult = getUserUseCase.invoke(page)) {
                is Result.Success -> {
                    val userInfo = usersResult.data.userInfo
                    _users.value = userInfo

                    saveUserUseCase.invoke(userInfo)
                    _dataLoading.postValue(false)
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(usersResult.exception.message)
                }
            }
        }

    }

    //Save data in Room database
    fun getLocalUserdata() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val getUserInfoLocal = getLocalUserUseCase.invoke()
            getUserInfoLocal.collect {
                _users.postValue(it)
                _dataLoading.postValue(false)
            }
        }
    }

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