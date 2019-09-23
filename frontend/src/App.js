import React from 'react';
import NavBar from "./components/NavBar";
import {Switch, Route} from 'react-router-dom'
import Home from "./components/Home";
import Footer from "./components/Footer";
import PatientPage from "./components/PatientPage";
import Default from "./components/Default";

function App() {
  return (
    <div className="App">
      <NavBar/>
        <Switch>
          <Route exact path="/" component={Home}/>
          <Route path="/patients/:id" component={PatientPage}/>
          <Route component={Default}/>
        </Switch>
      {/*<Footer/>*/}
    </div>
  );
}

export default App;
