package com.firstapplication.mars.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.MarsItemBinding
import com.firstapplication.mars.domain.model.MarsModel

class MarsAdapter : ListAdapter<MarsModel, MarsAdapter.MarsViewHolder>(MarsDiffUtil()) {

    class MarsViewHolder(
        private val binding: MarsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(model: MarsModel) = with(binding) {
            val imageUri = model.imageSrcUrl.toUri()
                .buildUpon()
                .scheme("https")
                .build()

            Glide.with(imgMars.context)
                .load(imageUri)
                .transition(withCrossFade())
                .centerCrop()
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.image_loading_gradient)
                        .error(R.drawable.round_broken_image)
                )
                .into(imgMars)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MarsViewHolder(MarsItemBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        holder.bind(getItem(position))
        startAnimation(holder.itemView)
    }

    private fun startAnimation(view: View) {
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.recycler_view_item).also {
            it.duration = 200
        }
        view.startAnimation(animation)
    }
}

class MarsDiffUtil : DiffUtil.ItemCallback<MarsModel>() {
    override fun areItemsTheSame(oldItem: MarsModel, newItem: MarsModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MarsModel, newItem: MarsModel): Boolean {
        return oldItem == newItem
    }
}