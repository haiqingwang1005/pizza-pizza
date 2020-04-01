import React, {useState} from "react";
import '../public/css/style.css'
import {
    Card,
    Col,
    Container,
    Row,
    CardTitle,
    CardBody,
    CardFooter,
    Form,
    Input,
    Button,
    FormGroup,
    Label
} from "reactstrap";
import Link from "next/link";
import sanitize from "../utils/sanitize";
import {generateRedirectParams, pizzaPostRequest} from "../utils/pizza_url";
import {FailTitle} from "./login_alert";
import Router from 'next/router'


const LoginPanel = (props) => {

    let [loginFail, setLoginFail] = useState({isFailed: false, message: ''});

    const inputUsername = React.createRef();
    const inputPassword = React.createRef();

    const login = (event) => {
        event.preventDefault();
        const password = sanitize(inputPassword.current.value);
        const username = sanitize(inputUsername.current.value);

        const body = {
            "password": password,
            "username": username
        };

        pizzaPostRequest('/signin',
            body,
            (status, data) => {
                console.log('Login successfully');
                setLoginFail({isFailed: false, message: ''});
                if (props.redirect) {
                    Router.push(props.redirect);
                } else {
                    Router.push('/welcome');
                }
            },
            (status, data) => {
                console.log('Login error');
                setLoginFail({isFailed: true, message: 'Invalid username or password'});
            },
            (error) => {
                console.error('Error:', error);
                setLoginFail({isFailed: true, message: 'Network error'});
            });
    };

    return (
        <Card className={"login-card"}>
            {loginFail.isFailed && <FailTitle message={loginFail.message}/>}
            <CardTitle className="login-heading">Welcome back!</CardTitle>
            <Form id="login_form" onSubmit={login}>
                <CardBody>
                    <Container>
                        <Row>
                            <Col sm={{size: 10, offset: 1}}>

                                <FormGroup className={"form-label-group"}>
                                    <Input type="text" id="inputUsername" className={"form-control"} placeholder=" "
                                           required
                                           innerRef={inputUsername}/>
                                    <Label for="inputUsername">Username</Label>
                                </FormGroup>

                                <FormGroup className="form-label-group">
                                    <Input type="password" id="inputPassword" className="form-control" placeholder=" "
                                           required
                                           innerRef={inputPassword}/>
                                    <Label for="inputPassword">Password</Label>
                                </FormGroup>

                                <FormGroup className="custom-control custom-checkbox mb-3">
                                    <Input type="checkbox" className="custom-control-input" id="customCheck1"/>
                                    <Label className="custom-control-label" for="customCheck1">
                                        Remember password
                                    </Label>
                                </FormGroup>

                            </Col>
                        </Row>
                    </Container>
                </CardBody>
                <CardFooter>
                    <Container>
                        <Row>
                            <Col sm={{size: 10, offset: 1}}>
                                <Button
                                    color="primary"
                                    className="btn-block login-btn text-uppercase font-weight-bold mb-2"
                                    type={"submit"}>
                                    Sign in
                                </Button>
                                <div className="text-center">
                                    <Link href={`/signup?${generateRedirectParams(props.redirect)}`} >
                                        <a className="small">New Customer?</a>
                                    </Link>
                                </div>
                            </Col>
                        </Row>
                    </Container>
                </CardFooter>
            </Form>
        </Card>
    );
};

export default LoginPanel;