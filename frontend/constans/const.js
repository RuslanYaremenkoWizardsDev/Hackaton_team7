
var baseUrl = "http://localhost:8081"
var frontUrl = "http://localhost:5000"

// для входа 
var authUrl = baseUrl + "/auth"
// для регистрации 
var regUrl = baseUrl + "/reg"
 
var mainUrl =  baseUrl + "/main"

//для get запроса для получения турниров в таблицу
var mainTourUrl = mainUrl + "/tournaments"

//для изменения логина и пароля через модалку MyAccount
var mainUpdateUser = mainUrl + "/updateUser"

const urls = {
    authUrl: authUrl,
    regUrl: regUrl,
    mainUrl: mainUrl,
    mainTourUrl: mainTourUrl,
    mainUpdateUser: mainUpdateUser
}

module.exports = {urls}