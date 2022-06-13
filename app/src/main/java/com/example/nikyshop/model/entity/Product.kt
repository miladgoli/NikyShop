package com.example.nikyshop.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize



@Parcelize
@Entity(tableName = "tbl_product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var category: String,
    var price:String,
    var imageUrl:String?
) : Parcelable