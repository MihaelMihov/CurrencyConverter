# CurrencyConverter

This REST API localhost Spring boot application allows you to plan your trip across Bulgaria, Greece, North Macedonia, Turkey and Romania. (Bulgaria's neighbors, support for other countries neighbors to be added soon)

It gets currency rates from the following Currency Exchange Rates API https://exchangerate.host/#/#docs.

To run the app, all you need to do is start it from your IDE of choice.

Endpoint is configured as follows:
*parameters marked in {} are specified by the end user
http://localhost:8080/trip/{ starting country}/{budget per country}/{total budget}/{Initial currency}

The number of round trips is calculated first: Round trips = TotalBudget / neighbors count (Currently neighbors count is 5)

Total budget per country is determined according to the following formula: Total budget per country = (round trips * budget per country)

Once total budget per country is calculated , it is the external API's job to convert this budget into the local currency of each neighbor and display the results.

See example output below: (Results are calculated as per the formulas above)

http://localhost:8080/trip/Turkey/100/1200/EUR

![alt text](https://github.com/MihaelMihov/CurrencyConverter/blob/master/src/main/Capture.JPG)
