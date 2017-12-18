# Description
Android application that consumes data from Github API and display them for users

## Screens

- Repositories screen: displays all best ranked repositories in an infinity scroll list. 
You can press any repository to select it and the delete it locally or you can just press 
one repository to open the next screen.

- Pull requests screen: displays all pull requests from specific repository selected on the
previous screen.

## Requirements

- Java 7
- Android Studio
- Android Device or AVD(Android Virtual Device)

## Running

- Download or clone the project: git clone https://github.com/bedrickprokop/githubrepos.git
- Open your Android Studio and point to the project
- Wait until gradle load dependencies
- Choose one flavor(The flavors are described below)
- Plug your android device with the "Developer options" enabled and "USB debugging" checked or
create an AVD(Android Virtual Device)
- Run

## Flavors

I've created two flavors to use while developing and testing. One for test the application with 
mock data and the other to use real data.

- Mock: Open the "Build Variants" side menu and choose in "Build Variant" section the "mockDebug" flavor. 
In this flavor you can test the application without connect to internet just using mock values.

- Prod: Open the "Build Variants" side menu and choose in "Build Variant" section the "prodDebug" or "prodRelease" 
flavors. In these flavors you can run the application in real mode, with real data.

## Screen Tests

- You can run the screen tests by right clicking on the java classes inside the "androidTest" package and
selecting the option "Run ClassTestScreenName".

Note: Unlock your phone for you see the tests running in your phone and use the flavor "mock" for this kind of test

## Unit Tests

- They are in "test" folder and you can run them by right clicking on the test class and selecting 
"Run ClassTestName".

