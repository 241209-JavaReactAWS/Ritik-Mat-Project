import { Link } from "react-router-dom"
import "./Login.css"
import { SyntheticEvent, useState } from "react"

function Login() {
  const [username, setUsername] = useState<string>('')
  const [password, setPassword] = useState<string>('')

  let login = () => {
    if(!username){
      alert("Please enter a username")
    }

    if(!password){
      alert("Please enter a password")
    }

  }

  let register = () => {
    if(!username){
      alert("Please enter a username")
    }

    if(!password){
      alert("Please enter a password")
    }
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
