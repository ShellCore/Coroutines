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
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_signup.*
import page.shellcore.tech.a4androidcoroutinesroom.R
import page.shellcore.tech.a4androidcoroutinesroom.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnclickListeners()
        initViewModel()
    }

    private fun setupOnclickListeners() {
        btnSignUp.setOnClickListener { onSignUp(it) }
        btnGotoLogin.setOnClickListener { onGoToLogin(it) }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            signupComplete.observe(viewLifecycleOwner, Observer { isComplete ->
                Toast.makeText(activity, "Signup complete", Toast.LENGTH_SHORT).show()
                val action = SignUpFragmentDirections.actionGoToMain()
                Navigation.findNavController(tilUsername).navigate(action)
            })
            error.observe(viewLifecycleOwner, Observer { error ->
                Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun onGoToLogin(it: View) {
        val action = SignUpFragmentDirections.actionGoToLogin()
        Navigation.findNavController(it).navigate(action)
    }

    private fun onSignUp(it: View) {

        val username = tilUsername.editText!!.text.toString()
        val password = tilPassword.editText!!.text.toString()
        val info = tilOtherInfo.editText!!.text.toString()

        if (username.isNullOrEmpty() || password.isNullOrEmpty() || info.isNullOrEmpty()) {
            Toast.makeText(activity, "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.signup(username, password, info)
        }
    }
}
