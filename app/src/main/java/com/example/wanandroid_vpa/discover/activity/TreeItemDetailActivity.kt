package com.example.wanandroid_vpa.discover.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.discover.fragment.TreeItemDetailFragment

/**
 * Created by geegumb on 2020/12/8
 *
 */
class TreeItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activcity_tree_item_detail)

        val fragment = TreeItemDetailFragment.newInstance()
        val bundle = Bundle()
        bundle.putInt(FLAG_CID, intent.getIntExtra(FLAG_CID, -1))
        bundle.putString(FLAG_TITLE, intent.getStringExtra(FLAG_TITLE))
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fl_container, fragment).commit()
    }

    companion object {
        const val FLAG_CID = "flag_cid"
        const val FLAG_TITLE = "flag_title"
    }
}