package com.vivek.movieapptask.data.network

import com.vivek.movieapptask.data.network.model.interfaces.PaginatedMovie
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MovieAppTaskApi {

    companion object {
        private var instanceBacking: MovieAppTaskApi? = null
        private val BASE_URL = "https://api.themoviedb.org/3/"

        val instance: MovieAppTaskApi
            get() = instanceBacking
                ?: throw IllegalStateException("MovieAppTaskApi Api not initialized. Please make sure you have called init()")

        fun init(token: String, complete: () -> Unit) {
            synchronized(MovieAppTaskApi::class.java) {
                val instance = instanceBacking
                if (instance != null) {
                    instanceBacking = instance
                } else {

                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                    val authorizationInterceptor = object : Interceptor {
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val newRequest: Request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer $token")
                                .build()
                            return chain.proceed(newRequest)
                        }
                    }

                    val client = OkHttpClient.Builder().addInterceptor(authorizationInterceptor)
                        .addInterceptor(loggingInterceptor).build()

                    val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client).build().create(ApiService::class.java)
                    instanceBacking = MovieAppTaskApiImpl(retrofit)
                    complete()
                }
            }
        }
    }

    fun getLatestMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    )

    fun getNowPlayingMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    )

    fun getPopularMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    )

    fun getTopRatedMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    )

    fun getUpcomingMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    )

    fun searchMovies(
        page: Int,
        lang: String,
        query: String,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    )
}