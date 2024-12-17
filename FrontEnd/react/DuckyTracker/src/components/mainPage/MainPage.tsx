import { Link } from "react-router-dom"
import "./MainPage.css"

function MainPage() {
  return (
    <div>
      <div className="head">
      <p id="balance">Balance: $$$$$</p>
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
      <div id="card-container"></div>
      <div id="backpack-storage">
        <button>
          <p>Buy more backpack storage</p>
          <p>$$$$</p>
        </button>
      </div>
    </div>
    </div>
  )
}

export default MainPage
