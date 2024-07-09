package com.my_app.arambyeol

import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.MobileAds
import com.my_app.arambyeol.chat.data.remote.api.ChatRetrofitObj
import com.my_app.arambyeol.chat.data.remote.model.DeviceUID
import com.my_app.arambyeol.chat.repository.ChatRepository
import com.my_app.arambyeol.chat.repository.UserRepository
import com.my_app.arambyeol.chat.viewmodel.ChatViewModel
import com.my_app.arambyeol.chat.viewmodel.ChatViewModelFactory
import com.my_app.arambyeol.chat.viewmodel.UserViewModel
import com.my_app.arambyeol.chat.viewmodel.UserViewModelFactory
import com.my_app.arambyeol.controller.MealPlanFetcher
import com.my_app.arambyeol.controller.NetworkChecker
import com.my_app.arambyeol.view.*
import kotlinx.coroutines.*
import com.my_app.arambyeol.controller.SendLog
import com.my_app.arambyeol.data.AppDatabase
import com.my_app.arambyeol.nav.setNavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private val mealPlanFetcher = MealPlanFetcher()
    private val sendLog = SendLog()
    private lateinit var networkChecker: NetworkChecker
    private lateinit var userViewModel: UserViewModel
    private lateinit var chatViewModel: ChatViewModel
    @Inject
    lateinit var chatRepository: ChatRepository


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeComponents()

        val deviceUID = getDeviceUID()
        Log.d("getDeviceId", deviceUID.toString())

        initializeMobileAds()
        setMidnightAlarm()
        initializeChat(deviceUID)

        lifecycleScope.launch {
            fetchMealPlan()
        }

        setContent {
            setNavigationGraph(context = this@MainActivity)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        sendLog.sendLog()
    }

    private fun initializeComponents() {
        db = AppDatabase.getDatabase(this)
        networkChecker = NetworkChecker(this)
        val chatInterface = ChatRetrofitObj.retrofitService
        val userRepository = chatInterface?.let { UserRepository(it) }
        val userViewModelFactory = userRepository?.let { UserViewModelFactory(it) }
        userViewModel = userViewModelFactory?.let {
            ViewModelProvider(this, it)[UserViewModel::class.java]
        } ?: throw IllegalStateException("UserViewModel initialization failed")
//        val chatRepository = chatInterface?.let { ChatRepository(it) }
        val chatViewModelFactory =  ChatViewModelFactory(chatRepository)
        chatViewModel = chatViewModelFactory?.let {
            ViewModelProvider(this, it)[ChatViewModel::class.java]
        } ?: throw IllegalStateException("ChatViewModel initialization failed")
    }

    private fun getDeviceUID(): DeviceUID {
        return DeviceUID(Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID))
    }

    private fun initializeMobileAds() {
        MobileAds.initialize(this)
    }

    private fun setMidnightAlarm() {
        mealPlanFetcher.setMidnightAlarm(this)
    }

    private suspend fun fetchMealPlan() {
        val mealPlan = withContext(Dispatchers.IO) {
            db.mealPlanDao().getMealPlan()
        }
        Log.d("onCreate_getMealPlan", mealPlan.toString())
        if ((mealPlan == null || mealPlan.isEmpty()) && networkChecker.isInternetAvailable()) {
            Log.d("onCreate_getMealPlan", "successNetwork")
            mealPlanFetcher.updateCourses(this@MainActivity)
        }
    }

    private fun initializeChat(deviceUID: DeviceUID) {
        userViewModel.handleSignUp(deviceUID)
    }
}