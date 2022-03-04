import {Todo} from "../model";
import {useState} from "react";
import "./TodoForm.css"
import {useTranslation} from "react-i18next";
import LanguageSelection from "./LanguageSelection";


interface TodoFormProps {
    onTodoCreation: (todos: Array<Todo>) => void
}

export default function TodoForm(props: TodoFormProps){

    const[title, setTitle] = useState('')
    const[text, setText] = useState('')


    const{t} = useTranslation()

    const addTask = () => {
        fetch(`${process.env.REACT_APP_DEV_URL}/todos`, {
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
            <LanguageSelection/>
            <input type="text" placeholder={t('title')} value={title} onChange={ev => setTitle(ev.target.value)} />
            <input className={"text-field"} type="text" placeholder={t('text')} value={text} onChange={ev => setText(ev.target.value)} />
            <button onClick={addTask} className={"send-button"}>{t('send')}</button>
        </div>
    )
}