const app = document.getElementById("root");

const container = document.createElement("div");
container.setAttribute("class", "container");

app.appendChild(container);

var request = new XMLHttpRequest();
request.open("GET", "/accounts", true);
request.onload = function() {
  // Begin accessing JSON data here
  var data = JSON.parse(this.response);
  if (request.status >= 200 && request.status < 400) {
    data.forEach(account => {
      const card = document.createElement("div");
      card.setAttribute("class", "card");

      const h1 = document.createElement("h1");
      h1.textContent = "IBAN: " + account.Iban;

      const p = document.createElement("p");
      const p2 = document.createElement("p");
      const p3 = document.createElement("p");
      const p4 = document.createElement("p");
      const p5 = document.createElement("p");

      if (account.User != null) {
        p.textContent = `User ID: ${account.User.Id}`;
      }
      p2.textContent = `Name: ${account.Name}`;
      p3.textContent = `Balance: ${account.Balance}`;
      p4.textContent = `Account type: ${account.Accounttype}`;
      p5.textContent = `Account status: ${account.Status}`;

      container.appendChild(card);
      card.appendChild(h1);
      if (account.User != null) {
        card.appendChild(p);
      }
      card.appendChild(p);
      card.appendChild(p2);
      card.appendChild(p3);
      card.appendChild(p4);
      card.appendChild(p5);
    });
  } else {
    const errorMessage = document.createElement("marquee");
    errorMessage.textContent = `Gah, it's not working!`;
    app.appendChild(errorMessage);
  }
};

request.send();
