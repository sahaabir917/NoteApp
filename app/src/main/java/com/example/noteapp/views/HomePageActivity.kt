package com.example.noteapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.noteapp.R
import com.example.noteapp.adapters.TabsPagerAdapter
import com.example.noteapp.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity(), DashBoardFragment.DashBoardListeners {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var tabAdapter: TabsPagerAdapter
    private var currenttab: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)
        viewRelatedWork()
    }

    private fun viewRelatedWork() {
        initViewerPager()
        initClickListener()
    }

    private fun initClickListener() {
        binding.imageView9.setOnClickListener {
            binding.viewPager.currentItem = 0
        }

        binding.imageView10.setOnClickListener {
            binding.viewPager.currentItem = 1
        }

        binding.imageView11.setOnClickListener {
            binding.viewPager.currentItem = 2
        }

        binding.imageView12.setOnClickListener {
            binding.viewPager.currentItem = 3
        }

        binding.imageView13.setOnClickListener {
            binding.viewPager.currentItem = 4
        }

    }

    private fun initViewerPager() {
        if (binding.viewPager.adapter != null) {

        } else {
            val dashboardFragment = DashBoardFragment()
            val noteFragment = NoteFragment()
            val favouriteFragment = FavouriteNoteFragment()
            val scheduleFragment = ScheduleFragment()
            val settingsFragment = SettingsFragment()

//            binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
//            binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//            childFragmentManager.executePendingTransactions()

            tabAdapter = TabsPagerAdapter(supportFragmentManager)
            tabAdapter
                .addFragment(
                    scheduleFragment, "schedulefragment"
                )
                .addFragment(
                    noteFragment, "Note Page"
                )
                .addFragment(
                    dashboardFragment, "DashBoard"
                )
                .addFragment(
                    favouriteFragment, "Favourite Page"
                )
                .addFragment(
                    settingsFragment, "Settings Page"
                )


            binding.viewPager.adapter = tabAdapter
        }

        binding.viewPager.adapter?.notifyDataSetChanged()
        currenttab = 2


        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                if (position == 0) {
                    binding.imageView9.setImageResource(R.drawable.ic_group_calender)
                    binding.imageView10.setImageResource(R.drawable.ic_iconly_light_document)
                    binding.imageView11.setImageResource(R.drawable.ic_iconly_dark_home)
                    binding.imageView12.setImageResource(R.drawable.ic_iconly_light_heart)
                    binding.imageView13.setImageResource(R.drawable.ic_iconly_light_setting)
                } else if (position == 1) {
                    binding.imageView9.setImageResource(R.drawable.ic_iconly_light_calendar)
                    binding.imageView10.setImageResource(R.drawable.ic_group_document)
                    binding.imageView11.setImageResource(R.drawable.ic_iconly_dark_home)
                    binding.imageView12.setImageResource(R.drawable.ic_iconly_light_heart)
                    binding.imageView13.setImageResource(R.drawable.ic_iconly_light_setting)
                } else if (position == 2) {
                    binding.imageView9.setImageResource(R.drawable.ic_iconly_light_calendar)
                    binding.imageView10.setImageResource(R.drawable.ic_iconly_light_document)
                    binding.imageView11.setImageResource(R.drawable.ic_group_14)
                    binding.imageView12.setImageResource(R.drawable.ic_iconly_light_heart)
                    binding.imageView13.setImageResource(R.drawable.ic_iconly_light_setting)
                } else if (position == 3) {
                    binding.imageView9.setImageResource(R.drawable.ic_iconly_light_calendar)
                    binding.imageView10.setImageResource(R.drawable.ic_iconly_light_document)
                    binding.imageView11.setImageResource(R.drawable.ic_iconly_dark_home)
                    binding.imageView12.setImageResource(R.drawable.ic_group_life)
                    binding.imageView13.setImageResource(R.drawable.ic_iconly_light_setting)
                } else if (position == 4) {
                    binding.imageView9.setImageResource(R.drawable.ic_iconly_light_calendar)
                    binding.imageView10.setImageResource(R.drawable.ic_iconly_light_document)
                    binding.imageView11.setImageResource(R.drawable.ic_iconly_dark_home)
                    binding.imageView12.setImageResource(R.drawable.ic_iconly_light_heart)
                    binding.imageView13.setImageResource(R.drawable.ic_group_settings)
                }

                currenttab = position
            }

        })
        binding.viewPager.currentItem = currenttab
        binding.viewPager.offscreenPageLimit = 1
    }

    override fun onNoteLayoutClicked() {
        binding.viewPager.currentItem = 1
    }

    override fun onRemenderLayoutClicked() {
    }

    override fun onSheduleLayoutClicked() {
        binding.viewPager.currentItem = 1
    }


}