package com.sopt.intime.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.intime.data.api.RetrofitServicePool
import com.sopt.intime.data.remote.request.TodoListRequest
import com.sopt.intime.data.remote.response.Data
import com.sopt.intime.data.remote.response.DataContent
import com.sopt.intime.data.remote.response.TodoListAllResponse
import com.sopt.intime.data.remote.response.UserTimeDataResponse
import com.sopt.intime.data.remote.response.UserTimeResponse
import retrofit2.Call
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _userTimeData = MutableLiveData<UserTimeDataResponse>()
    val userTimeData: LiveData<UserTimeDataResponse> = _userTimeData

    private val _todoListAll = MutableLiveData<Data>()
    val todoListAll: LiveData<Data> = _todoListAll

    private val _isExtraSuccessTodoList = MutableLiveData<Boolean>()
    val isExtraSuccessTodoList: LiveData<Boolean> = _isExtraSuccessTodoList

    val morningList = MutableLiveData<MutableList<DataContent>>()
    val lunchList = MutableLiveData<MutableList<DataContent>>()
    val dinnerList = MutableLiveData<MutableList<DataContent>>()

    fun getUserTime() {
        RetrofitServicePool.todoService.getUserTime(1).enqueue(
            object : retrofit2.Callback<UserTimeResponse> {
                override fun onResponse(
                    call: Call<UserTimeResponse>,
                    response: Response<UserTimeResponse>
                ) {
                    if (response.isSuccessful) {
                        _userTimeData.value = response.body()?.data!!
                    } else {

                    }
                }

                override fun onFailure(call: Call<UserTimeResponse>, t: Throwable) {
                }
            }
        )
    }

    fun getTodoListAll() {
        RetrofitServicePool.todoService.getTodoList(1).enqueue(
            object : retrofit2.Callback<TodoListAllResponse> {
                override fun onResponse(
                    call: Call<TodoListAllResponse>,
                    response: Response<TodoListAllResponse>
                ) {
                    if (response.isSuccessful) {
                        _todoListAll.value = response.body()?.data!!
                    }
                }

                override fun onFailure(call: Call<TodoListAllResponse>, t: Throwable) {
                }
            }
        )
    }

    fun postTodoList(content: String, timeTag: String) {
        RetrofitServicePool.todoService.postTodoList(1, TodoListRequest(content, timeTag)).enqueue(
            object : retrofit2.Callback<UserTimeResponse> {
                override fun onResponse(
                    call: Call<UserTimeResponse>,
                    response: Response<UserTimeResponse>
                ) {
                    _isExtraSuccessTodoList.value = response.isSuccessful
                }

                override fun onFailure(call: Call<UserTimeResponse>, t: Throwable) {
                }
            }
        )
    }
}
