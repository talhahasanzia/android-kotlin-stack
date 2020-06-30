package com.example.kotlinstack.home.adapters

import android.text.TextWatcher

import android.widget.EditText

import androidx.databinding.BindingAdapter


object EditTextBindingAdapters {
    @BindingAdapter("textChangedListener")
    @JvmStatic fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher) {
        editText.addTextChangedListener(textWatcher)
    }
}
