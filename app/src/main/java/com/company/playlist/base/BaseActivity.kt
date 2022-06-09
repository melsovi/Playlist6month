package com.company.playlist.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding, VM: BaseViewModel>: AppCompatActivity() {
    protected lateinit var binding: VB
    protected abstract val viewModel: VM
    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)

        checkInternet()
        initViewModel()
        initView()
        initListener()
    }

    abstract fun initView()
    abstract fun initListener()
    abstract fun initViewModel()
    abstract fun checkInternet()
}