import { Link } from "react-router-dom"
import "./MainPage.css"
import { useEffect, useState } from "react"
import { Duck } from "../../interfaces/Duck"
import axios from "axios"

async function obtainUserData() {
  await axios.get("http://localhost:8080/backpack",{withCredentials:true})
  .then((response)=>{
    return response.data
  })
}

function LogOut(){
  axios.get("http://localhost:8080/logout",{withCredentials:true})
  .then(() => window.location.href = "http://localhost:5173")
}

function Search(){
  let buttons = document.getElementById("logout-button")
  axios.post("http://localhost:8080/backpack",{},{withCredentials:true})
  .then((response) => {alert("Searching for Duck")})
  .catch((error) => {
      alert(error.data)
  })
}

function MainPage() {
  const [duck, setDuck] = useState<Duck[]>([])
  const [cash, setCash] = useState<number>(0.0)
  const [ss_amount, setSs] = useState<number>(0.0)
  const [s_amount, setS] = useState<number>(0.0)
  const [a_amount, setA] = useState<number>(0.0)
  const [b_amount, setB] = useState<number>(0.0)

  useEffect(() => {
      //Obtain Bank Value
      axios.get("http://localhost:8080/backpack",{withCredentials:true})
      .then((response)=>{
        setCash(response.data["bank_account"])
      })
      //Create Interval to Continuously Get Bank Account
      const interval = setInterval(()=> {
        axios.get("http://localhost:8080/backpack",{withCredentials:true})
        .then((response)=>{
          setCash(response.data["bank_account"])
        })
      },10000)

      const interval2 = setInterval(()=> {
        axios.get("http://localhost:8080/backpack/world",{withCredentials:true})
        .then((response)=>{
          setSs(response.data["ss_rank"])
          setS(response.data["s_rank"])
          setA(response.data["a_rank"])
          setB(response.data["b_rank"])
        })
      },10000)

      return () => {clearInterval(interval);clearInterval(interval2)}
  })


  useEffect(() => {
    axios.get<Duck[]>("http://localhost:8080/backpack/ducks",{withCredentials:true})
    .then((res) => {
      setDuck(res.data)
    })
  }, [])
  
  return (
    <div>
      <div className="head">
      <p id="balance">Balance: {cash}</p>
      <select>
        <option value="" selected  className="drop-down">Search</option>
        <option>SS: {ss_amount}</option>
        <option>S:  {s_amount}</option>
        <option>A:  {a_amount}</option> 
        <option>B:  {b_amount}</option>
    </select>



      <button id="logout-button" onClick={Search}>Search</button>
      <button id="logout-button" onClick={LogOut}>Logout</button>
      
      {/* <!--<p>Multiplier</p>--> */}
    </div>

    {/* <!-- <hr> --> */}
    <div id="outer-container">
      <div id="card-container">
        
      </div>
      <div id="backpack-storage">
        <button>
          <p>Buy more backpack storage</p>
          <p>$$$</p>
        </button>
      </div>
    </div>
    </div>
  )
}


export default MainPage
