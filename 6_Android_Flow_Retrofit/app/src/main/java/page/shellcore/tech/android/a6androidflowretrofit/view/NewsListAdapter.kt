package page.shellcore.tech.android.a6androidflowretrofit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news_article.view.*
import page.shellcore.tech.android.a6androidflowretrofit.R
import page.shellcore.tech.android.a6androidflowretrofit.model.NewsArticle

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val newsArticles = arrayListOf<NewsArticle>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_article, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(newsArticles[position])

    override fun getItemCount() = newsArticles.size

    fun onAddNewsItem(item: NewsArticle) {
        newsArticles.add(0, item)
        notifyItemInserted(0)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgArticle = itemView.imgArticle
        private val txtArticleTitle = itemView.txtArticleTitle
        private val txtArticleFeed = itemView.txtArticleFeed
        private val txtArticleAuthor = itemView.txtArticleAuthor

        fun bind(article: NewsArticle) {
            txtArticleTitle.text = article.title
            txtArticleFeed.text = article.publishedAt
            txtArticleAuthor.text = article.author
            imgArticle.loadImage(article.urlToImage)
        }
    }
}