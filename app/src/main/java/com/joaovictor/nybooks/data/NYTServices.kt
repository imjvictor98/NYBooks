package com.joaovictor.nybooks.data


import com.joaovictor.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {
    @GET("lists.json")
    fun listBooks(
        @Query("api-key") apiKey: String = "7VnrIAWk3tb5GSdfGdKGTfH5drsh4YnJ",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}