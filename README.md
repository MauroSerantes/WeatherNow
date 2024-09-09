# WeatherNow

_A simple, efficient, and visually appealing weather app built with Jetpack Compose. Get real-time weather updates and forecasts for your current location or any city you search for, powered by Open-Meteo API._

## About the app 📋

WeatherNow is a weather forecast app designed to provide current weather conditions and forecasts for the next week. It uses the Open-Meteo API to deliver accurate weather data based on your location or a city you search for.

The app is built with a **single activity architecture** using **Jetpack Compose**, and follows the **MVVM pattern** to maintain clean separation of concerns and reusability. It also includes caching mechanisms for offline access.

### Key Features:
- **Real-time weather data**: Get live updates on temperature, wind speed, pressure, and more.
- **7-day forecast**: View detailed weather predictions for the coming week.
- **Search for any city**: Find weather information for any location by name.
- **Error handling**: Friendly messages are displayed for network issues or location permission errors.
- **Weather notifications**: Receive hourly notifications based on the last retrieved location.
- **Offline access**: Cached weather data ensures the app works even without a network connection.

## Screenshots 📱

| Current Weather | Weekly Forecast | City Search | Notifications |
| --------------- | --------------- | ----------- | ------------- |
| ![Current Weather](https://github.com/user-attachments/assets/79eed13c-3a88-4d33-95ae-1ea53d67f348) | ![Weekly Forecast](https://github.com/user-attachments/assets/044ee45f-43fd-4149-9cf3-d56dc879141f) | ![City Search](https://github.com/user-attachments/assets/997996ac-34a7-4f7c-8949-b3e0698c15db) | ![Notifications](https://github.com/user-attachments/assets/4d238397-28b0-451b-83ce-c51905cc36ad) |



Para mejorar el README de tu proyecto WeatherNow, te recomiendo seguir una estructura clara, concisa y profesional que no solo describa las funcionalidades, sino que también sea atractiva para otros desarrolladores o potenciales colaboradores. Aquí hay algunas sugerencias:

1. Título y Descripción Breve
Usa un título destacado y una descripción concisa que capte el propósito de la app en pocas líneas.
markdown
Copiar código
# WeatherNow 🌤️
A simple, efficient, and visually appealing weather app built with Jetpack Compose. Get real-time weather updates and forecasts for your current location or any city you search for, powered by Open-Meteo API.
2. Badges (Opcional)
Puedes incluir badges para mostrar información relevante, como el estado del build, licencia, versiones, etc.

markdown
Copiar código
![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Platform](https://img.shields.io/badge/platform-android-blue)
3. Índice
Agrega un índice si el README es largo, esto ayuda a la navegación.

markdown
Copiar código
## Table of Contents
1. [About the App](#about-the-app)
2. [Screenshots](#screenshots)
3. [Features](#features)
4. [Tech Stack](#tech-stack)
5. [Installation](#installation)
6. [Usage](#usage)
7. [Authors](#authors)
8. [License](#license)
4. About the App (Más Detallado)
Aquí debes ser más descriptivo, pero claro. Enfatiza el objetivo de la app y sus características clave.

markdown
Copiar código
## About the App 📋
WeatherNow is a weather forecast app designed to provide current weather conditions and forecasts for the next week. It uses the Open-Meteo API to deliver accurate weather data based on your location or a city you search for.

The app is built with a **single activity architecture** using **Jetpack Compose**, and follows the **MVVM pattern** to maintain clean separation of concerns and reusability. It also includes caching mechanisms for offline access.

### Key Features:
- **Real-time weather data**: Get live updates on temperature, wind speed, pressure, and more.
- **7-day forecast**: View detailed weather predictions for the coming week.
- **Search for any city**: Find weather information for any location by name.
- **Error handling**: Friendly messages are displayed for network issues or location permission errors.
- **Weather notifications**: Receive hourly notifications based on the last retrieved location.
- **Offline access**: Cached weather data ensures the app works even without a network connection.
5. Screenshots
Asegúrate de que las imágenes sean claras y relevantes, y agrégales una breve descripción.

markdown
Copiar código
## Screenshots 📱

| Current Weather | Weekly Forecast | City Search | Notifications |
| --------------- | --------------- | ----------- | ------------- |
| ![Current Weather](https://github.com/user-attachments/assets/79eed13c-3a88-4d33-95ae-1ea53d67f348) | ![Weekly Forecast](https://github.com/user-attachments/assets/044ee45f-43fd-4149-9cf3-d56dc879141f) | ![City Search](https://github.com/user-attachments/assets/997996ac-34a7-4f7c-8949-b3e0698c15db) | ![Notifications](https://github.com/user-attachments/assets/4d238397-28b0-451b-83ce-c51905cc36ad) |

Copiar código
## Features ✨
- 🌡️ **Current Weather**: Displays temperature, wind speed, humidity, pressure.
- 📅 **7-Day Forecast**: Overview of upcoming weather conditions.
- 🔍 **City Search**: Find weather details by searching for different cities.
- 🔔 **Notifications**: Weather alerts and hourly notifications for your location.
- 🛠️ **Offline Access**: Cached weather data for use without an internet connection.
- 🚀 **Performance**: Optimized for smooth navigation and quick data retrieval.

Copiar código
## Tech Stack Used ⚙️
- **Kotlin** - The main language for development.
- **Jetpack Compose** - UI development with modern declarative approach.
- **Navigation Component** - For seamless navigation between screens.
- **MVVM Architecture** - For separation of concerns.
- **Dagger HILT** - For Dependency Injection.
- **Retrofit** - To consume RESTful APIs.
- **Room** - Local database to store weather data.
- **Fused Location Provider** - To obtain user's location.
- **Notifications Manager** - For creating weather alerts.
- **Simple Cache** - To handle offline data.

## Authors ✒️

* **Mauro Serantes** - [Mauro Serantes](https://github.com/MauroSerantes)



