package com.example.pattern.utils


import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun generateNext365Days(): List<String> {
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMM d")
    return List(365) { i ->
        today.plusDays(i.toLong()).format(formatter)
    }
}