let duckies = [
  {
    id: 1,
    // referenceId: 1,
    imgUrl: "./images/rank-A.png",
    name: "Duck1",
    rank: "A",
    value: 1000.0,
  },
  {
    id: 2,
    // referenceId: 1,
    imgUrl: "./images/rank-E.png",
    name: "Duck2",
    rank: "E",
    value: 0.1,
  },
  {
    id: 3,
    // referenceId: 1,
    imgUrl: "./images/rank-D.png",
    name: "Duck3",
    rank: "D",
    value: 1.0,
  },
  {
    id: 4,
    // referenceId: 1,
    imgUrl: "./images/rank-S.png",
    name: "Duck4",
    rank: "S",
    value: 10000.0,
  },
  {
    id: 5,
    // referenceId: 1,
    imgUrl: "./images/rank-B.png",
    name: "Duck5",
    rank: "B",
    value: 100.0,
  },
  {
    id: 6,
    //referenceId: 1,
    imgUrl: "./images/rank-SS.png",
    name: "Duck6",
    rank: "SS",
    value: 100000.0,
  },
];

let cardContainer = document.getElementById("card-container");

function populateCards(duckies) {
  for (card of duckies) {
    let cDiv = document.createElement("div");

    cDiv.innerHTML = `<img src=${card.imgUrl}>
        <div class="name-change">
        <input type="text" placeholder="${card.name}" name="name"> 
        <button id="checkmark"><img src="./images/checkmark.png"></button>
        </div>
        <p>Rank: ${card.rank}</p>
        <p>Value: ${card.value}</p>`;

    cDiv.classList.add("card");
    cDiv.classList.add(`rank${card["rank"]}`);

    cardContainer.append(cDiv);
  }
}

populateCards(duckies);
/*
Values:

SS - 100000.0
S - 10000.0
A - 1000.0
B - 100.0
C - 10.0
D - 1.0
E - 0.1
F - 0.01

imgUrl:
SS: "./images/rank-SS.jpg "
S: "./images/rank-S.jpg "
A: "./images/rank-A.jpg "
B: "./images/rank-B.jpg "
C: "./images/rank-C.jpg "
D: "./images/rank-D.jpg " 
E

*/
