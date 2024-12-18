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
export const LoggedIn = () => 
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
export const RegisterUser = (username,password) => 
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
export const LogUserIn = (username,password) => 
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

export const LoggedOut = () => 
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

export const getUserInfo = () => 
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

export const searchForDuck = () => 
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

export const renameDuck = (newName,duckId,duckReferenceId) => 
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

export const deleteDuck = (newName,duckId,duckReferenceId) => 
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

export const getAllDucksOwned = () =>
    {
        backpackInstance.get("ducks")
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }

export const increaseDuckAmount = (b,a,s,ss) =>
    {
        backpackInstance.patch("ducks",
            {
                "b_rank" : b,
                "a_rank" : a,
                "s_rank" : s,
                "ss_rank": ss
            }
        )
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            if(error.response.status == 400) return "Invalid Value";
            if(error.response.status == 401) return "Unauthorized"
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
        
    }

export const getWorldData = () => 
    {   
        backpackInstance.get("world")
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }

export const getNextBackpackPrice = () => 
    {
        backpackInstance.get("price")
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            if (error.response.status == 400) return "Too Expensive"
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }

export const buyNextBackpack = () => 
    {
        backpackInstance.get("price")
        .then((response) => {
            return response.data;
        })
        .catch((error) => {
            console.log("How did we get here");
            window.location.href = "https://localhost:5173/"
        })
    }


    

