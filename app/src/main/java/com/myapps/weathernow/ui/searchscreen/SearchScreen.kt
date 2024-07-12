import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.myapps.weathernow.R
import com.myapps.weathernow.domain.geographical.GeographicalDataModel
import com.myapps.weathernow.ui.common.LoadingProgress
import com.myapps.weathernow.ui.common.shimmerEffect
import com.myapps.weathernow.ui.mainscreen.components.EmptyDataStorage
import com.myapps.weathernow.ui.mainscreen.components.GeographicalPlaceData
import com.myapps.weathernow.ui.navigation.Screen
import com.myapps.weathernow.ui.placescreen.cache.GeographicalModelCache
import com.myapps.weathernow.ui.searchscreen.DataCacheState
import com.myapps.weathernow.ui.searchscreen.SearchEvents
import com.myapps.weathernow.ui.searchscreen.SearchState
import com.myapps.weathernow.ui.searchscreen.cache.GeographicalDataCache
import com.myapps.weathernow.ui.searchscreen.suggestion.SuggestionResultItem
import com.myapps.weathernow.ui.ui.theme.OceanBlueSoft
import com.myapps.weathernow.ui.ui.theme.TransparentBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    cacheState: DataCacheState,
    onEvent: (SearchEvents) -> Unit,
    navController: NavController
) {

    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var isSearchingData by rememberSaveable {
        mutableStateOf(false)
    }

    var listOfResults by rememberSaveable {
        mutableStateOf<List<GeographicalDataModel>>(emptyList())
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = text,
                    onQueryChange = {
                        text = it
                        onEvent(SearchEvents.Search(it))
                        if (text.isEmpty()) {
                            isSearchingData = false
                        }
                    },
                    onSearch = {
                        isExpanded = false
                        isSearchingData = true
                        onEvent(SearchEvents.Search(
                            it
                        ))
                    },
                    expanded = isExpanded,
                    onExpandedChange = {
                        isExpanded = it
                    },
                    placeholder = {
                        Text("Search cities")
                    },
                    leadingIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        },
                            modifier = Modifier
                                .drawBehind {
                                    drawCircle(
                                        Color.Transparent
                                    )
                                }
                                .size(40.dp)
                                .padding(10.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.arrow_back),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    trailingIcon = {
                        if (isExpanded) {
                            IconButton(onClick = {
                                if (text.isEmpty()) {
                                    isExpanded = false
                                } else {
                                    text = ""
                                }
                            },
                                modifier = Modifier
                                    .drawBehind {
                                        drawCircle(
                                            Color.Transparent
                                        )
                                    }
                                    .size(40.dp)
                                    .padding(10.dp)
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.baseline_close_24),
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    }
                )
            },
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
            colors = SearchBarColors(
                containerColor = OceanBlueSoft,
                dividerColor = Color.White
            )
        ) {
            when (state) {
                is SearchState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        LoadingProgress()
                    }
                }

                is SearchState.Success -> {
                    listOfResults = state.listOfResults

                    if (listOfResults.isEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(
                                text = "No suggestions",
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            repeat(listOfResults.size) { index ->
                                SuggestionResultItem(
                                    suggestionName = listOfResults[index].name,
                                    countryCode = listOfResults[index].countryCode,
                                    modifier = Modifier
                                        .padding(
                                            vertical = 8.dp,
                                            horizontal = 10.dp
                                        )
                                        .clickable {
                                            text = listOfResults[index].name
                                        }
                                )
                            }
                        }
                    }
                }

                is SearchState.Error -> {

                }
            }
        }

        if (!isSearchingData) {
            when (cacheState) {
                is DataCacheState.Loading -> {

                }

                is DataCacheState.Success -> {
                    if (cacheState.listOfResults.isEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            EmptyDataStorage(
                                customText = "Explore cities",
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                        GeographicalDataCache(
                            geographicalDataCache = cacheState.listOfResults,
                            onEvent = {
                                onEvent(it)
                            },
                            navController = navController
                        )
                    }
                }
            }
        } else {
            if(listOfResults.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    EmptyDataStorage(
                        customText = "No results",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                }
            }
            else{
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    repeat(listOfResults.size) { index ->
                        GeographicalPlaceData(
                            geographicalDataModel = listOfResults[index],
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 5.dp)
                                .clickable {
                                    GeographicalModelCache.geographicalModelCache.value =
                                        listOfResults[index]
                                    onEvent(SearchEvents.StoreGeographicalDataCache(listOfResults[index]))
                                    navController.navigate(Screen.GeographicalPlaceScreen.route)
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }

        }
    }

}