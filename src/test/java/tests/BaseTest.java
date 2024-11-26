package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
}
