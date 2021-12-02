package com.example.fruits.models

import com.google.gson.annotations.SerializedName

class Fruit {
 var name: String? = null
 @SerializedName ("image")
 var urlToImage: String? = null
 var description: String? = null
 var price: Int? = null
}