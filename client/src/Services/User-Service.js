import { myAxios } from "./helper";

export const signup=(user)=>{
    return myAxios.post('/auth/register',user).then((response)=>response.data)

    // let data = JSON.stringify({
    //   "name": "Sumit",
    //   "email": "sumit@gmail.com",
    //   "password": "abc",
    //   "about": "I am Web Developer"
    // });
    
    // let config = {
    //   method: 'post',
    //   maxBodyLength: Infinity,
    //   url: 'http://localhost:9090/auth/register',
    //   headers: { 
    //     'Content-Type': 'application/json'
    //   },
    //   data : data
    // };
    
    // return myAxios.request(config).then((response)=>response.data)

    
}

