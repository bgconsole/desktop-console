package com.bgconsole.domain

data class Project(
    val id: String,
    val name: String,
    val description: String?,
    val location: Location?,
    val versions: List<Version>?
) {
    companion object {
        fun empty(): Project {
            return Project("", "", null, null, null)
        }
    }
}
