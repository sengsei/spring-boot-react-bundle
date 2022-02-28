import React, {useEffect, useState} from "react";
import {State, Todo} from "../model";
import TodoItem from "./TodoItem"
import "./TodoList.css"

export default function TodoList() {

    const [todoItems, setTodoItems] = useState([] as Array<Todo>)
    const [title, setTitle] = useState('')
    const [text, setText] = useState('')
    const [id, setId] = useState('')
    const [state, setState] = useState(State.Open)
    const requestBody = {
        title: title,
        text: text,
    }


    useEffect(() => {

        fetch('http://localhost:8080/todos')
            .then(response => response.json())
            .then((data) => {
                setTodoItems(data)
            })
    }, []);


    const getTodoList = () => {
        fetch('http://localhost:8080/todos')
            .then(response => response.json())
            .then(items => setTodoItems(items))
    }

    const setAttributes = () => {
        fetch('http://localhost:8080/todos', {
            method: 'POST',
            body: JSON.stringify(requestBody),
            headers: {'Content-Type': 'application/json'}
        })
            .then(() => getTodoList())
            .then(() => {
                setTitle('')
                setText('')
                setState(State.Open)
            })
    }

    const deleteById = () => {
        fetch('http://localhost:8080/todos/'+id, {
            method: 'DELETE',
            body: JSON.stringify(requestBody
              ),
            headers: {
                'Content-Type': 'application/json'
            }, })
            .then((response) => response.json())
            .then((data) => setTodoItems(data))
    }

    return (
        <div>

            <div className={"post-it"}>
                <input type="text" placeholder={'title'} value={title} onChange={a => setTitle(a.target.value)}/>
                <input type="text" placeholder={'text'} value={text} onChange={a => setText(a.target.value)}/>
                <input type="checkbox"/>
                <input type="text" placeholder={'id'} value={id} onChange={a => setId(a.target.value)}/>
            </div>
            <div className={"button"}>
            <button onClick={setAttributes}> add todo</button>
            <button onClick={getTodoList}> get todolist</button>
            <button onClick={deleteById}>delete</button>
            </div>
            <div>

            </div>

        </div>

    )

}



