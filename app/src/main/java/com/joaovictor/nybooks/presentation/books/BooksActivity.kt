package com.joaovictor.nybooks.presentation.books

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joaovictor.nybooks.R
import com.joaovictor.nybooks.data.repository.BooksApiDataSource
import com.joaovictor.nybooks.presentation.base.BaseActivity
import com.joaovictor.nybooks.presentation.details.BooksDetailActivity
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.books_title)

        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(BooksApiDataSource())
            .create(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(recyclerBooks) {
                    layoutManager = LinearLayoutManager(
                        this@BooksActivity,
                        RecyclerView.VERTICAL,
                        false)

                    setHasFixedSize(true)

                    adapter = BooksAdapter(books) {book ->
                        val intent = BooksDetailActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description
                        )
                        startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let {viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first

                viewFlipper.second?.let {
                    errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getBooks()

    }
}
