export function postRequestWithoutToken(url, requestBody) {
    return new Promise(function (resolve, reject) {
      var request = new XMLHttpRequest();
      request.open("POST", url, true);
      request.setRequestHeader("Content-Type", "application/json");
      request.addEventListener("load", function () {
        if (request.status < 400) resolve(request);
        else reject(new Error("Request failed: " + request.statusText));
      });
      request.addEventListener("error", function () {
        reject(new Error("Network error"));
      });
      request.send(JSON.stringify(requestBody));
    });
}

export function postRequestWithToken(url, requestBody) {
  return new Promise(function (resolve, reject) {
    var request = new XMLHttpRequest();
    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader('Token', localStorage.getItem('token'))
    request.addEventListener("load", function () {
      if (request.status < 400) resolve(request);
      else reject(new Error("Request failed: " + request.statusText));
    });
    request.addEventListener("error", function () {
      reject(new Error("Network error"));
    });
    request.send(JSON.stringify(requestBody));
  });
}

export function getRequestWithToken(url) {
    return new Promise(function (resolve, reject) {
      var request = new XMLHttpRequest();
      request.open("GET", url, true);
      request.setRequestHeader('Token', localStorage.getItem('token'))
      request.addEventListener("load", function () {
        if (request.status < 400) {
          resolve(request);
        } else reject(new Error("Request failed: " + request.statusText));
      });
      request.addEventListener("error", function () {
        reject(new Error("Network error"));
      });
      request.send();
    });
}

export function getRequestWithoutToken(url) {
  return new Promise(function (resolve, reject) {
    var request = new XMLHttpRequest();
    request.open("GET", url, true);
    request.addEventListener("load", function () {
      if (request.status < 400) {
        resolve(request);
      } else reject(new Error("Request failed: " + request.statusText));
    });
    request.addEventListener("error", function () {
      reject(new Error("Network error"));
    });
    request.send();
  });
}

export function putRequestWithToken(url, requestBody) {
  return new Promise(function (resolve, reject) {
    var request = new XMLHttpRequest();
    request.open("PUT", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.setRequestHeader('Token', localStorage.getItem('token'))
    request.addEventListener("load", function () {
      if (request.status < 400) resolve(request);
      else reject(new Error("Request failed: " + request.statusText));
    });
    request.addEventListener("error", function () {
      reject(new Error("Network error"));
    });
    request.send(JSON.stringify(requestBody));
  });
}