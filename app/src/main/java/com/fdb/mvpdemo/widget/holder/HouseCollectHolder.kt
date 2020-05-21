package com.fdb.mvpdemo.widget.holder

import android.view.View
import com.fdb.mvpdemo.R
import com.fdb.mvpdemo.bean.HouseBean
import com.fdb.mvpdemo.widget.base.BaseHolder
import kotlinx.android.synthetic.main.item_house_collect.view.*

/**
 * Desc
 * Author dontlo
 * Date   2020/4/17.
 */
class HouseCollectHolder(itemView: View?) : BaseHolder<HouseBean?>(itemView){
    override fun getLayoutId(): Int {
        return R.layout.item_house_collect
    }

    override fun onSetData() {
        rootView.tv_name!!.text = mBean!!.communityName
    }
}