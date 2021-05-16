var baseUrl = "https://hackatonteam7.herokuapp.com"
// var frontUrl = "http://localhost:5000"

// для входа 
var authUrl = baseUrl + "/auth"
// для регистрации 
var regUrl = baseUrl + "/reg"
 
var mainUrl =  baseUrl + "/main"

//для get запроса для получения турниров в таблицу
var mainTourUrl = mainUrl + "/tournaments"

//для изменения логина и пароля через модалку MyAccount, пут запрос
var mainUpdateUser = mainUrl + "/updateUser"

//для получения списка юзеров, чтобы внести их в список игроков турнира, гет запрос

var mainAdminCreate = mainUrl + "/create"

const urls = {
    authUrl: authUrl,
    regUrl: regUrl,
    mainUrl: mainUrl,
    mainTourUrl: mainTourUrl,
    mainUpdateUser: mainUpdateUser,
    mainAdminCreate: mainAdminCreate
}

// console.log(urls);

module.exports = urls