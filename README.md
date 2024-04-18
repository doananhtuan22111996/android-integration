# Android Architecture Sample Project

This repository contains a sample Android project demonstrating various architectural patterns and best practices. Whether you're a beginner or an experienced Android developer, this project aims to provide insights into structuring your Android applications efficiently.

## Features

- **MVVM Architecture**: The project follows the Model-View-ViewModel (MVVM) architecture, separating concerns between data, UI, and logic.
- **Clean Architecture**: Implements the principles of Clean Architecture to create a highly maintainable and testable codebase with a clear separation of concerns.
- **Data Binding**: Utilizes Android Data Binding Library to bind UI components in layouts to data sources in the app's architecture.
- **Room Database**: Integrates Room Persistence Library to provide an abstraction layer over SQLite, making database interactions smoother and more robust.
- **Retrofit**: Implements Retrofit library for handling network requests and simplifying REST API consumption.
- **LiveData and ViewModel**: Uses LiveData and ViewModel to manage UI-related data in a lifecycle-conscious way and to persist data across configuration changes.
- **Dependency Injection**: Incorporates Dagger Hilt for dependency injection to keep the code modular, maintainable, and testable.
- **Coroutines**: Integrates Kotlin Coroutines for asynchronous programming, making code more concise and readable.
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
- **Dependencies**: Implements interfaces defined in the Domain layer; may depend on external libraries for network/database access.

## Getting Started

To get started with this project, follow these steps:

1. Clone this repository: `git clone https://github.com/doananhtuan22111996/android_architecture.git`
2. Open the project in Android Studio.
3. Build and run the project on an emulator or a physical device.

## Requirements

- Android Studio Arctic Fox (2020.3.1) or later
- Android SDK version 21 (Lollipop) or higher
- JDK 8 or higher

## Transition to Kotlin DSL for Gradle (build.gradle.kts)

As of the latest version, this project has transitioned from using Groovy-based build scripts to Kotlin DSL (build.gradle.kts) for Gradle. Kotlin DSL offers a more concise and type-safe way to define your build scripts, enhancing readability and maintainability.

## Contributing

Contributions are welcome! If you have suggestions, bug reports, or want to add new features, feel free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
