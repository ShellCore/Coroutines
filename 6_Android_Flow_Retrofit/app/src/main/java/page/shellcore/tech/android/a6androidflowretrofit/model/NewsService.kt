package page.shellcore.tech.android.a6androidflowretrofit.model

import retrofit2.http.GET

interface NewsService {

    @GET("news.json")
    suspend fun getNews(): List<NewsArticle>
}