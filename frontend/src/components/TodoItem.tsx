import React, {useState} from 'react';
import {State, TodoElement} from "../model";

const TodoItem = (props: TodoElement ) => {

    return (
        <div>
            <div>ID: {props.id}</div>
            <div>Title: {props.title}</div>
            <div>Text: {props.text}</div>
            <div>State: {props.state}</div>
        </div>
    )
}


export default TodoItem;