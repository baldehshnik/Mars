package com.firstapplication.mars.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.FragmentImageDetailBinding

class ImageDetailFragment : BaseFragment() {

    private var _binding: FragmentImageDetailBinding? = null
    private val binding: FragmentImageDetailBinding get() = _binding!!

    private val arguments: ImageDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_detail, container, false).also {
            _binding = FragmentImageDetailBinding.bind(it)
            loadAnimation()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeToolbarText()
        Glide.with(binding.img.context)
            .load(arguments.model.imageSrcUrl)
            .into(binding.img)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun changeToolbarText() {
        (activity as AppCompatActivity).supportActionBar?.let { bar ->
            bar.title = resources.getString(R.string.image_number, arguments.model.id)
        }
    }

    private fun loadAnimation() {
        val animation = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
    }
}