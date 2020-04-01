import React from 'react';
import {Col, Container, Row, Spinner} from 'reactstrap';
import '../public/css/style.css'

const PizzaSpinner = (props) => {
    return (
        <Container className={"pizza-spinner-container"}>
            <Row>
                <Col className={"col-xs-12 text-center"}>
                    <Spinner type="grow" color="warning" />
                    <Spinner type="grow" color="danger" />
                </Col>
            </Row>
        </Container>
    );
};

export default PizzaSpinner;