# ISE Proximity Tracking Android App Development Guide

Development guide for building a proximity tracking application using Java and Android Studio for the University of Illinois Urbana-Champaign Industrial and Systems Engineering department. The maintenance and proximity sensing (MAPS) project a tool to be developed that continuously throughout the day tracks the proximity of two subjects in an indoor space and reports the data to the research team. The current solution using custom hardware is expensive and infeasible due to the COVID-19 pandemic. This solution would allow for design, development, and deployment in a more efficient, cost effective and safe manner.

Common Acronyms:
* BLE: Bluetooth Low Energy
* RSSI: Recieved Signal Strength


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

## BLE Overview
Bluetooth Low Energy is an Android service that uses bluetooth to transfer small amounts of data in a significantly energy saving manner compared to classic bluetooth. In the context of developing the ISE Proximity Tracking app its Central vs. Peripheral allows for the transfer of information like recieved signal strength at high frequencies with low energy consumption. This makes it ideal for approximating proximity at very short intervals. 

For developing the proximity capabilities the devices need to commuincate and the RSSI must be recorded. The RSSI indicates how strong the connection is between the two devices which can be used to approximate proximity. Intuitively the closer two devices are the stronger their connection. The further they are apart the weaker the connection. This is experienced in everyday life using headphones with mobile devices, or any bluetooth device. This can be utilized to approximate the distance between the two subjects.

By implementing a server and client service in the background of the app, continous connections can be made and proximity can be approximated.

In a traditional device relationship the Android application would be the client and the device it is connecting to (watch, heart rate monitor, headphone, etc.) would be the server. In the context of tracking proximity between the two devices selection must take place for server vs client. 

## Server
The server will advertise its availability to the client over a UUID and wait for a connection before sharing requested materials. This means that the server device will always be open for connection and ready to share information. One user will be using the app as the server and will be waiting for periodic connections to the client to share RSS infromation.

### Server Implementation
```java
import android.app.Service;
//Service allows for the server to run in the background of the application
public class ProximityServer extends Service {
    //can be permenantely running in the background
}
```
implementation of server, supportive materials

## Client
The client will be the other device which is actively searching to make a connetion with a server device and request/recieve information regarding RSS. The device that is selected to be the client will follow a cycle of seraching for the server, requesting/recieving RSSI if connected and restarting the cycle for the next iteration. The client can then approximate proximity and share data.

### Client Implementation
implementation of client, supportive materials

## Background Service
The connection and retreval of RSSI data between the two applications is a task that must be performed a few times a minute, whether the user is or isn't directly on the application. To implement the capability to constantly check for proximity between the two devices and report the data a background service must be added to the application so that the scanning process of the client and constant adverstising process of the server are never stopped unless the application is terminated completely by the user.

### Background Service Implementation

How to implement

## Approximating Proximity with RSS Information
Once the client, server, and background service are all tied together for communication and connection between the two devices the retrieved RSSI value can then be processed to approximate proximity. Many IOT reserachers and academics have developed different formulas for leveraging RSSI to approximate the distance between two devices, but before implementing these approximations there are a few key parameters to understand. 

Typically an RSSI value of -26 would indicate the devices just inches apart and an RSSI value of -100 would indicate they are over 40 meters apart. Using the paramters and formula below RSSI can be used to estimate the meters between the two devices.

* RSSI: Recieved Signal Strength Indicator [-100,-30] dbm
* Measured Power: Approximation of 1 meter RSSI (-69 dbm)
* N: Constant from [2,4] indicating the environmental range factor

RSSI Formula

This formula on every scan will use the retrieved RSSI value and convert that signal strength to meters. 

The MAPS reserach team specified to categorize the proximity metrics and report the specific category in the data. Using the retreived RSSI the calculated proximity can be placed in one of three categories and recorded in the data.

Proximity Categories: 
* In the Same Room (1): 0-3.5 m
* In the Same Household but NOT in the Same Room (2): 3.5-10 m
* Not in the Same Household (3):  >10 m 

