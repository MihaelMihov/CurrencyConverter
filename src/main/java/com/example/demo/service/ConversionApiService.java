package com.example.demo.service;

import com.example.demo.handler.ApiHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConversionApiService implements ApiHandler {

    private static final String API_URL = "https://api.exchangerate.host";

    @Autowired
    private CountryService countryService;

    @Override
    public String getAPIResponseConversion(String from, String to, Double amount) {

        try {
            String urlConversion = API_URL + "/convert?from=" + from.toUpperCase() + "&to="
                    + to.toUpperCase() + "&amount=" + amount + "&places=2";

            URL obj = new URL(urlConversion);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } catch (UnknownHostException uhe) {
            System.out.println("No connection!");
        } catch (IOException ignored) {
        }

        return from + to + amount;
    }

    @Override
    public String[] extractConversionData(Object from, Object to, Object amount) {

        String jsonRespBlock;
        jsonRespBlock = getAPIResponseConversion((String) from, (String) to, (double) amount);

        try {
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(jsonRespBlock);

            assert json != null;

            double result = json.getAsJsonObject().getAsJsonPrimitive("result").getAsDouble();
            return new String[]{String.valueOf(result)};
        } catch (Exception exc) {
            //ignore
        }

        jsonRespBlock = "No data";
        return jsonRespBlock.split(",");
    }

    public int calculateTrips(String startCountry, double budgTotal, double budgPerCountry) {
        int neighboursCount = this.countryService.findCurrencies(startCountry).size();

        if ((budgTotal / (budgPerCountry * neighboursCount) > 0)) {
            return (int) (budgTotal / (budgPerCountry * neighboursCount));
        } else {
            return 0;
        }
    }

    public int calculateLeftOver(String startCountry, double budgTotal, double budgPerCountry, int tripsCount) {
        int neighboursCount = this.countryService.findCurrencies(startCountry).size();
        return (int) (budgTotal - (budgPerCountry * tripsCount * (neighboursCount)));
    }

    public List<String> getExchangeRates(String startCountry, String inputCurrency, double amount) {

        List<String> output = new ArrayList<>();
        List<String[]> currencyList = this.countryService.findCurrencies(startCountry);


        for (String[] s : currencyList) {
            String prompt = "Required budget in " + s[1] + " is: ";
            output.add(prompt + extractConversionData(inputCurrency, s[1], amount)[0]);
        }
        return output;
    }

    public List<String> getNeighbors(String startCountry) {

        List<String> neighborsList = this.countryService.findCountries(startCountry);
        return neighborsList;
    }

    public String formatOutput(String startCountry, Double budgPerCountry,
                               Double budgTotal, String currency) {

        int tripsCount = calculateTrips(startCountry, budgTotal, budgPerCountry);
        int leftOver = 0;
        if (tripsCount > 0) {
            leftOver = calculateLeftOver(startCountry, budgTotal, budgPerCountry, tripsCount);
        }

        List<String> neighbors = getNeighbors(startCountry);

        if (tripsCount > 0) {

            List<String> exchangeRates = getExchangeRates(startCountry, currency, tripsCount * budgPerCountry);

            return "Starting country: " + startCountry + "</br>Budget per country: " + budgPerCountry +
                    "<br/>Total budget: " + budgTotal + "<br/>Starting currency: " + currency
                    + "<br/>Number of round trips: " + tripsCount +
                    "<br/>Number of neighbors: " + neighbors.size() +
                    "<br/>Total budget per country: " + (tripsCount * budgPerCountry) + " " + currency +
                    "<br/>Full travel cost (Budget per country * Number of neighbors): " + (tripsCount * budgPerCountry * neighbors.size()) + " " + currency
                    + "<br/>Leftover is: " + leftOver + " " + currency
                    + "<br/><br/> Neighbor countries are: " + neighbors
                    + "<br/><br/>" + exchangeRates;

        } else {
            return "Starting country: " + startCountry + "</br>Budget per country: " + budgPerCountry +
                    "<br/>Total budget: " + budgTotal + "<br/>Starting currency: " + currency
                    + "<br/>Number of neighbors: " + neighbors.size()
                    +"<br/><br/>Neighbor countries are: " + neighbors
                    + "<br/><br/>Not enough Money for this trip";
        }
    }
}