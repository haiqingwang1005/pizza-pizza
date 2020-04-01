import React from "react";
import {PizzaIcon} from "../icons/pizza-icon";

const SizeIcon = (props) => {
    const sizeData = props.sizeData;
    const name = sizeData.name;

    return (
        <div>
            <PizzaIcon size={name}/>{" "}
            {
                `${sizeData.title}, ${sizeData.inch} inches`
            }
        </div>
    );
};

export default SizeIcon;
