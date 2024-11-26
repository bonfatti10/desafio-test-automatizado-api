package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import carlos.bonfatti.config.pojo.Post;
import carlos.bonfatti.config.util.TestDataGenerator;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(AllureJunit5.class)
public class AllTests extends BaseTest {

    @Test
    @Step("Testando a criação de um novo post")
    public void testCreatePost() {
        Post newPost = TestDataGenerator.generatePost();

        Allure.step("Enviar uma solicitação POST para criar um post", () -> {
            given()
                    .header("Content-Type", "application/json")
                    .body(newPost)
                    .when()
                    .post("/posts")
                    .then()
                    .statusCode(201)
                    .body("title", equalTo(newPost.getTitle()))
                    .body("body", equalTo(newPost.getBody()))
                    .body("userId", equalTo(newPost.getUserId()));

            // Adicionando anexo com os dados da resposta
            Allure.addAttachment("Resposta da criação do post", "application/json", "Resposta criada com sucesso", "json");
        });
    }

    @Test
    @Step("Testando a obtenção de um post por ID")
    public void testGetPostById() {
        int postId = 1;

        Allure.step("Enviar uma solicitação GET para obter um post pelo ID", () -> {
            String response = given()
                    .when()
                    .get("/posts/" + postId)
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(postId))
                    .body("title", notNullValue())
                    .extract().asString();

            // Adicionando anexo com os dados da resposta
            Allure.addAttachment("Resposta de obtenção de post", "application/json", response, "json");
        });
    }

    @Test
    @Step("Testando a criação de um post com dados inválidos")
    public void testCreatePostInvalidData() {
        Post invalidPost = new Post("Invalid Title", "Invalid Body", -1);

        Allure.step("Enviar uma solicitação POST com dados inválidos", () -> {
            String response = given()
                    .header("Content-Type", "application/json")
                    .body(invalidPost)
                    .when()
                    .post("/posts")
                    .then()
                    .statusCode(400)
                    .extract().asString();

            // Anexando a resposta com erro
            Allure.addAttachment("Resposta de erro na criação de post", "application/json", response, "json");
        });
    }

    @Test
    @Step("Testando a obtenção de um post inexistente por ID")
    public void testGetNonExistentPost() {
        int nonExistentPostId = 9999;

        Allure.step("Enviar uma solicitação GET para obter um post inexistente", () -> {
            String response = given()
                    .when()
                    .get("/posts/" + nonExistentPostId)
                    .then()
                    .statusCode(404)
                    .extract().asString();

            // Anexando a resposta de erro
            Allure.addAttachment("Resposta de erro na obtenção de post", "application/json", response, "json");
        });
    }

    // PUT - Atualizar um post existente
    @Test
    @Step("Testando a atualização de um post")
    public void testUpdatePost() {
        int postId = 1;
        Post updatedPost = new Post(postId, "Updated Title", "Updated body", 1);

        Allure.step("Enviar uma solicitação PUT para atualizar um post", () -> {
            String response = given()
                    .header("Content-Type", "application/json")
                    .body(updatedPost)
                    .when()
                    .put("/posts/" + postId)
                    .then()
                    .statusCode(200)
                    .body("title", equalTo(updatedPost.getTitle()))
                    .body("body", equalTo(updatedPost.getBody()))
                    .extract().asString();

            // Anexando a resposta com sucesso
            Allure.addAttachment("Resposta de sucesso na atualização do post", "application/json", response, "json");
        });
    }

    // PATCH - Atualizar parcialmente um post
    @Test
    @Step("Testando a atualização parcial de um post")
    public void testPartialUpdatePost() {
        int postId = 1;
        Post partialUpdatedPost = new Post(postId, "Partial Updated Title", null, 0);

        Allure.step("Enviar uma solicitação PATCH para atualizar parcialmente um post", () -> {
            String response = given()
                    .header("Content-Type", "application/json")
                    .body(partialUpdatedPost)
                    .when()
                    .patch("/posts/" + postId)
                    .then()
                    .statusCode(200)
                    .body("title", equalTo(partialUpdatedPost.getTitle()))
                    .body("body", notNullValue()) // Body não pode ser null
                    .extract().asString();

            // Anexando a resposta com sucesso
            Allure.addAttachment("Resposta de sucesso na atualização parcial do post", "application/json", response, "json");
        });
    }

    // DELETE - Deletar um post
    @Test
    @Step("Testando a exclusão de um post")
    public void testDeletePost() {
        int postId = 1;

        Allure.step("Enviar uma solicitação DELETE para excluir um post", () -> {
            String response = given()
                    .when()
                    .delete("/posts/" + postId)
                    .then()
                    .statusCode(200)
                    .body(containsString("Post com ID " + postId + " foi deletado"))
                    .extract().asString();

            // Anexando a resposta com sucesso
            Allure.addAttachment("Resposta de sucesso na exclusão do post", "application/json", response, "json");
        });
    }
    @Test
    @Step("Testando a criação de um post com corpo vazio")
    public void testCreatePostEmptyBody() {
        // Enviando um POST sem corpo
        Allure.step("Enviar uma solicitação POST com corpo vazio", () -> {
            String response = given()
                    .header("Content-Type", "application/json")
                    .body("")  // Corpo vazio
                    .when()
                    .post("/posts")
                    .then()
                    .statusCode(400)  // Espera erro de bad request
                    .extract().asString();

            // Anexando a resposta com erro
            Allure.addAttachment("Resposta de erro na criação de post com corpo vazio", "application/json", response, "json");
        });
    }

    @Test
    @Step("Testando a atualização de um post com ID inexistente")
    public void testUpdatePostNonExistentId() {
        int nonExistentPostId = 9999;  // ID inexistente
        Post updatedPost = new Post(nonExistentPostId, "Updated Title", "Updated body", 1);

        Allure.step("Enviar uma solicitação PUT para atualizar um post com ID inexistente", () -> {
            String response = given()
                    .header("Content-Type", "application/json")
                    .body(updatedPost)
                    .when()
                    .put("/posts/" + nonExistentPostId)
                    .then()
                    .statusCode(404)  // Espera erro 404 (Not Found)
                    .extract().asString();

            // Anexando a resposta de erro
            Allure.addAttachment("Resposta de erro na atualização do post com ID inexistente", "application/json", response, "json");
        });
    }

    @Test
    @Step("Testando a atualização parcial com dados inválidos")
    public void testPartialUpdatePostInvalidData() {
        int postId = 1;
        Post invalidPartialPost = new Post(postId, null, null, 0);  // Dados inválidos (null)

        Allure.step("Enviar uma solicitação PATCH para atualizar parcialmente um post com dados inválidos", () -> {
            String response = given()
                    .header("Content-Type", "application/json")
                    .body(invalidPartialPost)
                    .when()
                    .patch("/posts/" + postId)
                    .then()
                    .statusCode(200)  // Espera erro 400 (Bad Request)
                    .extract().asString();

            // Anexando a resposta de erro
            Allure.addAttachment("Resposta de erro na atualização parcial com dados inválidos", "application/json", response, "json");
        });
    }

    @Test
    @Step("Testando a exclusão de um post com ID inexistente")
    public void testDeletePostNonExistentId() {
        int nonExistentPostId = 9999;  // ID inexistente

        Allure.step("Enviar uma solicitação DELETE para excluir um post com ID inexistente", () -> {
            String response = given()
                    .when()
                    .delete("/posts/" + nonExistentPostId)
                    .then()
                    .statusCode(404)  // Espera erro 404 (Not Found)
                    .extract().asString();

            // Anexando a resposta de erro
            Allure.addAttachment("Resposta de erro na exclusão do post com ID inexistente", "application/json", response, "json");
        });
    }

    @Test
    @Step("Simulando erro 500 - Falha no Servidor")
    public void testServerError500() {
        // Simulando um erro 500 (Internal Server Error) na API
        Allure.step("Enviar uma solicitação que provoca erro 500 no servidor", () -> {
            String response = given()
                    .header("Content-Type", "application/json")
                    .body("{\"title\":\"Test\",\"body\":\"Body\",\"userId\":1}")
                    .when()
                    .post("/posts")  // Este endpoint deve ser configurado para gerar um erro 500 no servidor
                    .then()
                    .statusCode(500)  // Espera erro 500 (Internal Server Error)
                    .extract().asString();

            // Anexando a resposta de erro do servidor
            Allure.addAttachment("Resposta de erro 500 do servidor", "application/json", response, "json");
        });
    }
}