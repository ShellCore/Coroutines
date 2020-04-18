package page.shellcore.tech.a4androidcoroutinesroom.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import page.shellcore.tech.a4androidcoroutinesroom.R
import page.shellcore.tech.a4androidcoroutinesroom.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        initViewModel()
    }

    private fun setupOnClickListeners() {
        btnLogin.setOnClickListener { onLogin(it) }
        btnGotoSignUp.setOnClickListener { onGotoSignUp(it) }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            loginComplete.observe(viewLifecycleOwner, Observer { isComplete ->
                Toast.makeText(activity, "Login Complete", Toast.LENGTH_SHORT).show()
                val action = LoginFragmentDirections.actionGoToMain()
                Navigation.findNavController(tilUserName).navigate(action)
            })
            error.observe(viewLifecycleOwner, Observer { error ->
                Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun onLogin(v: View) {
        val username = tilUserName.editText?.text.toString()
        val password = tilPassword.editText?.text.toString()

        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(activity, "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.login(username, password)
        }
    }

    private fun onGotoSignUp(v: View) {
        val action = LoginFragmentDirections.actionGoToSignUp()
        Navigation.findNavController(v).navigate(action)
    }
}
