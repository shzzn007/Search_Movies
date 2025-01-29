package com.example.movies

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.ui.theme.MoviesTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>

//    private val TextView movieName;

    private val BASE_URL = "https://www.omdbapi.com/"
    private val TAG: String = "CHECK_RESPONSE"


    // Retrofit instance
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyApi::class.java)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = LinearLayoutManager(this)





        val searchBar = findViewById<AutoCompleteTextView>(R.id.search_bar)
        searchBar.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchBar.text.toString()
                if (query.isNotEmpty()) {
                    getMovies(query)
                }
            }
            true

        }

    }



    private fun getMovies(title: String,){
        api.getMovies(
            title=title,
            key = "320f6ab2"
        ).enqueue(object : Callback<MovieList>{
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.isSuccessful){
                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
                        myAdapter = MyAdapter(response.body()!!.movies!!)
                        layoutManager = manager
                        adapter = myAdapter


                    }

                }

            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }




        })

    }
}


