package com.bgconsole.domain

import com.bgconsole.platform.domain.Location

data class Environment(
    val id: String,
    val name: String,
    val description: String?,
    val location: Location?,
    val variables: List<Variable>?
) {

    companion object {
        fun empty() = Environment("", "", null, null, emptyList())
    }
}
