package com.bgconsole.desktop_engine.utils

import com.bgconsole.domain.Variable

object VariableReplacerUtils {

    fun replaceVariable(value: String, variables: List<Variable>): String {
        var command = value
        var abort = false
        var i=0 // Just to avoid an infinite loop if a value contains another ${}
        while (!abort && command.contains("\${") && i < 100) {
            val pos1 = command.indexOf("\${")
            val pos2 = command.indexOf("}", pos1)
            val theVar = command.substring(pos1 + 2, pos2)
            val replacer = variables.find { it.name == theVar }
            if (replacer != null) {
                command = command.replace("\${$theVar}", replacer.value, false)
                i++
            } else {
                abort = true
            }
        }
        return command
    }
}