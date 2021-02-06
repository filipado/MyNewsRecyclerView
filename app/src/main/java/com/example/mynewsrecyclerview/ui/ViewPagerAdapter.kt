package com.example.mynewsrecyclerview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mynewsrecyclerview.R


class ViewPagerAdapter(
    private val items: ArrayList<Fragment>,
    activity: AppCompatActivity
) : FragmentStateAdapter(activity){

    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}