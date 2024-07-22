package com.saeed.zanjan.mvpproject.journals

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saeed.zanjan.mvpproject.R
import com.saeed.zanjan.mvpproject.base.BaseActivity
import com.saeed.zanjan.mvpproject.data.Journal
import com.saeed.zanjan.mvpproject.data.Repository

class Journals :BaseActivity(),JournalsContractor.View {

    var presenter: JournalsContractor.Presenter? = null
    var journalsRecycler: RecyclerView? = null
    var backImage: ImageView? = null
    override fun getLayoutSetUp() {
        journalsRecycler = findViewById(R.id.journalsRecycler)
        backImage = findViewById(R.id.imgBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journals)
        presenter = JournalsPresenter(Repository(this))
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


    override fun showJournals(showJournalsList: List<Journal>) {
        var journalsAdapter: JournalsAdapter = JournalsAdapter(showJournalsList)
        journalsRecycler!!.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        journalsRecycler!!.adapter = journalsAdapter
        journalsAdapter.setonClickItem(
            object : JournalsAdapter.OnClickItem {
                override fun onItemClickListener(journal: Journal) {

                    if (journal.link!=null){
                        val intent = Intent()
                        intent.setDataAndType(Uri.parse(journal.link), "application/pdf")
                        startActivity(intent)

                    }



                }


            })
    }


    override fun showProgressBar(showOrNot: Boolean) {
        var  progressBar:LinearLayout = findViewById(R.id.linProgress)

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