package com.saeed.zanjan.mvpproject.contracts

import com.saeed.zanjan.mvpproject.base.BasePresenter
import com.saeed.zanjan.mvpproject.base.BaseView
import com.saeed.zanjan.mvpproject.data.Category

interface ContractsContractor {

    interface View:BaseView {
        fun showContractTypeList(showTypeList:List<Category>)
    }

    interface Presenter:BasePresenter<View>{

        fun getContractTypeList()
    }
}