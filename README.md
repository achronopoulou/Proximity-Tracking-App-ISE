# ISE Proximity Tracking Android App Development Guide

Development guide for building a proximity tracking application using Java and Android Studio for the University of Illinois Urbana-Champaign Industrial and Systems Engineering department. The maintenance and proximity sensing (MAPS) project a tool to be developed that continuously throughout the day tracks the proximity of two subjects in an indoor space and reports the data to the research team. The current solution using custom hardware is expensive and infeasible due to the COVID-19 pandemic. This solution would allow for design, development, and deployment in a more efficient, cost effective and safe manner.

## Proximity Tracking App Software Design Overview
### Platform and Software Development Tools
Designing this tool as a mobile app is a key part of allowing the research team to deploy the project with ease and scalability. Android appilications have a lower barrier of entry for deployment than IOS applications, which as why Android was choosen as the main platform for building the application. After building and deploying a prototype for Android devices, IOS can be explored by developing in Swift or a cross-platform mobile SDK like Flutter. 

The software development tools that should be used to develop this application are the Java 8 programming language, Android Studio, and APIs inherit to Android such as Bluetooth Low Energy. 

### Software Design Features
Core features that are necessary to successfully developing this application include the following
* BLE Server and Client Relationship
  * Server Design
  * Client Design
  * Connecting with UUID
  * Recieving RSSI
* Constant Streaming
* Approximating Proximity
* Store and Sharing Results

### Similar Implementations
Android App Implementations and toolkits that are similar in design and functionality to the goals of this apolication are the Safer-Illinois COVID Exposure Notification feature and Google Exposure Notification API. Both of these examples are designed specifically for COVID-19 exposure notifications, so they don't connect devices based on UUID (one to one connection) but rather attempt to connect with any advertising device. This means that these implementations can't be used directly for building this application, but are a great reference for understanding how BLE can be leveraged for proximity between devices.
* [Safer Illinois COVID Exposure Notification System](https://github.com/rokwire/safer-illinois-app/tree/develop/android/app/src/main/java/edu/illinois/covid/exposure)
* [Google Exposure Notification API](https://developers.google.com/android/exposure-notifications/exposure-notifications-api)


## Overview of Hardware
Devices Supporting BLE Relationship:
Devices that don't support relationship: extra work

## BLE
Description and why we are using it and how we will use it 
### Server
Desc of server, desc in context of this app, implementation of server, supportive materials

### Client
Desc of client, desc in context of this app, implementation of client, supportive materials

### RSSI
Desc of RSSI, in context to proximity, supportive materials

#### Approximating Proximity
algo and implementation

## Data Collection
collecting and storing data how to and algo 

## Data Sharing (Email)
how the team wants data shared, efficient ways to manage daata, possible solutions (CSV,google,db) algos

## Robustness
ensuring that the app works in different scenarios, solutions and algos

## Conclusion
conclusion aobut how the 
