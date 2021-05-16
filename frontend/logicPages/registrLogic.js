import postRequestWithoutToken from "../src/request";
import urls from "../constans/const";
import redirect from "../src/redirect";
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
  if (validateLogin(bodyToValidate.login) && validatePassword(bodyToValidate.password) && validateEmail(bodyToValidate.email) && (bodyToValidate.password === bodyToValidate.repeatPass)) {
    postRequestWithoutToken(urls.regUrl, bodyToSend).then(function (data) {
      if ((data.status = 200)) {
        redirect("index.html");
      };
    });
  } else {
    console.log("Не валидно...");
  };
//         if(data.status = 409){
//             console.log('sosi lapu');
//         }
//     })
// }

guestEnter.addEventListener("click", enterAsGuest);

function enterAsGuest() {
  localStorage.setItem("role", "guest");
  redirect("mainPage.html");
}
