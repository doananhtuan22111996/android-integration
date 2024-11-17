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
- **ViewModel**: Utilizes ViewModel to store and manage UI-related data in a lifecycle-conscious
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

## Modularization in Android

Modularization is a design pattern in Android development that involves dividing an application into
smaller, self-contained modules. This approach improves maintainability, scalability, and testing by
promoting separation of concerns and reusability. Each module can be developed, tested, and built
independently, making it easier to manage and understand the application as it grows.

### Benefits of Modularization

- **Separation of Concerns**: Each module focuses on a specific feature or functionality, reducing
  complexity and making it easier to manage.
- **Improved Build Times**: Changes in one module do not require a complete rebuild of the entire
  application, leading to faster build times.
- **Enhanced Reusability**: Modules can be reused across different projects or within different
  parts of the same project.
- **Better Testing**: Modules can be tested independently, which improves the quality and
  reliability of the application.

### Example of Modularization

![Modularization Pattern in Android](https://developer.android.com/topic/modularization)

*Image Source: [Example Modularization Pattern](https://developer.android.com/topic/modularization)*

For more information on modularization in Android, you can refer to the following resources:

- [Modularization in Android by Google](https://developer.android.com/topic/modularization)
- [Effective Android Modularization by Medium](https://medium.com/@nachare.reena8/android-app-modularization-and-adavantages-21d6755dd404)

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

1. **Request Access and Create Personal Access Token:**
    - Request access to the following repositories:
        - [android-corex](https://github.com/doananhtuan22111996/android-corex)
        - [android-components](https://github.com/doananhtuan22111996/android-components)
        - [android-versions](https://github.com/doananhtuan22111996/android-versions)
    - Create a personal access token on GitHub with the required permissions.

2. **Clone the Repository:**
   ```bash
   git clone https://github.com/doananhtuan22111996/android_architecture.git
   ```

3. **Open the Project:**
   Open the project in Android Studio.

4. **Configure Local Properties:**
   Add your GitHub username and personal access token to the `local.properties` file:
   ```properties
   GH_USERNAME=your_github_username
   GH_TOKEN=your_personal_access_token
   ```

5. **Create Keystore Files:**
   Create two keystore files: `root-keystore-dev.jks` and `root-keystore-prod.jks` (or any names you
   prefer). Ensure you update the names/paths in `gradlew` accordingly.

6. **Set Up Firebase Projects:**
   Set up Firebase projects for development and production environments for MDC-Android. This step
   is not required for Jetpack Compose.

7. **Build and Run the Project:**
   Build and run the project on an emulator or physical device.

## Requirements

- Android Studio Arctic Fox (2020.3.1) or later
- Android SDK version 26 (Lollipop) or higher
- JDK 8 or higher

## Implementation

| Feature                                                                | Implementation | Status     |
|------------------------------------------------------------------------|----------------|------------|
| MDC-Android                                                            |                | Available  |
| Jetpack Compose                                                        |                | Available  |
| [Flutter](https://github.com/doananhtuan22111996/flutter_architecture) |                | Available  |
| Unit Testing                                                           | All            | Processing |
| Android Testing                                                        | All            | Planned    |

## Transition to Kotlin DSL for Gradle (build.gradle.kts)

As of the latest version, this project has transitioned from using Groovy-based build scripts to
Kotlin DSL (build.gradle.kts) for Gradle. Kotlin DSL offers a more concise and type-safe way to
define your build scripts, enhancing readability and maintainability.

## Contributing

Contributions are welcome! If you have suggestions, bug reports, or want to add new features, feel
free to open an issue or submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

```

Feel free to adjust any specifics as needed!
