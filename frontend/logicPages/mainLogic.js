///
import '../style/style.scss';

import '../client/modalWindows/modalFields.js';

//import '../Client/modal/modalFields.js';

import '../style/modalFieldsAdmin.scss';
import '../style/reqPage.scss';
import '../../frontend/Client/modal/showModalFields.js';
//////
import { postRequestWithToken } from '../src/request'
import { getRequestWithToken } from '../src/request'
import { getRequestWithoutToken } from '../src/request'
import { putRequestWithToken } from '../src/request'
import  urls from '../constans/const'
import '../style/style.scss'
import redirect from '../src/redirect';
////

function checkRoleAndDrawTabs(){
    var role = localStorage.getItem('role')
    var tabTournaments = document.getElementById('tabTournaments')
    var tabCreate = document.getElementById("tabCreate")
    var tabStats = document.getElementById("tabStats")
    if(role === "USER"){
        tabCreate.classList.toggle('hide')
        
    }
    if(role === "GUEST"){
        tabStats.classList.toggle('hide')
        tabCreate.classList.toggle('hide')
    }
}
checkRoleAndDrawTabs()

//сделать гет запрос по заходу на странице мэйн на вкладке tournaments для получения турниров и отрисовки их в таблицу
function getTournamentsAndRender (){
    var role = localStorage.getItem('role')
    if (role || role !== "GUEST"){
        getRequestWithToken(urls.mainTourUrl).then(function(data){
            console.log(data.response);
            renderTournamentsTable(data.response)
        })
    }
    if (!role || role === "GUEST"){
        getRequestWithoutToken(urls.mainTourUrl).then(function(data){
            console.log(data.response);
            renderTournamentsTable(data)
        })
    }
}
getTournamentsAndRender()

//функция отрисовки турниров в таблице на вкладке tournaments
function renderTournamentsTable(data){
}
//получение инвайтов при входе
function getMessagesAndRender(){
    var role = localStorage.getItem('role')
    if(!role || role === 'GUEST') {
        return
    }
    if(role === "ADMIN"){
        getRequestWithToken()
    }
}
// getMessagesAndRender()

//функция отрисовки инвайтов
// function renderMessages(data){

// }

//функция отправки инвайта
function sendInvite(){
    postRequestWithToken(urls.mainInvite, body).then(function(data){
if(data.status === 200){
    console.log('invite succesfully sent');
}else{
    console.log("invite not sent");
}
    })
}

//функция подгружает всех юзеров из бд админу на добавление в турнир

function getUsers(){
    var role = localStorage.getItem('role')
    if (role === "ADMIN"){
        getRequestWithToken(urls.mainAdminCreate).then(function(data){
            console.log(JSON.parse(data.response));
            // showUsersToAdmin(data)
        })
    }else return
    
}
getUsers()
//функция добавляет в дропдаун у админа поля с юзерами
// showUsersToAdmin(data){

// }
var submitTour = document.getElementById("submit-date")
submitTour.addEventListener("click", sendTournament)

function sendTournament(){
    // тут надо собрать торнамент из модалки и отправить на пост реквест
    var nameTour = document.getElementById("tourName")
    var descriptionTour = document.getElementById("tourDesc")
    var modeCup = document.getElementById("modeCup")
    var modeChamp = document.getElementById("modeChamp")
    var tourPlace = document.getElementById("tourPlace")
    var tourDateStart = document.getElementById("tourDateStart")
    var tourDateEndReg = document.getElementById("tourDateEndReg")
    var levelAdvanced = document.getElementById("tourLevelAdvanced")
    var levelMiddle = document.getElementById("tourLevelMiddle")
    var levelBeginner = document.getElementById("tourLevelBeginner")
    var numberOfParts128 = document.getElementById("tourNum128")
    var numberOfParts64 = document.getElementById("tourNum64")
    var numberOfParts32 = document.getElementById("tourNum32")
    var numberOfParts16 = document.getElementById("tourNum16")
    var numberOfParts8 = document.getElementById("tourNum8")
    var scenarioTour = 'One-match-confrontation'
    var playersTour = "[pl1, pl2]"

    if(numberOfParts128.checked){
        var numberOfParts = numberOfParts128.value
    }
    if(numberOfParts64.checked){
        var numberOfParts = numberOfParts64.value
    }
    if(numberOfParts32.checked){
        var numberOfParts = numberOfParts32.value
    }
    if(numberOfParts16.checked){
        var numberOfParts = numberOfParts16.value
    }
    if(numberOfParts8.checked){
        var numberOfParts = numberOfParts8.value
    }
    if(modeCup.checked){
        var mode = modeCup.value
    }
    if(modeChamp.checked){
        var mode = modeChamp.value
    }
    if(levelAdvanced.checked){
        var level = levelAdvanced.value
    }
    if(levelMiddle.checked){
        var level = levelMiddle.value
    }
    if(levelBeginner.checked){
        var level = levelBeginner.value
    }

    var body = {
        name : nameTour.value,
        description: descriptionTour.value,
        mode: mode,
        place: tourPlace.value,
        dateStart: tourDateStart.value,
        dateRegEnd: tourDateEndReg.value,
        level: level,
        numberOfParts: numberOfParts,
        scenario: scenarioTour,
        players: playersTour
    }

    postRequestWithToken(urls.mainAdminCreate, body).then(function(data){
        if (data.status === 200){
            // console.log(data);
            console.log('tournament sent');
            getTournamentsAndRender()
        }else {
            console.log('tournament not sent');
        }
    })
}

function changeCreds(){
    var changeLogin = document.getElementById("changeLogin")
    var changePass = document.getElementById("changePass")
    var body = {
        login: changeLogin.value,
        password: changePass.value,
    }
    putRequestWithToken(urls.mainUpdateUser).then(function(data){
        if(data.status === 200){
            redirect("index.html")
        }else{
            console.log("не удалось изменить креды");
        }
    })
}
