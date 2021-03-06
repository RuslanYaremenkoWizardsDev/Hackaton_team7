import {postRequestWithoutToken} from "../src/request";
import urls from "../constans/const";
import redirect from "../src/redirect";
import '../style/reqPage.scss'
import {
  validateLogin,
  validatePassword,
  validateEmail,
} from "../client/validation/validation";

var loginInput = document.getElementById("loginReg");
var emailInput = document.getElementById("emailReg");
var passInput = document.getElementById("passwordReg");
var passInputRepeat = document.getElementById("passwordRepeatReg");
var submitBtn = document.getElementById("submitBtnReg");
var guestEnter = document.getElementById("guestEnter");

submitBtn.addEventListener("click", registrate);

function registrate() {
  var bodyToValidate = {
    login: loginInput.value,
    email: emailInput.value,
    password: passInput.value,
    repeatPass: passInputRepeat.value,
  };
  var bodyToSend = {
    login: loginInput.value,
    email: emailInput.value,
    password: passInput.value,
  };
  if (validateLogin(bodyToValidate.login) 
      && validatePassword(bodyToValidate.password) 
      && validateEmail(bodyToValidate.email) 
      && (bodyToValidate.password === bodyToValidate.repeatPass)) {
        console.log(urls);
    postRequestWithoutToken(urls.regUrl, bodyToSend).then(function (data) {
      console.log(data);
      if ((data.status === 200)) {
        redirect("index.html");
      };
      if(data.status === 409){
            console.log('не получилось');
        }
    });
  } else {
    console.log("Не валидно...");
  };
        
}
guestEnter.addEventListener("click", enterAsGuest);


function enterAsGuest(){
    localStorage.setItem("role", "GUEST")
    redirect("mainPage.html")
}

