package com.example.kotlinstack.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ErrorMessageParser @Inject constructor( @ActivityContext val context: Context) {
    // In reality map these to String Resources so they show messages in locale, hence context
    private val errorMap: Map<Int, String> = mapOf(
        404 to "Unable to find server",
        502 to "Services are down",
        400 to "Request cannot be processed"
    )

    fun getErrorMessage(id: Int): String {
        return errorMap[id] ?: "Unexpected error occured please try again or contact support"
    }


}