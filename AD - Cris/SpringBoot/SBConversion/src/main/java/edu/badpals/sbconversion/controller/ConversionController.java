package edu.badpals.sbconversion.controller;

import edu.badpals.sbconversion.model.ConversionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ConversionController {

    @GetMapping("/")
    public String conversion() {
        return "conversion";
    }

    @GetMapping("/convert")
    public String convert() {
        return "conversion2";
    }

    @PostMapping("/convert/resultado")
    public String convertResultado(@RequestParam("amount") int amount,
                                   @RequestParam("from") String from,
                                   @RequestParam("to") String to,
                                   Model model) {
        System.out.println("amount: " + amount);
        System.out.println("from: " + from);
        System.out.println("to: " + to);

        String url = String.format("https://api.frankfurter.app/latest?amount=%d&from=%s&to=%s", amount, from, to);
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        ConversionResponse response = restTemplate.getForObject(url, ConversionResponse.class);

        if (response != null && response.getRates().containsKey(to)) {
//            System.out.println(response.getRates().get(to));
//            double rate = response.getRates().get(to);
//            double result = amount * rate;
            model.addAttribute("result", "Result: " + response.getRates().get(to) + " " + to);
        } else {
            model.addAttribute("result", "Conversion rate not found for " + to);
        }

        return "resultado";
    }
}