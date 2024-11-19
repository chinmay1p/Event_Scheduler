function showadd(adds){
    var x = document.getElementById("addform");
    if (adds.value == "0") {
        x.style.display = "block";
        adds.value = "1";
    } 
    else {
        x.style.display = "none";
        adds.value = "0";
    }
}

function showdel(dels){
    var x = document.getElementById("delform");
    if (dels.value == "0") {
        x.style.display = "block";
        dels.value = "1";
    } 
    else {
        x.style.display = "none";
        dels.value = "0";
    }
}

function showupdate(ups){
    var x = document.getElementById("upform");
    if (ups.value == "0") {
        x.style.display = "block";
        ups.value = "1";
    } 
    else {
        x.style.display = "none";
        ups.value = "0";
    }
}
function closeupdate(){
    var x=document.getElementById("upform");
    x.style.display="none";
}
function showsearch(sear){
    var x = document.getElementById("searchform");
    if (sear.value == "0") {
        x.style.display = "block";
        sear.value = "1";
    } 
    else {
        x.style.display = "none";
        sear.value = "0";
    }
}
function closesearch(){
    var z=document.getElementById("searchTaskResult");
    z.innerText="";
    var xx=document.getElementById("searchbut");
    xx.style.display="none";
    var x = document.getElementById("searchform");
    x.style.display = "none";
    var y=document.getElementById("sear");
    y.value=0;
}
function shownext(nxt){
    var x = document.getElementById("nxtform");
    if (nxt.value == "0") {
        x.style.display = "block";
        nxt.value = "1";
    } 
    else {
        x.style.display = "none";
        nxt.value = "0";
    }
}
function closenxt(){
    var x = document.getElementById("nxtform");
    x.style.display = "none";
    var y=document.getElementById("nxt");
    y.value=0;
}
document.getElementById("drop2").addEventListener("mouseover", function() {
    document.querySelector(".calendar").classList.add("show");
});

document.getElementById("drop2").addEventListener("mouseout", function() {
    document.querySelector(".calendar").classList.remove("show");
});
