package carlos.bonfatti.config;

import io.restassured.RestAssured;

public class ApiConfig {
    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    public static void setBasePath(String path) {
        RestAssured.basePath = path;
    }

    public static void resetBasePath() {
        RestAssured.basePath = "";
    }
}
