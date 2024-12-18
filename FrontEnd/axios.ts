import axios from "axios";

const instance = axios.create({
    baseURL: "https://localhost:8080/",
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

//Check if there is a cookie
const LoggedIn = () => {
    axios.get("")
    .then((response) => {
        if(response.data != "none"){
            window.location.href = "./backpack"
            return response.data
        }
    })
    .catch((error) => {
        console.log("How did we get here");
    })
}

//Register a User
const RegisterUser = (username,password) => {
    axios.post("register", {
        params: {
            "username" : username,
            "password" : password
        }
    })
    .then((response) => {
        return "Success"
    })
    .catch((error) => {
        if(error.response.status == 409){
            return "Username Taken";
        }
        else if(error.response.status == 400){
            return "Username or Password Does Not Follow Formatting"
        }
        else{
            return "What have you done?"
        }
    })
}

//Log in
const LogUserIn = (username,password) => {
    axios.post("register", {
        params: {
            "username" : username,
            "password" : password
        }
    })
    .then((response) => {
        window.location.href = "./backpack"
        return "Success"
    })
    .catch((error) => {
        if(error.response.status == 400){
            return "Username or Password Does Not Follow Formatting"
        }
        else{
            return "What have you done?"
        }
    })
}