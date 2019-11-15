package com.example.kotlincruddemo.domain

interface Command<out T> {
    fun execute(): T
}