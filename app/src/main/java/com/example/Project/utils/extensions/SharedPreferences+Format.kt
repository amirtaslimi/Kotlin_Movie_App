package com.example.Project.utils.extensions

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun SharedPreferences.clearPrefrences(){
    this.edit().clear().apply()
}

fun SharedPreferences.remove(key: String){
    this.edit().remove(key).apply()
}

fun SharedPreferences.containsKey(key: String): Boolean{
    return this.contains(key)
}

fun SharedPreferences.putBoolean(key: String, value: Boolean?){
    this.edit().putBoolean(key, value!!).apply()
}

fun SharedPreferences.getBoolean(key: String): Boolean{
    return this.getBoolean(key, false)
}

fun SharedPreferences.getBoolean(key: String, defualtValue:Boolean?): Boolean{
    return this.getBoolean(key, defualtValue!!)
}

fun SharedPreferences.putStringList(key: String, items: ArrayList<String>?) {
    val gson = Gson()
    this.edit() {
        val json: String = gson.toJson(items)
        putString(key, json)
        apply()
    }}

fun SharedPreferences.getStringList(key: String): ArrayList<String>? {
    val gson = Gson()
    val json = this.getString(key, null)
    val type = object : TypeToken<ArrayList<String>>() {}.type
    return gson.fromJson(json, type)
}

fun SharedPreferences.addLikedMovieId(movieId: String, key: String) {
    val likedMovies = this.getStringList(key) ?: ArrayList()
    if (!likedMovies.contains(movieId)) {
        likedMovies.add(movieId)
        this.putStringList(key, likedMovies)
    }
}

fun SharedPreferences.removeLikedMovieId(movieId: String, key: String) {
    val likedMovies = this.getStringList(key) ?: ArrayList()
    if (likedMovies.contains(movieId)) {
        likedMovies.remove(movieId)
        this.putStringList(key, likedMovies)
    }
}

fun SharedPreferences.containsLikedMovieId(movieId: String, key: String): Boolean {
    val likedMovies = this.getStringList(key) ?: ArrayList()
    return likedMovies.contains(movieId)
}