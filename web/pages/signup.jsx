import '../public/css/style.css'
import React from "react";
import PizzaLayout from "../widget/pizza_layout";
import SignUpPanel from "../widget/signup_panel";
import {Col, Row} from "reactstrap";
import {useRouter} from "next/router";
import {redirectKey} from "../utils/pizza_url";

const SignUpPage = (props) => {
    const router = useRouter();
    const redirect = router.query[redirectKey];

    return (
        <PizzaLayout>
            <Row>
                <Col className={"text-center"} sm={{ size: 8, offset: 2 }} lg={{ size: 6, offset: 3 }}>
                    <SignUpPanel redirect={redirect}/>
                </Col>
            </Row>
        </PizzaLayout>
    );
};

export default SignUpPage;