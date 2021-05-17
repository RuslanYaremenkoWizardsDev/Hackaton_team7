import frontUrl from "../constans/const" 

export default function redirect(path){
    return window.location.pathname = `${path}`
}

// module.exports = redirect