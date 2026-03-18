package com.sliit.y1s2._5.Roomie;

import com.sliit.y1s2._5.Roomie.model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Test
    void testCustomerToCSV() {
        Customer c = new Customer("C001","John","Doe","john@email.com",
                "pass123","+94771234567","Colombo","2024-01-15");
        String csv = c.toCSV();
        assertEquals("C001,John,Doe,john@email.com,pass123,+94771234567,Colombo,2024-01-15", csv);
    }

    @Test
    void testCustomerFromCSV() {
        String csv = "C001,Jane,Doe,jane@email.com,secret,+94779876543,Galle,2024-02-01";
        Customer c = Customer.fromCSV(csv);
        assertEquals("C001",          c.getCustomerId());
        assertEquals("Jane",          c.getFirstName());
        assertEquals("jane@email.com",c.getEmail());
    }

    @Test
    void testCustomerFullName() {
        Customer c = new Customer();
        c.setFirstName("Alice");
        c.setLastName("Smith");
        assertEquals("Alice Smith", c.getFullName());
    }

    @Test
    void testCustomerCSVRoundTrip() {
        Customer original = new Customer("C002","Bob","Builder","bob@email.com",
                "pw456","+94760000001","Kandy","2024-03-10");
        Customer parsed = Customer.fromCSV(original.toCSV());
        assertEquals(original.getCustomerId(), parsed.getCustomerId());
        assertEquals(original.getEmail(),      parsed.getEmail());
    }
}
