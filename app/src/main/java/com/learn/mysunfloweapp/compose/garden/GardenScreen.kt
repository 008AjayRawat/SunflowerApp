package com.learn.mysunfloweapp.compose.garden

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.learn.mysunfloweapp.R
import com.learn.mysunfloweapp.compose.SunflowerImage
import com.learn.mysunfloweapp.compose.card
import com.learn.mysunfloweapp.data.PlantAndGardenPlantings
import com.learn.mysunfloweapp.viewmodel.GardenPlantingListViewModel
import com.learn.mysunfloweapp.viewmodel.PlantAndGardenPlantingsViewModel


@Composable
fun GardenScreen(
    modifier: Modifier = Modifier,
    viewModel: GardenPlantingListViewModel = hiltViewModel(),
    onAddPlantClick: () -> Unit,
    onPlantClick: (PlantAndGardenPlantings) -> Unit
) {
    val gardenPlants by viewModel.plantAndGardenPlantings.collectAsState(initial = emptyList())
    GardenScreen(
        gardenPlants = gardenPlants,
        modifier = modifier,
        onAddPlantClick = onAddPlantClick,
        onPlantClick = onPlantClick
    )
}

@Composable
fun GardenScreen(
    modifier: Modifier = Modifier,
    gardenPlants: List<PlantAndGardenPlantings>,
    onAddPlantClick: () -> Unit,
    onPlantClick: (PlantAndGardenPlantings) -> Unit = {}
) {
    if (gardenPlants.isEmpty()) {
        EmptyGarden(
            modifier = modifier,
            onAddPlantClick = onAddPlantClick
        )
    } else {
        GardenList(
            gardenPlants = gardenPlants,
            onPlantClick = onPlantClick
        )
    }
}


@Composable
fun EmptyGarden(
    modifier: Modifier = Modifier,
    onAddPlantClick: () -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.garden_empty),
            style = MaterialTheme.typography.h5
        )

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = dimensionResource(id = R.dimen.button_corner_radius),
                bottomStart = dimensionResource(id = R.dimen.button_corner_radius),
                bottomEnd = 0.dp
            ),
            onClick = onAddPlantClick
        ) {
            Text(
                color = MaterialTheme.colors.primary,
                text = stringResource(id = R.string.add_plant)
            )
        }
    }

}


@Composable
fun GardenList(
    modifier: Modifier = Modifier,
    gardenPlants: List<PlantAndGardenPlantings>,
    onPlantClick: (PlantAndGardenPlantings) -> Unit
) {

    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        state = gridState,
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.card_side_margin),
            vertical = dimensionResource(id = R.dimen.margin_normal)
        )
    ) {
        items(
            items = gardenPlants,
            key = { it.plant.plantId }
        ) {
            GardenListItems(
                plant = it,
                onPlantClick = onPlantClick
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GardenListItems(
    plant: PlantAndGardenPlantings,
    onPlantClick: (PlantAndGardenPlantings) -> Unit
) {
    val vm = PlantAndGardenPlantingsViewModel(plant)

    // Dimensions
    val cardSideMargin = dimensionResource(id = R.dimen.card_side_margin)
    val marginNormal = dimensionResource(id = R.dimen.margin_normal)

    Card(
        onClick = { onPlantClick(plant) },
        modifier = Modifier.padding(
            start = cardSideMargin,
            end = cardSideMargin,
            bottom = dimensionResource(id = R.dimen.card_bottom_margin)
        ),
        elevation = dimensionResource(id = R.dimen.card_elevation),
        shape = MaterialTheme.shapes.card,
    ) {
        Column(Modifier.fillMaxWidth()) {
            SunflowerImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.plant_item_image_height)),
                model = vm.imageUrl,
                contentDescription = plant.plant.description,
                contentScale = ContentScale.Crop,
            )

            // Plant name
            Text(
                text = vm.plantName,
                Modifier
                    .padding(vertical = marginNormal)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.subtitle1,
            )

            // Planted date
            Text(
                text = stringResource(id = R.string.plant_date_header),
                Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = vm.plantDateString,
                Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.subtitle2
            )

            // Last Watered
            Text(
                text = stringResource(id = R.string.watered_date_header),
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = marginNormal),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = vm.waterDateString,
                Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = pluralStringResource(
                    id = R.plurals.watering_next,
                    count = vm.wateringInterval,
                    vm.wateringInterval
                ),
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = marginNormal),
                style = MaterialTheme.typography.subtitle2
            )
        }
    }


}
