package com.bgconsole.domain

data class Location(
    val id: String,
    val type: LocationType,
    val name: String,
    val location: String,
    val relativeTo: String? = ""
)

enum class LocationType {
    FILE, DATABASE, REMOTE
}
