package page.shellcore.tech.android.a3androidcoroutineretrofit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_country.view.*
import page.shellcore.tech.android.a3androidcoroutineretrofit.R
import page.shellcore.tech.android.a3androidcoroutineretrofit.model.Country
import java.util.ArrayList

class CountriesListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountriesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_country, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(countries[position])

    override fun getItemCount() = countries.size

    fun updateCountries(countries: List<Country>) {
        this.countries.apply {
            clear()
            addAll(countries)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgFlag = itemView.imgFlag
        private val txtCountry = itemView.txtCountry
        private val txtCapital = itemView.txtCapital

        fun bind(country: Country) {
            imgFlag.loadImage(country.flag)
            txtCountry.text = country.countryName
            txtCapital.text = country.capital
        }
    }
}