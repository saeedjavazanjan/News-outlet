package com.saeed.zanjan.mvpproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.saeed.zanjan.mvpproject.MainActivity

public abstract class BaseFragment:Fragment() {
 var rootView:View?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView=inflater.inflate(getLayoutRes(),container,false)
        getLayoutSetUps()
        return rootView
    }

    abstract fun getLayoutRes():Int
    abstract fun getLayoutSetUps()
     fun getBaseActivity():BaseActivity{
        return MainActivity()

    }
}