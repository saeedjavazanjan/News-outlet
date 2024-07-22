package com.saeed.zanjan.mvpproject.contracts

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.data.Category
import com.saeed.zanjan.mvpproject.data.Journal
import com.saeed.zanjan.mvpproject.journals.JournalsAdapter
import com.squareup.picasso.Picasso

class ContractsTypeAdapter(typeList:List<Category>):RecyclerView.Adapter<ContractsTypeAdapter.ViewHolder>() {

    var typeList:List<Category>?=null
    init {
        this.typeList=typeList
    }

    private var onClickItem: ContractsTypeAdapter.OnClickItem?=null

    fun setonClickItem(it: ContractsTypeAdapter.OnClickItem){
        this.onClickItem=it
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View=LayoutInflater.from(parent.context).inflate(R.layout.contract_type_item,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder(typeList!![position])
        holder.itemView.setOnClickListener {
            onClickItem!!.onItemClickListener(typeList!!.get(position))

        }
    }

    override fun getItemCount(): Int {

        return typeList!!.size
    }
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        var itemImage:ImageView=view.findViewById(R.id.imgItem)
        var title:TextView=view.findViewById(R.id.txtTitle)
        fun binder(contractsType: Category){

            Picasso.get().load(contractsType.image)
                .error(R.drawable.ic_baseline_image_search_24)
                .placeholder(R.drawable.ic_baseline_image_search_24)
                .into(itemImage)

            title.setText(contractsType.name)



        }
    }
    public interface OnClickItem{
        fun onItemClickListener(category: Category)

    }
}