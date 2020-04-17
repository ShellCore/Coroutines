package page.shellcore.tech.a4androidcoroutinesroom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

import page.shellcore.tech.a4androidcoroutinesroom.R
import page.shellcore.tech.a4androidcoroutinesroom.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        initViewModel()
    }

    private fun setupOnClickListeners() {
        btnLogout.setOnClickListener { onLogout() }
        btnDeleteUser.setOnClickListener { onDeleteUser() }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            signout.observe(viewLifecycleOwner, Observer {

            })
            userDeleted.observe(viewLifecycleOwner, Observer {

            })
        }
    }

    private fun onLogout() {
        val action = MainFragmentDirections.actionGoToSignUp()
        Navigation.findNavController(txtUsername).navigate(action)
    }

    private fun onDeleteUser() {
        val action = MainFragmentDirections.actionGoToSignUp()
        Navigation.findNavController(txtUsername).navigate(action)
    }
}
