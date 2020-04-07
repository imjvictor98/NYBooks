package com.joaovictor.nybooks.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.joaovictor.nybooks.R

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, titleResId: Int, showBackButton: Boolean = false) {
        toolbar.title = getString(titleResId)
        toolbar.setTitleTextColor(getColor(R.color.colorWhite))
        setSupportActionBar(toolbar)
        if (showBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }

}
