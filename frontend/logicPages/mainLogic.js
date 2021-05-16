///
import '../style/style.scss';
import '../Client/js/modalFields.js';
import '../style/modalFieldsAdmin.scss';
//////
import { postRequestWithToken } from '../src/request'
import { getRequestWithToken } from '../src/request'
import { getRequestWithoutToken } from '../src/request'
import  urls from '../constans/const'
import '../style/style.scss'
////

function checkRoleAndDrawTabs(){
    var role = localStorage.getItem('role')
    var tabTournaments = document.getElementById('tabTournaments')
    var tabCreate = document.getElementById("tabCreate")
    var tabStats = document.getElementById("tabStats")
    if(role === "ADMIN"){
        tabTournaments.classList.toggle('hide')
        tabCreate.classList.toggle('hide')
        tabStats.classList.toggle('hide')
    }
    if(role === "USER"){
        tabTournaments.classList.toggle('hide')
        tabStats.classList.toggle('hide')
    }
    if(role === "GUEST"){
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
    if (!role || role === "GUEST"){
        getRequestWithoutToken(urls.mainTourUrl).then(function(data){
            renderTournamentsTable(data)
        })
    }
}
// getTournamentsAndRender()

//функция отрисовки турниров в таблице на вкладке tournaments
// function renderTournamentsTable(){

// }

//получение инвайтов при входе
function getMessageAndRender(){
    var role = localStorage.getItem('role')
    if(!role || role === 'GUEST') {
        getRequestWithoutToken(urls.mainInvite).then(function(data){
            renderMessages(data)
        })
    }else{
        getRequestWithToken(urls.mainInvite).then(function(data){
            renderMessages(data)
        })
    }
}
// getMessageAndRender()

//функция отрисовки инвайтов
// function renderMessages(data){

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

//функция подгружает всех юзеров из бд админу на добавление в турнир
function getUsers(){
    getRequestWithToken(urls.mainAdminCreate).then(function(data){
        showUsersToAdmin(data)
    })
}

//функция добавляет в дропдаун у админа поля с юзерами
// showUsersToAdmin(data){

// }


function sendTournament(data){
    // тут надо собрать торнамент из модалки и отправить на пост реквест
    postRequestWithToken(urls.mainAdminCreate, body).then(function(data){
        if (data.status === 200){
            console.log('tournament sent');
        }else {
            console.log('tournament not sent');
        }
    })
}