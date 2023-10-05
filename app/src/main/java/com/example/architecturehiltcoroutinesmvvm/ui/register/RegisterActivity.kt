package com.example.architecturehiltcoroutinesmvvm.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.architecturehiltcoroutinesmvvm.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {

        }
    }
}