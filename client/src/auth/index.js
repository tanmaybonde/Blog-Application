// isloggedin

export const isLoggedIn=()=>{
    let data=localStorage.getItem("data")
    if(data !=null){
        return true;
    }else{
        return false;
    }
};


// dologin
export const doLogin=(data)=>{
    localStorage.setItem("data",JSON.stringify(data))
};


// dologout
export const doLogOut=()=>{
    localStorage.removeItem("data")
}

// getcurrentuser
export const getCurrentUserDetail=()=>{
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).user;
    }
    else{
        return undefined;
    }
}




