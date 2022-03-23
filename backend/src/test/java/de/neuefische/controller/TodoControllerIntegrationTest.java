package de.neuefische.controller;


import de.neuefische.model.LoginData;
import de.neuefische.model.TodoElement;
import de.neuefische.model.UserDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collection;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void integrationTest() {
        TodoElement todo = new TodoElement();
        todo.setTitle("Java");

        ResponseEntity<UserDocument> createUserResponse = restTemplate.postForEntity("/api/users", new UserDocument(null, "test@email.de", "123456", "123456", null), UserDocument.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(Objects.requireNonNull(createUserResponse.getBody()).getEmail(), ("test@email.de"));

        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/api/auth/login", new LoginData("test@email.de", "123456"), String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotBlank();

        ResponseEntity<String> addTodoResponse = restTemplate.exchange(
                "/todos",
                HttpMethod.POST,
                new HttpEntity<>(todo, createHeaders(loginResponse.getBody())),
                String.class
        );

        assertThat(addTodoResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertTrue(Objects.requireNonNull(addTodoResponse.getBody()).contains("Java"));

        ResponseEntity<Collection> listAllItemResponse = restTemplate.exchange(
                "/todos",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(loginResponse.getBody())),
                Collection.class
        );

        assertThat(Objects.requireNonNull(listAllItemResponse.getBody()).size()).isEqualTo(1);
    }

    private HttpHeaders createHeaders(String token){
        String authHeader = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        return headers;
    }


}
