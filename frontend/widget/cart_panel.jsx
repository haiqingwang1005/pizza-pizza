import React, {useState} from "react";
import {
    Card,
    CardBody,
    CardHeader,
    Col,
    ListGroup,
    ListGroupItem,
    Container,
    Row,
    ButtonDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem
} from "reactstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faShoppingBag, faDollarSign} from '@fortawesome/free-solid-svg-icons'

const PizzaItem = (props) => {
    console.log(props.item);
    const pizza = props.item.pizza;

    const [isOpen, setOpen] = useState(false);
    const [quantity, setQuantity] = useState(1);
    const toggle = () => setOpen(!isOpen);

    const onPick = (val) => {
        setQuantity(val);
    };
    return (
        <ListGroupItem>
            <Container className="themed-container" fluid={true}>
                <Row>
                    <Col className={"text-left"}>
                        {pizza.displayName}
                    </Col>

                    <Col className={"text-right"}>
                        <div className={"float-right"}>
                            <FontAwesomeIcon icon={faDollarSign}/>
                            {pizza.price}
                        </div>
                    </Col>
                </Row>
                <Row>
                    <Col className={"text-right"}>
                        <div className={"float-right"}>
                            <ButtonDropdown isOpen={isOpen} toggle={toggle} className={"pizza-shop-cart-amount-picker"}>
                                <DropdownToggle caret size="sm">
                                    Qty: {quantity}
                                </DropdownToggle>
                                <DropdownMenu>
                                    <DropdownItem onClick={() => onPick(0)}>0 (Delete)</DropdownItem>
                                    <DropdownItem onClick={() => onPick(1)}>1</DropdownItem>
                                    <DropdownItem onClick={() => onPick(2)}>2</DropdownItem>
                                    <DropdownItem onClick={() => onPick(3)}>3</DropdownItem>
                                    <DropdownItem onClick={() => onPick(4)}>4</DropdownItem>
                                    <DropdownItem onClick={() => onPick(5)}>5</DropdownItem>
                                </DropdownMenu>
                            </ButtonDropdown>
                        </div>
                    </Col>
                </Row>
            </Container>
        </ListGroupItem>
    );
};
const PizzaPanel = (props) => {
    const pizzas = props.pizzas;
    return (
        <div>
            <ListGroup flush>
                <ListGroupItem color={"warning"} className={"text-left"}>Pizzas</ListGroupItem>
                {
                    pizzas.map((item) => {
                        return <PizzaItem key={item.pizza.id} item={item}/>
                    })
                }
            </ListGroup>
        </div>
    );
};

const CartPanel = (props) => {
    console.log(props.cartItems);

    return (
        <Row>
            <Col className={"text-center"} xs={{size: 10, offset: 1}}>
                <Card className={"pizza-shop-card-card"}>
                    <CardHeader>
                        <FontAwesomeIcon icon={faShoppingBag}/>{' '}My Shopping Cart
                    </CardHeader>
                    <CardBody>
                        <PizzaPanel pizzas={props.cartItems.pizzas}/>
                    </CardBody>
                </Card>
            </Col>
        </Row>
    );
};

export default CartPanel;

/*



                    <InputNumber prefix={"Quantity"} size={"sm"} defaultValue={1} max={99} min={0} className={"pizza-shop-cart-amount-picker"}/>

<InputNumber defaultValue={1} max={99} min={0} className={"float-right pizza-shop-cart-amount-picker"}/>
Quantity
 <NumericInput min={0} max={10} value={0} className={"pizza-shop-cart-amount-picker"}/>
 */