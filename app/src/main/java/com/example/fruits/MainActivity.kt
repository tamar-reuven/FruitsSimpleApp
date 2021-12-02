package com.example.fruits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruits.interfaces.OnFruitClickListener
import com.example.fruits.interfaces.RetrofitService
import com.example.fruits.adapters.FruitsRecyclerAdapter
import com.example.fruits.models.Fruit
import com.example.fruits.models.FruitList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val BASE_URL="https://dev-api.com/fruitsBT/"

class MainActivity : AppCompatActivity(), OnFruitClickListener {

    private var layoutManager:RecyclerView.LayoutManager?=null
    private var adapter:RecyclerView.Adapter<FruitsRecyclerAdapter.ViewHolder>?=null
    var fruits= mutableListOf<Fruit>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)
        fruitsRecyclerView.layoutManager = layoutManager

        fetchDataFromApi()

    }

    private fun fetchDataFromApi(){
        try{val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            val api = retrofit.create(RetrofitService:: class.java)
            //get the fruits, put it in the right model and deal with the response
            api.getFruitsList().enqueue(object : Callback<FruitList>{

                override fun onResponse(call: Call<FruitList>, response: Response<FruitList>) {
                    fruits = response.body()!!.fruitsList
                    setRecyclerView(fruits)
                }

                override fun onFailure(call: Call<FruitList>, t: Throwable) {
                    Toast.makeText(applicationContext,"Sorry, could not fetch fruits "+ t.message, Toast.LENGTH_LONG).show()
                }

            })}catch (e : Exception){
            Toast.makeText(applicationContext,"Error!!!", Toast.LENGTH_LONG).show()

        }
    }

    private fun setRecyclerView(fruits: MutableList<Fruit>) {
        adapter= FruitsRecyclerAdapter(baseContext,fruits,this@MainActivity)
        (adapter as FruitsRecyclerAdapter).notifyDataSetChanged()
        fruitsRecyclerView.adapter = adapter
    }

    override fun onFruitItemClick(position: Int) {
        val intent = Intent(this, Details::class.java)
        intent.putExtra("name", fruits?.get(position)?.name)
        intent.putExtra("description", fruits?.get(position)?.description)
        intent.putExtra("price", fruits[position].price.toString())
        intent.putExtra("image", fruits?.get(position)?.urlToImage)
        startActivity(intent)
    }
}
