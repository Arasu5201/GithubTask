package com.arasu.githubissues.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.annotation.StringRes
import com.arasu.githubissues.retrofit.UserEntity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Arasu on 11-05-2021.
 */

private val OUTPUT: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private val INPUT: String = "MM-dd-yyyy"

@SuppressLint("SimpleDateFormat")
fun dateConversion(dates: String): String {
    val input = SimpleDateFormat(OUTPUT)
    val output = SimpleDateFormat(INPUT)

    var d: Date? = null
    try {
        d = input.parse(dates)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return "Updated at: " + output.format(d)
}

//    Code: Object -> String
fun stringFromObject(list: UserEntity?): String? {
    val gson = Gson()
    return gson.toJson(list)
}

//Code: String-> Object
fun getObjectFromString(jsonString: String?): UserEntity? {
    val listType: Type = object : TypeToken<UserEntity?>() {}.type
    return Gson().fromJson<UserEntity>(jsonString, listType)
}

fun isNetworkActive(context: Context?): Boolean {
    if (context == null) {
        return false
    }

    var result = false
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        cm?.run {
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI)
                    result = true
                else if (type == ConnectivityManager.TYPE_MOBILE)
                    result = true
            }
        }
    }
    return result
}

fun View.showSnackbar(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    val snackBar = Snackbar.make(this, resources.getString(messageRes), length)
    snackBar.f()
    snackBar.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    setAction(actionRes, listener)
    color?.let { setActionTextColor(color) }
}

class MyException(message: String) : Exception(message)