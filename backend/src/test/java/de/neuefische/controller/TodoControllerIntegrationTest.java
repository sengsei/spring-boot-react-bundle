package de.neuefische.controller;

import de.neuefische.TodoElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void integrationTest() {

        TodoElement todo1 = new TodoElement("Sport");

        ResponseEntity<TodoElement[]> response1 = restTemplate.postForEntity("/todos", todo1, TodoElement[].class);

        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response1.getBody()).containsExactlyInAnyOrder(todo1);


        TodoElement todo2 = new TodoElement("Java");

        ResponseEntity<TodoElement[]> response2 = restTemplate.postForEntity("/todos", todo2, TodoElement[].class);

        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response2.getBody()).containsExactlyInAnyOrder(todo1, todo2);


        restTemplate.delete("/todos/" + todo1.getId());
        ResponseEntity<TodoElement[]> response3 = restTemplate.getForEntity("/todos", TodoElement[].class);

        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response3.getBody()).containsExactlyInAnyOrder(todo2);


        TodoElement todoWithChanges = new TodoElement();
        todoWithChanges.setId(todo2.getId());
        todoWithChanges.setTitle("Java");
        todoWithChanges.setText(todo2.getText());
        todoWithChanges.setState(todo2.getState());

        restTemplate.put("/todos/" + todo2.getId(), todoWithChanges);
        ResponseEntity<TodoElement[]> response4 = restTemplate.getForEntity("/todos", TodoElement[].class);

        assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response4.getBody()).containsExactlyInAnyOrder(todoWithChanges);

    }
}
