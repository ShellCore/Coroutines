package page.shellcore.tech.a4androidcoroutinesroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import page.shellcore.tech.a4androidcoroutinesroom.model.LoginState
import page.shellcore.tech.a4androidcoroutinesroom.model.UserDatabase

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }

    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun login(username: String, password: String) {
        scope.launch {
            val user = db.getUser(username)
            if (user == null) {
                withContext(Dispatchers.Main) {
                    error.value = "User not found"
                }
            } else {
                if (user.passwordHash == password.hashCode()) {
                    LoginState.login(user)
                    withContext(Dispatchers.Main) {
                        loginComplete.value = true
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        error.value = "Password is incorrect"
                    }
                }
            }
        }
    }
}