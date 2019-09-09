package com.aniketkadam.emotionwheel

fun getTextInFile(fileName: String): String? =
    ClassLoader.getSystemResourceAsStream(fileName)?.bufferedReader().use { it?.readText() }
