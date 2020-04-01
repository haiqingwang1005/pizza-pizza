import '../public/css/style.css';
import {OverlayTrigger, Tooltip} from "react-bootstrap";
import React from "react";

const Hover = (props) => {
    return (
        <div>
            <OverlayTrigger key={"right"} placement={"right"} overlay={
                <Tooltip id={"tooltip-right"}>
                    {props.words}
                </Tooltip>
            }>
                {props.children}
            </OverlayTrigger>
        </div>
    );
};

export default Hover;