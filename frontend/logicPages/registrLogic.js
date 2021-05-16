import postRequestWithoutToken  from "../src/request" 
import urls from "../constans/const" 
import redirect from "../src/redirect"

var loginInput = document.getElementById("loginReg")
var emailInput = document.getElementById("loginReg")
var passInput = document.getElementById("passwordReg")
var passInputRepeat = document.getElementById("passwordReg")
var submitBtn = document.getElementById("submitBtnReg")
var guestEnter = document.getElementById("guestEnter")

submitBtn.addEventListener("click", registrate)

function registrate (){
    var bodyToValidate = {
        login: loginInput.value,
        email: emailInput.value,
        password: passInput.value,
        repeatPass: passInputRepeat
    }
    //bodyToValidate отправить на валидацию
    var bodyToSend = {
        login: loginInput.value,
        email: emailInput.value,
        password: passInput.value,
    }
    postRequestWithoutToken(urls.regUrl, bodyToSend).then(function(data){
        if(data.status = 200){
            redirect("index.html")
        }
    })
}

guestEnter.addEventListener("click", enterAsGuest)

function enterAsGuest(){
    localStorage.setItem("role", "guest")
    redirect("mainPage.html")
}