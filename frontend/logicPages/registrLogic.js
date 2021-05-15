import postRequest  from "../src/request" 
import regUrl from "../constans/const" 
import redirect from "../src/redirect"

var loginInput = document.getElementById("loginReg")
var passInput = document.getElementById("passwordReg")
var passInputRepeat = document.getElementById("passwordReg")
var submitBtn = document.getElementById("submitBtnReg")

submitBtn.addEventListener("click", registrate)

function registrate (){
    var body = {
        login: loginInput.value,
        password: passInput.value,
    }
    //боди отправить на валидацию
    console.log(body);
    postRequest(regUrl, body).then(function(data){
        if(data.status = 200){
            redirect("index.html")
        }
    })
}
