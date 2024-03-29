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
import { signup } from "../../../Services/User-Service";
import { toast } from "react-toastify";

export function SignupForm(props) {

	const [data, setData] = useState({
		name: '',
		email: '',
		password: '',
		about: ''
	})

	const [error, setError] = useState({
		errors: {},
		isError: false
	})

	useEffect(() => {
		console.log(data)
	}, [data])


	const handleChange = (event, property) => {
		setData({ ...data, [property]: event.target.value })
	}

	const submitForm = (event) => {
		event.preventDefault();
	
		if (!data.name || !data.email || !data.password || !data.about) {
			toast.error("Form Data is Invalid !!!");
			return;
		}
		console.log(data);
	
		// validate data (you can add your validation logic here)
	
		// call the api
		signup(data)
			.then((resp) => {
				console.log(resp);
				console.log("success");
				toast.success("User is registered Successfully");
				setData({
					name: '',
					email: '',
					password: '',
					about: ''
				});
			})
			.catch((error) => {
				console.log(error);
				console.log("error log");
	
				if (error.response && error.response.data) {
					const errors = error.response.data;
					Object.keys(errors).forEach((field) => {
						toast.error(`${field}: ${errors[field]}`);
					});
				} else {
					toast.error("An error occurred. Please try again later.");
				}
			});
	};
	

	const { switchToSignin } = useContext(AccountContext);
	return (



		<BoxContainer>
			<FormContainer onSubmit={submitForm}>
				<Input type="name" placeholder="Full name" onChange={(e) => handleChange(e, 'name')} value={data.name} onInvalid={true}/>
				<Input type="email" placeholder="Email" onChange={(e) => handleChange(e, 'email')} value={data.email} />
				<Input type="password" placeholder="Password" onChange={(e) => handleChange(e, 'password')} value={data.password} />
				<textarea type="about" className="form-control" placeholder="About" onChange={(e) => handleChange(e, 'about')} value={data.about}></textarea>
				<Marginer direction="vertical" margin={10} />
				<Marginer direction="vertical" margin="5px" />
				<div className="text-center">
					<SubmitButton type="submit">Signup</SubmitButton>
				</div>
			</FormContainer>
			<LineText>
				Already have an account?{" "}
				<BoldLink onClick={switchToSignin}>
					Signin
				</BoldLink>
			</LineText>
		</BoxContainer>
	);
}