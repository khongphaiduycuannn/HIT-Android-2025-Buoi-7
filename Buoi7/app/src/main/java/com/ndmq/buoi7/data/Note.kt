package com.ndmq.buoi7.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Note(
    var id: Int = UUID.randomUUID().hashCode(),
    val title: String,
    val content: String,
    var favorite: Boolean = false
) : Parcelable
