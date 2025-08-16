package com.in28minutes.microservices.currencyconversionservice.controller;

import com.in28minutes.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.in28minutes.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrency(@PathVariable String from,
                                              @PathVariable String to,
                                              @PathVariable double quantity) {
        Map<String, String> uriVariables = Map.of("from", from, "to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}"
                , CurrencyConversion.class
                , uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();
        return new CurrencyConversion(currencyConversion.getId(), from, to,
                currencyConversion.getConversionMultiple(),
                quantity,
                quantity * currencyConversion.getConversionMultiple(),
                currencyConversion.getEnvironment());
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrencyFeign(@PathVariable String from,
                                                   @PathVariable String to,
                                                   @PathVariable double quantity) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValues(from, to);

        return new CurrencyConversion(currencyConversion.getId(),
                from, to, currencyConversion.getConversionMultiple(), quantity,
                quantity * currencyConversion.getConversionMultiple(),
                currencyConversion.getEnvironment());


    }

}
