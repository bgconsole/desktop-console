package com.bgconsole.desktop_ui.utils

import com.bgconsole.domain.Version
import javafx.collections.ObservableList
import javafx.util.StringConverter

class VersionObservableConverter(private val versionList: ObservableList<Version>) : StringConverter<Version>() {
    override fun toString(p0: Version?): String {
        return p0?.name.orEmpty()
    }

    override fun fromString(p0: String?): Version {
        return p0?.let { versionList.find { it.name == p0 } }!!

    }
}