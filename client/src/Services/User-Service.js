import { myAxios } from "./helper";

export const signup=async(user)=>{
    const response = await myAxios.post('/auth/register', user);
    return response.data;

}

export const loginUser=async(user)=>{
    const response=await myAxios.post('/auth/login',user);
    return response.data;
}

