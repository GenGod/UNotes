import React from 'react';
import {
  NavigationHeader,
  NavigationUl,
  NavigationLi,
  NavigationLink,
  Logo,
} from '../styles/Navigation';

const Navigation = (): JSX.Element => {
  return (
    <NavigationHeader>
      <NavigationUl>
        <NavigationLi>
          <NavigationLink title="Home" to="/">
            <Logo src="images/logo.png" />
          </NavigationLink>
        </NavigationLi>
        <NavigationLi>
          <NavigationLink title="Privacy" to="/privacy">
            Privacy
          </NavigationLink>
        </NavigationLi>
        <NavigationLi>
          <NavigationLink title="Home" to="/">
            Home
          </NavigationLink>
        </NavigationLi>
      </NavigationUl>
    </NavigationHeader>
  );
};

export default Navigation;
