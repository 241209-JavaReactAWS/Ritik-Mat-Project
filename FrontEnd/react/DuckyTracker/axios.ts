import axios from "axios";
import HttpResponse from "./src/interfaces/httpResponse";

export const instance = axios.create({
    baseURL: "http://localhost:8080/",
    headers: {
        'Access-Control-Allow-Origin': '*',
    }
})

export const backpackInstance = axios.create({
    baseURL: "https://localhost:8080/backpack",
    headers: {
        'Access-Control-Allow-Origin': '*'
    }
})

//Check if there is a cookie
export const LoggedIn = () => 
    {
        instance.get("",{withCredentials:true})
        .then((response) => {
            if(response.data != "none"){
                window.location.href = "./backpack"
                return {
                    "status" : response.status,
                    "response" : response.data
                }
            }
        })
        .catch((error) => {
            return {
                "status" : error.response.status,
                "response" : "How did we get here"
            }
        })
    }

//Register a User
export const RegisterUser = (username:string,password:string) => 
    {
        instance.post("register", {
            params: {
                "username" : username,
                "password" : password
            }
        })
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            if(error.response.status == 409){
                return {
                    "status" : error.response.status,
                    "response" : "Username Already Exists"
                }
            }
            else if(error.response.status == 400){
                return {
                    "status" : error.response.status,
                    "response" : "Username or Password does not follow formatting"
                }
            }
            else{
                return {
                    "status" : error.response.status,
                    "response" : "What have you done"
                }
            }
        })
    }

//Log in
export const LogUserIn = (username:string,password:string) => 
    {
        instance.get("login", {
            params: {
                "username" : username,
                "password" : password
            }
        })
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            if(error.response.status == 400){
                return {
                    "status" : error.response.status,
                    "response" : "Invalid User Data"
                }
            }
            else{
                return {
                    "status" : error.response.status,
                    "response" : "What have you done"
                }
            }
        })
    }

export const LoggedOut = () => 
    {
        instance.get("logout")
        .then((response) => {
            if(response.data != "none"){
                window.location.href = "https://localhost:5173/"
                return {
                    "status" : response.status,
                    "response" : response.data
                }
            }
        })
        .catch((error) => {
            return {
                "status" : error.response.status,
                "response" : "How did we get here"
            }
        })
    }

export const getUserInfo = () => 
    {
        backpackInstance.get("")
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            window.location.href = "https://localhost:5173/"
            return {
                "status" : error.response.status,
                "response" : "How did we get here"
            }
        })
    }

export const searchForDuck = () => 
    {
        backpackInstance.post("")
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            window.location.href = "https://localhost:5173/"
            return {
                "status" : error.response.status,
                "response" : "How did we get here"
            }
        })
    }

export const renameDuck = (newName:string,duckId:number,duckReferenceId:number) => 
    {
        backpackInstance.patch("",{
            "id": duckId,
            "nickname" : newName,
            "referenceId" : duckReferenceId
        })
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            if(error.response.status == 400){ 
                return {
                    "status" : error.response.status,
                    "response" : "Name too short"
                }
            }
            window.location.href = "https://localhost:5173/"
            return {
                "status" : error.response.status,
                "response" : "What have you done"
            }
        })
    }

export const deleteDuck = (newName:string,duckId:number,duckReferenceId:number) => 
    {
        backpackInstance.patch("",{
            "id": duckId,
            "nickname" : newName,
            "referenceId" : duckReferenceId
        })
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            window.location.href = "https://localhost:5173/"
            return {
                "status" : error.response.status,
                "response" : "What have you done"
            }
        })
    }

export const getAllDucksOwned = () =>
    {
        backpackInstance.get("ducks")
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            window.location.href = "https://localhost:5173/"
            return {
                "status" : error.response.status,
                "response" : "What have you done"
            }
        })
    }

export const increaseDuckAmount = (b:number,a:number,s:number,ss:number) =>
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
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            if(error.response.status == 400){
                return {
                    "status" : error.response.status,
                    "response" : "Invalid Value"
                }
            } 
            if(error.response.status == 401){
                return {
                    "status" : error.response.status,
                    "response" : "Unauthorized"
                }
            }
            return {
                "status" : error.response.status,
                "response" : "How did we get here"
            }
        })
        
    }

export const getWorldData = () => 
    {   
        backpackInstance.get("world")
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            return {
                "status" : error.response.status,
                "response" : "How did we get here"
            }
        })
    }

export const getNextBackpackPrice = () => 
    {
        backpackInstance.get("price")
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            if (error.response.status == 400){
                return {
                    "status" : error.response.status,
                    "response" : "Too Expensive"
                }
            }
            return {
                "status" : error.response.status,
                "response" : "How did we get here"
            }
        })
    }

export const buyNextBackpack = () => 
    {
        backpackInstance.get("price")
        .then((response) => {
            return {
                "status" : response.status,
                "response" : response.data
            }
        })
        .catch((error) => {
            return {
                "status" : error.response.status,
                "response" : "How did we get here"
            }
        })
    }


    

