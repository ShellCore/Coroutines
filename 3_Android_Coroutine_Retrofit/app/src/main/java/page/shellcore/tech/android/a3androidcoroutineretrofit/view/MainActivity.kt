package page.shellcore.tech.android.a3androidcoroutineretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import page.shellcore.tech.android.a3androidcoroutineretrofit.R
import page.shellcore.tech.android.a3androidcoroutineretrofit.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountriesListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)
            .get(ListViewModel::class.java)
        viewModel.refresh()

        recCountries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries
            .observe(this, Observer {countries ->
                countries?.let {
                    recCountries.visibility = View.VISIBLE
                    countriesAdapter.updateCountries(it)
                }
            })

        viewModel.countryLoadError
            .observe(this, Observer { isError ->
            isError?.let {
                txtError.visibility = if (it == null) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading
            .observe(this, Observer { isLoading ->
                isLoading.let {
                    progressBar.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        recCountries.visibility = View.GONE
                        txtError.visibility = View.GONE
                    }
                }
            })
    }
}
