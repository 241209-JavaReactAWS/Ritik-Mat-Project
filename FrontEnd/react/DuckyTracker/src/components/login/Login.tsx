import { Link } from "react-router-dom"
import "./Login.css"

function Login() {
  return (
    <div>
      <div className="container">
        <div className="login-box">
            <h2>Login</h2>
            <form>
            <label className="label" htmlFor={"username"}> <b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" className="input" required/>
            <label className="label" htmlFor="password"> <b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" className="input"required/>
            </form>
        </div>
        <Link to="/FrontEnd/react/DuckyTracker/src/components/mainPage/MainPage.tsx"><button id="registerButton" type="submit">Register</button></Link>
        <Link to="/FrontEnd/react/DuckyTracker/src/components/mainPage/MainPage.tsx"><button id="loginButton" type="submit">Login</button></Link>
     </div>
    </div>
  )
}

export default Login
