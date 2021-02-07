package com.vivek.movieapptask.data.network

import com.vivek.movieapptask.BuildConfig
import com.vivek.movieapptask.data.network.model.interfaces.PaginatedMovie
import com.vivek.movieapptask.data.network.model.response.PaginatedMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieAppTaskApiImpl(private val apiService: ApiService) : MovieAppTaskApi {

    override fun getLatestMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    ) {
        apiService.getLatestMovies(page, BuildConfig.API_KEY).enqueue(object :
            Callback<PaginatedMovieResponse> {
            override fun onFailure(call: Call<PaginatedMovieResponse>, t: Throwable) {
                catch(Error(t));
            }

            override fun onResponse(
                call: Call<PaginatedMovieResponse>,
                response: Response<PaginatedMovieResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
                        complete(it)
                    }
            }
        })
    }

    override fun getNowPlayingMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    ) {
        apiService.getNowPlayingMovies(page, BuildConfig.API_KEY).enqueue(object :
            Callback<PaginatedMovieResponse> {
            override fun onFailure(call: Call<PaginatedMovieResponse>, t: Throwable) {
                catch(Error(t));
            }

            override fun onResponse(
                call: Call<PaginatedMovieResponse>,
                response: Response<PaginatedMovieResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
                        complete(it)
                    }
            }
        })
    }

    override fun getPopularMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    ) {
        apiService.getPopularMovies(page, BuildConfig.API_KEY).enqueue(object :
            Callback<PaginatedMovieResponse> {
            override fun onFailure(call: Call<PaginatedMovieResponse>, t: Throwable) {
                catch(Error(t));
            }

            override fun onResponse(
                call: Call<PaginatedMovieResponse>,
                response: Response<PaginatedMovieResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
                        complete(it)
                    }
            }
        })
    }

    override fun getTopRatedMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    ) {
        apiService.getTopRatedMovies(page, BuildConfig.API_KEY).enqueue(object :
            Callback<PaginatedMovieResponse> {
            override fun onFailure(call: Call<PaginatedMovieResponse>, t: Throwable) {
                catch(Error(t));
            }

            override fun onResponse(
                call: Call<PaginatedMovieResponse>,
                response: Response<PaginatedMovieResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
                        complete(it)
                    }
            }
        })
    }

    override fun getUpcomingMovies(
        page: Int,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    ) {
        apiService.getUpcomingMovies(page, BuildConfig.API_KEY).enqueue(object :
            Callback<PaginatedMovieResponse> {
            override fun onFailure(call: Call<PaginatedMovieResponse>, t: Throwable) {
                catch(Error(t));
            }

            override fun onResponse(
                call: Call<PaginatedMovieResponse>,
                response: Response<PaginatedMovieResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
                        complete(it)
                    }
            }
        })
    }

    override fun searchMovies(
        page: Int,
        lang: String,
        query: String,
        catch: (Error) -> Unit,
        complete: (PaginatedMovie?) -> Unit
    ) {
        apiService.searchMovies(page, BuildConfig.API_KEY, lang, query).enqueue(object : Callback<PaginatedMovieResponse>{
            override fun onFailure(call: Call<PaginatedMovieResponse>, t: Throwable) {
                catch(Error(t))
            }

            override fun onResponse(
                call: Call<PaginatedMovieResponse>,
                response: Response<PaginatedMovieResponse>
            ) {
                if (response.isSuccessful)
                    response.body()?.let {
                        complete(it)
                    }
            }
        })
    }
}