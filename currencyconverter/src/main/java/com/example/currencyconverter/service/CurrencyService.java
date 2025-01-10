package com.example.currencyconverter.service;  

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.currencyconverter.model.ConversionRequest;  

@Service  
public class CurrencyService {  
    private final String API_URL = "https://api.currencylayer.com/live?access_key=3c48602c41f8e187d6ee70491b580951";

    @Value("${api.key}") // From application.properties  
    private String apiKey;  

    public Map<String, Object> getExchangeRates(String base) {  
        RestTemplate restTemplate = new RestTemplate();  
        //String url = API_URL + "?access_key=" + apiKey + "&base=" + base; // Include access key  
//        return restTemplate.getForObject(url, Map.class);  
        return restTemplate.getForObject(API_URL, Map.class);  
    }  

    public double convertCurrency(ConversionRequest request) {  
        Map<String, Object> rates = getExchangeRates(request.getFrom());  
        Map<String, Object> exchangeRates = (Map<String, Object>) rates.get("quotes");  

        if (exchangeRates == null) {  
            throw new RuntimeException("No exchange rates available. Please check the API response.");  
        }  

        Double rate = (Double) exchangeRates.get("USD" + request.getTo()); // Ensure currency codes are correct  
        if (rate == null) {  
            throw new RuntimeException("Invalid currency code: " + request.getTo());  
        }  

        return request.getAmount() * rate;  
    }  
}