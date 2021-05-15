window.addEventListener('DOMContentLoaded', checkRoleAndDraw)
function checkRoleAndDraw(){
    var role = localStorage.getItem('role')
    var tabTournaments = document.getElementById('tabTournaments')
    var tabCreate = document.getElementById("tabCreate")
    var tabStats = document.getElementById("tabStats")
    if(role === "admin"){
        tabTournaments.classList.toggle('hide')
        tabCreate.classList.toggle('hide')
        tabStats.classList.toggle('hide')
    }
    if(role === "user"){
        tabTournaments.classList.toggle('hide')
        tabStats.classList.toggle('hide')
    }
    if(role === "guest"){
        tabTournaments.classList.toggle('hide')
    }
}
