import React, {Component} from 'react';

class PatientDetails extends Component {
  render() {
    return (
      <div>
        <p className="title">{this.props.patient.id}</p>
        <p>{this.props.patient.patientName}</p>
      </div>
    );
  }
}

export default PatientDetails;