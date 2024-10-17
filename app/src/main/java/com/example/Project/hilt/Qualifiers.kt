package com.example.Project.hilt
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RegisterPrefs

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LikedMoviesPrefs
