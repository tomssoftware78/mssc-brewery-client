package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@ConfigurationProperties(prefix = "sfg.brewery",ignoreUnknownFields = false)
@Component
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer/";
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    private String apihost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BeerDto getBeerById(UUID uuid) {
        return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
    }

    public CustomerDto getCustomerById(UUID randomUUID) {
        CustomerDto customerDto = restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + randomUUID, CustomerDto.class);
        return customerDto;
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(apihost + BEER_PATH_V1 + uuid.toString(), beerDto);
    }

    public void deleteBeer(UUID uuid) {
        restTemplate.delete(apihost + BEER_PATH_V1 + uuid.toString());
    }

    public URI saveNewCustomer(CustomerDto customer) {
        URI uri = restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customer);
        return uri;
    }

    public void updateCustomer(UUID uuid, CustomerDto customer) {
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + UUID.randomUUID(), customer);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + uuid);
    }
}
