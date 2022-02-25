package de.neuefische;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TodoElement {
    private String id = UUID.randomUUID().toString();
    private String title = "";
    private String text = "";
    private TodoState state = TodoState.Open;

    public TodoElement(String title){
        this.title = title;
    }


}
