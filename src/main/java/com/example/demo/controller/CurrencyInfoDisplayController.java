package com.example.demo.controller;

import com.example.demo.service.ConversionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class CurrencyInfoDisplayController {

    private ConversionApiService service;
    private String[] exchangeResults;
    private String template;

    @Autowired
    public CurrencyInfoDisplayController(final ConversionApiService service) {
        this.service = service;
    }

    @GetMapping("/exchange")
    public String exchangeRate(@RequestParam(value = "from", defaultValue = "EUR") String from,
                               @RequestParam(value = "to", defaultValue = "BGN") String to,
                               @RequestParam(value = "amount", defaultValue = "1") double amount) {

        exchangeResults = this.service.extractConversionData(from, to, amount);
        template = "Exchange rate of %s %s to %s: ";

        if (exchangeResults.length == 0) {
            return "error";
        }

        return String.format(template, amount, from, to) + exchangeResults[0] + " " + to;
    }

    @GetMapping("/trip/{startCountry}/{budgPerCountry}/{budgTotal}/{currency}")
    @ResponseBody
    public String tripDetails(@PathVariable String startCountry, @PathVariable Double budgPerCountry,
                              @PathVariable Double budgTotal, @PathVariable String currency) {
        return this.service.formatOutput(startCountry,budgPerCountry,
                budgTotal,currency);
    }
}
