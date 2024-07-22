package com.saeed.zanjan.mvpproject.bookMarks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.data.Banners
import com.saeed.zanjan.mvpproject.data.DataSource
import com.saeed.zanjan.mvpproject.data.News
import com.saeed.zanjan.mvpproject.data.Repository
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

  class BookmarkedNewsAdapter() :
    RecyclerView.Adapter<BookmarkedNewsAdapter.BannersViewHolder>() {
     var bannerlist:List<News>?=null
      var dataSource:DataSource?=null
         private var onClickItem:OnClickItem?=null
      private var onBookMarkItem:OnBookMarkItem?=null

      fun setonBookMarkItem(item: OnBookMarkItem){
          this.onBookMarkItem=item

      }
      fun setonClickItem(it: OnClickItem){
          this.onClickItem=it
      }


     init {
         bannerlist=ArrayList<News>()
     }
constructor( bannerlist: List<News>):this(){
this.bannerlist=bannerlist

}




     fun setNewsList(list:List<News>){
         this.bannerlist=list
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_last_news,parent, false)
        dataSource=Repository(parent.context)
        return BannersViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        holder.binder(bannerlist!!.get(position),dataSource!!)
        holder.itemView.setOnClickListener {
            onClickItem!!.onItemClickListener(bannerlist!!.get(position))

          //  onBookMarkItem!!.onBookMark(bannerlist!!.get(position))
        }
        holder.bookMark.setOnClickListener {

            if (dataSource!!.getCurrentSavedPackage(bannerlist!!.get(position).id)){
                bannerlist!!.get(position).isBookMarked=false
                holder.bookMark!!.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                dataSource!!.deleteBookMark(bannerlist!!.get(position))
            }else{
                bannerlist!!.get(position).isBookMarked=true
                holder.bookMark!!.setImageResource(R.drawable.ic_baseline_bookmark_added_24)
                dataSource!!.bookMark(bannerlist!!.get(position))
            }
        }

    }

    override fun getItemCount(): Int {

        return bannerlist!!.size
    }

    class BannersViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView:ImageView=itemView.findViewById(R.id.image_Last_News_Main)
        var bookMark:ImageView=itemView.findViewById(R.id.saveItem)
        // var circleImageView:CircleImageView=itemView.findViewById(R.id.icon)
        var txtTitle:TextView=itemView.findViewById(R.id.txt_title_Main_news)
        var txtData:TextView=itemView.findViewById(R.id.txt_date_main_news)




        fun binder(news: News,dataSource: DataSource) {
            Picasso.get()
                .load(news.image)
                .error(R.drawable.ic_baseline_image_search_24)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(imageView)

            txtTitle.text=news.title
            txtData.text=news.date

            if (dataSource.getCurrentSavedPackage(news.id)){
                bookMark!!.setImageResource(R.drawable.ic_baseline_bookmark_added_24)
            }else{
                bookMark!!.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
            }







        }
    }
    public interface OnClickItem{
        fun onItemClickListener(news:News)

    }
   public   interface OnBookMarkItem{
          fun onBookMark(news:News)
      }
}