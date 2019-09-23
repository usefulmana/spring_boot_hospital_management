import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import styled from 'styled-components';

class NavBar extends Component {
  render() {
    return (
      <NavBarWrapper>
        <nav className="navbar is-primary" role="navigation" aria-label="main navigation">
          <div className="navbar-brand">
            <a className="navbar-item">
              <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28"/>
            </a>

            <a role="button" className="navbar-burger burger" aria-label="menu" aria-expanded="false"
               data-target="navbarBasicExample">
              <span aria-hidden="true"></span>
              <span aria-hidden="true"></span>
              <span aria-hidden="true"></span>
            </a>
          </div>

          <div id="navbarBasicExample" className="navbar-menu">
            <div className="navbar-start">
              <Link to="/" className="navbar-item" style={{ textDecoration: 'none' }}>
                Home
              </Link>
            </div>
            <div className="navbar-end">
              <div className="navbar-item">
                <div className="buttons">
                  <a className="button is-primary">
                    Log in
                  </a>
                </div>
              </div>
              <div className="navbar-item">
                <div className="field">
                  <div className="control has-icons-right">
                    <input className="input is-success"
                           type="text"
                           placeholder="Enter Patient's Name"
                           name="query"/>
                    <span className="icon is-small is-right">
                  <i className="fas fa-search"/>
                </span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </nav>
      </NavBarWrapper>
    );
  }
}
const NavBarWrapper = styled.div`
  .field{
    text-align: center;
    margin: auto;
  }
`;


export default NavBar;