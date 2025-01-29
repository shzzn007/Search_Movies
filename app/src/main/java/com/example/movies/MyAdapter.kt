package com.example.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val data: List<Movie>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            val title_textview = view.findViewById<TextView>(R.id.movie_name_1)
            val poster_imageview = view.findViewById<ImageView>(R.id.movie_image_1)
            val movie_type = view.findViewById<TextView>(R.id.movie_description_1)

//            val card_view = view.findViewById<CardView>(R.id.myCardView)
//            card_view.setOnClickListener {
//                if (title_textview.text.isNullOrEmpty() or movie_type.text.isNullOrBlank()) {
//                    movie_type.error = "Field can't be null"
//                } else {
//                    // Get movie data
//                }
//            }


            title_textview.text = movie.Title
            movie_type.text = movie.Type

            Glide.with(view.context).load(movie.Poster).centerCrop().into(poster_imageview)

        }
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movies_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

}

