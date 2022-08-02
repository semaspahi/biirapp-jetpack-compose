package com.sema.composeapp.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.sema.base.data.model.Beer
import com.sema.composeapp.R
import com.sema.composeapp.ui.navigation.MainActions
import com.sema.composeapp.ui.theme.*
import com.sema.composeapp.ui.view.beer.BeerViewModel

@Composable
fun BeerList(actions: MainActions, beerViewModel: BeerViewModel = viewModel()) {

    @Composable
    fun BeerListTopBar(
        onMenuClick: () -> Unit,
        onSearchClick: () -> Unit,
        onFilterClick: () -> Unit
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onMenuClick() }) {
                Icon(
                    painter = painterResource(id = com.sema.base.R.drawable.ic_menu),
                    contentDescription = null,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onSearchClick() }) {
                    Icon(
                        painter = painterResource(id = com.sema.base.R.drawable.ic_search),
                        contentDescription = null,
                    )
                }

                IconButton(onClick = { onFilterClick() }) {
                    Icon(
                        painter = painterResource(id = com.sema.base.R.drawable.ic_filter),
                        contentDescription = null,
                    )
                }
            }
        }
    }

    @Composable
    fun BeerImage(beer: Beer) {
        Image(
            painter = rememberAsyncImagePainter(beer.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
    }

    @Composable
    fun BeerListItem(beer: Beer, onClick: () -> Unit) {
        Card(
            shape = Shapes.large,
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp)
                .clickable { onClick.invoke() }
            ,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
            ) {

                BeerImage(beer)
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(0.55f)
                        .padding(8.dp)
                        .wrapContentHeight()
                ) {
                    Text(
                        text = beer.name,
                        fontWeight = FontWeight.Bold,
                        style = Typography.subtitle1,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = beer.tagline,
                        style = Typography.caption,
                        maxLines = 2
                    )
                }
            }
        }
    }

    @Composable
    fun BindList(data: List<Beer>) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),

        ) {
            items(
                items = data,
                key = { beer -> beer.id },
                itemContent = {
                    BeerListItem(it, onClick = { actions.gotoBeerDetail.invoke(it.id) })
                })
        }
    }

    @Composable
    fun BeerContent(beerViewModel: BeerViewModel) {
        val data by beerViewModel.beerListFlow.collectAsState()
        data.loading.let { if (it) LoadingBar() }
        data.success?.let { BindList(it) }
        data.error?.let { }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            BeerListTopBar(onMenuClick = {}, onFilterClick = {}, onSearchClick = {})
            Text(
                text = stringResource(R.string.beer_fragment_title),
                style = Typography.h1,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 32.dp,
                    bottom = 16.dp,
                    end = 120.dp
                ),
            )
            BeerContent(beerViewModel)
        }
    }
}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator()
    }
}

