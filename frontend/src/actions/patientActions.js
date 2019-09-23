import {
  CREATE_NEW_PATIENT, DELETE_PATIENT_INFO,
  GET_A_PATIENT_BY_ID,
  GET_ALL_PATIENTS,
  GET_PATIENTS_BY_NAME,
  UPDATE_PATIENT_INFO
} from "../actions/types";

import {GET_A_PATIENT_BY_ID_ROUTE, GET_ALL_PATIENTS_ROUTE, GET_PATIENTS_BY_NAME_ROUTE} from "../apiRoutes";

export const getAllPatients = () => dispatch => {
  fetch(GET_ALL_PATIENTS_ROUTE())
    .then(res => res.json())
    .then(patients => dispatch({
      type: GET_ALL_PATIENTS,
      payload: patients
    }))
    .catch(err => console.log(err))
};

export const getPatientsByName = (name) => dispatch => {
  fetch(GET_PATIENTS_BY_NAME_ROUTE(name))
    .then(res=> res.json())
    .then(p => dispatch({
      type: GET_PATIENTS_BY_NAME,
      payload: p
    }))
    .catch(err => console.log(err))
};

export const getPatientById = (id) => dispatch => {
  fetch(GET_A_PATIENT_BY_ID_ROUTE(id))
    .then(res=> res.json())
    .then(p => dispatch({
      type: GET_A_PATIENT_BY_ID,
      payload: p
    }))
    .catch(err => console.log(err))
};
