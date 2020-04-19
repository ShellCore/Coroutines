package page.shellcore.tech.android.a6androidflowretrofit.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel: ViewModel() {

    val newsArticles = MutableLiveData<String>()
}