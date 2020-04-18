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

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }

    val userDeleted = MutableLiveData<Boolean>()
    val signOut = MutableLiveData<Boolean>()

    fun onSignOut() {
        LoginState.logout()
        signOut.value = true
    }

    fun onDeleteUser() {
        scope.launch {
            LoginState.user?.let { user ->
                db.deleteUser(user.id)
            }
            withContext(Dispatchers.Main) {
                LoginState.logout()
                userDeleted.value = true
            }
        }
    }
}