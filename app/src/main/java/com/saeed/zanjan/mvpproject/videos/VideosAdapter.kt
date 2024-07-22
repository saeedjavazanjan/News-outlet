package com.saeed.zanjan.mvpproject.videos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.jzvd.JzvdStd
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.data.Videos
import com.squareup.picasso.Picasso

class VideosAdapter(var list: List<Videos>):RecyclerView.Adapter<VideosAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.videos_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder(list.get(position))

    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var player:JzvdStd=itemView.findViewById(R.id.jz)

        fun binder(video:Videos){
            player.setUp(video.videolink,"")
            player.setScreen(JzvdStd.SCREEN_NORMAL)
            Picasso.get()
                .load(video.image)
                .error(R.drawable.ic_baseline_ondemand_video_24)
                .placeholder(R.drawable.ic_baseline_ondemand_video_24)
                .into(player.posterImageView)
            player.fullscreenButton.visibility=View.GONE
            player.backButton.visibility=View.GONE
            player.batteryLevel.visibility=View.GONE
            player.tinyBackImageView.visibility=View.GONE
            player.currentTimeTextView.visibility=View.VISIBLE


        }
    }

}