package com.example.Project.hilt
import android.content.Context
import android.content.SharedPreferences
import com.example.Project.shared_component.APIServices.APIServiceMovie
import com.example.Project.shared_component.Repository.MovieRepository
import com.example.Project.Feature.Register.Domain.Repository.RegisterRepository
import com.example.Project.shared_component.APIServices.APIServiceUser
import com.example.Project.shared_component.data.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideMovieRepository(api: APIServiceMovie): MovieRepository {
        return MovieRepository(api)
    }

    @Provides
    fun provideUserRepository(api: APIServiceUser): RegisterRepository {
        return RegisterRepository(api)
    }

    @RegisterPrefs
    @Provides
    fun provideRegisterSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCE_KEY_REGISTER_TOKEN, Context.MODE_PRIVATE)
    }

    @LikedMoviesPrefs
    @Provides
    fun provideLikedMoviesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCE_KEY_LIKED_MOVIES, Context.MODE_PRIVATE)
    }

}
