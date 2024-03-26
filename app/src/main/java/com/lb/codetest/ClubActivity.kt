package com.lb.codetest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lb.codetest.models.Squad
import com.lb.codetest.ui.SquadUI
import com.lb.codetest.ui.theme.ClubTheme
import com.lb.codetest.ui.theme.CodeTestTheme
import com.lb.codetest.ui.theme.SecondaryTextColor
import com.lb.codetest.viewmodel.ClubViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

const val CLUB_THEME = "ClubTheme"

class ClubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clubTheme = getClubTheme(intent)
        setContent {
            CodeTestTheme(primaryColor = clubTheme.primary) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = clubTheme.background
                ) {
                    ClubScreen(clubTheme)
                }
            }
        }
    }

    private fun getClubTheme(intent: Intent): ClubTheme {
        return intent.getSerializableExtra(CLUB_THEME) as? ClubTheme ?: ClubTheme.UCL
    }
}

@Composable
fun ClubScreen(clubTheme: ClubTheme, clubViewModel: ClubViewModel = viewModel()) {
    val squad by clubViewModel.squadFlow.collectAsStateWithLifecycle()
    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        modifier = Modifier,
        toolbar = {
            TopBarContainer(clubTheme = clubTheme)
        }
    ) {
        ClubTabsUI(clubTheme = clubTheme, squad = squad)
    }
}

@Composable
fun ClubTabsUI(clubTheme: ClubTheme, squad: Squad, modifier: Modifier = Modifier) {
    val tabs = listOf(
        stringResource(R.string.overview),
        stringResource(R.string.matches),
        stringResource(R.string.groups),
        stringResource(R.string.stats),
        stringResource(R.string.squad)
    )

    var selectedTabIndex by remember { mutableStateOf(tabs.lastIndex) }

    Column(
        modifier = modifier.then(Modifier.fillMaxWidth())
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = clubTheme.tabsContainerColor,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = clubTheme.accentColor
                )
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTabIndex == index
                val color = if (isSelected) clubTheme.accentColor else SecondaryTextColor

                Tab(text = {
                    Text(
                        text = title,
                        color = color,
                        fontSize = 13.sp
                    )
                },
                    selected = isSelected,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        when (selectedTabIndex) {
            tabs.lastIndex -> SquadUI(clubTheme, squad)
            else -> EmptyTabUI(clubTheme = clubTheme)
        }
    }
}

@Composable
fun EmptyTabUI(clubTheme: ClubTheme) {
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(clubTheme.primary)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarContainer(clubTheme: ClubTheme) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    )

    Box(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = painterResource(id = clubTheme.topbarImage),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Column {

            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    scrolledContainerColor = clubTheme.primary
                ),
                title = {},
                navigationIcon = {
                    val activity = LocalContext.current as? Activity
                    IconButton(onClick = { activity?.finish() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.topbar_action),
                            contentDescription = null
                        )
                    }
                }
            )

            Text(
                text = stringResource(id = R.string.barcelona),
                modifier = Modifier.padding(top = 12.dp, start = 16.dp),
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.playing),
                modifier = Modifier.padding(top = 72.dp, start = 16.dp),
                color = SecondaryTextColor,
                fontSize = 14.sp
            )

            Text(
                text = stringResource(id = R.string.round),
                modifier = Modifier.padding(start = 16.dp, bottom = 24.dp),
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Image(
            painter = painterResource(id = R.drawable.crest),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(110.dp)
        )
    }
}