Link to Reserach
### Proximity Approximation Implementation
```
/*
Returns an approximation of the proximity between the two devices in one of three categories.
DEFINE THRESHOLD CONSTANTS for CATEGORZING:
  double PROXIMITY_LB = 0.0;
  double PROXIMITY_MB = 3.5;
  double PROXIMITY_UB = 10.0;
  if the two devices are in the same room...
    Same Room: [PROXIMITY_LB,PROXIMITY_MB] -> return 1
  if the two devices are in different rooms...
    Different Room: [PROXIMITY_MB,PROXIMITY_UB] -> return 2
  if the two devices are not in the same household...
    Not in Same Household: > PROXIMITY_UB -> return 3
@param rssi the retreived signal strength value from the two devices
@param n the constant indicating environmental range between [2,4]
@param mp the measured power value that approximates 1 meter (typically -69.0 dbm)
@return the integer representing categorized proximity
*/

public int approximate_proximity(double rssi, int n, double mp) {
  double estimated_proximity = Math.pow(10,((mp-rssi)/(10.0*double(n))))
  if (estimated_proximity > PROXIMITY_UB) {
    return 3;
  } else if (estimated_proximity > PROXIMITY_MB && estimated_proximity <= PROXIMITY_UB) {
    return 2;
  } else if (estimated_proximity >= PROXIMITY_LB && estimated_proximity <= PROXIMITY_MB) {
    return 1;
  } else {
    return -1;
  }
}
```

## All Together
How does it all work together? Flow chart? 

## Overview of Hardware
Certain older Android devices do not support both advertising and scanning for BLE. BLE as stated above is more of an efficient practice for cokllecting RSS packets. Older devices that do not support BLE would require a classic bluetooth server/client implementation to collect RSS data. The implementation therefore would be different for the scanning and advertising, but calculations related to estimating proximity would remain the same. A classic bluetooth implementation would use significantly more battery, and therefore not be recommended as this app is designed to work throughout the day as the subject moves around. Purchasing and deplpoying on newer models is recommended for this reason. Any device with bluetooth 5 should suffice.

## Data Collection
After proximity is estimated and categorized two key pieces should be saved in the apps internal data, and later exported as CSV for the research team. The timestamp of when the proximity was recorded and the integer value specifying which category the proximity value belongs to. This can be implemented by storing the data into an array initiaized in the MainActivity, and writing the structure to a CSV at the end of the collection period. 




## Data Sharing (Email)
Exporting the recorded data as a CSV and sharing it via an email account specified by the team is the preffered method of sharing data with the MAPS team. 
how the team wants data shared, efficient ways to manage daata, possible solutions (CSV,google,db) algos

## Implementing a Simple User Interface for Key Functionality
This applications mainly performs background tasks (client-server relationship, proximity approximation, data sharing) which don't require a lot of input from the users. Therefore a simple user-interface will suffice in building and deploying the application. There are a few key requirements in the user interace listed below.

* Toggle for Selecting Server vs Client
* Data Export and Sharing
* Failure to Connect Selection


Backend Implementation
## Robustness

### Device Dying During Scan Cycles
THere is a possibility of the user's device dying during the scan cycle which would trigger the onDestroy() function in the different features, similar to if the application was manually destroyed. However, in this case it was unintentionally and the data for the day is still being collected. When an Android devices battery reaches 0.5% the onDestroy() function is called, therefore a features should be implemented in the onDestroy function to automatically share current data. If the app is being initiated in a time of day where data was already shared then it should pick up from where it left off, and if not then it should start a new day of proximity sensing for cycles. Indicators can be used to sense low battery in advance and prevent against that. 

### Failure to Connect
If the devices fail to connect over UUID a self selection can be used to identify the server device. This would basically allow for hte user to connect to a device and remember the server device for futher connection. This would specifically be used if the UUID is not functioning correctly in identifying a device.


## Conclusion
The implementation for the MAPS Proxmity Tracking application should be constructed as outlined above. This guide can serve as a useful tool for developers tackling this project. Deviations of course will be made in implementing the application for other devices, but the basic outline of background services, BLE server/client communication, and RSSI based proximity estimation should be used.
