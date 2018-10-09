package br.com.repository.model

import android.databinding.BaseObservable
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Owner(@SerializedName("login")
            var name: String,
            @SerializedName("avatar_url")
            var urlImage: String) : BaseObservable(), Parcelable {


}