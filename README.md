# PathVision_app

<h2>Current version under development</h2>

PathVision app is a simple REST app created as an example of my Android skills. 

It provides functionalities:
* authentication, 
* searching job paths and required skills from external API (data scrapped from Glassdoor by myself), 
* saving data to internal Room database,
* tracking progress of learning skills.

Used technologies and approaches:
* Firebase authentication
* Firebase database
* Room database
* Kotlin coroutines
* Retrofit
* Koin for dependency injection
* LiveData
* JSON processing with Gson
* MVVM

Future plans:
* Add landscape views
* Add caching
* Add support for TalkBack (accessibility) 
* Cloud messaging (send push notification when a new dataset in API is available)
* Merging data from internal Room database and Firebase database after user authenticates
* Github connection to track repositories used to learning for skills / careers
