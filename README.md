# WeatherNow

_This is an application for get the weather forecast information of your current location. It consumes information
from Weather Forecast Api of open-meteo.com_

## About the app 📋

The application is single activity because it was design using Jetpack Compose for the UI.

There are two principal screens, in wichs you can navigate using navigation component.

The main screen shows a card with the principal information of the weahter at the current 
moment, suchs as the temperature , wind pressure , humidity, wind speed and a chart of the evolution
of the temperature at every hour. Then it shows a display of the weather code images.

The second principal screen shows a the basic weather information of the next week. It is a summary
with the minimun and maximun temperature and the weather code of the day.

The app is prepared to show some errors messages when somethig goes wrong, like network connectivity,
location premissions not granted or the gps is not activated.

#### Here some screeshots of the main app presentation

```
Main Screen
```

![Screenshot_20240312_105603_WeatherNow](https://github.com/MauroSerantes/WeatherNow/assets/146656323/371470dc-a03b-4b04-90a0-ad8591527928)
![Screenshot_20240312_105603_WeatherNow](https://github.com/MauroSerantes/WeatherNow/assets/146656323/0e091bbe-4a7e-4065-a2ce-c1c3a142761c)

```
Second Screen
```

![Screenshot_20240312_105716_WeatherNow](https://github.com/MauroSerantes/WeatherNow/assets/146656323/6385112a-2231-4b0f-ae27-c683b6537f5b)
![Screenshot_20240312_105726_WeatherNow](https://github.com/MauroSerantes/WeatherNow/assets/146656323/669596d4-e024-44ff-b921-d354f3fa1b99)


```
Error Display
```
![Screenshot_20240312_105756_WeatherNow](https://github.com/MauroSerantes/WeatherNow/assets/146656323/a2bd6a13-ded8-4e33-b484-ea300d603065)
![Screenshot_20240312_105811_WeatherNow](https://github.com/MauroSerantes/WeatherNow/assets/146656323/d5d6c895-38de-45c6-a71e-0d157c530665)


## Tech Stack Used And Architectural pattern ⚙️
* Jetpack Compose - For the views
* Navigation Component with Compose- For simple navigation between screens
* MVVM(Model-View-ViewModel) - Main Architecture pattern
* Dagger HILT - For dependency injection
* Kotlin - The main language
* Simple Cache for Weahter information 
* Retrofit - For easy consume of RESTful APIs in Android application.
* Fused Location Provider
* Permissions Handler


### Personal Commentaries
_WeatherNow is an unfinish application. It was a good way to see the power of Jetpack Compose in UI desing and implementation.
The two last things to finish the app are notifications for the weather every day and a map for know the weather in 
other places, cities or countries. I am learning about this things in Jetpack Compose_ 

## Authors ✒️

* **Mauro Serantes** - [Mauro Serantes](https://github.com/MauroSerantes)



