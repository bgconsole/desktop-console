package com.bgconsole.domain

data class Instruction(
    val id: String,
    val type: String,
    val name: String,
    val description: String?,
    val alias: String,
    val instruction: String,
    val shellType: String?,
    val consoleId: String?,
    val execBefore: String?,
    val execAfter: String?
)
