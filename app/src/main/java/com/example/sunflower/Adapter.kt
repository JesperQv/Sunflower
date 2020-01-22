package com.example.sunflower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MemeAdapter: RecyclerView.Adapter<MemeViewHolder>() {

    private var memes: ArrayList<Meme> = ArrayList()

    fun setMemes(newMemes: List<Meme>) {
        memes.clear()
        memes.addAll(newMemes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        return MemeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int = memes.size

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        holder.onBind(memes[position])
    }
}

class MemeViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    private val textView: TextView = view.findViewById(R.id.meme_name)
    private val imageView: ImageView = view.findViewById(R.id.meme_image)

    fun onBind(meme: Meme) {
        textView.text = meme.name
        Glide.with(view).load(meme.imageUrl).into(imageView)
    }
}