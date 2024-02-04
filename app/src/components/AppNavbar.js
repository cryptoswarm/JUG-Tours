import React, { useContext, useState } from "react";
import {
  Collapse,
  Nav,
  Navbar,
  NavbarBrand,
  NavbarToggler,
  NavItem,
  NavLink,
} from "reactstrap";
import { Link } from "react-router-dom";
import { CurrentUserContext } from "./Home";

const AppNavbar = ({ action }) => {
  const [isOpen, setIsOpen] = useState(false);
  const currentUser = useContext(CurrentUserContext);

  console.log("value of currentUserContext =", currentUser);

  return (
    <Navbar color="dark" dark expand="md">
      <NavbarBrand tag={Link} to="/">
        Home
      </NavbarBrand>
      <NavbarToggler
        onClick={() => {
          setIsOpen(!isOpen);
        }}
      />
      <Collapse isOpen={isOpen} navbar>
        <Nav className="justify-content-end" style={{ width: "100%" }} navbar>
          <NavItem>
            <NavLink href="https://www.linkedin.com/in/mokhtar-safir-623b4843/">
              @cryptodev
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="https://github.com/cryptoswarm/JUG-Tours">
              GitHub
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink onClick={action}>
              {currentUser ? "Logout" : "Login"}
            </NavLink>
          </NavItem>
        </Nav>
      </Collapse>
    </Navbar>
  );
};

export default AppNavbar;
