import React, { useState } from 'react';
import { NavLink as ReactLink, useNavigate } from 'react-router-dom';
import blog from "../photos/blog.png"
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText,
} from 'reactstrap';
import { useEffect } from 'react';
import { doLogOut, getCurrentUserDetail, isLoggedIn } from '../auth';

function CustomNavbar(args) {
  const [isOpen, setIsOpen] = useState(false);

let navigate=useNavigate();
  const [login, setLogin] = useState(false)
  const [user, setUser] = useState(undefined)

  useEffect(() => {

    setLogin(isLoggedIn())
    setUser(getCurrentUserDetail())
  }, [login])


  const logout=()=>{
    doLogOut(()=>{
      setLogin(false)
      
    })
    navigate("/")
  }

  const toggle = () => setIsOpen(!isOpen);

  return (
    <div>
      <Navbar {...args} expand={"md"} color='dark' dark={1}>
        <NavbarBrand tag={ReactLink} to="/">
          <img
            alt="logo"
            src={blog}
            style={{
              height: 40,
              width: 40
            }}
          />
          My Blogs
        </NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>

            <NavItem>
              <NavLink tag={ReactLink} to="/">
                Home
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/about">About</NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/services">Service</NavLink>
            </NavItem>

            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
                More
              </DropdownToggle>
              <DropdownMenu right>
                <DropdownItem>Contact Us</DropdownItem>
                <DropdownItem divider />
                <DropdownItem>FaceBook</DropdownItem>
                <DropdownItem>Instagram</DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          <Nav navbar>

            {
              login && (
                <>
                  <NavItem>
              <NavLink tag={ReactLink} to="/user/profile-info">Profile</NavLink>
            </NavItem>

                  <NavItem>
                    <NavLink>
                      {user.email}
                    </NavLink>
                  </NavItem>

                  <NavItem>
                    <NavLink onClick={logout}>
                      Logout
                    </NavLink>
                  </NavItem>
                </>
              )
            }

            {
              !login && (
                <>
                  <NavItem>
                    <NavLink tag={ReactLink} to="/login">
                      Login
                    </NavLink>
                  </NavItem>

                  <NavItem>
                    <NavLink tag={ReactLink} to="/signup">Signup</NavLink>
                  </NavItem>
                </>



              )
            }


          </Nav>
        </Collapse>
      </Navbar>
    </div>
  );
}

export default CustomNavbar;