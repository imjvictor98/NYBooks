package com.joaovictor.nybooks.data.repository

import com.joaovictor.nybooks.data.ApiService
import com.joaovictor.nybooks.data.BooksResult
import com.joaovictor.nybooks.data.model.Book
import com.joaovictor.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BooksRepository{
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        ApiService.service.listBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.bookResults) {
                                val book = result.bookDetails[0].getBookModel()
                                books.add(book)
                            }

                        }
                        booksResultCallback(BooksResult.Success(books))
                    }
                    else -> booksResultCallback(BooksResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }
        })
    }
}