const app = document.getElementById("root");
document.getElementById("ibanFromButton").onclick = function() {getTransactionsByFromIban()};

const container = document.createElement("div");
container.setAttribute("class", "container");

app.appendChild(container);

var requestAllTransactions = new XMLHttpRequest();
var requestIbanFromTransactions = new XMLHttpRequest();

requestAllTransactions.open("GET", "/transactions", true);
requestIbanFromTransactions.open("GET", `/transactions?FromIban=${ibanFrom}`, true);


var ibanFrom; 

function getTransactionsByFromIban(){  
    ibanFrom = document.getElementById("ibanFrom").value;
    // Begin accessing JSON data here
    var data = JSON.parse(this.response);
    if (requestAllTransactions.status >= 200 && requestAllTransactions.status < 400) {
      data.forEach(transaction => {
        const card = document.createElement("div");
        card.setAttribute("class", "card");
  
        const h1 = document.createElement("h1");
        h1.textContent = "Transaction: " + transaction.Id;
  
        const p = document.createElement("p");
        const p2 = document.createElement("p");
        const p3 = document.createElement("p");
        const p4 = document.createElement("p");
        
        p.textContent = `Iban from: ${transaction.FromIban}`;
        p2.textContent = `Iban to: ${transaction.To}`;
        p3.textContent = `Performed by: ${transaction.PerformedBy}`;
        p4.textContent = `Performed by: ${transaction.Amount}`;
  
        container.appendChild(card);
        card.appendChild(h1);
        card.appendChild(p);
        card.appendChild(p2);
        card.appendChild(p3);
        card.appendChild(p4)
      });
    }
    requestIbanFromTransactions.send();
};

requestAllTransactions.onload = function() {
  // Begin accessing JSON data here
  var data = JSON.parse(this.response);
  if (requestAllTransactions.status >= 200 && requestAllTransactions.status < 400) {
    data.forEach(transaction => {
      const card = document.createElement("div");
      card.setAttribute("class", "card");

      const h1 = document.createElement("h1");
      h1.textContent = "Transaction: " + transaction.Id;

      const p = document.createElement("p");
      const p2 = document.createElement("p");
      const p3 = document.createElement("p");
      const p4 = document.createElement("p");
      p.textContent = `Iban from: ${transaction.FromIban}`;
      p2.textContent = `Iban to: ${transaction.To}`;
      p3.textContent = `Performed by: ${transaction.PerformedBy}`;
      p4.textContent = `Performed by: ${transaction.Amount}`;

      container.appendChild(card);
      card.appendChild(h1);
      card.appendChild(p);
      card.appendChild(p2);
      card.appendChild(p3);
      card.appendChild(p4)
    });
  } else {
    const errorMessage = document.createElement("marquee");
    errorMessage.textContent = `Gah, it's not working!`;
    app.appendChild(errorMessage);
  }
};

requestAllTransactions.send();
