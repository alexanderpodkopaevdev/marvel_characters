package com.alexanderpodkopaev.marvelcharacters.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.marvelcharacters.R

class CharactersLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CharactersLoadStateAdapter.CharactersLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: CharactersLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersLoadStateViewHolder {
        return CharactersLoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state_footer, parent, false),
            retry
        )
    }

    inner class CharactersLoadStateViewHolder(itemView: View, retry: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val btnRetry = itemView.findViewById<Button>(R.id.btn_retry)
        private val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)
        private val tvError = itemView.findViewById<TextView>(R.id.tv_error)

        init {
            btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                tvError.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState is LoadState.Error
            tvError.isVisible = loadState is LoadState.Error
        }

    }
}