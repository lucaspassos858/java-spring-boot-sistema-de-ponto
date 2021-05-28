import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import Register from './components/Register';
import ClockingForm from './components/ClockingForm';

export default function Routes(){
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Register} />
                <Route path="/clocking" component={ClockingForm} />
            </Switch>
        </BrowserRouter>
    )
}