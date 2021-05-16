import {postRequestWithoutToken}  from "../src/request" 
import urls from "../constans/const" 
import redirect from "../src/redirect"

var loginInput = document.getElementById("loginAuth")
var passInput = document.getElementById("passwordAuth")
var emailInput = document.getElementById("emailAuth")
var submitBtn = document.getElementById("submitBtnAuth")
var guestEnter = document.getElementById("guestEnter")

submitBtn.addEventListener("click", authorize)

function authorize (){
    var body = {
        login: loginInput.value,
        email: emailInput.value,
        password: passInput.value,
    }
    //боди отправить на валидацию
    postRequestWithoutToken(urls.authUrl, body).then(function(data){
        if(data.status = 202){
            var token = data.token
            var role = data.role
            document.cookie = `token = ${token}, max-age = 3600`
            //убрать занесение токена в локал сторедж
            localStorage.setItem("token", token)
            localStorage.setItem("role", role)
            redirect("mainPage.html")
        }
        if(data.status === 403){
            //функция отрисовки сообщения о том, что такого пользователя не существует 
            redirect("reg")
        }
    })
}
guestEnter.addEventListener("click", enterAsGuest)

function enterAsGuest(){
    localStorage.setItem("role", 'GUEST')
    redirect("mainPage.html")
}