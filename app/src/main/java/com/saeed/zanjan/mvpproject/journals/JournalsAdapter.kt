package com.saeed.zanjan.mvpproject.journals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.data.Journal
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.home.NewsAdapter
import com.squareup.picasso.Picasso

class JournalsAdapter(list: List<Journal>):RecyclerView.Adapter<JournalsAdapter.ViewHolder> (){

    var list:List<Journal>?=null
    private var onClickItem: JournalsAdapter.OnClickItem?=null

init {
    this.list=list
}
    fun setonClickItem(it: JournalsAdapter.OnClickItem){
        this.onClickItem=it
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.journals_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder(list!!.get(position))
        holder.itemView.setOnClickListener {
            onClickItem!!.onItemClickListener(list!!.get(position))

        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var imageView: ImageView =itemView.findViewById(R.id.image_journal)
        var txtTitle: TextView =itemView.findViewById(R.id.txt_title)
     //   var txtdescription: TextView =itemView.findViewById(R.id.txt_description)
        fun binder(journals: Journal){
            Picasso.get()
                .load(journals.image)
                .error(R.drawable.ic_baseline_image_search_24)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(imageView)

            txtTitle.text=journals.title
            //txtdescription.text=journals.description


        }


    }
    public interface OnClickItem{
        fun onItemClickListener(journal: Journal)

    }
}