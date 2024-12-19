import { Link } from "react-router-dom"
import "./Login.css"
import { SyntheticEvent, useState } from "react"
import {instance,backpackInstance} from "../../../axios"

function Login() {
  const [username, setUsername] = useState<string>('')
  const [password, setPassword] = useState<string>('')

  let login = () => {
    if(!username){
      alert("Please enter a username")
      return;
    }

    if(!password){
      alert("Please enter a password")
      return;
    }

    instance.get("login",{withCredentials:true})
        .then((response) => {
            if(response.data != "none"){
                window.location.href = "./backpack"
            }
        })
        .catch((error) => {
          alert("Username or password was entered incorrectly")
          return;
        })
        
  }

  let register = () => {
    if(!username){
      alert("Please enter a username")
    }

    if(!password){
      alert("Please enter a password")
    }

    instance.post("register", {
      withCredentials: true,
      params: {
          "username" : username,
          "password" : password
      }
    })
    .then((response) => {
        alert("User Created Successfully");
        return;
    })
    .catch((error) => {
        if(error.status == 409){
            alert("Username Already Exists");
            return;
        }
        else if(error.status == 400){
            alert("Username or Password does not follow formatting");
            return;
        }
        else{
            return;
        }
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
