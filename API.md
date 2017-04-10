**Create game**
----
  Creates a new game.

* **URL**

  /game/create

* **Method:**

  `POST`
  
* **URL Params**

  None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 201 OK <br />
    **Content:** `{ id : 123 }`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "The request was invalid or cannot be served." }`

* **Sample Call:**

  ```$.ajax({
    url: "/game/create",
    dataType: "json",
    type: "POST",
    success: function(data) {
      this.id = data.id;
    }
  });```
  
 **Show game**
----
  Returns json data about a single game.

* **URL**

  /game/:id

* **Method:**

  `GET`
  
* **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

   None

* **Success Response:**

  * **Code:** 200 OK <br />
    **Content:** ```{
	              gamePhase: 0,
                  levels: [0,1,2,3,4,...,1],
                  workers: {
                    player1: ["a2","b4"],
                    player2: ["b2","c4"]
                  },
	              currentPlayer: 0,
	              winner: -1
               }```
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "The request was invalid or cannot be served." }`

* **Sample Call:**

  ```$.ajax({
    url: "/game/123",
    dataType: "json",
    type: "GET",
    success: function(data) {
      this.levels = data.levels;
    }
  });```
  
 **Show possible fields**
----
  Returns json data about possible fields.

* **URL**

  /game/:id/possibleFields?worker=:worker

* **Method:**

  `GET`
  
* **URL Params**

   **Required:**
 
   `id=[integer]`
   
   AND
   
   `worker=[string]`
   
   OR
   
   `builder=[string]`   

* **Data Params**

    None	

* **Success Response:**

  * **Code:** 200 OK <br />
    **Content:** `{ fields: ["a2","a3","b4"] }`
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "The request was invalid or cannot be served." }`

* **Sample Call:**

   ```$.ajax({
     url: "/game/123/possibleFields?worker="a2",
     dataType: "json",
     type: "GET",
     success: function(data) {
       this.fields = data.fields;
     }
   });```
  
  **Move**
----
  Do a worker or build move.

* **URL**

  /game/:id/move

* **Method:**

  `PUT`
  
* **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

   **Required:**
 
   ```{
      "move": ["a2","a3"],
	  "build": ["a4"]
   }```

* **Success Response:**

  * **Code:** 200 OK <br />
    **Content:**
 
* **Error Response:**

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "The request was invalid or cannot be served." }`

* **Sample Call:**

  ```$.ajax({
    url: "/game/123/move,
    dataType: "json",
    type: "POST",
    headers: {"X-HTTP-Method-Override": "PUT"},
    data: '{"move": ["a2","a3"], "build": ["a4"]}'
  });```
  