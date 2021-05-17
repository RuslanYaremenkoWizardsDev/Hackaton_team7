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


// ГЕТ и ПОСТ для админа 
var mainTournamentInvite = mainUrl + "/tournamentRequest"
var mainTournamentInviteAccept = mainUrl + "/main/tournamentRequest/accept"
// https://hackatonteam7.herokuapp.com/main/invite

export default {
    authUrl: authUrl,
    regUrl: regUrl,
    mainUrl: mainUrl,
    mainTourUrl: mainTourUrl,
    mainUpdateUser: mainUpdateUser,
    mainAdminCreate: mainAdminCreate,
    mainTournamentInvite: mainTournamentInvite,
    mainTournamentInviteAccept: mainTournamentInviteAccept
}

// console.log(urls);

// module.exports = urls