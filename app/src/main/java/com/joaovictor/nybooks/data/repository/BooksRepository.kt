package com.joaovictor.nybooks.data.repository

import com.joaovictor.nybooks.data.BooksResult

interface BooksRepository {
    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}