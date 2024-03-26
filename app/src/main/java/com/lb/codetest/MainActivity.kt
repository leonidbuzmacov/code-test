package com.lb.codetest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lb.codetest.ui.theme.ClubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeSelectionScreen { showClub(it) }
        }
    }

    private fun showClub(clubTheme: ClubTheme) {
        startActivity(Intent(this, ClubActivity::class.java).apply {
            putExtra(CLUB_THEME, clubTheme)
        })
    }
}

@Composable
fun ThemeSelectionScreen(onThemeSelected: (clubTheme: ClubTheme) -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { onThemeSelected(ClubTheme.UCL) }) {
            Text(text = "Start UCL")
        }

        Spacer(modifier = Modifier.width(50.dp))

        Button(onClick = { onThemeSelected(ClubTheme.UEL) }) {
            Text(text = "Start UEL")
        }
    }
}