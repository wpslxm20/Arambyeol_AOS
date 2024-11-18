package com.my_app.arambyeol.chat.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.my_app.arambyeol.base.App
import com.my_app.arambyeol.chat.data.remote.model.DeviceUID
import com.my_app.arambyeol.chat.data.remote.model.LoginResponse
import com.my_app.arambyeol.chat.data.remote.model.NicknameResponse
import com.my_app.arambyeol.chat.data.remote.model.SignUpResponse
import com.my_app.arambyeol.chat.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel (
    private val repository: UserRepository
) : ViewModel() {
    val tag = "UserViewModel"
    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> = _nickname


    fun handleSignUp(deviceUID: DeviceUID) {
        val funName = "handleSignUp"

        viewModelScope.launch {
            val call = repository.signUp(deviceUID)
            call?.enqueue(object : Callback<SignUpResponse> {
                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                    if (response.isSuccessful) {
                        Log.d(tag, "$funName success: ${response.body()}")
                        handleLogin(deviceUID)
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                        Log.d(tag, "$funName fail: $errorMessage")
                    }
                }
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Log.e(tag, "$funName exception: ${t.message}")
                }
            })
        }
    }

    private fun handleLogin(deviceUID: DeviceUID) {
        val funName = "handleLogin"

        viewModelScope.launch {
            val call = repository.login(deviceUID)
            call?.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("$tag", "$funName success : ${response.body()}")
                        val tokenData = response.body()?.data
                        if (tokenData != null) {
                            repository.saveToken(tokenData.accessToken, tokenData.refreshToken)
                            val accessToken = App.token_prefs.accessToken
                            val refreshToken = App.token_prefs.refreshToken
                            Log.d("accessToken", "$accessToken")
                            Log.d("refreshToken", "$refreshToken")

                            loadNickname()
                        }
                    }
                    else {
                        val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                        Log.d("$tag", "$funName fail : $errorMessage")
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("$tag", "$funName exception: ${t.message}")
                }
            })
        }
    }

    // 닉네임 조회
    // 로그인 성공 시 토큰 값으로 닉네임 가져오기
    // 가져온 데이터 SharedPreferences에 저장
    fun loadNickname() {
        val funName = "loadNickname"

        val call = repository.getNickname()
        call?.enqueue(object : Callback<NicknameResponse> {
            override fun onResponse(
                call: Call<NicknameResponse>,
                response: Response<NicknameResponse>
            ) {
                if (response.isSuccessful) {
                    _nickname.postValue(response.body()?.data?.nickname)
                    Log.d("$tag", "$funName success : ${response.body()}")
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Log.d("$tag", "$funName fail : $errorMessage")
                }
            }
            override fun onFailure(call: Call<NicknameResponse>, t: Throwable) {
                Log.e("$tag", "$funName exception: ${t.message}")
            }

        })
    }

    // 토큰 재발급
    // - 토큰 만료 시에 사용자가 아직 채팅페이지에 접속되어있을 경우
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}