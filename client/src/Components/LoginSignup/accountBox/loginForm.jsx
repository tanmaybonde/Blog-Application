import React, { useContext, useState } from "react";
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
import { toast } from "react-toastify";
import { loginUser } from "../../../Services/User-Service";
import { doLogin } from "../../../auth";

export function LoginForm(props) {


  const [loginDetails, setloginDetails] = useState({
    username: '',
    password: '',
  })




  const handleChange = (event, field) => {
    let acutalValue = event.target.value
    setloginDetails({
      ...loginDetails,
      [field]: acutalValue
    })
  }

  const handleformsubmit = (event) => {
    event.preventDefault();
    console.log(loginDetails)

    if (loginDetails.username.trim() === "" || loginDetails.password.trim() === "") {
      toast.error("Username and Password is Required")
      return;
    }

    loginUser(loginDetails)
      .then((data) => {
        console.log(data);
        
        doLogin(data,()=>{  
          console.log("login detail save")
          // redirect to dashboard page
        })


        toast.success("User Login Successfully");
      })
      .catch((error) => {
        console.log(error);
        if (error.response && error.response.data && error.response.data.message) {
          toast.error(error.response.data.message);
        } else {
          toast.error("An error occurred during login");
        }
      })
      .finally(() => {
        // Clear the loginDetails regardless of success or failure
        setloginDetails({
          username: "",
          password: "",
        });
      });

  }


  const { switchToSignup } = useContext(AccountContext);

  return (
    <BoxContainer>
      <FormContainer onSubmit={handleformsubmit}>
        {/* by using this value attribute this willl bind ahe upper attaribte to our field   */}
        <Input type="email" placeholder="Email" value={loginDetails.username} onChange={(e) => handleChange(e, 'username')} />
        <Input type="password" placeholder="Password" value={loginDetails.password} onChange={(e) => handleChange(e, 'password')} />
        <div className="text-center">
          <SubmitButton type="submit">Signin</SubmitButton>
        </div>
      </FormContainer>
      <Marginer direction="vertical" margin={10} />
      <MutedLink href="#">Forget your password?</MutedLink>
      <Marginer direction="vertical" margin="1.6em" />
      <Marginer direction="vertical" margin="5px" />
      <LineText>
        Don't have an accoun?{" "}
        <BoldLink onClick={switchToSignup} href="#">
          Signup
        </BoldLink>
      </LineText>
    </BoxContainer>
  );
}