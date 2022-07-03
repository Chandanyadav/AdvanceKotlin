package com.example.testkotlinapp.presentation.viewmodel

import androidx.lifecycle.*
import com.example.domain.common.Result
import com.example.domain.entities.UserDetail
import com.example.domain.usecases.GetUserDetailUseCase
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var _userDetail = MutableLiveData<UserDetail>()
    val userDetail = _userDetail

    fun getUserDetail(userId: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val usersResult = getUserDetailUseCase.invoke(userId)) {
                is Result.Success -> {
                    _userDetail.value = usersResult.data!!
                    _dataLoading.postValue(false)
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(usersResult.exception.message)
                }
            }
        }
    }


    class UserDetailViewModelFactory(
        private val getUserDetailUseCase: GetUserDetailUseCase,
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserDetailViewModel(
                getUserDetailUseCase
            ) as T
        }
    }
}