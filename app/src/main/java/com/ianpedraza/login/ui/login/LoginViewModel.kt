package com.ianpedraza.login.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ianpedraza.login.utils.Event

class LoginViewModel: ViewModel() {

    private val _loginSuccess: MutableLiveData<Event<String>> =
        MutableLiveData()

    val loginSuccess: LiveData<Event<String>>
        get() = _loginSuccess

    private val _showUsernameError: MutableLiveData<Event<Boolean>> =
        MutableLiveData()

    val showUsernameError: LiveData<Event<Boolean>>
        get() = _showUsernameError

    private val _showPasswordError: MutableLiveData<Event<Boolean>> =
        MutableLiveData()

    val showPasswordError: LiveData<Event<Boolean>>
        get() = _showPasswordError

    fun validateLogin(
        username: String?,
        password: String?,
    ) {
        showUsernameError(false)
        showPasswordError(false)

        if (username.isNullOrEmpty()) {
            showUsernameError(true)
            return
        }

        if (password.isNullOrEmpty()) {
            showPasswordError(true)
            return
        }

        _loginSuccess.value = Event(username)
    }

    private fun showUsernameError(show: Boolean) {
        _showUsernameError.value = Event(show)
    }

    private fun showPasswordError(show: Boolean) {
        _showPasswordError.value = Event(show)
    }

}