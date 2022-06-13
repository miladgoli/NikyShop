package com.example.nikyshop.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tbl_user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("user_name")
    var userName: String,
    var password: String,
    @SerializedName("is_admin")
    var isAdmin: Boolean = false
) : Parcelable