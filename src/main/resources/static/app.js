async function fetchTasks() {
    const response = await fetch("/tasks/all");
    const tasks = await response.json();
    displayTasks(tasks);
}

function formatDateTime(dateTimeString) {
    const date = new Date(dateTimeString);
    const options = {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        hour12: false 
    };
    return date.toLocaleDateString('en-GB', options).replace(',', '');
}

function displayTasks(tasks) {
    const taskListDiv = document.getElementById("taskList");
    taskListDiv.innerHTML = "";
    let cnt=1;
    tasks.forEach(task => {
        const taskDiv = document.createElement("div");
        taskDiv.id = `task-${task.name}`;
        taskDiv.className='newtask';
        const taskname = document.createElement("p");
        taskname.id='newtxt1';
        const tasktime = document.createElement("p");
        tasktime.id='newtxt2';
        const times=formatDateTime(task.dateTime);
        taskname.innerText = `${cnt})  ${task.name} -`;
        tasktime.innerText = `${times}`;
        taskDiv.appendChild(taskname);
        taskDiv.appendChild(tasktime);
        taskListDiv.appendChild(taskDiv);
        cnt+=1;
    });
}

document.getElementById("addTaskForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    const name = document.getElementById("addTaskName").value;
    const dateTime = document.getElementById("addTaskDateTime").value;
    await fetch("/tasks/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, dateTime })
    });
    var addform = document.getElementById("addform");
    addform.style.display = "none";
    fetchTasks();
    document.getElementById("addTaskName").value="";
    dateTime = document.getElementById("addTaskDateTime").value="";
});

document.getElementById("deleteTaskForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    const name = document.getElementById("deleteTaskName").value;
    const response = await fetch(`/tasks/delete?name=${name}`, { 
        method: "DELETE"
    });
    
    if (response.ok) {
        alert("Task deleted!");
        fetchTasks();
        var delform = document.getElementById("delform");
        delform.style.display = "none";
    }
    else {
        alert("Failed to delete task.");
        var delform = document.getElementById("delform");
        delform.style.display = "none";
    }
    document.getElementById("deleteTaskName").value="";
});


document.getElementById("updateTaskForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    const name = document.getElementById("updateTaskName").value;
    const dateTime = document.getElementById("updateTaskDateTime").value;
    const response = await fetch(`/tasks/search?name=${name}`);
    const task = await response.json();
    const times=formatDateTime(task.dateTime);
    if(task.name=="abc"){
        alert("Task not found!");
    }
    else{
        await fetch("/tasks/update", {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, dateTime })
        });
        var upform = document.getElementById("upform");
        upform.style.display = "none";
        alert("Task updated!");
        fetchTasks();
    }
    document.getElementById("updateTaskName").value="";
    document.getElementById("updateTaskDateTime").value="";
});

document.getElementById("nxt").addEventListener("click", async function() {
    const response = await fetch("/tasks/next");
    const task = await response.json();
    const times=formatDateTime(task.dateTime);
    if(task.name=="abc"){
        document.getElementById("nextTaskResult").innerText ="No Upcoming Tasks!";
    }
    else{
        document.getElementById("nextTaskResult").innerText =`Next Task: ${task.name} on ${times}`;   
    }
});

document.getElementById("searchTaskForm").addEventListener("submit", async function(event) {
    event.preventDefault();
    const name = document.getElementById("searchTaskName").value;
    const response = await fetch(`/tasks/search?name=${name}`);
    const task = await response.json();
    const times=formatDateTime(task.dateTime);
    if(task.name=="abc"){
        document.getElementById("searchTaskResult").innerText="Task not found!";
    }
    else{
        document.getElementById("searchTaskResult").innerText=`Task found: ${task.name} on ${times}`;   
    }
    var x=document.getElementById("searchbut");
    x.style.display="block";
    document.getElementById("searchTaskName").value="";
});

fetchTasks();