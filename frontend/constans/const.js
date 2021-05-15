var baseUrl = "http://localhost:8081"
var frontUrl = "http://localhost:5000"

// для входа 
var authUrl = baseUrl + "/auth"
// для регистрации 
var regUrl = baseUrl + "/reg"
 
var mainUrl =  baseUrl + "/main"
var mainTourUrl = mainUrl/ + "/tournaments"

console.log(authUrl, regUrl);

module.exports = {authUrl, regUrl, mainUrl}