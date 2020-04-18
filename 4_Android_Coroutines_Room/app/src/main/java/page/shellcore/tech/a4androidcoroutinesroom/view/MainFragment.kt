package page.shellcore.tech.a4androidcoroutinesroom.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

import page.shellcore.tech.a4androidcoroutinesroom.R
import page.shellcore.tech.a4androidcoroutinesroom.model.LoginState
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

        txtUsername.text = LoginState.user?.username
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
            signOut.observe(viewLifecycleOwner, Observer {
                Toast.makeText(activity, "Signed out", Toast.LENGTH_SHORT).show()
                goToSignUpScreen()
            })
            userDeleted.observe(viewLifecycleOwner, Observer {
                Toast.makeText(activity, "User deleted", Toast.LENGTH_SHORT).show()
                goToSignUpScreen()
            })
        }
    }

    private fun goToSignUpScreen() {
        val action = MainFragmentDirections.actionGoToSignUp()
        Navigation.findNavController(txtUsername).navigate(action)
    }

    private fun onLogout() {
        viewModel.onSignOut()
    }

    private fun onDeleteUser() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Delete User")
                .setMessage("Are you sure you want to delete this user?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.onDeleteUser()
                }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }
}
