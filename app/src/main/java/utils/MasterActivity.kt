package utils

import androidx.appcompat.app.AppCompatActivity

abstract class MasterActivity : AppCompatActivity() {

    protected abstract fun setupView()
    protected abstract fun setupViewModel()
    protected abstract fun populateView()
}