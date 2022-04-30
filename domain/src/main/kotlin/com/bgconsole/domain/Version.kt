package com.bgconsole.domain

data class Version(
    val id: String,
    val name: String,
    val description: String,
    val aggregates: List<Aggregate>,
    val environments: List<Environment>
)
