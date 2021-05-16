import '../style/style.scss';
import '../Client/js/modalFields.js';
import '../style/modalFieldsAdmin.scss';

function checkRoleAndDrawTabs(){
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
    if(!role){
        tabTournaments.classList.toggle('hide')
        tabCreate.classList.toggle('hide')
        tabStats.classList.toggle('hide')
    }
}
checkRoleAndDrawTabs()

//сделать гет запрос по заходу на странице мэйн на вкладке tournaments для получения турниров и записи их в таблицу

async function getTournamentsAndRender (){
    await getRequest(urls.mainTourUrl).then(function(data){
        renderTournamentsTable(data)
    })
}
// getTournamentsAndRender()

//функция отрисовки турниров в таблице на вкладке tournaments

// function renderTournamentsTable(){

// }

// function Tournament (name, desc, mode, place, dateStart, dateReg, level, numOfParts, scenario, invPlayers =  []){

// }

//общение между админом и пользователем реализовать через таблицу запросов, у запроса есть статус "открытый", "закрытый".