package com.bgconsole.domain

data class Workspace(
    val id: String,
    val name: String,
    val description: String,
    val location: Location,
    val projects: List<Project>
)
