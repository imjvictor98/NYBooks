package com.joaovictor.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joaovictor.nybooks.R
import com.joaovictor.nybooks.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_books_detail.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksDetailActivity : BaseActivity() {

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, title: String, description: String) : Intent {
            return Intent(context, BooksDetailActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_detail)

        setupToolbar(toolbarMain, R.string.book_details_header, true)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        book_details_title.text = title
        book_details_description.text = description
    }
}
