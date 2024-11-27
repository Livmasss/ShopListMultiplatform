package com.livmas.my_collections_app.utils

fun String.isDigitsOnly() = all {
    it.isDigit()
}

fun String.removeExtraSpaces() =
    replace("^\\s+".toRegex(), "")
        .replace("\\s+\$".toRegex(), "")
        .replace("\\s+".toRegex(), " ")


