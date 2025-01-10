package com.example.currencyconverter;  

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import com.example.currencyconverter.model.ConversionRequest;
import com.example.currencyconverter.service.CurrencyService;  

public class CurrencyServiceTest {  

    @Test  
    public void testConvertCurrency() {  
        CurrencyService currencyService = new CurrencyService();  
        ConversionRequest request = new ConversionRequest();  
        request.setFrom("USD");  
        request.setTo("EUR");  
        request.setAmount(100);  

        // Mock the response from the API  
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);  
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Map.class)))  
                .thenReturn(Map.of("rates", Map.of("EUR", 0.945)));  

        double result = currencyService.convertCurrency(request);  
        assertEquals(94.5, result);  
    }  
}