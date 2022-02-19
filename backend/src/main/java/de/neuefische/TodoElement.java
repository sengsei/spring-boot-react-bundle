package de.neuefische;

import java.util.Objects;
import java.util.UUID;

public class TodoElement {
    private String id;
    private String title;
    private String text;
    private boolean state;

    public TodoElement() {
        this.id = UUID.randomUUID().toString();
        this.state = false;
        this.text = "";
        this.title = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TodoElement{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoElement that = (TodoElement) o;
        return state == that.state && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, state);
    }
}
