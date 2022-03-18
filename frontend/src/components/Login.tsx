import {useState} from "react";


const Login = () => {

    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[token, setToken] = useState(localStorage.getItem("token"))

    const login = () => {
       fetch(`${process.env.REACT_APP_BASE_URL}/api/auth/login`, {
           method:"POST",
           body: JSON.stringify({
               "username":email,
               "password": password
           }),
           headers: {
               "Content-Type":"application/json",
               "Authorization": "Bearer " + token
           }
       })
           .then(response => {
               return response.text()
           })
           .then((responseBody: string) => {localStorage.setItem("token", responseBody)})


    }

    return (
            <div>
               <input type={"text"} placeholder={"E-Mail"} value={email} onChange={e => setEmail(e.target.value)}/><br/>
               <input type={"text"} placeholder={"password"} value={password} onChange={e => setPassword(e.target.value)}/><br/>
                <button onClick={login}>Login</button>

            </div>

    )

}

export default Login