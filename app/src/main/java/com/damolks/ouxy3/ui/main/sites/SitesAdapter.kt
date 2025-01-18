package com.damolks.ouxy3.ui.main.sites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.databinding.ItemSiteBinding

class SitesAdapter : ListAdapter<Site, SitesAdapter.SiteViewHolder>(SiteDiffCallback()) {

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

    class SiteViewHolder(private val binding: ItemSiteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(site: Site) {
            binding.apply {
                siteName.text = site.name
                siteAddress.text = site.address
                clientName.text = site.contactName ?: "Pas de contact spécifié"
            }
        }
    }

    private class SiteDiffCallback : DiffUtil.ItemCallback<Site>() {
        override fun areItemsTheSame(oldItem: Site, newItem: Site) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Site, newItem: Site) =
            oldItem == newItem
    }
}