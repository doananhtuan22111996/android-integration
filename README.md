# Android Architecture Sample Project

This repository contains a sample Android project demonstrating various architectural patterns and
best practices. Whether you're a beginner or an experienced Android developer, this project aims to
provide insights into structuring your Android applications efficiently.

## Features

- **MVVM Architecture**: The project follows the Model-View-ViewModel (MVVM) architecture,
  separating concerns between data, UI, and logic.
- **Clean Architecture**: Implements the principles of Clean Architecture to create a highly
  maintainable and testable codebase with a clear separation of concerns.
- **Data Binding**: Utilizes Android Data Binding Library to bind UI components in layouts to data
  sources in the app's architecture.
- **Room Database**: Integrates Room Persistence Library to provide an abstraction layer over
  SQLite, making database interactions smoother and more robust.
- **Retrofit**: Implements Retrofit library for handling network requests and simplifying REST API
  consumption.
- ~~**LiveData**: Uses LiveData to manage UI-related data in a lifecycle-conscious way and to
  persist data across configuration changes.~~
- **StateFlow/SharedFlow**: Integrates StateFlow and SharedFlow from Kotlin Coroutines for managing
  state and events in a reactive and lifecycle-aware manner.
- **ViewModel**: Utilizes ViewModel to store and manage UI-related data in a lifecycle conscious
  way, allowing data to survive configuration changes.
- **Dependency Injection**: Incorporates Dagger Hilt for dependency injection to keep the code
  modular, maintainable, and testable.
- **Coroutines**: Integrates Kotlin Coroutines for asynchronous programming, making code more
  concise and readable.
- **Paging Library**: Utilizes the Paging Library developed by Android to load and display large
  sets of data in chunks, enhancing performance and user experience.
- **Jetpack Compose**: Adopts Jetpack Compose, Android’s modern toolkit for building native UI, to
  create responsive and dynamic interfaces with less boilerplate code.
- **M3 Material**: Implements Material Design 3 (M3) to provide a modern and cohesive design system
  that enhances the user interface and user experience with updated components and styles.
- **Android Testing**: Includes comprehensive testing strategies using Espresso for UI testing and
  Robolectric for unit testing, ensuring robust and reliable application behavior.
- **Unit Testing**: Provides unit tests for ViewModel classes using JUnit and Mockito frameworks.

## Clean Architecture Layers

The project is structured using Clean Architecture principles, consisting of the following layers:

### Presentation Layer

- **Responsibility**: Handles UI-related concerns and user interactions.
- **Components**: Activities, Fragments, ViewModels, Adapters, and UI-related classes.
- **Dependencies**: Depends on Domain layer; independent of Data layer.

### Domain Layer

- **Responsibility**: Contains business logic and use cases of the application.
- **Components**: Use cases, business models, and domain interfaces.
- **Dependencies**: Independent of Presentation and Data layers.

### Data Layer

- **Responsibility**: Manages data retrieval and storage.
- **Components**: Repositories, data sources (local and remote), and data models.
- **Dependencies**: Implements interfaces defined in the Domain layer; may depend on external
  libraries for network/database access.

## Getting Started

To get started with this project, follow these steps:

1. Clone this
   repository: `git clone https://github.com/doananhtuan22111996/android_architecture.git`
2. Open the project in Android Studio.
3. Sync source to create `env.dev.properties` and `prod.dev.properties` then input info to this
4. Create 2 keystore with format names are `root-keystore-dev.jks` and `root-keystore.prod.jks`.
    - You can change the name if you want. But `remember` change new name/path in gradlew
5. Create your firebase project and config for 2 env: `dev` and `prod` only for `MDC-Android`
    - The jetpack compose ignore this step. Move to step 6
6. Build and run the project on an emulator or a physical device.

## Requirements

- Android Studio Arctic Fox (2020.3.1) or later
- Android SDK version 26 (Lollipop) or higher
- JDK 8 or higher

## Implementation

|                                                                        | Implementation | Status      |
|------------------------------------------------------------------------|----------------|-------------|
| MDC-Android                                                            |                | Available   |
| Jetpack Compose                                                        |                | Available   |
| [Flutter](https://github.com/doananhtuan22111996/flutter_architecture) |                | Available   |
| Unit testing                                                           | All            | Not planned |
| Android testing                                                        | All            | Not planned |

## Transition to Kotlin DSL for Gradle (build.gradle.kts)

As of the latest version, this project has transitioned from using Groovy-based build scripts to
Kotlin DSL (build.gradle.kts) for Gradle. Kotlin DSL offers a more concise and type-safe way to
define your build scripts, enhancing readability and maintainability.

## Contributing

Contributions are welcome! If you have suggestions, bug reports, or want to add new features, feel
free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
