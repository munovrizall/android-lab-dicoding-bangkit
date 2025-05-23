package com.dicoding.picodiploma.productdetail.api

data class ProductModel(
    val name: String,
    val price: String,
    val store: String,
    val date: String,
    val rating: String,
    val countRating: String,
    val size: String,
    val color: String,
    val desc: String,
    val image: Int
)
