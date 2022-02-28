import {Todo} from "../model";
import {useState} from "react";
import "./TodoForm.css"


interface TodoFormProps {
    onTodoCreation: (todos: Array<Todo>) => void
}

export default function TodoForm(props: TodoFormProps){

    const[title, setTitle] = useState('')
    const[text, setText] = useState('')

    const addTask = () => {
        fetch('http://localhost:8080/todos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: title,
                text: text
            })
        })
            .then(response => response.json())
            .then((todosFromBackend: Array<Todo>) => props.onTodoCreation(todosFromBackend))

    }

    return (
        <div>
            <input type="text" placeholder="Title" value={title} onChange={ev => setTitle(ev.target.value)} />
            <input className={"text-field"} type="text" placeholder="Text" value={text} onChange={ev => setText(ev.target.value)} />
            <button onClick={addTask} className={"send-button"}>Senden</button>

        </div>
    )
}