package com.damolks.ouxy3.ui.onboarding.sites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.databinding.ItemSiteBinding

class SiteAdapter(
    private val onDeleteClick: (Site) -> Unit
) : ListAdapter<Site, SiteAdapter.SiteViewHolder>(SiteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val binding = ItemSiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SiteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SiteViewHolder(private val binding: ItemSiteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(site: Site) {
            with(binding) {
                siteName.text = site.name
                siteAddress.text = site.address
                clientName.text = site.contactName
                deleteButton.setOnClickListener { onDeleteClick(site) }
            }
        }
    }
}

class SiteDiffCallback : DiffUtil.ItemCallback<Site>() {
    override fun areItemsTheSame(oldItem: Site, newItem: Site): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Site, newItem: Site): Boolean {
        return oldItem == newItem
    }
}