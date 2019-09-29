package br.com.guilherme.solution.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(val login: String) : Parcelable