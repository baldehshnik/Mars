package com.firstapplication.mars.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.FragmentImageDetailBinding
import com.firstapplication.mars.ui.adapter.MarsFactAdapter
import com.firstapplication.mars.ui.utils.ShareButtonInterpolator
import com.firstapplication.mars.ui.utils.getAnimationListener
import com.firstapplication.mars.ui.utils.getTransitionListener

const val SHARE_TYPE = "text/plain"

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
            (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

            if (savedInstanceState == null) {
                loadAnimation()
            } else {
                binding.titleCard.isVisible = true
                binding.btnShare.isVisible = true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMarsInformation()
        loadToolbar()

        binding.btnShare.addOnHideAnimationListener(
            getAnimationListener(
                onAnimationStart = {
                    binding.titleCard.animate()
                        .setDuration(80L)
                        .setStartDelay(0L)
                        .scaleX(0.0f)
                        .scaleY(0.0f)
                }
            )
        )

        binding.btnShare.addOnShowAnimationListener(
            getAnimationListener(
                onAnimationStart = {
                    binding.titleCard.animate()
                        .setDuration(150L)
                        .setStartDelay(0L)
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                }
            )
        )

        binding.btnShare.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.share_button)
            animation.interpolator = ShareButtonInterpolator(0.2, 10.0)
            it.startAnimation(animation)
            share()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun share() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                resources.getString(R.string.share_link_format, arguments.model.imageSrcUrl)
            )
            type = SHARE_TYPE
        }
        val shareIntent = Intent.createChooser(intent, binding.twToolbarTitle.text)
        startActivity(shareIntent)
    }

    private fun loadToolbar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        val title = resources.getString(R.string.image_number, arguments.model.id)
        binding.twToolbarTitle.text = title
        binding.toolbar.title = title
        Glide.with(binding.toolbarImage.context)
            .load(arguments.model.imageSrcUrl)
            .into(binding.toolbarImage)
    }

    private fun loadMarsInformation() {
        val adapter = MarsFactAdapter(resources.getStringArray(R.array.mars_info))
        binding.rwMarsInfo.adapter = adapter
    }

    private fun loadAnimation() {
        val onTransitionStart = { view: View ->
            view.apply {
                scaleX = 0.0f
                scaleY = 0.0f
                visibility = View.VISIBLE
                animate().setDuration(150L).setStartDelay(150L).scaleX(1.0f).scaleY(1.0f)
            }
        }

        val animation = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
            .addListener(
                getTransitionListener {
                    onTransitionStart(binding.titleCard)
                    onTransitionStart(binding.btnShare)
                }
            )
        sharedElementEnterTransition = animation
    }
}