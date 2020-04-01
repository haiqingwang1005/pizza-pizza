import PizzaNav from "./nav";
import PizzaFooter from "./footer";
import React from "react";
import {Container} from "reactstrap";
import {motion} from 'framer-motion';

const PizzaLayout = (props) => {
    let easing = [0.175, 0.85, 0.42, 0.96];

    const variants = {
        exit: {
            x: 100,
            opacity: 0,
            transition: {
                duration: 0.5,
                ease: easing,
            }
        },
        enter: {
            x: 0,
            opacity: 1,
            transition: {
                duration: 0.5,
                ease: easing,
            }
        }
    };

    return (
        <motion.div initial="exit"
                    animate="enter"
                    exit="exit"
                    variants={variants}>
            <div style={props.style} className={props.className}>
                <PizzaNav/>
                <Container className={"pizza-body"}>
                    {props.children}
                </Container>
                <PizzaFooter/>
            </div>
        </motion.div>
    );
};

export default PizzaLayout;