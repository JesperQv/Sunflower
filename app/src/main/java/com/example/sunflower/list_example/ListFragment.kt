package com.example.sunflower.list_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sunflower.R

class ListFragment : Fragment() {

    private lateinit var list: RecyclerView
    private lateinit var adapter: MemeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)

        list = root.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(context)
        adapter = MemeAdapter()
        list.adapter = adapter
        val memes = ArrayList<Meme>()
        for (i in 0..50) {
            memes.addAll(generateMemes())
        }
        adapter.setMemes(memes)

        return root
    }

    private fun generateMemes(): ArrayList<Meme> {
        val list =  listOf(
            Meme(
                "If u have knife",
                "https://i.imgur.com/0dKlW4u.png"
            ),
            Meme(
                "sees u walking",
                "https://i.imgur.com/DqoZN4p.png"
            ),
            Meme(
                "baby off a cliff",
                "https://i.imgur.com/iU2XV5G.png"
            ),
            Meme(
                "sees person",
                "https://i.imgur.com/u9cQz8w.png"
            ),
            Meme(
                "all ppl should b killed",
                "https://i.imgur.com/VJ4jFg2.png"
            ),
            Meme(
                "use car ",
                "https://i.imgur.com/tiz0eS9.png"
            ),
            Meme(
                "sex no",
                "https://i.imgur.com/Mv7SDBM.png"
            ),
            Meme(
                "what if i told u",
                "https://i.imgur.com/8x0yWHs.png"
            ),
            Meme(
                "bernie",
                "https://i.imgur.com/8FbJjCr.jpg"
            ),
            Meme(
                "spider",
                "https://i.imgur.com/yhx4xTH.jpg"
            )
        )
        val arrayList = ArrayList<Meme>()
        arrayList.addAll(list)
        return arrayList
    }
}