package com.artonov.codelabjetcoffee

import android.R.attr.category
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.artonov.codelabjetcoffee.model.Menu
import com.artonov.codelabjetcoffee.model.dummyBestSellerMenu
import com.artonov.codelabjetcoffee.model.dummyCategory
import com.artonov.codelabjetcoffee.model.dummyMenu
import com.artonov.codelabjetcoffee.ui.components.CategoryItem
import com.artonov.codelabjetcoffee.ui.components.HomeSection
import com.artonov.codelabjetcoffee.ui.components.MenuItem
import com.artonov.codelabjetcoffee.ui.components.Search
import com.artonov.codelabjetcoffee.ui.components.SectionText
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
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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