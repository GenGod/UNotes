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
  margin-right: 3em;
  font-weight: 700;
  font-size: larger;
`;

export const NavigationLink = styled(Link)`
  text-decoration: none;
  &:focus,
  &:visited,
  &:link,
  &:active {
    text-decoration: none;
  }
  color: #eee;

  &:hover {
    text-decoration: none;
    color: #aaa;
  }
`;

export const Logo = styled.img`
  width: 2em;
  height: 2em;
  margin-top: -0.2em;
`;
