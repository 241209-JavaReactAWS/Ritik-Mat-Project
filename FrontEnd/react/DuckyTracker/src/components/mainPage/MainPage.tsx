import { Link } from "react-router-dom"
import "./MainPage.css"
import { useEffect, useState } from "react"
import { Duck } from "../../interfaces/Duck"
import axios from "axios"

async function updateCash() {
  await axios.get("http://localhost:8080/backpack",{withCredentials:true})
  
}

function MainPage() {
  const [duck, setDuck] = useState<Duck[]>([])
  const [cash, setCash] = useState<number>(0.0)

  useEffect(() => {
    axios.get<Duck[]>("http://localhost:8080/ducks")
    .then((res) => {
      setDuck(res.data)
    })
  }, [])

  let buyStroage = () => {
  useEffect(() => {
    axios.patch<number>("http://localhost:8080/backpack/price", cash)
    .then((res) => {
      setCash(res.data)
    })
    .catch((err) => {
      console.log(err)
      alert("Insufficient funds")
    })

  }, )
}
  
  return (
    <>
    <div>
      <div className="head">
      <p id="balance">Balance: {cash}</p>
      <select>
        <option value="" selected disabled hidden className="drop-down">Search</option>
        
        <option>SS</option>
        <option>S</option>
        <option>A</option>
        <option>B</option>
        
    </select>

      <Link to={"/"}><button id="logout-button">Logout</button></Link>
      {/* <!--<p>Multiplier</p>--> */}
    </div>

    {/* <!-- <hr> --> */}
    <div id="outer-container">
      <div id="card-container">
       {duck.map((card) => {
        return (
          <div key={card.id}>
          if(card.rank === "SS"){
            <img src="./images/rank-SS.png"></img>
          }
          if(card.rank === "S"){
            <img src="./images/rank-S.png"></img>
          }
          if(card.rank === "A"){
            <img src="./images/rank-A.png"></img>
          }
          if(card.rank === "B"){
            <img src="./images/rank-B.png"></img>
          }
          if(card.rank === "C"){
            <img src="./images/rank-C.png"></img>
          }
          if(card.rank === "D"){
            <img src="./images/rank-D.png"></img>
          }
          if(card.rank === "E"){
            <img src="./images/rank-E.png"></img>
          }
          if(card.rank === "F"){
            <img src="./images/rank-F.png"></img>
          }
          <p>{card.nickname}</p>
          <p>{card.rank}</p>
          if(card.rank === "SS"){
            <p>Value: 100000.0 </p>
          }
          if(card.rank === "S"){
            <p>Value: 10000.0 </p>
          }
          if(card.rank === "A"){
            <p>Value: 1000.0 </p>
          }
          if(card.rank === "B"){
            <p>Value: 100.0 </p>
          }
          if(card.rank === "C"){
            <p>Value: 10.0 </p>
          }
          if(card.rank === "D"){
            <p>Value: 1.0 </p>
          }
          if(card.rank === "E"){
            <p>Value: 0.1 </p>
          }
          if(card.rank === "F"){
            <p>Value: 0.01 </p>
          }
          </div>
        )
        
       })} 
      </div>
      <div id="backpack-storage">
        <button onClick={buyStroage}>
          <p>Buy more backpack storage</p>
          <p>$$$</p>
        </button>
      </div>
    </div>
    </div>
    </>
  )
}


export default MainPage

// Values:

// SS - 100000.0
// S - 10000.0
// A - 1000.0
// B - 100.0
// C - 10.0
// D - 1.0
// E - 0.1
// F - 0.01