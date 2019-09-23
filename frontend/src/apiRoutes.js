export const GET_ALL_PATIENTS_ROUTE = () => {
  return "http://localhost:8080/api/v1/patients";
};
export const GET_PATIENTS_BY_NAME_ROUTE = (name) => {
  return `http://localhost:8080/api/v1/patients/byName/${name}`
};
export const GET_A_PATIENT_BY_ID_ROUTE = (id) => {
  return `http://localhost:8080/api/v1/patients/${id}`
};
export const CREATE_PATIENT_ROUTE = () => {
  return "http://localhost:8080/api/v1/patients";
};
export const UPDATE_PATIENT_INFO_ROUTE = (id) => {
  return `http://localhost:8080/api/v1/patients/${id}`
};
export const DELETE_PATIENT_INFO_ROUTE = (id) => {
  return `http://localhost:8080/api/v1/patients/${id}`
};