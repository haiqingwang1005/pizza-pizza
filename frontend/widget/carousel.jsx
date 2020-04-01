import '../public/css/style.css'
import React from "react";
import {Carousel} from "react-bootstrap";

const PizzaCarousel = (props) => {
    return (
        <Carousel>
            {
                props.items.map((item) => {
                    const key = item.key;
                    const header = item.header;
                    const caption = item.caption;
                    const src = item.src;
                    return (
                        <Carousel.Item style={{"backgroundImage": `url(${src})`}} key={key}>
                            <Carousel.Caption className={"d-none d-sm-block"}>
                                <h3>{header}</h3>
                                <p>{caption}</p>
                            </Carousel.Caption>
                        </Carousel.Item>
                    );
                })
            }
        </Carousel>
    );
};

export default PizzaCarousel;
