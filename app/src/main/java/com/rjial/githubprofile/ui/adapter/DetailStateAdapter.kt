package com.rjial.githubprofile.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.ui.fragment.FollowersUserFragment
import com.rjial.githubprofile.ui.fragment.FollowingUserFragment

class DetailStateAdapter(activity: AppCompatActivity, val detail: DetailUsernameResponse): FragmentStateAdapter(activity) {
    companion object {
        const val DETAIL_DATA = "detail_data"
    }
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val followersUserFragment = FollowersUserFragment()
        val detailBundle = Bundle()
        detailBundle.putParcelable(DETAIL_DATA, detail)
        followersUserFragment.arguments = detailBundle
        val followingUserFragment = FollowingUserFragment()
        followingUserFragment.arguments = detailBundle
        val fragment = when(position) {
            0 -> followersUserFragment
            1 -> followingUserFragment
            else -> FollowersUserFragment()
        }
        return fragment
    }
}