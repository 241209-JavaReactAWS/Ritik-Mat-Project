let duckies = [
    {
        id: 1,
       // referenceId: 1,
        name: "Duck1",
        rank: "A",

    },
    {
        id: 2,
       // referenceId: 1,
        name: "Duck2",
        rank: "E",

    },
    {
        id: 3,
       // referenceId: 1,
        name: "Duck3",
        rank: "D",

    },
    {
        id: 4,
       // referenceId: 1,
        name: "Duck4",
        rank: "D",

    },
    {
        id: 5,
       // referenceId: 1,
        name: "Duck5",
        rank: "B",

    },
    {
        id: 6,
        //referenceId: 1,
        name: "Duck6",
        rank: "SS",

    }
]

let cardContainer = document.getElementById('card-container')

function populateCards(duckies){
    for(card of duckies){
        let cDiv = document.createElement('div')

        cDiv.innerHTML = `<p>Name: ${card.name}</p> 
        <p>Rank: ${card.rank}</p>`

        cDiv.classList.add('card')
        cDiv.classList.add(`rank${card["rank"]}`)
        
        cardContainer.append(cDiv)
        

    }
}

populateCards(duckies);