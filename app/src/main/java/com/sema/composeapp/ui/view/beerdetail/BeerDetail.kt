package com.sema.composeapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.sema.base.data.model.Beer
import com.sema.composeapp.R
import com.sema.composeapp.ui.navigation.MainActions
import com.sema.composeapp.ui.theme.*
import com.sema.composeapp.ui.view.beerdetail.BeerDetailViewModel

@Composable
fun BeerDetail(
    beerDetailViewModel: BeerDetailViewModel = viewModel(),
    mainActions: MainActions
) {

    @Composable
    fun BeerImage(beer: Beer) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(beer.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(145.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
        }
    }

    @Composable
    fun BeerContentRow(beer: Beer) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.beer_detail_ibu),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.width(8.dp))
                beer.ibu?.let {
                    Text(
                        text = stringResource(beerDetailViewModel.showIbu(it)),
                        fontSize = 13.sp,
                        color = white,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .background(color = yellow, shape = RoundedCornerShape(20.dp))
                            .padding(start = 8.dp, end = 8.dp)

                    )
                }
                Spacer(modifier = Modifier.width(28.dp))
                Text(
                    text = stringResource(id = R.string.beer_detail_abv),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.beer_detail_abv_percentage, beer.abv.toString()),
                    fontSize = 13.sp,
                    color = yellow,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .border(width = 1.dp, color = yellow, shape = RoundedCornerShape(20.dp))
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }

    @Composable
    fun BindBeerData(beer: Beer) {
        Column(modifier = Modifier.fillMaxSize()) {
            BeerImage(beer = beer)
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(24.dp)
            ) {
                Text(
                    text = beer.name,
                    style = Typography.h1,
                    maxLines = 1
                )
                Text(
                    text = beer.tagline,
                    style = Typography.caption,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(32.dp))
                BeerContentRow(beer)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = beer.description,
                    style = Typography.caption,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    @Composable
    fun BeerDetailContent(beerDetailViewModel: BeerDetailViewModel) {
        val data by beerDetailViewModel.beerDetailFlow.collectAsState()
        data.loading.let { if (it) LoadingBar() }
        data.success?.let { BindBeerData(it) }
        data.error?.let { }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            TopBarWithBack { mainActions.upPress.invoke() }
            BeerDetailContent(beerDetailViewModel)
        }
    }

}

@Composable
fun TopBarWithBack(title: String? = null, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
            )
        }
        title?.let {
            Text(
                text = it,
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        }
    }
}
