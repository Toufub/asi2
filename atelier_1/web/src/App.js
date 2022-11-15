import './App.css';
import CardList from './components/card/card.list.js'
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

function App() {
  return (
    <div className="App">
      <h1>Welcome to card shop</h1>
      <Row>
        <Col md={4} lg={4}>
          <CardList/>
        </Col>
      </Row>
    </div>
  );
}

export default App;
