package com.courselara.receitafacil.core.util.extensions

fun String.containsNumber(): Boolean {
    val regex = Regex(".*\\d+.*")
    return regex.matches(this)
}

fun String.containsUpperCase(): Boolean {
    val regex = Regex(".*[A-Z]+.*")
    return regex.matches(this)
}

fun String.containsSpecialChar(): Boolean {
    val regex = Regex(".*[^A-Za-z\\d]+.*")
    return regex.matches(this)
}

fun String.toFormattedPhoneNumber(): String {

    if (this.length != 11) {
        throw IllegalArgumentException("O numero de telefone deve ter 11 digitos")
    }

    if (!this.all { it.isDigit() }) {
        throw IllegalArgumentException("O numero de telefone deve conter apenas digitos")
    }

    var areaCode = this.substring(0,2)
    val firstDigit = this.substring(2,3)
    val firstPart = this.substring(3,7)
    val secondPart = this.substring(7,11)

    return "$areaCode $firstDigit $firstPart-$secondPart"
}