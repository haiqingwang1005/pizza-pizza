import React from "react";
import PizzaCarousel from "../widget/carousel";
import PizzaLayout from "../widget/pizza_layout";
import StoreCards from "../widget/stores";
import useSWR from 'swr';
import pizza_backend_url, {fetcher} from "../utils/pizza_url";

const items = [
    {
        src: 'images/pizza.jpg',
        caption: 'Our pizza is delicious',
        header: 'Delicious',
        key: '1'
    },
    {
        src: 'images/vege_meat.jpg',
        caption: 'Organic material, nutritious',
        header: 'Healthy',
        key: '2'
    },
    {
        src: 'images/dough.jpg',
        caption: 'Just out of oven',
        header: 'Fresh',
        key: '3'
    }
];

export default function WelcomePage() {

    const {data, error} = useSWR(pizza_backend_url + "/stores", fetcher);
    let storeInfo = data;
    if (error) {
        storeInfo = undefined;
    }
    return (
        <PizzaLayout>
            <PizzaCarousel items={items}/>
            <StoreCards storesInfo={storeInfo}/>
        </PizzaLayout>
    );
}