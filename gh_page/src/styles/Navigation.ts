import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const NavigationHeader = styled.header`
  position: sticky;
  top: 0;
  background-color: #20232a;
  height: 4rem;
`;

export const NavigationUl = styled.ul`
  list-style-type: none;
  margin-block-start: 0;
  display: inline-flex;
  padding-top: 1em;
`;

export const NavigationLi = styled.li`
  margin-right: 1em;
`;

export const NavigationLink = styled(Link)`
  text-decoration: none;
  &:focus,
  &:hover,
  &:visited,
  &:link,
  &:active {
    text-decoration: none;
  }
  color: #eee;
`;

export const Logo = styled.img`
  background-image: url(images/logo.png);
  width: 4em;
  height: 4em;
`;
