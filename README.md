# RxJava With Retrofit Demo
This project describes Api integration with RxJava using retrofit.

You need to create **ApiService class** which includes methods for APIs and BaseUrl of the APIs. Method in this class is created with annotation so that can be used with Retrofit. You can create method with annotation in following way,

    @GET("title/ipsum/content/blah")
    Maybe<ResponseBody> getRetroRxTest();
    
In above method i have used **RxJava Maybe observable** which is used to give us the result directly. **ResponseBody in Maybe Observable** is used when you want to get **String response (like JSON)**. If you don't want to parse JSON than you can just provide **pojo/model** class to any observable.

In this demo i have also mentioned two type for subscribing the observable.<br/>
    1. Using **Lambda** (Read below to know more about using lambda expressions in Android Studio).<br/>
    2. Using **Anonymous class** or **subscribing Observer**.
    
### Enable Lambda expression or Java 8 Features in Android studio.
To enable Java 8 Features in android studio project you need to add following things in your **app modules build.gradle** file,<br/><br/>
```
android {
    defaultConfig {
        //This option is used to enable java 8 features.
        jackOptions {
            enabled true
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```
<br/>
See this demo to know more about Using RxJava with retrofit.
<br/><br/>

**NOTE:** Enabling Java 8 features may disable **INSTANT RUN** for the project.