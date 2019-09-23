import React, {Component} from 'react';
import styled from "styled-components";
class Home extends Component {
  render() {
    return (
      <HomeWrapper>
        <div className="container">
          <div className="tile is-ancestor">
            <div className="tile is-vertical is-12">
              <div className="tile">
                <div className="tile is-parent is-vertical">
                  <article className="tile is-child notification is-primary has-text-centered">
                    <i className="fas fa-stethoscope fa-7x"/>
                    <br/>
                    <br/>
                    <strong style={{fontSize: 25}}> Diseases </strong>
                  </article>
                  <article className="tile is-child notification is-warning has-text-centered has-text-white">
                    <i className="fas fa-pills fa-7x"/>
                    <br/>
                    <br/>
                    <strong style={{fontSize: 25}}> Medicines </strong>
                  </article>
                </div>
                <div className="tile is-parent">
                  <article className="tile is-child notification is-info has-text-white has-text-centered">
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <i className="fas fa-procedures fa-7x"/>
                    <br/>
                    <br/>
                    <strong style={{fontSize: 25}}> Add A New Patient </strong>
                  </article>
                </div>
              </div>
              <div className="tile is-parent">
                <article className="tile is-child notification is-danger has-text-white">

                  <div className="columns">
                    <div className="column has-text-right">
                      <i className="fas fa-microscope fa-7x"/>
                    </div>
                    <div className="column has-text-left">
                      <strong style={{fontSize: 45}}> Labs </strong>
                    </div>
                  </div>
                </article>
              </div>
            </div>
          </div>
        </div>
      </HomeWrapper>
    );
  }
}


const HomeWrapper = styled.div`
  .container{
    margin: 7em auto;
  }
  .is-child{
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  }
  .is-child:hover{
    transform: scale(1.05);
  }
  .has-text-left{
    margin:auto;
  }
`;
export default Home;