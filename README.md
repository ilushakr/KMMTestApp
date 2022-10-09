# KMMTestApp

Kotlin multi platform app for fetching remote data save it to local database and show

# Techs
- Koin 
- Ktor
- SQLDelight

Whole logic hides inside shared modules and observes in android/ios app as state in viewmodels

# To run app on iphone: 
1) build the project
2) run ./gradlew :sharedmain:assembleXCFramework
3) open xcode with this project and go to project preferences -> General -> Frameworks, Libraries, and Embeded Content
4) find generated on step 2 framework at project_root/sharedmain/build/XCFrameworks/debug or release/ and add it to xcode

now u can use both modules in the ios app

# Screenshots

Just dummy screen with list of users)

<img height="358" alt="image" src="https://user-images.githubusercontent.com/37439482/194768373-e8a9f5d3-28ba-4ad4-afa2-04c703c308b7.png">
<img height="358" alt="image" src="https://user-images.githubusercontent.com/37439482/194768247-f8434202-d94c-4f61-abe5-2d6b89ef687b.png">

