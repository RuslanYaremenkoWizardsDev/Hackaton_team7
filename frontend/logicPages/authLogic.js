
import {postRequestWithoutToken}  from "../src/request" 

import '../style/style.scss'

import urls from "../constans/const" 
import redirect from "../src/redirect"
import { validateLogin, validatePassword, validateEmail } from "../client/validation/validation"

var loginInput = document.getElementById("loginAuth")
var passInput = document.getElementById("passwordAuth")
var submitBtn = document.getElementById("submitBtnAuth")
var guestEnter = document.getElementById("guestEnter")

submitBtn.addEventListener("click", authorize)

function authorize (){
    var body = {
        login: loginInput.value,
        password: passInput.value,
    }
    if (validateLogin(body.login) && validatePassword(body.password)) {
        postRequestWithoutToken(urls.authUrl, body).then(function(data){
            if(data.status === 200){
                console.log(data);
                var response = JSON.parse (data.response)
                var role = response.role
                var token = response.token
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
    } else {
        console.log("Не валидно...");
    } 
}
guestEnter.addEventListener("click", enterAsGuest)

function enterAsGuest(){
    localStorage.setItem("role", 'GUEST')
    redirect("mainPage.html")
}

