import { postRequestWithToken } from '../src/request'
import { getRequestWithToken } from '../src/request'
import { getRequestWithoutToken } from '../src/request'
import { getRequest } from '../src/request'
import  urls  from '../src/request'
import '../style/style.scss'

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

//сделать гет запрос по заходу на странице мэйн на вкладке tournaments для получения турниров и отрисовки их в таблицу
function getTournamentsAndRender (){
    var role = localStorage.getItem('role')
    if (role){
        getRequestWithToken(urls.mainTourUrl).then(function(data){
            renderTournamentsTable(data)
        })
    }
    if (!role){
        getRequestWithoutToken(urls.mainTourUrl).then(function(data){
            renderTournamentsTable(data)
        })
    }
}
// getTournamentsAndRender()
//получение инвайтов при входе
function getMessages(){
    var role = localStorage.getItem('role')
    if(!role) {
        return
    }else{
        getRequestWithToken(urls.mainInvite).then(function(data){
            renderMessages(data)
        })
    }
}
// getMessages()

//функция отрисовки инвайтов
// function renderMessages(data){

// }

//функция отрисовки турниров в таблице на вкладке tournaments
// function renderTournamentsTable(){

// }

//функция отправки инвайта
function sendInvite(){
    // var body = {
    //     status: status
    // }
    postRequestWithToken(urls.mainInvite, body).then(function(data){
if(data.status === 200){
    console.log('invite succesfully sent');
}else{
    console.log("invite not sent");
}
    })
}

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

function sendTournament(data){
    // тут надо собрать торнамент и отправить на пост реквест
    postRequestWithToken(urls.mainAdminCreate, body).then(function(data){
        if (data.status === 200){
            console.log('tournament sent');
        }else {
            console.log('tournament not sent');
        }
    })
}