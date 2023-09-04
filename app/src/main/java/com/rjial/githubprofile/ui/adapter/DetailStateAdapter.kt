package com.rjial.githubprofile.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rjial.githubprofile.ui.fragment.FollowersUserFragment
import com.rjial.githubprofile.ui.fragment.FollowingUserFragment

class DetailStateAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FollowersUserFragment()
            1 -> FollowingUserFragment()
            else -> FollowersUserFragment()
        }
    }
}