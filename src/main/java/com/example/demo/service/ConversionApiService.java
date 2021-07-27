package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;

import com.example.demo.handler.ApiHandler;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class ConversionApiService implements ApiHandler {

    private static final String API_URL = "https://api.exchangerate.host";
    private static final String[] CURRENCIES = {"BGN","EUR","RON","TRY","MKD"};


    @Override
    public String getAPIResponseConversion(String from, String to, Double amount) {

        try {
            String urlConversion = API_URL + "/convert?from=" + from.toUpperCase() + "&to="
                    + to.toUpperCase() + "&amount=" + String.valueOf(amount) + "&places=2";

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

        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(jsonRespBlock);

        assert json != null;
        double result = json.getAsJsonObject().getAsJsonPrimitive("result").getAsDouble();

        return new String[] {String.valueOf(result)};
    }

    public int calculateTrips(double budgTotal, double budgPerCountry){
        int neighboursCount =5;
        return (int) (budgTotal / (budgPerCountry * neighboursCount));
    }

    public int calculateLeftOver(double budgTotal,double budgPerCountry, int tripsCount){
        int neighboursCount=5;
        return (int) (budgTotal % (neighboursCount * budgPerCountry * tripsCount));
    }

    public String[] getExchangeRates(String inputCurrency, double amount){

        String[] output = new String[5];
        HashMap<String,String> currencyNames = new HashMap<>();
        currencyNames.put("EUR","Euro");
        currencyNames.put("TRY","Turkish Lira");
        currencyNames.put("MKD","Macedonian Denar");
        currencyNames.put("BGN","Leva");
        currencyNames.put("RON","Romanian Leu");

        for (int i =0 ; i < CURRENCIES.length ; i++) {
            String prompt = "Required budget in " + currencyNames.get(CURRENCIES[i])  + " is: ";
            output[i] = prompt + extractConversionData(inputCurrency, CURRENCIES[i], amount)[0];
        }

        return output;
    }
}
