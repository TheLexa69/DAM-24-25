package demoapp.controller;

import demoapp.model.entity.Customer;
import demoapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public String listCustomers(Model model) {

        List<Customer> customers = customerService.allCustomers();
        model.addAttribute("customers", customers);
        return "listCustomers";
    }
}
