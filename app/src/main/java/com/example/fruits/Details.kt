package com.example.fruits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fruit_details.*

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fruit_details)

        val fruitImage = imageDetail
        val fruitName = nameDetail
        val fruitDescription = descriptionDetail
        val fruitPrice = priceDetail

        fruitName.text = intent.getStringExtra("name").toString().uppercase()
        fruitDescription.text = intent.getStringExtra("description").toString()
        fruitPrice.text = intent.getStringExtra("price").toString() +"$"
        Glide.with(this).load(intent.getStringExtra("image").toString()).into(fruitImage);

    }
}