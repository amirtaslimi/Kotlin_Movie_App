# MovieApp 🎬

A feature-rich Android movie application that allows users to browse, search, like movies, and authenticate themselves. This app leverages modern Android development technologies such as Retrofit, Hilt, MVVM, and animation to deliver a smooth and responsive experience.

## Features 🚀

- **Movie List**: Fetches a list of popular movies from a backend server.
- **Liked Movies**: Users can like/unlike movies and manage their favorites list.
- **Search Functionality**: Easily search for movies by title.
- **User Authentication**: Secure login and Exception Handling system to personalize the user experience.
- **Splash Screen**: Smooth entry experience for users.
- **Fragment-based Navigation**: Clean navigation between different parts of the app.


## Architecture 🏛️

The app is built using the **MVVM** (Model-View-ViewModel) architecture pattern and **Repository** pattern for better separation of concerns, scalability, and testability.

## Tech Stack 🛠️

- **Kotlin**: The primary programming language.
- **MVVM**: Architecture pattern for organizing code.
- **Retrofit**: Networking library for making API calls to the backend server.
- **Hilt**: Dependency Injection for managing app components.
- **Shared Preferences**: Used for storing user preferences and liked movies locally.
- **LiveData & ViewModel**: Managing UI-related data lifecycle-conscious.
- **Fragments**: For handling different UI parts like Movie List, Liked Movies, and Search.
