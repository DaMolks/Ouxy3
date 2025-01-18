package com.damolks.ouxy3.ui.main.sites

sealed class SiteEvent {
    data class Success(val message: String) : SiteEvent()
    data class Error(val message: String) : SiteEvent()
}