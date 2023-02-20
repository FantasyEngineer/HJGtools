package com.hjg.hjgtools.activity.widget

import com.hjg.base.base.HJGBaseActivity
import com.hjg.hjgtools.R
import kotlinx.android.synthetic.main.activity_text_view.*

class TextViewActivity : HJGBaseActivity() {
    override fun getContentID(): Int {
        return R.layout.activity_text_view
    }

    override fun initViewAction() {
        imageTextview.setImageInView(ImageTextView.LEFT, R.drawable.fab_add)
    }
}