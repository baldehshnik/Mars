package com.firstapplication.mars.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.firstapplication.mars.R
import com.firstapplication.mars.databinding.MarsInfoBinding

class MarsFactAdapter(
    private val items: Array<String>
) : RecyclerView.Adapter<MarsFactAdapter.MarsFactViewHolder>() {

    inner class MarsFactViewHolder(
        private val binding: MarsInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, value: String) {
            binding.twInfo.text = HtmlCompat.fromHtml(
                binding.root.resources.getString(R.string.fact_format, position, value),
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsFactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarsFactViewHolder(MarsInfoBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: MarsFactViewHolder, position: Int) {
        holder.bind(position + 1, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}