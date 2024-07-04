package com.my_app.arambyeol.chat.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.my_app.arambyeol.chat.data.remote.model.DeviceUID
import com.my_app.arambyeol.chat.data.remote.model.SignUpResponse
import com.my_app.arambyeol.chat.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel (
    private val repository: UserRepository
) : ViewModel() {
    // 여기서 live data로 관리해야 할 것은 닉네임 정도가 아닐까 싶음
    // 토큰값은 sharedPreferences에 저장할 거니깐

    // signUpResult의 결과에 따라 로그인을 실행할 지를 정해야 하니 이렇게 하는 것이 좋을 듯
    private val _signUpResult = MutableLiveData<SignUpResponse>()
    val signUpResult: LiveData<SignUpResponse> = _signUpResult

    // 회원가입
    // - 회원가입 서버 요청 후 성공, 실패 결과 받기
    fun handleSignUp(deviceUID: DeviceUID) {
        val tag = "UserViewModel"
        val funName = "handleSignUp"
        viewModelScope.launch {
            val call = repository.signUp(deviceUID)
            // 동기 호출
            call?.enqueue(object : Callback<SignUpResponse> {
                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                    if (response.isSuccessful) {
                        _signUpResult.postValue(response.body())
                        Log.d(tag, "$funName success: ${response.body()}")
                        //
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                        Log.d(tag, "$funName fail: $errorMessage")
//                    // 메세지가 이미 있는 사용자라고 하면 로그인, 단순 오류이면 에러 처리
                    }
                }
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Log.e(tag, "$funName exception: ${t.message}", t)
                }
            })
        }
    }

    // 로그인
    // - 회원가입 성공 시 로그인
    // - 로그인 성공 시 토큰값 SharedPreferences에 저장
    private fun handleLogin(deviceUID: DeviceUID) {

    }



    // 닉네임 조회
    // 로그인 성공 시 토큰 값으로 닉네임 가져오기
    // 가져온 데이터 SharedPreferences에 저장

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