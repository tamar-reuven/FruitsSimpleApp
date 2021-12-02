package com.example.fruits.models

import com.example.fruits.models.Fruit
import com.google.gson.annotations.SerializedName

class FruitList{
    @SerializedName("fruits")
    lateinit var fruitsList: MutableList<Fruit>
    lateinit var err:String
}