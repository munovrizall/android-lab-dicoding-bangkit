package com.dicoding.picodiploma.productdetail.api

import android.content.Context
import com.dicoding.picodiploma.productdetail.R

class RemoteDataSource(private val context: Context) {

    fun getDetailProduct(): ProductModel {
        return ProductModel(
            name = context.getString(R.string.name),
            store = context.getString(R.string.store),
            size = context.getString(R.string.size),
            color = context.getString(R.string.color),
            desc = context.getString(R.string.description),
            image = R.drawable.shoes,
            date = context.getString(R.string.date),
            rating = context.getString(R.string.rating),
            price = context.getString(R.string.price),
            countRating = context.getString(R.string.countRating)
        )
    }
}