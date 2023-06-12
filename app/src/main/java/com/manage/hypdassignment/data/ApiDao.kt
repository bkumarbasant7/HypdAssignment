package com.manage.hypdassignment.data

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.manage.hypdassignment.BuildConfig
import com.manage.hypdassignment.data.request.SimilarProductRequest
import com.manage.hypdassignment.data.response.CollectionResponse
import com.manage.hypdassignment.data.response.SimilarProductResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.*

interface ApiDao {
    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val client = retrofit.create<ApiDao>()
    }

    @GET("app/influencer/collection")
    suspend fun getCollections(
        @Query("id") id: String = "6475b6c7cbd697567cc42bda",
        @Query("status") publish: String = "publish"
    ): Response<CollectionResponse>


    @Headers("Content-Type: application/json")
    @POST("v3/app/catalog/similar")
    suspend fun getSimilarProduct(
        @Header("Authorization") Authorization: String = "ZXlKaGJHY2lPaUpJVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SnBaQ0k2SWpZME56UmhOemRoTkRCa1pqQXhNVFpqWTJOaE1EVTVOeUlzSW1OMWMzUnZiV1Z5WDJsa0lqb2lOalEzTkdFM04yRTBNR1JtTURFeE5tTmpZMkV3TlRrNElpd2lZMkZ5ZEY5cFpDSTZJalkwTnpSaE56ZGhOREJrWmpBeE1UWmpZMk5oTURVNU9TSXNJblI1Y0dVaU9pSmpkWE4wYjIxbGNpSXNJbkp2YkdVaU9pSjFjMlZ5SWl3aWRYTmxjbDluY205MWNITWlPbTUxYkd3c0ltWjFiR3hmYm1GdFpTSTZJaUlzSW1SdllpSTZJakF3TURFdE1ERXRNREZVTURBNk1EQTZNREJhSWl3aVpXMWhhV3dpT2lKaGVXVXVZbUZpZFdOb1lXdEFhRzkwYldGcGJDNWpiMjBpTENKd2FHOXVaVjl1YnlJNmV5SndjbVZtYVhnaU9pSXJPVEVpTENKdWRXMWlaWElpT2lJNU5UWXdORFl5TlRRMEluMHNJbkJ5YjJacGJHVmZhVzFoWjJVaU9tNTFiR3dzSW5Cb2IyNWxYM1psY21sbWFXVmtJanAwY25WbExDSnBibVpzZFdWdVkyVnlYMmx1Wm04aU9uc2lYMmxrSWpvaU5qUTNOR0U0TWpNME1HUm1NREV4Tm1OalkyRXdOVGxpSWl3aWJtRnRaU0k2SWtOb1lXMXdZV3RzWVd3Z1NtRjVZVzUwYVd4aGJDQkhZV1JoSWl3aWRYTmxjbTVoYldVaU9pSmphR0Z0Y0dGcklpd2liMjVpYjJGeVpHbHVaMTl6ZEdGblpYTWlPbnNpZDJWc1kyOXRaVjl6WTNKbFpXNWZibTkwWDNacFpYZGxaQ0k2Wm1Gc2MyVXNJbk4wYjNKcFpYTmZibTkwWDNacFpYZGxaQ0k2Wm1Gc2MyVXNJbk4wYjNKbFgyNXZkRjlqY21WaGRHVmtJanBtWVd4elpTd2laM1ZwWkdWa1gycHZkWEp1WlhsZmJtOTBYMk52YlhCc1pYUmxaQ0k2ZEhKMVpYMTlMQ0pqY21WaGRHVmtYM1pwWVNJNkltMXZZbWxzWlNKOS5QTnA0cUxHaHA2QmQya0ZzTU5DTjlOTjhLMmYtUzY0b0JnZ1Q5TlVqVlRz",
        @Body catalog_ids: SimilarProductRequest,
    ): Response<SimilarProductResponse>
}