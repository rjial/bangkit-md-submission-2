package com.rjial.githubprofile.ui.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rjial.githubprofile.R
import com.rjial.githubprofile.databinding.FragmentFollowersUserBinding
import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.model.viewmodel.UsernameFollowersViewModel
import com.rjial.githubprofile.ui.adapter.DetailStateAdapter
import com.rjial.githubprofile.ui.adapter.FollowersDetailAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FollowersUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowersUserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentFollowersUserBinding
    private lateinit var followersViewModel: UsernameFollowersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowersUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        val decorDividerItemDecoration = DividerItemDecoration(view.context, layoutManager.orientation)

        followersViewModel = ViewModelProvider(this).get(UsernameFollowersViewModel::class.java)

        if (arguments != null) {
            val data: DetailUsernameResponse? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getParcelable(DetailStateAdapter.DETAIL_DATA, DetailUsernameResponse::class.java)
            } else {
                requireArguments().getParcelable<DetailUsernameResponse>(DetailStateAdapter.DETAIL_DATA)
            }
            followersViewModel.postFollowers(requireNotNull(data).login)
        }

        followersViewModel.listFollowers.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = FollowersDetailAdapter(it)
                binding.rvFollowers.layoutManager = layoutManager
                binding.rvFollowers.addItemDecoration(decorDividerItemDecoration)
                binding.rvFollowers.adapter = adapter
            } else {
                Toast.makeText(view.context, "Failed fetch", Toast.LENGTH_SHORT).show()
            }
        }

        followersViewModel.isLoading.observe(viewLifecycleOwner) {
            when(it) {
                true -> {
                    binding.rvFollowers.visibility = View.INVISIBLE
                    binding.pbFollowers.visibility = View.VISIBLE
                }
                false -> {
                    binding.rvFollowers.visibility = View.VISIBLE
                    binding.pbFollowers.visibility = View.INVISIBLE
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FollowersUserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FollowersUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        const val FOLLOWERS_DATA = "followers_data"
    }
}