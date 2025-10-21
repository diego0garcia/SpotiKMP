package com.diego.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform