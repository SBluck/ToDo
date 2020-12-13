alert("this works")
getData();

function getData(){
    fetch('http://localhost:9092/tdList/read')
    .then(
        function(response) {
            if (response.status !== 200) {
                console.log('Problem accessing the json website. Status code: ' +
                    response.status);
                return;
            }
            // Check text in the response
            response.json().then(function(data) {
                console.log(data);
                for(let i=0;i<data.length;i++) {
                    console.log("id",data[i].id);
                    console.log("name",data[i].name);
                    let para = document.createElement("p");
                    para.className ="alert alert-warning";
                    para.innerText = ` The id is ${data[i].id} and the name is ${data[i].name}`
                    // ${(document.querySelector("#resp").innerHTML = data[i].id)}
                    // ${(document.querySelector("#resp").innerHTML = data[i].name)}
                    // ${(document.querySelector("#resp").innerHTML = data[i].email)}
                    // ${(document.querySelector("#resp").innerHTML = data[i].body)}`
                    
                    let tdListDiv = document.getElementById("tdListDiv");
                    tdTaskDiv.appendChild(para);
                }
            })
        }
    )
    .catch(function(err){
        console.log('Error during fetch', err);
    });
}