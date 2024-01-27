import React, { useContext } from "react";
import {
  BoldLink,
  BoxContainer,
  FormContainer,
  Input,
  LineText,
  MutedLink,
  SubmitButton,
} from "./common";
import { Marginer } from "../marginer";
import { AccountContext } from './accountContext';
import { useState } from "react";
import { useEffect } from "react";

export function SignupForm(props) {

  const[data,setData]=useState({
    name:'',
    email:'',
    password:'',
    about:''
  })

  const[error,setError]=useState({
    errors:{},
    isError:false
  })

  useEffect(()=>{
    console.log(data)
  },[data])


  const handleChange=(event,property)=>{
    setData({...data,[property]:event.target.value})
  }

  const submitForm=(event)=>{
    event.preventDefault()


    console.log(data)
    // validte data

    // call the api
  }

  const { switchToSignin } = useContext(AccountContext);
  return (

    

    <BoxContainer>
      <FormContainer onSubmit={submitForm}>
        <Input type="name" placeholder="Full name" onChange={(e)=>handleChange(e,'name')} value={data.name}/>
        <Input type="email" placeholder="Email" onChange={(e)=>handleChange(e,'email')} value={data.email}/>
        <Input type="password" placeholder="Password" onChange={(e)=>handleChange(e,'password')} value={data.password}/>
        <textarea type="about" className="form-control" placeholder="Confirm password" onChange={(e)=>handleChange(e,'about')} value={data.about}></textarea>
      </FormContainer>
      <Marginer direction="vertical" margin={10} />
      <SubmitButton type="submit">Signup</SubmitButton>
      <Marginer direction="vertical" margin="5px" />
      <LineText>
        Already have an account?{" "}
        <BoldLink onClick={switchToSignin} href="#">
          Signin
        </BoldLink>
      </LineText>
    </BoxContainer>
  );
}