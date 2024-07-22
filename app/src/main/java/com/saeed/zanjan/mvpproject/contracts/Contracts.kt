package com.saeed.zanjan.mvpproject.contracts

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseActivity
import com.saeed.zanjan.mvpproject.data.Category
import com.saeed.zanjan.mvpproject.data.Journal
import com.saeed.zanjan.mvpproject.data.Repository
import com.saeed.zanjan.mvpproject.journals.JournalsAdapter
import com.saeed.zanjan.mvpproject.journals.JournalsContractor

class Contracts : BaseActivity(),ContractsContractor.View {
    var presenter:ContractsContractor.Presenter?=null
    var typeRecycler: RecyclerView?=null
    var backImage: ImageView?=null
    override fun getLayoutSetUp() {
        typeRecycler=findViewById(R.id.contractsRecycler)
        backImage=findViewById(R.id.imgBack)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contracts)
        presenter=ContractsPresenter(Repository(this))
        getLayoutSetUp()
        backImage!!.setOnClickListener {
            finish()
        }

    }
    override fun onStart() {
        super.onStart()
        presenter!!.onAttachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter!!.onDetachView()
    }
    override fun showContractTypeList(showTypeList: List<Category>) {

        var typeAdapter:ContractsTypeAdapter= ContractsTypeAdapter(showTypeList)
        var layoutManager:StaggeredGridLayoutManager=
            StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        typeRecycler!!.layoutManager=layoutManager
        typeRecycler!!.adapter=typeAdapter
        typeAdapter.setonClickItem(
            object : ContractsTypeAdapter.OnClickItem {
                override fun onItemClickListener(category: Category) {

                    if (category.number!=null){
                        val intent = Intent()
                        intent.setDataAndType(Uri.parse(category.number), "application/pdf")
                        startActivity(intent)

                    }



                }


            })


    }

    override fun showProgressBar(showOrNot: Boolean) {
        var  progressBar: LinearLayout = findViewById(R.id.linProgress)

        if (showOrNot)
            progressBar!!.visibility = View.VISIBLE
        else
            progressBar!!.visibility = View.GONE
    }

    override fun context(): Context {
return this

    }

    override fun showError(text: String) {
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    }
}