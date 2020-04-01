import PizzaSpinner from "./spinner";
import EmptyCart from "./cart_empty";
import Router from "next/router";
import React from "react";
import CartPanel from "./cart_panel";

const CartBoard = (props) => {
    const data = props.cartData;
    if (!data) {
        return (
            <PizzaSpinner/>
        );
    } else if (data.status === 404) {
        console.info('Empty cart.');
        return (
            <EmptyCart/>
        );
    } else if (data.status === 200) {
        return <CartPanel cartItems={data.response}/>
    } else if (data.status === 401 || data.status === 403) {
        console.info('Not authorized.');
        Router.push('/login');
        return <div/>
    } else {
        Router.push('/welcome');
        return <div/>
    }
};

export default CartBoard;
