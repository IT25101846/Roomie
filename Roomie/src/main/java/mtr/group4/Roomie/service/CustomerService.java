package mtr.group4.Roomie.service;

import mtr.group4.Roomie.model.Customer;
import mtr.group4.Roomie.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;

    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Optional<Customer> login(String email, String password) {
        Optional<Customer> opt = customerRepo.findByEmail(email);
        if (opt.isPresent() && opt.get().authenticate(password)) return opt;
        return Optional.empty();
    }

    public boolean register(String firstName, String lastName, String email,
                            String password, String phone, String address) {
        if (customerRepo.findByEmail(email).isPresent()) return false; // duplicate
        String id = customerRepo.generateId();
        String today = LocalDate.now().toString();
        Customer c = new Customer(id, firstName, lastName, email, password, phone, address, today);
        customerRepo.save(c);
        return true;
    }

    public Optional<Customer> getById(String id) { return customerRepo.findById(id); }

    public Optional<Customer> findByEmail(String email) { return customerRepo.findByEmail(email); }

    public ArrayList<Customer> getAllCustomers() { return customerRepo.findAll(); }

    public void updateCustomer(Customer customer) { customerRepo.update(customer); }

    public void deleteCustomer(String id) { customerRepo.delete(id); }

    public int countCustomers() { return customerRepo.findAll().size(); }
}
