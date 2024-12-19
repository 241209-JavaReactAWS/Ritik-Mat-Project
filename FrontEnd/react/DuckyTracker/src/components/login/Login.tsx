import { Link } from "react-router-dom"
import "./Login.css"
import axios from "axios"
import { SyntheticEvent, useEffect, useState } from "react"
import {instance,backpackInstance} from "../../../axios"

function Login() {
  const [username, setUsername] = useState<string>('')
  const [password, setPassword] = useState<string>('')

  //UNCOMMENT FOR INSTA LOGIN BASED ON COOKIE
  useEffect(()=>{
    axios.get("http://localhost:8080/",{withCredentials:true})
    .then(()=>{ window.location.href = "./FrontEnd/react/DuckyTracker/src/components/mainPage/MainPage.tsx"})
  })

  let login = () => {
    if(!username){
      alert("Please enter a username")
      return;
    }

    if(!password){
      alert("Please enter a password")
      return;
    }

    axios.post("http://localhost:8080/login",{"username" : username,"password" : password},
      {withCredentials:true})
      .then((response) => {
            if(response.data != "none"){ window.location.href = "./FrontEnd/react/DuckyTracker/src/components/mainPage/MainPage.tsx"}
        })
        .catch((error) => { alert("Username or password was entered incorrectly");
      })
        
  }

  let register = () => {
    if(!username){
      alert("Please enter a username")
    }

    if(!password){
      alert("Please enter a password")
    }

    axios.post("http://localhost:8080/register",{"username" : username,"password" : password},
      {withCredentials:true})
    .then((response) => {alert("User Created Successfully");})
    .catch((error) => {
        if(error.status == 409){alert("Username Already Exists");}
        else if(error.status == 400){alert("Username or Password does not follow formatting");}
    })

  }

  return (
    <div>
      <div className="container">
        <div className="login-box">
            <h2>Login</h2>
            <form>
            <label className="label" htmlFor="username"> <b>Username</b></label>
            <input type="text" placeholder="Enter Username" value={username} id="username-input" onChange={(e:SyntheticEvent) => {setUsername((e.target as HTMLInputElement).value)}} required/>
            <label className="label" htmlFor="password"> <b>Password</b></label>
            <input type="password" placeholder="Enter Password" value={password} id="password-input" onChange={(e:SyntheticEvent) => {setPassword((e.target as HTMLInputElement).value)}} required/>
            </form>
        </div>
        <button id="registerButton" type="submit" onClick={register}>Register</button>
        <button id="loginButton" type="submit" onClick={login}>Login</button>
     </div>
    </div>
  )
}

export default Login
