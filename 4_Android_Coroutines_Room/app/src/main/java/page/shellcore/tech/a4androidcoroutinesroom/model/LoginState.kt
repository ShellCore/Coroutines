package page.shellcore.tech.a4androidcoroutinesroom.model

object LoginState {
    var isLoggedIn = false
    var user: User? = null

    fun logout() {
        isLoggedIn = false
        user = null
    }

    fun login(user: User) {
        isLoggedIn = true
        this.user = user
    }
}