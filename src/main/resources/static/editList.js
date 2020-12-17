const params = new URLSearchParams(window.location.search);

for(let param of params ){
    let id = param[0];
    console.log(id);
    getData(id)
}

function getData(id){
    fetch('http://localhost:9092/tdList/read/'+id)
      .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          // Examine the text in the response
          response.json().then(function(data) {
             console.log("MY DATA OBJ",data)

             document.querySelector("input#tdListId").value = data.id
             document.querySelector("input#tdListName").value = data.name
                
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
    }


    document.querySelector("form.editList").addEventListener("submit", function (stop) {
    stop.preventDefault();
    let formElements = document.querySelector("form.editList").elements;
    console.log(formElements)
    let id =formElements["tdListId"].value;
    let name=formElements["tdListName"].value;
    
 
    let data = {
      "id":id,
      "name":name,
 /*      "tdList":{
          "id":2
      } */
    }
    console.log("Data to post",data)
    console.log(id)

    sendData(data,id)
  });


  function sendData(data,id){
    fetch("http://localhost:9092/tdList/update/"+id, {
        method: 'put',
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        },
        body:JSON.stringify(data)
      })
      .then(function (data) {
        console.log('Request succeeded with JSON response', data);
      })
      .catch(function (error) {
        console.log('Request failed', error);
      });
    }