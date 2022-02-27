import React from 'react';
import {State, TodoElement} from "../model";

const TodoItem = (props: TodoElement ) => {





    return (
        <>
            <div>Title: {props.title}</div>
            <div>Text: {props.text}</div>
            <div>State: {props.state}</div>
        </>

    );
};

export default TodoItem;