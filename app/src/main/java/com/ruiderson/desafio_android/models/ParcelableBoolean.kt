package com.ruiderson.desafio_android.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelableBoolean (
    val value: Boolean
) : Parcelable