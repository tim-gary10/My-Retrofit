package com.garyjmj.myretrofit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        val userId: Int?,
        val id: Int?,
        val title: String?,
        val body: String?,
): Parcelable
