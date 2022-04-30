package com.bgconsole.domain

data class Project(
    val name: String,
    val description: String,
    val location: Location,
    val versions: List<Version>
)
