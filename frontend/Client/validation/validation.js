export const validateLogin = (login) => {
  if (!login) {
    return false;
  }
  const reg = new RegExp(/^[a-z0-9]{3,18}/, "i");
    if (!reg.test(login)) {
      return false;
    }
  return true;
};

export const validatePassword = (password) => {
  if (!password) {
    return false;
  }
  const reg = new RegExp(/^[a-z0-9]{6,20}/, "i");
  if (!reg.test(password)) {
    return false;
  }
  return true;
};

export const validateEmail = (email) => {
  if (!email) {
    return false;
  }
  const reg = new RegExp(/^[a-zA-Z0-9._-]+@[a-z0-9-]+.+.[a-z]{2,4}$/);
  if (!reg.test(email)) {
    return false;
  }
  return true;
};
