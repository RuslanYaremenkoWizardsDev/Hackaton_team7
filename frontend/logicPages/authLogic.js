import postRequest  from "../src/request" 
import authUrl from "../constans/const" 
import redirect from "../src/redirect"

var loginInput = document.getElementById("loginAuth")
var passInput = document.getElementById("passwordAuth")
var submitBtn = document.getElementById("submitBtnAuth")

submitBtn.addEventListener("click", authorize)

function authorize (){
    var body = {
        login: loginInput.value,
        password: passInput.value,
    }
    //боди отправить на валидацию
    console.log(body);
    postRequest(authUrl, body).then(function(data){
        if(data.status = 200){
            var token = data.token
            var role = data.role
            document.cookie = `token = ${token}, max-age = 3600`
            localStorage.setItem("role", role)
            redirect("mainPage.html")
        }
    })
}