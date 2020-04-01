import '../public/css/style.css';

import React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faShoppingCart} from '@fortawesome/free-solid-svg-icons'
import { motion } from 'framer-motion';

const PizzaCartSticker = (props) => {
    return (
        <motion.div className={"pizza-shop-cart-sticker"}
                    whileHover={{ scale: 1.1 }}
                    whileTap={{ scale: 0.9 }}>
            <div className={"pizza-shop-cart-sticker-content"}>
                <FontAwesomeIcon icon={faShoppingCart} size={"lg"} color={"orange"}/>
            </div>
        </motion.div>
    );
};

export default PizzaCartSticker;
