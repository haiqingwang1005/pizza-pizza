import '../public/css/style.css';

import React from "react";

import PizzaLayout from "../widget/pizza_layout";
import MenuBoard from "../widget/menu_board";

import useSWR from "swr";
import pizza_backend_url, {fetcher} from "../utils/pizza_url";

const Menu = (props) => {
    const {data: toppingData, error: toppingError} = useSWR(pizza_backend_url + "/toppings", fetcher);

    let toppingsInfo = toppingData;
    if (toppingError) {
        toppingsInfo = undefined;
    }

    return (
        <PizzaLayout>
            <MenuBoard toppingsInfo={toppingsInfo}/>
        </PizzaLayout>
    );
};

export default Menu;