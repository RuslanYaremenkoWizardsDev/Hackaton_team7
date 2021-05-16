///
import '../style/style.scss';
import '../Client/js/modalFields.js';
import '../style/modalFieldsAdmin.scss';
//////
import { postRequestWithToken } from '../src/request'
import { urls } from '../src/request'
import '../style/style.scss'
////

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

function getTournamentsAndRender (){
    getRequest(urls.mainTourUrl).then(function(data){
        renderTournamentsTable(data)
    })
}
// getTournamentsAndRender()

//функция отрисовки турниров в таблице на вкладке tournaments
// function renderTournamentsTable(){

// }

//общение между админом и пользователем реализовать через таблицу запросов, у запроса есть статус "открытый", "закрытый"

// var tournament = {
//     name: "string",
//     description: "string",
//     mode: "string",
//     place: "string",
//     dateStart: "string",
//     dateRegEnd: "string",
//     level: "string",
//     numberOfParts: "type Number",
//     scenario: "string",
//     players: " тут массив из игроков представленый строкой [pl1, pl2, pl3]",
//     status: "string"
// }


function sendTournament(tournament){
    // body = body
    postRequestWithToken(urls.mainAdminCreate, body).then(function(data){
        if (data.status === 200){
            console.log('турнир отправлен');
        }else {
            console.log('турнир не отправлен');
        }
    })
}