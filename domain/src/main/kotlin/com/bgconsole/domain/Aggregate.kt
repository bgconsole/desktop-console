package com.bgconsole.domain

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
