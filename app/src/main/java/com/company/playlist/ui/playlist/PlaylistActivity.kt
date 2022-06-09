package com.company.playlist.ui.playlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.company.playlist.R
import com.company.playlist.base.BaseActivity
import com.company.playlist.databinding.ActivityPlaylistBinding
import com.company.playlist.model.Item
import com.company.playlist.ui.playlist_detail.PlaylistDetailActivity

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding, PlaylistViewModel>() {

    companion object {
        const val KEY = "key"
    }

    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(inflater)
    }

    override fun checkInternet() {
    }

    override fun initViewModel() {
        viewModel.getPlaylist().observe(this) {
            Toast.makeText(this, it.kind, Toast.LENGTH_SHORT).show()
            initRecyclerView(it.items)
        }
    }

    override fun initView() {

    }

    override fun initListener() {
    }

    private fun initRecyclerView(playlistsList: List<Item>) {
        binding.recyclerMain.adapter = PlaylistAdapter(playlistsList, this::onItemClick)
    }

    private fun onItemClick(channelId: String){
        val intent = Intent(this, PlaylistDetailActivity::class.java)
        intent.putExtra(KEY,channelId)
        startActivity(intent)

    }


}