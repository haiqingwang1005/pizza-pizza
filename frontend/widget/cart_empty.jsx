import React from "react";
import EmptyCartIcon from "../icons/empty-cart-icon";
import {Col, Container, Row, Button} from "react-bootstrap";
import Link from 'next/link';

const EmptyCart = (props) => {
    return (
        <Container
            className={"d-flex flex-column justify-content-center align-items-center pizza-shop-cart-empty-container"}>
            <Row className={"pizza-shop-cart-empty-row"}>
                <Col>
                    <EmptyCartIcon/>
                </Col>
            </Row>
            <Row className={"pizza-shop-cart-empty-row"}>
                <Col>
                    {'Your Cart is Empty. Add Items to Get Started'}
                </Col>
            </Row>
            <Row className={"pizza-shop-cart-empty-row"}>
                <Col>
                    <Link href="/menu">
                        <Button variant="secondary">
                            Check Our Menu!
                        </Button>
                    </Link>
                </Col>
            </Row>
        </Container>

    );
};

export default EmptyCart;
