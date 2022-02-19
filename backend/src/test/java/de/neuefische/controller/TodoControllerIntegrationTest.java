package de.neuefische.controller;

import de.neuefische.TodoElement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void shouldAddTodo() {
        TodoElement todoElement = new TodoElement();
        todoElement.setTitle("Sport");

        testRestTemplate.postForEntity("/todos", todoElement, TodoElement[].class);

        ResponseEntity<TodoElement> response = testRestTemplate.getForEntity("/todos/Sport", TodoElement.class);
        assertEquals(todoElement.getId(), Objects.requireNonNull(response.getBody()).getId());

    }


    @Test
    void shouldGetElementByName() {
        TodoElement tE2 = new TodoElement();
        tE2.setTitle("Java");

        testRestTemplate.postForEntity("/todos", tE2, TodoElement.class);

        ResponseEntity<TodoElement> response = testRestTemplate.getForEntity("/todos/Java", TodoElement.class);
        assertEquals(tE2.getTitle(), Objects.requireNonNull(response.getBody()).getTitle());

    }



}