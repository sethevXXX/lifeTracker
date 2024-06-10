package com.example.project

import android.content.Context

object Monster {

    var HP = 1000

    fun updateBossHP(context: Context, health: Int) {
        saveBossHPToFile(context, fileNameMonster, health)
    }

    fun getBossHP(context: Context): Int {
        return readBossHPFromFile(context, fileNameMonster)
    }

}
