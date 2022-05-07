package com.bgconsole.domain

import com.bgconsole.platform.domain.Location

data class Aggregate(
    val id: String,
    val name: String,
    val description: String?,
    val location: Location?,
    val instructions: List<Instruction>?
) {
    companion object {
        fun empty() = Aggregate("", "", null, null, emptyList())
    }
}
