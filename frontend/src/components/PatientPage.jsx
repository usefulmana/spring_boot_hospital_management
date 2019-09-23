import React, {Component} from 'react';
import styled from "styled-components";
import {getPatientById} from "../actions/patientActions";
import {connect} from 'react-redux';
import PatientDetails from "./PatientDetails";

class PatientPage extends Component {
  constructor(props){
    super(props);
    this.state = {
    };
  }
  componentDidMount() {
    const {match: {params}} = this.props;
    this.props.getPatientById(params.id);
  }

  render() {
    const displayPatientInfo = () => {
      return (
        <div>
          <div className="tile is-ancestor">
            <div className="tile is-4 is-vertical is-parent">
              <div className="tile is-child box">
                <PatientDetails patient={this.props.patients.item}/>
              </div>
            </div>
            <div className="tile is-parent">
              <div className="tile is-child box">
                <div className="columns">
                  <div className="column">
                    <p className="title">Visit History</p>
                  </div>
                  <div className="column has-text-right">
                    <a className="button is-normal">View All</a>
                  </div>
                </div>

                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam semper diam at erat pulvinar, at
                  pulvinar felis blandit. Vestibulum volutpat tellus diam, consequat gravida libero rhoncus ut. Morbi
                  maximus, leo sit amet vehicula eleifend, nunc dui porta orci, quis semper odio felis ut quam.</p>
                <p>Suspendisse varius ligula in molestie lacinia. Maecenas varius eget ligula a sagittis. Pellentesque
                  interdum, nisl nec interdum maximus, augue diam porttitor lorem, et sollicitudin felis neque sit amet
                  erat. Maecenas imperdiet felis nisi, fringilla luctus felis hendrerit sit amet. Aenean vitae gravida
                  diam, finibus dignissim turpis. Sed eget varius ligula, at volutpat tortor.</p>
                <p>Integer sollicitudin, tortor a mattis commodo, velit urna rhoncus erat, vitae congue lectus dolor
                  consequat libero. Donec leo ligula, maximus et pellentesque sed, gravida a metus. Cras ullamcorper a
                  nunc ac porta. Aliquam ut aliquet lacus, quis faucibus libero. Quisque non semper leo.</p>
              </div>
            </div>

          </div>
          <div className="tile is-12 boxy">
            <article className="tile is-child notification is-link has-text-white">
              <div className="columns">
                <div className="column has-text-right">
                  <i className="fas fa-calendar-alt fa-7x"/>
                </div>
                <div className="column has-text-left">
                  <strong style={{fontSize: 45}}> New Visit </strong>
                </div>
              </div>
            </article>
          </div>
        </div>
      )
    };
    return (
      <PatientInfoWrapper>
        <div className="container">
          {displayPatientInfo()}
        </div>
      </PatientInfoWrapper>
    );
  }
}


const PatientInfoWrapper = styled.div`
  .container{
    margin: 7em auto;
  }
  .box, .boxy{
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  }
  .has-text-left{
    margin:auto;
  }
`;
const mapStateToProps = state => ({
  patients: state.patients
});
export default connect(mapStateToProps, {getPatientById}) (PatientPage);