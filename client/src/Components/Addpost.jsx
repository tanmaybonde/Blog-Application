import React from "react";
import { useForm } from "react-hook-form";
import { Container } from "reactstrap";
import styled from "styled-components";

const Styles = styled.div`
 background: lavender;
 padding: 25px;
 margin-top: 10px;

 h1 {
   border-bottom: 1px solid white;
   color: #3d3d3d;
   font-family: sans-serif;
   font-size: 20px;
   font-weight: 600;
   line-height: 24px;
   padding: 10px;
   text-align: center;
 }

 form {
   background: white;
   border: 1px solid #dedede;
   display: flex;
   flex-direction: column;
   justify-content: space-around;
   margin: 0 auto;
   max-width: 1000px;
   padding: 30px 50px;
 }

 input {
   border: 1px solid #d9d9d9;
   border-radius: 4px;
   box-sizing: border-box;
   padding: 10px;
   width: 100%;
 }

 label {
   color: #3d3d3d;
   display: block;
   font-family: sans-serif;
   font-size: 14px;
   font-weight: 500;
   margin-bottom: 5px;
 }

 .error {
   color: red;
   font-family: sans-serif;
   font-size: 12px;
   height: 30px;
 }

 textarea {
    height: 300px;
    overflowY: auto; /* Show scrollbar when content exceeds the height */
  }

 .Button {
   background-color: #6976d9;
   color: white;
   font-family: sans-serif;
   font-size: 14px;
   margin: 20px 0px;
`;

  
function Form() {
    const { register, handleSubmit } = useForm();

    return (
        <form >
            <h1>What going in your mind? </h1>
            <label>Post Title</label>
            <input id="title" />
            <label>Post Content</label>
            <textarea id="content" style={{ height: '300px' }} />
            <label htmlFor="category">Post Category</label>
            <select id="category" style={{ height: '40px' }}>
                <option value="category1">Category 1</option>
                <option value="category2">Category 2</option>
                <option value="category3">Category 3</option>
                {/* Add more options as needed */}
            </select>
            <Container className="text-center pt-3">
                    <button type="submit" className="btn btn-primary rounded-pill" style={{ height: '40px' }}>Submit</button>
                    <button type="reset" className="btn btn-danger rounded-pill ms-2" style={{ height: '40px' }}>Reset</button>
               </Container>
        </form>
    );
}

const Addpost = () => {
    return (
        <Container>
            <Styles>
                <Form />
             
            </Styles>
        </Container>
    );
}

export default Addpost;
