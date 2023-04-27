/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.learn.mysunfloweapp.viewmodel

import androidx.lifecycle.*
import com.learn.mysunfloweapp.BuildConfig
import com.learn.mysunfloweapp.data.GardenPlantingRepository
import com.learn.mysunfloweapp.data.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel used in [PlantDetailFragment].
 */
@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
) : ViewModel() {

    val plantId: String = savedStateHandle.get<String>(PLANT_ID_SAVED_STATE_KEY)!!

    val isPlanted = gardenPlantingRepository.isPlanted(plantId).asLiveData()
    val plant = plantRepository.getPlant(plantId).asLiveData()

    private val _showSnackbar = MutableLiveData(false)
    val showSnackbar: LiveData<Boolean>
        get() = _showSnackbar

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
            _showSnackbar.value = true
        }
    }

    fun dismissSnackbar() {
        _showSnackbar.value = false
    }

    fun hasValidUnsplashKey() = ("cxMSGfW7l8ZePawwWnHf2v6LiiUJTvGHPsjH1wy_wjk" != "null")

    companion object {
        private const val PLANT_ID_SAVED_STATE_KEY = "plantId"
    }
}