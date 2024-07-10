package com.my_app.arambyeol.chat.di

import android.content.Context
import android.provider.Settings
import com.my_app.arambyeol.chat.data.remote.api.ChatInterface
import com.my_app.arambyeol.chat.data.remote.api.ChatRetrofitObj
import com.my_app.arambyeol.chat.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideChatInterface(): ChatInterface {
        return ChatRetrofitObj.retrofitService ?: throw IllegalStateException("Retrofit not initialized")
    }

    @Provides
    @Singleton
    fun provideChatRepository(): ChatRepository {
        val service = provideChatInterface()
        return ChatRepository(service)
    }

    @Provides
    @Singleton
    fun provideDeviceUID(@ApplicationContext context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}