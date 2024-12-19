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
  
  return (
    <div>
      <div className="head">
      <p id="balance">Balance: {cash}</p>
      <select>
        <option value="" selected disabled hidden className="drop-down">Search</option>
        <table>
        <option>SS</option>
        <option>S</option>
        <option>A</option>
        <option>B</option>
        </table>
    </select>

      <Link to={"/"}><button id="logout-button">Logout</button></Link>
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
