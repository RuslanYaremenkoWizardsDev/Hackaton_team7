var baseUrl = "https://hackatonteam7.herokuapp.com"
// var frontUrl = "http://localhost:5000"

// для входа 
var authUrl = baseUrl + "/auth"
// для регистрации 
var regUrl = baseUrl + "/reg"
 
var mainUrl =  baseUrl + "/main"

//для get запроса для получения турниров в таблицу
// https://hackatonteam7.herokuapp.com/main/tournaments
var mainTourUrl = mainUrl + "/tournaments"

//для изменения логина и пароля через модалку MyAccount, пут запрос
//https://hackatonteam7.herokuapp.com/main/updateUser
var mainUpdateUser = mainUrl + "/updateUser"

//для получения списка юзеров, чтобы внести их в список игроков турнира, гет запрос и пост запрос на внесение турнира в бд

// https://hackatonteam7.herokuapp.com/main/create

var mainAdminCreate = mainUrl + "/create"

//ПОЛУЧИТЬ И ОТПРАВИТЬ ИНВАЙТЫ/РЕКВЕСТЫ
// ГЕТ для юзера и ПОСТ для админа
var mainTournamentInvite = mainUrl + "/tournamentInvite"
// ПОСТ для юзера и ГЕТ для админа
var mainTournamentRequest = mainUrl + "/tournamentRequest"

//ПРИНЯТЬ ИЛИ ОТКЛОНИТЬ ИНВАЙТЫ/РЕКВЕСТЫ
//Для юзера принять инвайт
var mainTournamentInviteAccept = mainUrl + "/tournamentInvite/accept"
//Для админа принять реквест
var mainTournamentRequestAccept = mainUrl + "tournamentRequest/accept"

export default {
    authUrl: authUrl,
    regUrl: regUrl,
    mainUrl: mainUrl,
    mainTourUrl: mainTourUrl,
    mainUpdateUser: mainUpdateUser,
    mainAdminCreate: mainAdminCreate,
    mainTournamentInvite: mainTournamentInvite,
    mainTournamentRequest: mainTournamentRequest, 
    mainTournamentInviteAccept: mainTournamentInviteAccept,
    mainTournamentRequestAccept: mainTournamentRequestAccept
}

// console.log(urls);

// module.exports = urls