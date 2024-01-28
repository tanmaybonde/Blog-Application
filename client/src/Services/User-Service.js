import { myAxios } from "./helper";

export const signup=(user)=>{
    return myAxios.post('/auth/register').then((response)=>response.data)
}