package com.damolks.ouxy3.ui.main.marketplace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damolks.ouxy3.data.model.MarketplaceModule
import com.damolks.ouxy3.databinding.ItemMarketplaceModuleBinding

class MarketplaceModuleAdapter(
    private val onInstallClick: (String) -> Unit
) : ListAdapter<MarketplaceModule, MarketplaceModuleAdapter.ViewHolder>(ModuleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMarketplaceModuleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, onInstallClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemMarketplaceModuleBinding,
        private val onInstallClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(module: MarketplaceModule) {
            binding.apply {
                moduleTitle.text = module.name
                moduleDescription.text = module.description
                moduleVersion.text = module.version
                installButton.isEnabled = !module.isInstalled
                installButton.text = if (module.isInstalled) "Installé" else "Installer"
                installButton.setOnClickListener {
                    if (!module.isInstalled) {
                        onInstallClick(module.id)
                    }
                }
            }
        }
    }
}

class ModuleDiffCallback : DiffUtil.ItemCallback<MarketplaceModule>() {
    override fun areItemsTheSame(oldItem: MarketplaceModule, newItem: MarketplaceModule): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MarketplaceModule, newItem: MarketplaceModule): Boolean {
        return oldItem == newItem
    }
}