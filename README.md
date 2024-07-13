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

#### Here some screenshots of the app

![Screenshot_20240712_230148](https://github.com/user-attachments/assets/79eed13c-3a88-4d33-95ae-1ea53d67f348)
![Screenshot_20240712_230225](https://github.com/user-attachments/assets/044ee45f-43fd-4149-9cf3-d56dc879141f)
![Screenshot_20240712_230731](https://github.com/user-attachments/assets/997996ac-34a7-4f7c-8949-b3e0698c15db)
![Screenshot_20240712_230625](https://github.com/user-attachments/assets/4d238397-28b0-451b-83ce-c51905cc36ad)
![Screenshot_20240712_230703](https://github.com/user-attachments/assets/b09f44fa-c775-4d60-b5a2-19ecb4794dc6)
![Screenshot_20240712_230807](https://github.com/user-attachments/assets/3c0d0cb9-2a74-4f46-bf1a-0a8b78264ef6)
![Screenshot_20240712_230910](https://github.com/user-attachments/assets/b437d3eb-6ec7-495b-bfaf-6b13dfbe2b36)
![Screenshot_20240712_231320](https://github.com/user-attachments/assets/aef709c4-3905-49d6-afa4-f4233a3f1267)


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



