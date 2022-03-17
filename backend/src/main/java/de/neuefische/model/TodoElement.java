package de.neuefische.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
@Data
@NoArgsConstructor
public class TodoElement {

    @Id
    private String id;
    private String title = "";
    private String text = "";
    private TodoState state = TodoState.Open;
    private String userId;

    public TodoElement(String title){
        this.title = title;
    }


}
