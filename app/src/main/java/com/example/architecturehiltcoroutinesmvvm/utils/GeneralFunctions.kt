
package com.example.architecturehiltcoroutinesmvvm.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.provider.SyncStateContract
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.architecturehiltcoroutinesmvvm.R
import com.google.android.material.textfield.TextInputLayout
@SuppressLint("SimpleDateFormat")

object GeneralFunctions {



    fun showTextInputLayoutError(textInputLayout: TextInputLayout, message: String): Boolean {
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = message
        textInputLayout.requestFocus()
        return false
    }
    fun hideTextInputLayoutError(textInputLayout: TextInputLayout) = textInputLayout.apply { isErrorEnabled = false }

    fun validateEmail(context: Context, textInputLayout: TextInputLayout, editText: EditText): Boolean {

        if (editText.text.toString().isEmpty())
            return showTextInputLayoutError(textInputLayout, context.getString(R.string.enter_email))
        else
            hideTextInputLayoutError(textInputLayout)

        if (!editText.text.toString().trim().matches(Regex(Constants.EMAIL_REGEX)))
            return showTextInputLayoutError(textInputLayout, context.getString(R.string.invalid_email))
        else
            hideTextInputLayoutError(textInputLayout)

        return true

    }

        fun validatePassword(context: Context, textInputLayout: TextInputLayout, editText: EditText): Boolean {

            if (editText.text.toString().isEmpty())
                return showTextInputLayoutError(textInputLayout, context.getString(R.string.enter_password))
            else
                hideTextInputLayoutError(textInputLayout)

            if (editText.text.toString().length !in 8..20)
                return showTextInputLayoutError(textInputLayout, context.getString(R.string.password_must_be_8_20_characters))
            else
                hideTextInputLayoutError(textInputLayout)

            return true

        }
    fun validEmptyTextInputLayout(tripleList: List<Triple<TextInputLayout, EditText, String>>): Boolean {
        tripleList.forEach {
            hideTextInputLayoutError(it.first)
            if (it.second.text.toString().isEmpty())
                return showTextInputLayoutError(it.first, it.third)
        }

        return true
    }

    fun loadImage(context: Context, url: String, imageView: ImageView, changeScale: Boolean = true) {
        Glide.with(context).load(url).listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean): Boolean {
                imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                if (changeScale)
                    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                return false
            }
        }).error(R.drawable.no_image)
            .placeholder(R.drawable.no_image).into(imageView)
    }
}

