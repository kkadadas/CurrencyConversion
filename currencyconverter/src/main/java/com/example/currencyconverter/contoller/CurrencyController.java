package com.example.currencyconverter.contoller;  

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.currencyconverter.model.ConversionRequest;
import com.example.currencyconverter.service.CurrencyService;  

@RestController  
@RequestMapping("/api")  
public class CurrencyController {  

    private final CurrencyService currencyService;  

    public CurrencyController(CurrencyService currencyService) {  
        this.currencyService = currencyService;  
    }  

    @GetMapping("/rates")  
    public ResponseEntity<Map<String, Object>> getRates(@RequestParam(defaultValue = "USD") String base) {  
        
            Map<String, Object> rates = currencyService.getExchangeRates(base);  
            return ResponseEntity.ok(rates);  
        
    }  

    @PostMapping("/convert")  
    public ResponseEntity<Map<String, Object>> convertCurrency(@RequestBody ConversionRequest request) {  
        try {  
            double convertedAmount = currencyService.convertCurrency(request);  
            return ResponseEntity.ok(Map.of(  
                "from", request.getFrom(),  
                "to", request.getTo(),  
                "amount", request.getAmount(),  
                "convertedAmount", convertedAmount  
            ));  
        } catch (HttpClientErrorException e) {  
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid currency code"));  
        } catch (Exception e) {  
            return ResponseEntity.status(503).body(Map.of("error", "External API is unavailable"));  
        }  
    }  
}