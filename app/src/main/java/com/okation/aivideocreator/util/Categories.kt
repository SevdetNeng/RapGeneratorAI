package com.okation.aivideocreator.util

enum class Categories {
    FUN,
    SAD,
    HAPPY,
    SEXY,
    LOVE;
    fun getName() : String {
        return name.first() + name.substring(1).lowercase()
    }
}