import {Alert} from "react-bootstrap";
import React from "react";

export const FailTitle = (props) => {
    return (
        <Alert variant={'danger'}>
            {props.message}
        </Alert>
    );
};
