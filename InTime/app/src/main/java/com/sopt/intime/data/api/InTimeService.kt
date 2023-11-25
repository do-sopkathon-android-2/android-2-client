package com.sopt.intime.data.api

import com.sopt.intime.data.remote.request.TodoListRequest
import com.sopt.intime.data.remote.request.UserTimeRequest
import com.sopt.intime.data.remote.response.TodoListAllResponse
import com.sopt.intime.data.remote.response.TodoListFilterResponse
import com.sopt.intime.data.remote.response.UserTimeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface InTimeService {
    @POST("/api/v1/user-time/{userId}")
    fun postUserTime(
        @Path("userId") userId: Long,
        @Body userTimeRequest: UserTimeRequest
    ): Call<UserTimeResponse>

    @GET("/api/v1/user-time/{userId}")
    fun getUserTime(
        @Path("userId") userId: Long
    ): Call<UserTimeResponse>

    @POST("/api/v1/todo-list/{userId}")
    fun postTodoList(
        @Path("userId") userId: Long,
        @Body todoListRequest: TodoListRequest
    ): Call<UserTimeResponse>

    @GET("/api/v1/todo-list/{userId}/all")
    fun getTodoList(
        @Path("userId") userId: Long
    ): Call<TodoListAllResponse>

    @GET("/api/v1/todo-list/{userId}")
    fun getTodoListFilter(
        @Path("userId") userId: Long,
        @Query("timeTag") timeTag: String
    ): Call<TodoListFilterResponse>

    @DELETE("/api/v1/todo-list/{todoId}/all")
    fun deleteTodoList(
        @Path("todoId") todoId: Long,
    ): Call<UserTimeResponse>
}

