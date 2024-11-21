package com.livmas.my_collections_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform