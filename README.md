# CurrencyConverter

This REST API localhost Spring boot application allows you to plan your trip across the world. :)

The app relies on H2 embedded database

The trip planner automatically finds your neighbors (Based on the Starting Country parameter) and it calculates the budget needed for each of your travels converted to the local currency of your neughbors.

This app uses currency exchange rates from the following API https://exchangerate.host/#/#docs.

To run the app, all you need to do is start it from your IDE of choice.

You can also run it by opening command prompt in the folder where your pom.xml file is located.

Once you open command promt please type: mvn spring-boot:run

Endpoint is configured as follows:
*parameters marked in {} are specified by the end user
http://localhost:8080/trip/{ Starting country}/{Budget per country}/{Total budget}/{Input Currency}

The number of round trips is calculated first: Round trips = TotalBudget / neighbors count (Currently neighbors count is 5)

Total budget per country is determined according to the following formula: Total budget per country = (round trips * budget per country)

If there is leftover, it is always returned in the original currency.

Once total budget per country is calculated , it is the external API's job to convert this budget into the local currency of each neighbor.

You can also see the total number of your neighbors and the Grand total for the entire trip

http://localhost:8080/trip/Turkey/100/1200/EUR

Before getting the result, you will be asked to login. Please use the following credentials:

Username: root
Password: root

See example output below: 
![alt text](https://github.com/MihaelMihov/CurrencyConverter/blob/master/src/main/Capture.JPG)

Results are calculated as per the formulas above. 

You can experiment by changing the input currency, total budget, budget per country etc.

If your money is not enough for a full trip, you will receive the output below:

http://localhost:8080/trip/Turkey/500/1200/EUR

![alt text](https://github.com/MihaelMihov/CurrencyConverter/blob/master/src/main/Capture2.JPG)

Lastly, if a particular currency doesn't have exchange rate listed, you will see "No data". For Example:

http://localhost:8080/trip/Russia/500/8000/EUR

![alt text](https://github.com/MihaelMihov/CurrencyConverter/blob/master/src/main/Capture3.JPG)

I hope you enjoyusing this app.
