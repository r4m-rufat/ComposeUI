package com.codeworld.composeui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeworld.composeui.R
import com.codeworld.composeui.ui.theme.ButtonBlue
import com.codeworld.composeui.ui.theme.DarkerButtonBlue
import com.codeworld.composeui.ui.theme.DeepBlue
import com.codeworld.composeui.ui.theme.LightRed
import com.codeworld.composeui.ui.theme.TextWhite

@Composable
fun MainScreen(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DeepBlue)
            .padding(12.dp)
    ) {
        Column {
            GreetingBox()
            FeatureSection(
                featuresList = listOf("Sweet sleep", "Insomnia", "Depression"),
                onClick = { feature ->

                }
            )
            Meditation()
        }
        BottomMenu(
            menuList = listOf(
                BottomMenuItemModel("Home", R.drawable.ic_home),
                BottomMenuItemModel("Meditate", R.drawable.ic_bubble),
                BottomMenuItemModel("Sleep", R.drawable.ic_moon),
                BottomMenuItemModel("Music", R.drawable.ic_music),
                BottomMenuItemModel("Profile", R.drawable.ic_profile)
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun GreetingBox(
    name: String = "Rufat"
) {

    Row(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {

            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.bodySmall
            )

        }

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp),
        )

    }

}

@Composable
fun FeatureSection(
    featuresList: List<String>,
    onClick: (feature: String) -> Unit
) {

    var selectedFeatureIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {

        items(featuresList.size) { index ->

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .padding(end = 12.dp)
                    .clickable {
                        selectedFeatureIndex = index
                        onClick(featuresList[index])
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (selectedFeatureIndex == index) ButtonBlue else DarkerButtonBlue)
                    .padding(12.dp)
            ) {

                Text(
                    text = featuresList[index],
                    style = MaterialTheme.typography.bodySmall,
                    color = TextWhite
                )

            }

        }

    }

}

@Composable
fun Meditation() {

    Row(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(LightRed)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column() {

            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Text(
                text = "Meditation 3-10 min",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )

        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )

        }

    }

}


@Composable
fun BottomMenu(
    menuList: List<BottomMenuItemModel>,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        menuList.forEachIndexed { index, item ->

            BottomMenuItem(
                menuItem = item,
                selectedItemIconColor = Color.White,
                unSelectedItemIconColor = DarkerButtonBlue,
                selectedTextColor = TextWhite,
                unSelectedTextColor = DarkerButtonBlue,
                index = index,
                clickedItem = {

                }
            )

        }

    }

}


@Composable
fun BottomMenuItem(
    menuItem: BottomMenuItemModel,
    selectedItemIconColor: Color,
    unSelectedItemIconColor: Color,
    selectedTextColor: Color,
    unSelectedTextColor: Color,
    index: Int,
    clickedItem: (Int) -> Unit
) {

    var selectedItemIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .width(70.dp)
            .fillMaxHeight()
            .clickable {
                selectedItemIndex = index
                clickedItem(selectedItemIndex)
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(if (selectedItemIndex == index) ButtonBlue else Color.Transparent)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(id = menuItem.res),
                contentDescription = menuItem.title,
                tint = if (selectedItemIndex == index) selectedItemIconColor else unSelectedItemIconColor
            )

        }

        Text(
            text = menuItem.title,
            fontSize = 14.sp,
            color = if (selectedItemIndex == index) selectedTextColor else unSelectedTextColor
        )

    }

}


