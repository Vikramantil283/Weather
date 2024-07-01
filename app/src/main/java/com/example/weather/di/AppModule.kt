package com.example.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weather.network.ApiInterface
import com.example.weather.network.URL
import com.example.weather.network.reository.DataRepository
import com.example.weather.network.repositoryImpl.DataRepoImpl
import com.example.weather.roomDatabase.AppDatabase
import com.example.weather.roomDatabase.daoImpl.WeatherDaoImpl
import com.example.weather.utils.Constants
import com.example.weather.utils.Constants.ACCEPT
import com.example.weather.utils.Constants.APPLICATION_JSON
import com.example.weather.viewModel.WeatherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // For Retrofit Instance
    @Provides
    @Singleton
    fun providesRetrofitInstance(@Named("WithOutCaching") client: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(URL.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    // For Retrofit Client
    @Provides
    @Singleton
    @Named("WithOutCaching")
    fun provideOkHttpClientWithOut(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder().addHeader(ACCEPT, APPLICATION_JSON).build()
            chain.proceed(newRequest)
        }).addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
            .connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build()
    }

    //For Api Interface
    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    // For Repository Implementation
    @Provides
    @Singleton
    fun provideDataRepository(
        dataRepository: DataRepository,
        weatherDaoImpl: WeatherDaoImpl
    ): WeatherViewModel {
        return WeatherViewModel(dataRepository, weatherDaoImpl)
    }

    // For DataRepo Implementation
    @Provides
    @Singleton
    fun provideDataRepoImpl(
        apiClient: ApiInterface,
    ): DataRepository {
        return DataRepoImpl(apiClient)
    }


    // For Database Instance
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }


    // For DB WeatherDao Repository
    @Singleton
    @Provides
    fun providesWeatherDaoRepo(database: AppDatabase): WeatherDaoImpl {
        return WeatherDaoImpl(database)
    }

}