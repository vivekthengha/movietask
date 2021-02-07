package com.vivek.movieapptask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.facebook.shimmer.ShimmerFrameLayout

abstract class BaseFragment : Fragment() {

    abstract val layoutId: Int
    abstract val viewModel: BaseViewModel
    abstract val shimmer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutId, container, false)
        observeBaseValues()
        return view
    }

    private fun observeBaseValues() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            shimmer.visibility =  View.GONE
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
    }

}