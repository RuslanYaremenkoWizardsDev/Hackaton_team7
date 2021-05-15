export const validateLogin = (login) => {
  //const reg = new RegExp(/^[a-z0-9]{3,18}/, "i");
  //   if (!reg.test(login)) {
  //     return false;
  //   }
  if (login.length < 3 || login.length > 18) {
    return "Логин должен содержать от 3 до 18 символов";
  }
  if (login !== (/^[a-z0-9_-]/, "i")) {
    return "Логин должен содержать символы  английского алфавита a-z; A-Z и 0-9"
  }
  return true;
};

export const validatePassword = (password) => {
//   const reg = new RegExp(/^[a-z0-9]{6,20}/, "i");
//   if (!reg.test(password)) {
//     return false;
//   }
if (password.length < 6 || password.length > 20) {
    return "Пароль должен содержать от 6 до 20 символов";
  }
  if (password !== (/^[a-z0-9_-]/, "i")) {
    return "Логин должен содержать символы  английского алфавита a-z; A-Z и 0-9"
  }
  return true;
};

export const validateEmail = (email) => {
  const reg = new RegExp(/^[a-zA-Z0-9._-]+@[a-z0-9-]+.+.[a-z]{2,4}$/);
  if (!reg.test(email)) {
    return false;
  }
  return true;
};

//   const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$&@*-])[A-Za-z\d$&@*-]{5,}$/;
