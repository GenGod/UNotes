import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import Privacy from './Privacy';

const MainRouter: React.FunctionComponent = () => {
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <Privacy />
        </Route>
      </Switch>
    </Router>
  );
};

export default MainRouter;
