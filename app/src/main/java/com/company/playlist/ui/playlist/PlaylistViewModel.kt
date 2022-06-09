package com.company.playlist.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.playlist.`object`.Constant
import com.company.playlist.base.BaseViewModel
import com.company.playlist.model.Playlists
import com.company.playlist.remote.ApiService
import com.company.playlist.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel: BaseViewModel() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylist(): LiveData<Playlists> {

        return playlist()
    }

    private fun playlist(): LiveData<Playlists> {
        val data = MutableLiveData<Playlists>()
        apiService.getPlaylists(Constant.part, Constant.channelId, API_KEY)
            .enqueue(object: Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {

                }

            })
        return data

    }
}