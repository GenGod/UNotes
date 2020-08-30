import styled from 'styled-components';

const Root = styled.div`
  padding-left: 2em;
  padding-right: 2em;
  padding-top: 2em;
  padding-bottom: 2em;
  background-color: #eee;
  color: #222;
  display: block;
`;

const Header = styled.h1`
  padding-bottom: 1em;
  text-align: center;
`;

const MainDiv = styled.div`
  text-align: left;
  box-shadow: 0 0 0 0 rgba(0, 0, 0, 0.25);
  padding-left: 1em;
  padding-right: 1em;
  padding-top: 1em;
  padding-bottom: 1em;
  background-color: #fff;
`;

export { Root, Header, MainDiv };
