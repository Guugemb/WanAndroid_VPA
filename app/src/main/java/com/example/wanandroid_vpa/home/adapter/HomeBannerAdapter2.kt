package com.example.wanandroid_vpa.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import com.example.wanandroid_vpa.R
import com.example.wanandroid_vpa.home.adapter.HomeBannerAdapter2.BannerFragment.Companion.BANNER_BEAN
import com.example.wanandroid_vpa.home.bean.BannerBeanWrapper.BannerBean

/**
 * Created by geegumb on 2020/12/2
 *
 */
class HomeBannerAdapter2(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    private val mDataList = arrayListOf<BannerBean>()

    override fun getCount(): Int {
        return mDataList.size
    }

    override fun getItem(position: Int): Fragment {
        val fragment = BannerFragment.newInstance()
        val bundle = Bundle()
        bundle.putSerializable(BANNER_BEAN, mDataList[position])
        fragment.arguments = bundle
        return fragment
    }

    fun addData(beans: List<BannerBean>?) {
        beans?.let {
            mDataList.addAll(beans)
            notifyDataSetChanged()
        }
    }

    internal class BannerFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.item_banner_home, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val imagePath = (arguments?.get(BANNER_BEAN) as BannerBean).imagePath
            Glide.with(view).load(imagePath).into(view as ImageView)
        }

        companion object {
            fun newInstance() = BannerFragment()
            const val BANNER_BEAN = "banner_bean"
        }
    }
}