package com.lb.codetest.models

data class Squad(
    val goalkeepers: List<Player> = emptyList(),
    val defenders: List<Player> = emptyList(),
    val midfildiers: List<Player> = emptyList(),
    val forwards: List<Player> = emptyList(),
    val coach: List<Player> = emptyList()
)
