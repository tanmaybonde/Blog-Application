import React, { useState } from 'react';
import { NavLink as ReactLink } from 'react-router-dom';
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

function CustomNavbar(args) {
  const [isOpen, setIsOpen] = useState(false);

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
              <NavLink tag={ReactLink} to="/login">
                Login
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/signup">Signup</NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/about">About</NavLink>
            </NavItem>


            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
                More
              </DropdownToggle>
              <DropdownMenu right>
              <DropdownItem tag={ReactLink} to="/services">Service</DropdownItem>
                <DropdownItem>Contact Me</DropdownItem>
                <DropdownItem divider />
                <DropdownItem>Reset</DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          <NavbarText >Youtube</NavbarText>
        </Collapse>
      </Navbar>
    </div>
  );
}

export default CustomNavbar;