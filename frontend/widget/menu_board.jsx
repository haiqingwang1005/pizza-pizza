import {
    Container,
    Row,
    Col,
    Card,
    CardHeader,
    CardBody,
    CardImg,
    CardText,
    CardFooter,
} from "reactstrap";
import React, {useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCartPlus, faStar} from '@fortawesome/free-solid-svg-icons'
import {getImageUrl} from "../utils/pizza_url";
import PizzaSpinner from "./spinner";
import Hover from "./hover";
import MenuModal from "./menu_modal";
import PizzaCartSticker from "./shop_cart_sticker";

const MenuItem = (props) => {
    const [show, setShow] = useState(false);
    return (
        <Card className={"pizza-menu-item"}>
            <CardHeader className={"text-uppercase text-center"}>
                <div className={"float-left"}>
                    {props.topping.title}
                </div>
                {
                    props.topping.isPremium &&
                    <Hover words={"Chief Recommends!"}>
                        <FontAwesomeIcon className={"float-right"} icon={faStar}/>
                    </Hover>
                }
            </CardHeader>
            <CardImg width="100%" src={getImageUrl(props.topping.imagePath)} alt="Card image cap"/>
            <CardBody>
                <CardText>
                    {props.topping.description}
                </CardText>
            </CardBody>
            <CardFooter className={"text-center"}>
                <Hover words={"Add to Chart"}>
                    <a className={"pizza-clickable"} onClick={() => setShow(true)}>
                        <FontAwesomeIcon icon={faCartPlus} words={"Add to Chart"}/>
                    </a>
                </Hover>
            </CardFooter>
            <MenuModal show={show}
                       onHide={() => setShow(false)}
                       topping={props.topping}/>
        </Card>
    );
};

const MenuSection = (props) => {
    return (
        <Container className={"pizza-menu-section"}>
            <Card>
                <CardHeader className={"text-center"}>
                    {props.title}
                </CardHeader>
                <CardBody>
                    <Container>
                        <Row>
                            {
                                props.toppings.map((item) => {
                                    return (
                                        <Col sm="12" md="6" lg="4" key={item.name}>
                                            <MenuItem topping={item}/>
                                        </Col>
                                    );
                                })
                            }
                        </Row>
                    </Container>
                </CardBody>
            </Card>
        </Container>
    );
};

const categorizeToppings = (toppings) => {
    const res = [];
    const meat = [];
    const vege = [];
    toppings.map((item) => {
        if (item.toppingType === 'vegetable') {
            vege.push(item);
        }
        if (item.toppingType === 'meat') {
            meat.push(item);
        }
    });
    res.push({key: "Meat", toppings: meat});
    res.push({key: "Vegetable", toppings: vege});
    return res;
};

const MenuBoard = (props) => {
    const toppingsInfo = props.toppingsInfo;
    if (!toppingsInfo) {
        return (<PizzaSpinner/>);
    } else {
        const categorized = categorizeToppings(toppingsInfo);
        return (
            <div>
                <PizzaCartSticker/>
                {
                    categorized.map((item) => {
                        return <MenuSection toppings={item.toppings}
                                            key={item.key}
                                            title={item.key}/>
                    })
                }

            </div>

        );
    }
};

export default MenuBoard;