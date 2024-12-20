import { JSXElementConstructor, useEffect, useState } from "react"
import { Duck } from "../../interfaces/Duck"
import axios from "axios"

function Card(rank: string, id: number) {
    const [duck, setDuck] = useState<Duck[]>([])
    useEffect(() => {
        axios.get<Duck[]>("http://localhost:8080/backpack/ducks",{withCredentials:true})
        .then((res) => {
          setDuck(res.data)
        })
      }, [])

      let getUrl = (rank: string) =>   {
        
          if(rank === "SS"){
            return "./images/rank-SS.png"
          }
          if(rank === "S"){
            return "./images/rank-S.png"
          }
          if(rank === "A"){
            return "./images/rank-A.png"
          }
          if(rank === "B"){
            return "./images/rank-B.png"
          }
          if(rank === "C"){
             return "./images/rank-C.png"
          }
          if(rank === "D"){
             return "./images/rank-D.png"
          }
          if(rank === "E"){
             return "./images/rank-E.png"
          }
          if(rank === "F"){
             return "./images/rank-F.png"
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

          
        
        
        
       
      

    
  return (
    <div>
      <div id={storeId.toString()}>
        <img src={getUrl(rank)}></img>
        <p>{}</p>


      </div>
    </div>
  )
}

export default Card


