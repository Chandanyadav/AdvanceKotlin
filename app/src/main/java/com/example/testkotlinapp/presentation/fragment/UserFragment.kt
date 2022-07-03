package com.example.testkotlinapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testkotlinapp.LayoutUtils
import com.example.testkotlinapp.Listener.OnItemClickListener
import com.example.testkotlinapp.R
import com.example.testkotlinapp.TestKotlinBlueprintsApplication
import com.example.testkotlinapp.presentation.adapter.UserAdapter
import com.example.testkotlinapp.presentation.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment(), OnItemClickListener {
    lateinit var userAdapter: UserAdapter


    private val userViewModel: UserViewModel by viewModels {
        UserViewModel.UserViewModelFactory(
            ((requireActivity().application) as TestKotlinBlueprintsApplication).getUserUseCase,
            ((requireActivity().application) as TestKotlinBlueprintsApplication).saveUserUseCase,
            ((requireActivity().application) as TestKotlinBlueprintsApplication).getLocalUserUseCase,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userAdapter = UserAdapter(requireContext(), arrayListOf(), this)
        userViewModel.getUsers(2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        obserbViewModel()

    }

    companion object {
        const val COLUMNS_COUNT = 2
    }

    private fun obserbViewModel() {

        userViewModel.users.observe(viewLifecycleOwner, {
            userAdapter.updateUsers(it)
        })

        userViewModel.dataLoading.observe(viewLifecycleOwner, { loading ->
            when (loading) {
                true -> LayoutUtils.crossFade(pbLoading, rvUsers)
                false -> LayoutUtils.crossFade(rvUsers, pbLoading)
            }
        })

        rvUsers.apply {
            layoutManager =
                GridLayoutManager(requireContext(), COLUMNS_COUNT)
            adapter = userAdapter
        }

        userViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(
                requireContext(),
                getString(R.string.an_error_has_occurred),
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    override fun onItemClick(userId: String) {
        println("UserID $userId")
        val action: NavDirections = UserFragmentDirections.actionUserFragmentToUserDetailsFragment()
            .setUserId(userId.toInt())
        findNavController().navigate(action)
    }
}