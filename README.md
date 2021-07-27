# CurrencyConverter

This REST API localhost Spring boot application allows you to plan your trip across Bulgaria, Greece, North Macedonia, Turkey and Romania. (Bulgaria's neighbors, worldwide support to be added soon)

It gets currency rates from the following Currency Exchange Rates API https://exchangerate.host/#/#docs.

To run the app, all you need to do is start it from your IDE of choice.

Endpoint is configured as follows:
*parameters marked in {} are specified by the end user
http://localhost:8080/trip/{starting country}/{budget per country}/{total budget}/{Initial currency}

![alt text](file://localhost/C:/Users/Mihael/Desktop/Capture.jpg?raw=true)

The application looks for all countries in the region, takes the budget per country and converts it to the local currency.
