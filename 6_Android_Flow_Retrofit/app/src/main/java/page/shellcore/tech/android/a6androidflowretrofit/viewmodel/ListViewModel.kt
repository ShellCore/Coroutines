package page.shellcore.tech.android.a6androidflowretrofit.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import page.shellcore.tech.android.a6androidflowretrofit.model.NewsRepository

class ListViewModel: ViewModel() {

    val newsArticles = NewsRepository().getNewsArticles().asLiveData()
}