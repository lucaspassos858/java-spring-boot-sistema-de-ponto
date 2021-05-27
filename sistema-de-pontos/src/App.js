import React, { Component } from 'react';
import PointForm from './ClockingForm';
import Register from './Register';
import "./styles.css";
class App extends Component {
  render() {
    const isLoggedIn = true;
    const renderCondition = () => {
        if(isLoggedIn){
            return <PointForm/>
        } else{
            return <Register />
        }
    } 

    return (
      <div className="App">
        {renderCondition()}
      </div>
    );
  }
}

export default App;