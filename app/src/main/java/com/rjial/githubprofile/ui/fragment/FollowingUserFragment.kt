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
import com.rjial.githubprofile.databinding.FragmentFollowingUserBinding
import com.rjial.githubprofile.model.response.DetailUsernameResponse
import com.rjial.githubprofile.model.viewmodel.UsernameFollowingViewModel
import com.rjial.githubprofile.ui.adapter.DetailStateAdapter
import com.rjial.githubprofile.ui.adapter.FollowingDetailAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FollowingUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowingUserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentFollowingUserBinding
    private lateinit var followingViewModel: UsernameFollowingViewModel

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
        binding = FragmentFollowingUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)
        val decorDividerItemDecoration = DividerItemDecoration(view.context, layoutManager.orientation)

        followingViewModel = ViewModelProvider(this)[UsernameFollowingViewModel::class.java]
        if (arguments != null) {
            val data: DetailUsernameResponse? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getParcelable(DetailStateAdapter.DETAIL_DATA, DetailUsernameResponse::class.java)
            } else {
                requireArguments().getParcelable<DetailUsernameResponse>(DetailStateAdapter.DETAIL_DATA)
            }
            followingViewModel.postFollowing(data!!.login)
        }

        followingViewModel.listFollowing.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = FollowingDetailAdapter(it)
                binding.rvFollowing.layoutManager = layoutManager
                binding.rvFollowing.addItemDecoration(decorDividerItemDecoration)
                binding.rvFollowing.adapter = adapter
            } else {
                Toast.makeText(view.context, "Failed fetch", Toast.LENGTH_SHORT).show()
            }
        }
        followingViewModel.isLoading.observe(viewLifecycleOwner) {
            when(it) {
                true -> {
                    binding.rvFollowing.visibility = View.INVISIBLE
                    binding.pbFollowing.visibility = View.VISIBLE
                }
                false -> {
                    binding.rvFollowing.visibility = View.VISIBLE
                    binding.pbFollowing.visibility = View.INVISIBLE
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
         * @return A new instance of fragment FollowingUserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FollowingUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}