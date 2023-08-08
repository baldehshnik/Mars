package com.firstapplication.mars.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.MarsItemBinding
import com.firstapplication.mars.domain.model.MarsModel
import com.firstapplication.mars.ui.utils.OnMarsImageClickListener

class MarsAdapter(
    private val listener: OnMarsImageClickListener
) : ListAdapter<MarsModel, MarsAdapter.MarsViewHolder>(MarsDiffUtil()) {

    class MarsViewHolder(
        private val binding: MarsItemBinding,
        private val listener: OnMarsImageClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MarsModel) = with(binding) {
            var isImageLoaded = false
            imgMars.transitionName = model.id
            Glide.with(imgMars.context)
                .load(model.imageSrcUrl)
                .addListener(getImageLoadingListener { isImageLoaded = true })
                .transition(withCrossFade())
                .centerCrop()
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.image_loading_gradient)
                        .error(R.drawable.round_broken_image)
                )
                .into(imgMars)

            imgMars.setOnClickListener {
                if (isImageLoaded) {
                    listener.onClick(model, imgMars)
                }
            }
        }

        private inline fun getImageLoadingListener(crossinline onSuccess: () -> Unit): RequestListener<Drawable> {
            return object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any?,
                    target: Target<Drawable>?, isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?, model: Any?, target: Target<Drawable>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    onSuccess()
                    return false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MarsViewHolder(MarsItemBinding.inflate(layoutInflater), listener)
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        holder.bind(getItem(position))
        startAnimation(holder.itemView)
    }

    private fun startAnimation(view: View) {
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.recycler_view_item)
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