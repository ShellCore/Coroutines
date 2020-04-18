package page.shellcore.tech.a4androidcoroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import page.shellcore.tech.a4androidcoroutinesroom.model.LoginState
import page.shellcore.tech.a4androidcoroutinesroom.model.User
import page.shellcore.tech.a4androidcoroutinesroom.model.UserDatabase

class SignUpViewModel(application: Application): AndroidViewModel(application) {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun signup(username: String, password: String, info: String) {
        scope.launch {
            val user = db.getUser(username)
            if (user != null) {
                withContext(Dispatchers.Main) {
                    error.value = "User already exists"
                }
            } else {
                val user = User(username, password.hashCode(), info)
                val userId = db.insertUser(user)
                user.id = userId
                LoginState.login(user)
                withContext(Dispatchers.Main) {
                    signupComplete.value = true
                }
            }
        }
    }
}