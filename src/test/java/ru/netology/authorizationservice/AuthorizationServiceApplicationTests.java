package ru.netology.authorizationservice;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorizationServiceApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> myAppDev = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    @Container
    private static final GenericContainer<?> myAppProd = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myAppDev.start();
        myAppProd.start();
    }

    @Test
    void contextLoadsSuccess() {
        ResponseEntity<String> forDev = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/authorize?user=Tom&password=123456789", String.class);
        ResponseEntity<String> forProd = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/authorize?user=Bob&password=qazwsxedcrfv", String.class);

        Assert.assertEquals("[\"WRITE\",\"READ\"]", forDev.getBody());
        Assert.assertEquals("[\"DELETE\"]", forProd.getBody());

        System.out.println(forDev.getBody());
        System.out.println(forProd.getBody());
    }

    @Test
    void contextLoadsBadRequest() {
        ResponseEntity<String> forDev = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/authorize?user=Tom&password=123789", String.class);
        ResponseEntity<String> forProd = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/authorize?user=Bob&password=qazrfv", String.class);

        Assert.assertEquals("Exception: Unknown user Tom", forDev.getBody());
        Assert.assertEquals("Exception: Unknown user Bob", forProd.getBody());

        System.out.println(forDev.getBody());
        System.out.println(forProd.getBody());
    }

}
