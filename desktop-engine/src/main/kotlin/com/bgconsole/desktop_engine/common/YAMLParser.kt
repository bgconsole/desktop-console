package com.bgconsole.desktop_engine.common

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File
import java.net.URL

class YAMLParser<E>(private val entityType: E, private val entityTypes: List<E>) {

    fun readArray(absolutePath: String): List<E> {
        val mapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
        val url = URL("file:$absolutePath")
        return mapper.readValue(url, entityTypes::class.java)
    }

    fun readOne(absolutePath: String): E {
        val mapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
        val url = URL("file:$absolutePath")
        return mapper.readValue(url, entityType!!::class.java)
    }

    fun writeArray(entities: List<E>, absolutePath: String) {
        val mapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
        val writer = mapper.writer(DefaultPrettyPrinter())
        writer.writeValue(File(absolutePath), entities)
    }

    fun writeOne(entity: E, absolutePath: String) {
        val mapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
        val writer: ObjectWriter = mapper.writer(DefaultPrettyPrinter())
        writer.writeValue(File(absolutePath), entity)
    }

}