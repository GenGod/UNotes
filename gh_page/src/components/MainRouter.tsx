import React from 'react';
import { HashRouter as Router, Route } from 'react-router-dom';

import Privacy from './Privacy';
import Home from './Home';
import Navigation from './Navigation';

const MainRouter: React.FunctionComponent = () => {
  return (
    <Router basename="/">
      <Navigation />
      <Route exact path="/" component={Home} />
      <Route path="/privacy" component={Privacy} />
    </Router>
  );
};

export default MainRouter;
