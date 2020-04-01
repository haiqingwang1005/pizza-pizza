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
    Button,
    Form,
    FormGroup, Input, Label
} from "reactstrap";
import sanitize from "../utils/sanitize";
import {generateRedirectParams, pizzaPostRequest} from "../utils/pizza_url";
import {FailTitle} from "./login_alert";
import Link from "next/link";
import Router from "next/router";

const SignUpPanel = (props) => {

    let [signUpFail, setSignUpFail] = useState({isFailed: false, message: ''});

    const inputUsername = React.createRef();
    const inputEmail = React.createRef();
    const inputFirstName = React.createRef();
    const inputLastName = React.createRef();
    const inputPassword = React.createRef();
    const inputVerifyPassword = React.createRef();

    const verifyPassword = () => {
        const password = sanitize(inputPassword.current.value);
        const verify = sanitize(inputVerifyPassword.current.value);
        if (password !== verify) {
            console.log('not equal');
            inputVerifyPassword.current.classList.add('login-input-error');
        } else {
            inputVerifyPassword.current.classList.remove('login-input-error');
        }
    };

    const signUp = (event) => {
        event.preventDefault();
        const password = sanitize(inputPassword.current.value);
        const verify = sanitize(inputVerifyPassword.current.value);
        if (password === verify) {
            const email = sanitize(inputEmail.current.value);
            const username = sanitize(inputUsername.current.value);
            const first = sanitize(inputFirstName.current.value);
            const last = sanitize(inputLastName.current.value);

            const body = {
                "email": email,
                "firstname": first,
                "lastname": last,
                "password": password,
                "username": username
            };

            pizzaPostRequest('/register',
                body,
                () => {
                    console.log('Sign up successfully');
                    setSignUpFail({isFailed: false, message: ''});
                    if (props.redirect) {
                        Router.push(props.redirect);
                    } else {
                        Router.push('/welcome');
                    }
                },
                (status, data) => {
                    console.log('Sign up failure');
                    let msg = 'Failed to sign up';
                    if (status === 409) {
                        msg = 'Account already exists'
                    }
                    setSignUpFail({isFailed: true, message: msg});
                },
                (error) => {
                    console.error('Error:', error);
                    setSignUpFail({isFailed: true, message: 'Network error'});
                });
        }
    };

    return (
        <Card className={"login-card"}>
            {signUpFail.isFailed && <FailTitle message={signUpFail.message}/>}
            <CardTitle className="login-heading">Sign Up</CardTitle>
            <Form id="signup_form" onSubmit={signUp}>
                <CardBody>
                    <Container>
                        <Row>
                            <Col sm={{size: 10, offset: 1}}>
                                <FormGroup className={"form-label-group"}>
                                    <Input type="text"
                                           id="inputUsername"
                                           className={"form-control"}
                                           placeholder=" "
                                           required
                                           innerRef={inputUsername}/>
                                    <Label for="inputUsername">Username</Label>
                                </FormGroup>

                                <FormGroup className="form-label-group">
                                    <Input type="email"
                                           id="inputEmail"
                                           className="form-control"
                                           placeholder=" "
                                           required
                                           innerRef={inputEmail}/>
                                    <Label htmlFor="inputEmail">Email address</Label>
                                </FormGroup>

                                <FormGroup className="form-label-group">
                                    <Input type="text"
                                           id="inputFirstName"
                                           className="form-control"
                                           placeholder=" "
                                           required
                                           innerRef={inputFirstName}/>
                                    <Label for="inputFirstName">First name</Label>
                                </FormGroup>

                                <FormGroup className="form-label-group">
                                    <Input type="text"
                                           id="inputLastName"
                                           className="form-control"
                                           placeholder=" "
                                           required
                                           innerRef={inputLastName}/>
                                    <Label for="inputLastName">Last name</Label>
                                </FormGroup>

                                <FormGroup className="form-label-group">
                                    <Input type="password"
                                           id="inputPassword"
                                           className="form-control"
                                           placeholder=" "
                                           required={true}
                                           innerRef={inputPassword}/>
                                    <Label for="inputPassword">Password</Label>
                                </FormGroup>

                                <FormGroup className="form-label-group" id="verifyPasswordDiv">
                                    <Input type="password"
                                           id="verifyPassword"
                                           className="form-control"
                                           placeholder=" "
                                           required
                                           innerRef={inputVerifyPassword}
                                           onChange={verifyPassword}/>
                                    <Label for="verifyPassword">Verify Password</Label>
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
                                    Sign up
                                </Button>
                                <div className="text-center">
                                    <Link href={`/login?${generateRedirectParams(props.redirect)}`}>
                                        <a className="small">Already have an account?</a>
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

export default SignUpPanel;