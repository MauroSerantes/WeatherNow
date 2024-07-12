# WeatherNow

_This is an application for get the weather forecast information of your current location. It consumes information
from Weather Forecast Api of open-meteo.com_

## About the app 📋

The application is single activity because it was design using Jetpack Compose for the UI.

There are four principal screens, in wichs you can navigate using navigation component.

The main screen shows a card with the principal information of the weahter at the current 
moment, suchs as the temperature , wind pressure , humidity, wind speed and a chart of the evolution
of the temperature at every hour. Then it shows a display of the weather code images.

The second screen shows the basic weather information of the next week in the same location. It is a summary
with the minimun and maximun temperature and the weather code of the day.

I recently added the search screen and the other places weather screen.Now you can search by 
text differents cities  and check its current and weekly weather.

The app is prepared to show some errors messages when somethig goes wrong, like network connectivity,
location premissions not granted or the gps is not activated.

Also, the application can display hourly weather notifications of the last location obtained by Gps.

#### Here some video of the Weahter Now app remaster






## Tech Stack Used And Architectural pattern ⚙️
* Jetpack Compose - For the views
* Navigation Component with Compose- For simple navigation between screens
* MVVM(Model-View-ViewModel) - Main Architecture pattern
* Dagger HILT - For dependency injection
* Kotlin - The main language
* Simple Cache for Weahter information
* Room - For local data storage
* Retrofit - For easy consume of RESTful APIs in Android application.
* Fused Location Provider
* Permissions Handler
* Notifications Manager

## Authors ✒️

* **Mauro Serantes** - [Mauro Serantes](https://github.com/MauroSerantes)



