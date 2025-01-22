package demoapp.service;

import demoapp.model.entity.Customer;
import demoapp.model.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        customerRepository.save(new Customer("Mercedes", "Hernández"));
        customerRepository.save(new Customer("Antonio", "Crespo"));
        customerRepository.save(new Customer("Luly", "Vázquez"));

    }

    public List<Customer> allCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer createCustomer(String firstName, String lastName) {
        Customer c = new Customer(firstName, lastName);
        customerRepository.save(c);
        return c;
    }
    public Customer updateCustomer(Long idCustomer, String firstName, String lastName) {
        Customer c = customerRepository.findById(idCustomer).orElse(null);
        if (c == null) {
            throw new CustomerServiceException("No existe customer con id " + idCustomer);
        }
        c.setFirstName(firstName);
        c.setLastName(lastName);
        return c;
    }

    public void deleteCustomer(Long idCustomer) {
        Customer c = customerRepository.findById(idCustomer).orElse(null);
        if (c == null) {
            throw new CustomerServiceException("No existe customer con id " + idCustomer);
        }
        customerRepository.delete(c);
    }

}
