package page.shellcore.tech.android.a3androidcoroutineretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import page.shellcore.tech.android.a3androidcoroutineretrofit.model.Country

class ListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        val dummyData = generateDummyCountries()

        countries.value = dummyData
        countryLoadError.value = null
        loading.value = false
    }

    private fun generateDummyCountries(): ArrayList<Country> = arrayListOf<Country>().apply {
        add(Country("dummyCountry1", "dummyCapital1", ""))
        add(Country("dummyCountry2", "dummyCapital2", ""))
        add(Country("dummyCountry3", "dummyCapital3", ""))
        add(Country("dummyCountry4", "dummyCapital4", ""))
    }

    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }
}