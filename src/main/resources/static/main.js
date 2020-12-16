//alert("this works")
getList();
getTask();

function getList(){
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
                    let row = document.createElement("div")
                    row.className="row"

                    let newDiv = document.createElement("div")
                    newDiv.className="alert alert-info"
                    let para = document.createElement("p");
                    para.innerText = `${data[i].name}`
                    
                    let aButton = document.createElement("a");
                    aButton.className= "btn btn-light"
                    aButton.href="pagename.html/id="+data[i].id;
                    aButton.innerText="Update"
                    
                    let aButton2 = document.createElement("a");
                    aButton2.className= "btn btn-dark"
                    aButton2.href="pagename.html/id="+data[i].id;
                    aButton2.innerText="Delete"

                    let col1 = document.createElement("div")
                    col1.className="col-md"
                    col1.append(para)
                    let col2 = document.createElement("div")
                    col2.className="col-xs"
                    col2.append(aButton,aButton2)
                    row.append(col1, col2)
                 
                    let tdListDiv = document.getElementById("tdListDiv");
//                    newDiv.append(para,aButton,aButton2)
                    newDiv.append(row)
                    tdListDiv.appendChild(newDiv);
                }
            })
        }
    )
    .catch(function(err){
        console.log('Error during fetch', err);
    });
}
function getTask(){
    fetch('http://localhost:9092/tdTask/read')
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
                    console.log("todo",data[i].todo);
                    let row = document.createElement("div")
                    row.className="row"

                    let newDiv = document.createElement("div")
                    newDiv.className="alert alert-primary"
                    let para = document.createElement("p");
                    para.innerText = `${data[i].todo}`
                    
                    let aButton = document.createElement("a");
                    aButton.className= "btn btn-light"
                    aButton.href="pagename.html/id="+data[i].id;
                    aButton.innerText="Update"
                    
                    let aButton2 = document.createElement("a");
                    aButton2.className= "btn btn-dark"
                    aButton2.href="pagename.html/id="+data[i].id;
                    aButton2.innerText="Delete"

                    let col1 = document.createElement("div")
                    col1.className="col-md"
                    col1.append(para)
                    let col2 = document.createElement("div")
                    col2.className="col-xs"
                    col2.append(aButton,aButton2)
                    row.append(col1, col2)
                 
                    let tdTaskDiv = document.getElementById("tdTaskDiv");
                    newDiv.append(row)
                    tdTaskDiv.appendChild(newDiv);

                }
            })
        }
    )
    .catch(function(err){
        console.log('Error during fetch', err);
    });
}