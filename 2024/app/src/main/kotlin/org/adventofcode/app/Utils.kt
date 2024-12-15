package org.adventofcode.app.utils

import java.io.InputStream

fun readLines(filename: String): List<String> {
    val classLoader = Thread.currentThread().contextClassLoader
    val inputStream: InputStream = classLoader.getResourceAsStream(filename) ?: throw IllegalArgumentException("File not there my guy...")
    return inputStream.bufferedReader().readLines()
}
