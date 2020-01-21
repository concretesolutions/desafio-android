package br.com.rmso.popularrepositories.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner (
    var login: String,
    var avatar_url: String
): Parcelable