# PublicPhoto
PublicPhoto is a library which enables you to fetch user's public photo as published on Gravatar.  If no image is published against the given email, then you can fetch a palceholder instead (similar to Github &amp; Stackoverflow).

# How to use:
In your Settings.gradle.kts, add:

        maven { url = uri("https://jitpack.io") }
and in your app level build.gradle.kts, add:

        implementation("com.github.TusharGogna:PublicPhoto:LATEST_VERSION")

Current version is **1.0**

Then, in order to fetch the public Gravatar photo, simply call the method: **getPublicPhoto(...)** which returns a URL in the form of a String. The method looks something like this:
             
        getPublicPhoto(EMAIL (String), IMAGE_SIZE (Int), IS_DEFAULT (Boolean) , DEFAULT_IMAGE_TYPE (ENUM of Type PlaceHolderType))
Example:

      getPublicPhoto("test@github.com", 400, true, PlaceHolderType.ROBOHASH)

  <img src="https://github.com/TusharGogna/PublicPhoto/assets/36148180/728dd315-a1bb-4967-94c2-ac85e63a2608" width=50% height=50%>


Similarly, you can use different PlaceHolderType as per your needs. Following are the different types available:
    
    MP
    IDENTICON
    MONSTERID
    WAVATAR
    RETRO
    ROBOHASH
    BLANK

  <img width="865" alt="Screenshot 2023-12-11 at 11 24 11 PM" src="https://github.com/TusharGogna/PublicPhoto/assets/36148180/590c9510-5480-4339-8f19-613c4b9778fe">
