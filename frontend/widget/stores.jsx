import '../public/css/style.css'

import React from "react";
import {
    Card,
    CardBody,
    CardImg,
    CardTitle,
    CardText,
    Container, Row, Col, CardHeader
} from "reactstrap";
import PizzaSpinner from "./spinner";
import pizza_backend_url from "../utils/pizza_url";

const getImageUrl = (imagePath) => {
    return pizza_backend_url + imagePath;
};

const StoreCard = (props) => {
    return (
        <Card className={"store-card"}>
            <CardImg top width="100%" src={getImageUrl(props.storeInfo.imagePath)}
                     alt="Card image cap"/>
            <CardBody>
                <CardTitle className={"store-card-title "}>{props.storeInfo.name}</CardTitle>
                <CardText className="text-right">
                    {`${props.storeInfo.address.address1}\n`}
                    <br/>
                    {`${props.storeInfo.address.city} ${props.storeInfo.address.state}, ${props.storeInfo.address.zip}`}
                </CardText>
            </CardBody>
        </Card>
    );
};

const StoreCards = (props) => {
    if (props.storesInfo) {
        return (
            <Container>
                <Row>
                    {
                        props.storesInfo.map((item) => {
                            return (
                                <Col sm="12" md="6" lg="4" key={item.id}>
                                    <StoreCard storeInfo={item}/>
                                </Col>
                            )
                        })
                    }
                </Row>
            </Container>
        );
    } else {
        return (<PizzaSpinner/>);
    }

};

export default StoreCards;
