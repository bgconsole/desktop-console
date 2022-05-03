package com.bgconsole.domain

data class Environment(
    val id: String,
    val name: String,
    val description: String,
    val location: Location?,
    val variables: List<Variable>?
)
