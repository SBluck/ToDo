//alert("this works")
getList();
getTask();

function getList() {
    fetch('http://localhost:9092/tdList/read')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Problem accessing the json website. Status code: ' +
                        response.status);
                    return;
                }
               
                // Check text in the response
                response.json().then(function (data) {
                    console.log(data);
                    for (let i = 0; i < data.length; i++) {
                        console.log("id", data[i].id);
                        console.log("name", data[i].name);

                        let row = document.createElement("div")
                        row.className = "row"

                        let newDiv = document.createElement("div")
                        newDiv.className = "alert alert-info";
                        let para = document.createElement("p");
                        para.innerText = `${data[i].id} ${data[i].name}`

                        let editButton = document.createElement("a");
                        editButton.className = "btn btn-light";
                        editButton.href = "editList.html?" + data[i].id;
                        //editButton.target = "#myModalList" + data[i].id;
                        editButton.innerText = "Edit";

                        let delButton = document.createElement("a");
                        delButton.className = "btn btn-dark"
                        delButton.innerText = "Delete"
                        delButton.onclick = function () {
                            deleteById(data[i].id, "tdList"); return false;
                        }

                        let col1 = document.createElement("div")
                        col1.className = "col-md"
                        col1.append(para)
                        let col2 = document.createElement("div")
                        col2.className = "col-xs"
                        col2.append(editButton, delButton)
                        row.append(col1, col2)

                        let tdListDiv = document.getElementById("tdListDiv");
                        newDiv.append(row)
                        tdListDiv.appendChild(newDiv);

                        // options.append(option);
                    }
                })
            }
        )
        .catch(function (err) {
            console.log('Error during fetch', err);
        });
}
function getOptionList() {
    fetch('http://localhost:9092/tdList/read')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Problem accessing the json website. Status code: ' +
                        response.status);
                    return;
                }
                var options = "";
                // Check text in the response
                response.json().then(function (data) {
                    console.log(data);
                    for (let i = 0; i < data.length; i++) {
                        console.log("id", data[i].id);
                        console.log("name", data[i].name);

                        let option = "<option value='" + data[i].name + "'>" + data[i].name + "</option>";
                       
                        options.append(option);
                    }
                })
            }
        )
        .catch(function (err) {
            console.log('Error during fetch', err);
        });
}
function getTask() {
    fetch('http://localhost:9092/tdTask/read')
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Problem accessing the json website. Status code: ' +
                        response.status);
                    return;
                }
                // Check text in the response
                response.json().then(function (data) {
                    console.log(data);
                    for (let i = 0; i < data.length; i++) {
                        console.log("id", data[i].id);
                        console.log("todo", data[i].todo);
                        let row = document.createElement("div")
                        row.className = "row"

                        let newDiv = document.createElement("div")
                        newDiv.className = "alert alert-primary"
                        let para = document.createElement("p");
                        para.innerText = `${data[i].todo}`

                        let editButton = document.createElement("a");
                        editButton.className = "btn btn-light"
                        editButton.href = "editTask.html?" + data[i].id
                        //editButton.href = "#editTask"
                        // editButton.onclick = $(".open-EditTask", function () { var myTaskId = data[i].id; $(".modal-body #taskId").val(myTaskId); });
                        editButton.innerText = "Edit"

                        let delButton = document.createElement("a");
                        delButton.className = "btn btn-dark"
                        delButton.innerText = "Delete"
                        delButton.onclick = function () {
                            deleteById(data[i].id, "tdTask"); return false;
                        }

                        let col1 = document.createElement("div")
                        col1.className = "col-md"
                        col1.append(para)
                        let col2 = document.createElement("div")
                        col2.className = "col-xs"
                        col2.append(editButton, delButton)
                        row.append(col1, col2)

                        let tdTaskDiv = document.getElementById("tdTaskDiv");
                        newDiv.append(row)
                        tdTaskDiv.appendChild(newDiv);

                    }  // for ends here
                }) // end check json response text
            } // end response function
        ) // end .then
        .catch(function (err) {
            console.log('Error during fetch', err);
        });
} // end getTask function


document.querySelector("form.newList").addEventListener("submit", function (stop) {
    stop.preventDefault();
    let formElements = document.querySelector("form.newList").elements;
    console.log(formElements);
    let name = formElements["name"].value;
    let data = {
        "name": name,
    }
    console.log("Data to post", data);
    sendData(data);
});

document.querySelector("form.newTodo").addEventListener("submit", function (stop) {
    stop.preventDefault();
    let formElements = document.querySelector("form.newTodo").elements;
    console.log(formElements);
    let todo = formElements["todo"].value;
    let todoList = formElements["todoList"].value;
    let todoListInt = parseInt(todoList) 
    let data = {
        "todo": todo,
        "tdList":{
            "id":todoListInt
        }
    }
    console.log("Data to post", data);
    sendDataList(data);
});

/* function newTask() {
    fetch("http://localhost:9092/tdList/create/", {
        method: 'create',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
    });
    refreshPage();
} */

function sendData(data) {
    fetch("http://localhost:9092/tdList/create", {
        method: 'post',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(data)
    });
    //delay for 3000 (ms) 
    // refreshPage();
}

function sendDataList(data) {
    fetch("http://localhost:9092/tdTask/create", {
        method: 'post',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(data)
    });
    //delay for 3000 (ms) 
    // refreshPage();
}

function deleteById(id, toDelete) {
    fetch("http://localhost:9092/" + toDelete + "/delete/" + id, {
        method: 'delete',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
    });
    refreshPage();
}

/* function deleteTask(id) {
    fetch("http://localhost:9092/tdTask/delete/" + id, {
        method: 'delete',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
    });
    refreshPage(); 
} */

function refreshPage() {
    location.reload()
}