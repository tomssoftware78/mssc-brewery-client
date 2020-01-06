package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerClientTest {

    @Autowired
    private BreweryClient client;

    @Test
    public void testGetcustomerById() {
        CustomerDto customer = client.getCustomerById(UUID.randomUUID());
        assertNotNull(customer);
    }

    @Test
    void testSaveNewCustomer() {
        URI uri = client.saveNewCustomer(CustomerDto.builder().name("a new customer").build());
        assertNotNull(uri);

        System.out.println(uri.toString());
    }

    @Test
    void testUpdateCustomer() {
        CustomerDto customer = CustomerDto.builder().name("Updated customer").build();
        client.updateCustomer(UUID.randomUUID(), customer);
    }

    @Test
    void testDeleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }

}
