package com.example.testkotlinapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.testkotlinapp.R
import com.example.testkotlinapp.TestKotlinBlueprintsApplication
import com.example.testkotlinapp.common.Constants
import com.example.testkotlinapp.presentation.viewmodel.UserDetailViewModel
import kotlinx.android.synthetic.main.fragment_user_details.*


class UserDetailsFragment : Fragment() {

    private val userDetailViewModel: UserDetailViewModel by viewModels {
        UserDetailViewModel.UserDetailViewModelFactory(
            ((requireActivity().application) as TestKotlinBlueprintsApplication).getUserDetailUseCase,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments ?: return
        val args = UserDetailsFragmentArgs.fromBundle(bundle)

        userDetailViewModel.getUserDetail(args.userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oversebViewModel()
    }

    private fun oversebViewModel(){
        userDetailViewModel.userDetail.observe(viewLifecycleOwner) {
            // userAdapter.updateUsers(it)
            Toast.makeText(
                requireContext(),
                it.userInfo.email,
                Toast.LENGTH_SHORT
            ).show()
            Glide.with(requireContext())
                .load(it.userInfo.avatar)
                .into(ivLoadingImg)
            txtFirstLastName.text =
                it.userInfo.firstName + Constants.SINGLE_SPACE + it.userInfo.lastName
            txtDetail.text = it.userSupport.textInfo

        }

        userDetailViewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> progressBar.visibility = View.VISIBLE
                false -> progressBar.visibility = View.GONE
            }
        }

        userDetailViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.an_error_has_occurred),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}