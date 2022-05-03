package com.bgconsole.desktop_engine.desktop_services.opened.version

import com.bgconsole.domain.Version

data class OpenedVersionContent(
    val versions: Map<String, List<Version>>
) {
    companion object {
        fun default(): OpenedVersionContent = OpenedVersionContent(emptyMap())
    }
}