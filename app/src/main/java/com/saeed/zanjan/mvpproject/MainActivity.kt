package com.saeed.zanjan.mvpproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textview.MaterialTextView
import com.saeed.zanjan.mvpproject.base.BaseActivity
import com.saeed.zanjan.mvpproject.bookMarks.BookMarksFragment
import com.saeed.zanjan.mvpproject.announcement.AnnouncementFragment
import com.saeed.zanjan.mvpproject.contracts.Contracts
import com.saeed.zanjan.mvpproject.home.HomeFragment
import com.saeed.zanjan.mvpproject.journals.Journals
import com.saeed.zanjan.mvpproject.search.SearchActivity
import com.saeed.zanjan.mvpproject.survey.Survey
import com.saeed.zanjan.mvpproject.videos.VideosFragment
import com.saeed.zanjan.mvpproject.welfare.Welfare
import kotlin.system.exitProcess
import android.content.ActivityNotFoundException
import android.net.Uri


class MainActivity : BaseActivity() {
    var bottomNavigationView: BottomNavigationView? = null
    var fragment:Fragment=HomeFragment()
    var backpressed=false
    var imgSearch: ImageView?=null
    var txtTitle:MaterialTextView?=null
    var menuImage:ImageView?=null
    var drawerLayout:DrawerLayout?=null
    var navigationView:NavigationView?=null

    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HomeFragment())
        getLayoutSetUp()



        bottomNavigationView!!.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> {
                    txtTitle!!.text="اخبار"
                    replaceFragment(HomeFragment())
                }
                R.id.announcement -> {
                    txtTitle!!.text="اطلاعیه"
                    replaceFragment(AnnouncementFragment())}
                R.id.favorits->{
                    txtTitle!!.text="علاقه مندی ها"
                    replaceFragment(BookMarksFragment())}
                R.id.videos->{
                    txtTitle!!.text="ویدئو ها"
                    replaceFragment(VideosFragment())}

                else ->  true
            }
        }

        navigationView?.setNavigationItemSelectedListener(object :NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.search->{
                        var intent: Intent = Intent(this@MainActivity, SearchActivity::class.java)
                        startActivity(intent)
                        drawerLayout?.close()
                    }
                    R.id.Welfare->{

                        var intent:Intent= Intent(this@MainActivity,Welfare::class.java)
                        startActivity(intent)
                        drawerLayout?.close()
                    }
                    R.id.Survey->{

                        var intent:Intent= Intent(this@MainActivity,Survey::class.java)
                        startActivity(intent)
                        drawerLayout?.close()
                    }
                    R.id.journals->{

                        var intent:Intent= Intent(this@MainActivity,Journals::class.java)
                        startActivity(intent)
                        drawerLayout?.close()
                    }
                    R.id.contracts->{

                        var intent:Intent= Intent(this@MainActivity,Contracts::class.java)
                        startActivity(intent)
                        drawerLayout?.close()
                    }
                    R.id.instagram->{

                        val uri: Uri = Uri.parse("https://www.instagram.com/irantransfogroup/?hl=en")
                        val likeIng = Intent(Intent.ACTION_VIEW, uri)

                        likeIng.setPackage("com.instagram.android")

                        try {
                            startActivity(likeIng)
                        } catch (e: ActivityNotFoundException) {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.instagram.com/irantransfogroup/?hl=en")
                                )
                            )
                        }
                        drawerLayout?.close()
                    }
                    R.id.telegram->{

                        val uri: Uri = Uri.parse("https://t.me/Itransfo")
                        val likeIng = Intent(Intent.ACTION_VIEW, uri)

                        likeIng.setPackage("com.telegram")

                        try {
                            startActivity(likeIng)
                        } catch (e: ActivityNotFoundException) {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://t.me/Itransfo")
                                )
                            )
                        }
                        drawerLayout?.close()
                    }
                    R.id.aparat->{

                        val uri: Uri = Uri.parse("https://www.aparat.com/irantransfo")
                        val likeIng = Intent(Intent.ACTION_VIEW, uri)
                            startActivity(likeIng)


                        drawerLayout?.close()
                    }
                    R.id.website->{

                        val uri: Uri = Uri.parse("http://www.iran-transfo.com/")
                        val likeIng = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(likeIng)


                        drawerLayout?.close()
                    }
                    R.id.share->{

                        val intent = Intent(Intent.ACTION_SEND)
                        //TODO change link
                        intent.putExtra(Intent.EXTRA_TEXT, "http://www.iran-transfo.com/")
                        intent.type = "text/plain"
                        startActivity(Intent.createChooser(intent, "Share Via"))


                        drawerLayout?.close()
                    }
                    else->return true
                }
                return true
            }


        })

      /*  var tab:Ima=bottomNavigationView?.menu!!.findItem(R.id.announcement) as Button

           //var tabLayout: View = bottomNavigationView?.menu!!.findItem(R.id.announcement) as View
        val badge = BadgeDrawable.create(this)
        badge.isVisible = true
        BadgeUtils.attachBadgeDrawable(badge,tab );

        badge.number = 100
        badge.maxCharacterCount = 3
        badge.backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.blue)
*/
    }

    fun replaceFragment(fragment:Fragment):Boolean{
        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain,fragment)
        fragmentTransaction.isAddToBackStackAllowed
        fragmentTransaction.commit()

        return true
    }



    override fun getLayoutSetUp() {
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        txtTitle=findViewById(R.id.pageTitle)
        imgSearch=findViewById(R.id.imgSearch)
        menuImage=findViewById(R.id.imgMenu)
        drawerLayout=findViewById(R.id.drawerLayout)
        navigationView=findViewById(R.id.navigation)

        menuImage?.setOnClickListener {
            drawerLayout?.openDrawer(GravityCompat.START)
        }
        imgSearch?.setOnClickListener {
            var intent: Intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        if(bottomNavigationView!!.selectedItemId==R.id.home) {
            if(!backpressed){
                Toast.makeText(this,"برای خروج دو بار کلیک کنید",Toast.LENGTH_SHORT).show()
                backpressed=true
                var handler=Handler()
                handler.postDelayed(Runnable { kotlin.run {
                    backpressed=false
                } },2000)
            }else{

                exitProcess(0)
            }


        }else{
            bottomNavigationView!!.selectedItemId=R.id.home
        }
    }
}