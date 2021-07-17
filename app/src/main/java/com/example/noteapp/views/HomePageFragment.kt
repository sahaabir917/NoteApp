package com.example.noteapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.noteapp.R
import com.example.noteapp.adapters.TabsPagerAdapter
import com.example.noteapp.databinding.FragmentHomePageBinding
import com.google.android.material.tabs.TabLayout


class HomePageFragment : Fragment() {

    private lateinit var binding : FragmentHomePageBinding
    private lateinit var tabAdapter: TabsPagerAdapter
    private var currenttab: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        initViewerPager()

        return binding.root
    }

    private fun initViewerPager() {
        if (binding.viewPager.adapter != null) {

        } else {
            val dashboardFragment = DashBoardFragment()
            val noteFragment = NoteFragment()
            val favouriteFragment = DashBoardFragment()
            val scheduleFragment = NoteFragment()
            val settingsFragment = DashBoardFragment()

//            binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
//            binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//            childFragmentManager.executePendingTransactions()

            tabAdapter = TabsPagerAdapter(childFragmentManager)
            tabAdapter
                .addFragment(
                    scheduleFragment, "My Friends"
                )
                .addFragment(
                    noteFragment, "Add Friend"
                )
                .addFragment(
                    dashboardFragment, "Requests"
                )
                .addFragment(
                    favouriteFragment, "Sent Requests"
                )
                .addFragment(
                    settingsFragment, "Sent Requests"
                )


            binding.viewPager.adapter = tabAdapter
//            binding.tabLayout.setupWithViewPager(binding.viewPager)
        }
//        if (isFromNotification) {
//            currenttab = 2
//        } else {
//            currenttab = 0
//        }
        binding.viewPager.adapter?.notifyDataSetChanged()

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currenttab = position
            }

        })
        binding.viewPager.currentItem = currenttab
        binding.viewPager.offscreenPageLimit = 1
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomePageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}