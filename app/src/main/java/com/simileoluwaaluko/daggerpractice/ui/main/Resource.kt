package com.simileoluwaaluko.daggerpractice.ui.main

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.annotation.NonNull
import androidx.annotation.Nullable


class Resource <T> (val status: Status, val data: T, val message: String?) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, @Nullable data: T): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading (@Nullable data: T): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}