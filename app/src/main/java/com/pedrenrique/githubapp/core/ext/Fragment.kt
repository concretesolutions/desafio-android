package com.pedrenrique.githubapp.core.ext

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

val Fragment.supportActivity: AppCompatActivity?
    get() = activity as? AppCompatActivity

val Fragment.supportActionBar: ActionBar?
    get() = supportActivity?.supportActionBar