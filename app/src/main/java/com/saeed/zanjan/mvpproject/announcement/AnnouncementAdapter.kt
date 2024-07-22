package com.saeed.zanjan.mvpproject.announcement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.data.Announce
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.home.NewsAdapter
import com.squareup.picasso.Picasso
import java.util.ArrayList


class AnnouncementAdapter(categoryList: List<Announce>) :
    RecyclerView.Adapter<AnnouncementAdapter.CategoryViewHolder>() {
    private var onClickItem: AnnouncementAdapter.OnClickItem?=null
    fun setonClickItem(it: AnnouncementAdapter.OnClickItem){
        this.onClickItem=it
    }
    private var categoryList: List<Announce> = ArrayList<Announce>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.announcement_layout,parent, false)
        return CategoryViewHolder(view)
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(categoryList[position])
        holder.itemView.setOnClickListener {
            onClickItem!!.onItemClickListener(categoryList!!.get(position))

        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView=itemView.findViewById(R.id.image_category)
        private val textView: TextView=itemView.findViewById(R.id.txt_category)
        fun bindCategory(category: Announce) {
            textView.text=category.title
            Picasso.get().load(category.image).into(imageView)

            itemView.setOnClickListener {
             /* *//*  val intent = Intent(itemView.context, ListCategory_Activity::class.java)
                intent.putExtra("grouping", category.getNumber().toString() + "")*//*
                itemView.context.startActivity(intent)*/
            }
        }


    }

    init {
        this.categoryList = categoryList
    }

    public interface OnClickItem{
        fun onItemClickListener(announce: Announce)

    }
}
