package com.cavista_test.main.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Processing(
    @SerializedName("status")
    val status: String?
): Parcelable