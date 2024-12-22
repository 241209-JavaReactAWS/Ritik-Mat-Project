import { JSXElementConstructor, useEffect, useState } from "react"
import { Duck } from "../../interfaces/Duck"
import axios from "axios"
import rankSS from "./images/rank-SS.png"
import rankS from "./images/rank-S.png"
import rankA from "./images/rank-A.png"
import rankD from "./images/rank-D.png"
import rankB from "./images/rank-B.png"
import rankC from "./images/rank-C.png"
import rankE from "./images/rank-E.png"
import rankF from './images/rank-F.png'
import checkmark from "./images/checkmark.png"
import trash from "./images/trash.png"
import "./Card.css"
function Card(props: any) {
  const[ nickname, setNickname] = useState<string>('')
  
  const handleNameChange = (event:any) => {
    setNickname(event.target.value)
  }

      let getUrl = (rank: string) =>   {
        
          if(rank === "SS"){
            return rankSS
          }
          if(rank === "S"){
            return rankS
          }
          if(rank === "A"){
            return rankA
          }
          if(rank === "B"){
            return rankB
          }
          if(rank === "C"){
             return rankC
          }
          if(rank === "D"){
             return rankD
          }
          if(rank === "E"){
             return rankE
          }
          if(rank === "F"){
             return rankF
          }
        }

    let getValue = (rank: string) => {
        if(rank === "SS"){
            return 100000.0 
          }
          if(rank === "S"){
            return 10000.0 
          }
          if(rank === "A"){
            return 1000.0 
          }
          if(rank === "B"){
            return 100.0 
          }
          if(rank === "C"){
            return 10.0 
          }
          if(rank === "D"){
            return 1.0 
          }
          if(rank === "E"){
            return 0.1 
          }
          if(rank === "F"){
            return 0.01 
          }
    }

    let storeId = (id: number)=>{
        return id;
    }

    let className = (rank: string) =>{
        if(rank === "SS"){
            return "rankSS" 
          }
          if(rank === "S"){
            return "rankS" 
          }
          if(rank === "A"){
            return "rankA" 
          }
          if(rank === "B"){
            return "rankB" 
          }
          if(rank === "C"){
            return "rankC" 
          }
          if(rank === "D"){
            return "rankD" 
          }
          if(rank === "E"){
            return "rankE" 
          }
          if(rank === "F"){
            return "rankF" 
          }
    }

    let updateNickname = () =>{
      let data = props.duck;
      data.nickname = nickname
      axios.patch("http://localhost:8080/backpack",data,{withCredentials: true})
      .then((response) => {
        alert("Nickname updated to " + nickname)
      })
      .catch((err) =>{
        console.log(err)
      })

    }

    let deleteCard = () => {
      axios.delete("http://localhost:8080/backpack",{withCredentials: true,data:props.duck})
      .then(response => {
        alert("Card deleted")
      })
      .catch((err) => {
        console.log(err)
      })
    }


          
        
        
        
       
      

    
  return (
    <div>
      <div className={className(props.duck.rank)}>
        <img src={getUrl(props.duck.rank)}></img>
        <div className="name-change">
        <input type="text" placeholder={props.duck.nickname} onChange={handleNameChange} name="name"/> 
        <button id="checkmark" onClick={updateNickname}><img src={checkmark}/></button>
        </div>
        <p>Rank: {props.duck.rank}</p>
        <p>Value: {getValue(props.duck.rank)}</p>
        <button id="trash" onClick={deleteCard}><img src={trash}/></button>
      </div>
    </div>
  )
}

export default Card


