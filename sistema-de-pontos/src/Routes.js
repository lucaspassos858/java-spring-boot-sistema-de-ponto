import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import Register from './components/Register';
import ClockingForm from './components/ClockingForm';
import ClockingView from './components/ClockingView';

export default function Routes(){
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Register} />
                <Route path="/clocking" component={ClockingForm} />
                <Route path="/view" component={ClockingView} />
            </Switch>
        </BrowserRouter>
    )
}