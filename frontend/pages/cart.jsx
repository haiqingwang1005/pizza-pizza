import '../public/css/style.css';

import PizzaLayout from "../widget/pizza_layout";
import React from "react";
import useSWR from "swr";
import {fetcherWithToken, generateRedirectParams} from "../utils/pizza_url";
import Router from "next/router";
import CartBoard from "../widget/cart_board";
import { useCookies } from 'react-cookie'

const Cart = (props) => {
    const [cookies] = useCookies(['token']);
    const {data, error} = useSWR(["/cart", cookies.token], fetcherWithToken);

    if (error) {
        console.error('fetching cart failed.');
        Router.push('/login?' + generateRedirectParams('/cart'));
        return <div/>
    }
    return (
        <PizzaLayout>
            <CartBoard cartData={data}/>
        </PizzaLayout>
    );
};

export default Cart;