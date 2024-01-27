import { useState } from "react";
import Base from "../Components/Base";
import Account from "../Components/LoginSignup/accountBox/index"
import styled from "styled-components";
const AppContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
const Login = () => {
    const loginState = useState('signin');
    return (
        <Base>
            <AppContainer>
                <Account loginState={loginState} />
            </AppContainer>
        </Base>
    );
};

export default Login;