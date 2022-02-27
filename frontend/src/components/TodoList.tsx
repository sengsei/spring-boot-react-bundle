import {useEffect, useState} from "react";
import {TodoElement} from "../model";
import TodoItem from "./TodoItem"

export default function TodoList() {

    const [todoItems, setTodoItems] = useState([] as Array<TodoElement>)
    const [title, setTitle] = useState('')

    useEffect(() => {

        fetch('http://localhost:8080/todos')
            .then(response => response.json())
            .then((data) => {
                console.log("TodoItems list: ", data)
                setTodoItems(data)
            })
    }, []);


   const getTodoList = () => {
       fetch('http://localhost:8080/todos')
           .then(response => response.json())
           .then(items => setTodoItems(items))
   }

    return (
        <div>
           <button onClick={getTodoList}> get todolist</button>

        </div>

    )

}



