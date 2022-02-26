import React, { useState, useEffect } from 'react';

function App() {

    useEffect(() => {
        console.log("i am hooked up")

        fetch('http://localhost:8080/todos').then(response => response.json())
            .then(todoItems => console.log(todoItems))
    })

    return (
        <div>

        </div>
    );
}

export default App;
