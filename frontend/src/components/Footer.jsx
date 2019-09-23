import React, {Component} from 'react';
import styled from "styled-components";

class Footer extends Component {
  render() {
    return (
      <FooterWrapper>
        <footer className="footer">
          <div className="content has-text-centered">
            <p>
              <strong>Bulma</strong> by <a href="https://jgthms.com">Jeremy Thomas</a>. The source code is licensed
              <a href="http://opensource.org/licenses/mit-license.php"> MIT</a>. The website content
              is licensed <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY NC SA 4.0</a>.
            </p>
          </div>
        </footer>
      </FooterWrapper>
    );
  }
}

const FooterWrapper = styled.div`
  footer{
    position:absolute;
    left:0; 
    bottom:0;
    right:0;
  }
`;

export default Footer;