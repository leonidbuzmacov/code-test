package com.lb.codetest.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lb.codetest.R
import com.lb.codetest.models.Player
import com.lb.codetest.models.Squad
import com.lb.codetest.ui.theme.ClubTheme
import com.lb.codetest.ui.theme.DividerColor
import com.lb.codetest.ui.theme.SecondaryTextColor

@Composable
fun SquadUI(clubTheme: ClubTheme, squad: Squad, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            PlayerGroup(squad.goalkeepers, stringResource(R.string.goalkeepers), clubTheme)
        }

        item {
            PlayerGroup(squad.defenders, stringResource(R.string.defenders), clubTheme)
        }

        item {
            PlayerGroup(squad.midfildiers, stringResource(R.string.midfildiers), clubTheme)
        }

        item {
            PlayerGroup(squad.forwards, stringResource(R.string.forwards), clubTheme)
        }

        item {
            PlayerGroup(squad.coach, stringResource(R.string.coach), clubTheme)
        }

        item {
            Text(
                text = stringResource(id = R.string.player_list_b),
                modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 60.dp),
                color = SecondaryTextColor,
                fontSize = 14.sp
            )
        }

    }
}

@Composable
fun PlayerGroup(playerList: List<Player>, title: String, clubTheme: ClubTheme) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .background(clubTheme.primary)
            .padding(vertical = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp),
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            Modifier.fillMaxWidth()
        ) {
            playerList.forEachIndexed { index, player ->
                PlayerItem(
                    player = player,
                    isLastItem = playerList.size - 1 == index
                )
            }
        }
    }
}

@Composable
fun PlayerItem(player: Player, isLastItem: Boolean) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 10.dp),
                painter = painterResource(id = player.image),
                contentDescription = null
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = player.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = player.country, color = SecondaryTextColor, fontSize = 12.sp)
            }

            Text(
                text = player.number.toString(),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (!isLastItem) {
            Divider(
                color = DividerColor,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}