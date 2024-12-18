import axios from "axios";

const instance = axios.create({
    baseURL: "https://localhost:8080/",
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

const backpackInstance = axios.create({
    baseURL: "https://localhost:8080/backpack",
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

//Check if there is a cookie
const LoggedIn = () => 
    {
        instance.get("")
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
const RegisterUser = (username,password) => 
    {
        instance.post("register", {
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
const LogUserIn = (username,password) => 
    {
        instance.get("login", {
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

const LoggedOut = () => 
    {
        instance.get("logout")
        .then((response) => {
            if(response.data != "none"){
                window.location.href = "https://localhost:5173/"
                return response.data
            }
        })
        .catch((error) => {
            console.log("How did we get here");
        })
    }

const getUserInfo = () => 
    {
        backpackInstance.get("")
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }

const searchForDuck = () => 
    {
        backpackInstance.post("")
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }

const renameDuck = (newName,duckId,duckReferenceId) => 
    {
        backpackInstance.patch("",{
            "id": duckId,
            "nickname" : newName,
            "referenceId" : duckReferenceId
        })
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            if(error.response.status == 400) return "Name Too Short"
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }

const deleteDuck = (newName,duckId,duckReferenceId) => 
    {
        backpackInstance.patch("",{
            "id": duckId,
            "nickname" : newName,
            "referenceId" : duckReferenceId
        })
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }

const getAllDucksOwned = () =>
    {
        backpackInstance.get("")
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }
    

