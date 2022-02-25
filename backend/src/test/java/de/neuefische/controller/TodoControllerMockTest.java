package de.neuefische.controller;

import de.neuefische.TodoElement;
import de.neuefische.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerMockTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private TodoService todoService;


    @Test
    void shouldGetTodoByTitel() {
       TodoElement todoElement = new TodoElement();
       todoElement.setTitle("Java");

       when(todoService.getTodoElementById("Java")).thenReturn(todoElement);

       ResponseEntity<TodoElement> response = testRestTemplate.getForEntity("/todos/Java", TodoElement.class);
       assertEquals(todoElement.getTitle(), Objects.requireNonNull(response.getBody()).getTitle());
    }

}