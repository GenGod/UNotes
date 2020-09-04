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
          <Logo />
        </NavigationLi>
        <NavigationLi>
          <NavigationLink to="/privacy">Privacy</NavigationLink>
        </NavigationLi>
        <NavigationLi>
          <NavigationLink to="/">Home</NavigationLink>
        </NavigationLi>
      </NavigationUl>
    </NavigationHeader>
  );
};

export default Navigation;
