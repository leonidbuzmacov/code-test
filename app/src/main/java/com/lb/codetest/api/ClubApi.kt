package com.lb.codetest.api

import com.lb.codetest.R
import com.lb.codetest.models.Player
import com.lb.codetest.models.Squad

class ClubApi {

    fun getSquad(): Squad {
        return Squad(
            goalkeepers = buildPlayersList(1, 4),
            defenders = buildPlayersList(5, 8),
            midfildiers = buildPlayersList(9, 12),
            forwards = buildPlayersList(13, 16),
            coach = buildPlayersList(17, 17)
        )
    }

    private fun buildPlayersList(start: Int, end: Int): List<Player> {
        val playersList = mutableListOf<Player>()

        for (i in start..end) {
            playersList.add(
                Player(
                    name = "Player name",
                    country = "Country",
                    number = i,
                    image = R.drawable.avatar
                )
            )
        }

        return playersList
    }
}