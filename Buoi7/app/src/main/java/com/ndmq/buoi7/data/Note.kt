package com.ndmq.buoi7.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity(tableName = "note")
data class Note(
    @PrimaryKey
    var id: Int = UUID.randomUUID().hashCode(),
    val title: String,
    val content: String,
    var favorite: Boolean = false
) : Parcelable
