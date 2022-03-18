import React, {useEffect, useState} from "react";
import {Todo} from "../model";
import TodoForm from "./TodoForm";
import TodoItem from "./TodoItem";
import {useTranslation} from "react-i18next";

export default function TodoList() {

    const [todos, setTodos] = useState([] as Array<Todo>)
    const {t} = useTranslation();
    const[errorMessage, setErrorMessage] = useState('')
    const[deleteErrorMessage, setDeleteErrorMessage] = useState('')


    const fetchAll = () => {
        const token = localStorage.getItem("token")
        fetch(`${process.env.REACT_APP_BASE_URL}/todos`, {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        })
            .then(response =>
            {
                if(response.ok){
                    return  response.json()
                }
                throw new Error('Es sind keine Todos zum Anzeigen vorhanden!')

            })
            .then((todosFromBackend: Array<Todo>) => setTodos(todosFromBackend))
            .catch((e: Error) => setErrorMessage(e.message))
    }

    const deleteChecked = () => {
        const token = localStorage.getItem("token")
        fetch(`${process.env.REACT_APP_BASE_URL}/todos`, {
            method: 'DELETE',
            headers: {
                "Authorization": "Bearer " + token
            }
        })
            .then(response => {
                if (response.ok){
                    return response.json()
                }
                throw new Error('Die zu löschende Nachricht existiert nicht!')
            } )
            .then((todosFromBackend: Array<Todo>) => setTodos(todosFromBackend))
            .catch(e => setDeleteErrorMessage(e.message))
    }

    useEffect(() => {
        setTimeout(() => setErrorMessage(''), 10000)
        fetchAll()
    }, []);

    return (
        <div className="space-y-1 space-x-2">
            <div>
                <TodoForm onTodoCreation={setTodos} />
            </div>
            <div className={'space-y-1'}>
                {
                    deleteErrorMessage ? <h1>{deleteErrorMessage}</h1> : <button className={'bg-slate-200 hover:bg-slate-400 px-2 rounded'} onClick={deleteChecked}>{t('delete-checked')}</button>
                }

            </div>
            <div className={'bg-yellow-100 max-w-xl m-auto mt-20'}>
                {
                    errorMessage ? <h1>{errorMessage}</h1>
                        :
                    todos.map((todo) => <div  key={todo.id} data-testid={'add-task'}><TodoItem todo={todo} onTodoDeletion={fetchAll} onTodoChange={setTodos} /></div>)}
            </div>
        </div>
    )

}



