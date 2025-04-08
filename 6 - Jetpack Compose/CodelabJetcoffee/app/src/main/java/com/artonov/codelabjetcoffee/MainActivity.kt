package com.artonov.codelabjetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artonov.codelabjetcoffee.model.BottomBarItem
import com.artonov.codelabjetcoffee.model.Menu
import com.artonov.codelabjetcoffee.model.dummyBestSellerMenu
import com.artonov.codelabjetcoffee.model.dummyCategory
import com.artonov.codelabjetcoffee.model.dummyMenu
import com.artonov.codelabjetcoffee.ui.components.CategoryItem
import com.artonov.codelabjetcoffee.ui.components.HomeSection
import com.artonov.codelabjetcoffee.ui.components.MenuItem
import com.artonov.codelabjetcoffee.ui.components.Search
import com.artonov.codelabjetcoffee.ui.theme.CodelabJetcoffeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodelabJetcoffeeTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = { BottomBar() }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Banner()
            HomeSection(
                title = stringResource(R.string.section_category),
                content = { CategoryRow() }
            )
            HomeSection(
                title = stringResource(R.string.menu_favorite),
                content = { MenuRow(dummyMenu) }
            )
            HomeSection(
                title = stringResource(R.string.section_best_seller_menu),
                content = { MenuRow(dummyBestSellerMenu) }
            )
        }
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(160.dp)
        )
        Search()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(dummyCategory.size) { index ->
            CategoryItem(dummyCategory[index])
        }
    }
}

@Composable
fun MenuRow(listMenu: List<Menu>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu.size) { index ->
            MenuItem(listMenu[index])
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            ),
        )
        navigationItems.map {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )

                },
                label = {
                    Text(it.title)
                },
                selected =
                    it.title == navigationItems[0].title,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview() {
    CodelabJetcoffeeTheme {
        JetCoffeeApp()
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryRowPreview() {
    CodelabJetcoffeeTheme {
        CategoryRow()
    }
}

@Composable
@Preview(showBackground = true)
fun MenuRowPreview() {
    CodelabJetcoffeeTheme {
        MenuRow(
            listMenu = dummyMenu,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BottomBarPreview() {
    CodelabJetcoffeeTheme {
        BottomBar()
    }
}