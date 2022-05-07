package com.bgconsole.domain

import com.bgconsole.platform.domain.Location

data class Version(
    val id: String,
    val name: String,
    val description: String?,
    val location: Location?,
    val aggregates: List<Aggregate>?,
    val environments: List<Environment>?
) {
    companion object {
        fun empty(): Version = Version("", "", null, null, emptyList(), emptyList())
    }
}
