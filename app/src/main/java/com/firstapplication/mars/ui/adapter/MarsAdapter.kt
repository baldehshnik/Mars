package com.firstapplication.mars.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
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
//            twPrice.text = "${model.price} $"

            val imageUri = model.imageSrcUrl.toUri()
                .buildUpon()
                .scheme("https")
                .build()

            Glide.with(imgMars.context)
                .load(imageUri)
                .transition(withCrossFade())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.round_image_search)
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