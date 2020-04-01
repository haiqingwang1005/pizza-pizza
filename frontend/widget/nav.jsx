import '../public/css/style.css';

import React, {useEffect, useState} from 'react';
import Link from 'next/link';
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
} from 'reactstrap';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPizzaSlice, faSignOutAlt, faClipboard, faShoppingCart} from '@fortawesome/free-solid-svg-icons'
import {default_path, fetcherWithToken} from "../utils/pizza_url";
import useSWR from "swr";
import Router from "next/router";
import {useCookies} from 'react-cookie'

const getFirstName = (token) => {
    const {data, error} = useSWR(['/profile', token], fetcherWithToken);
    if (error) {
        return undefined;
    }
    if (data && data.status === 200) {
        return data.response.firstname;
    } else {
        return undefined;
    }
};

const UserDropdown = (props) => {
    return (
        <UncontrolledDropdown nav inNavbar>
            <DropdownToggle nav caret>
                <span>
                    {`Hello, ${props.name}`}
                </span>
            </DropdownToggle>
            <DropdownMenu right>
                <DropdownItem>
                    <Link href={"/cart"}>
                        <div>
                            <FontAwesomeIcon icon={faShoppingCart}/>{' '}
                            <a className={"nav-drop-link"}>Shop Cart</a>
                        </div>
                    </Link>
                </DropdownItem>
                <DropdownItem>
                    <Link href={"/"}>
                        <div>
                            <FontAwesomeIcon icon={faClipboard}/>{'  '}
                            <a className={"nav-drop-link"}>My Orders</a>
                        </div>
                    </Link>
                </DropdownItem>
                <DropdownItem divider/>
                <DropdownItem onClick={props.signOut}>
                    <FontAwesomeIcon icon={faSignOutAlt}/> Sign out
                </DropdownItem>
            </DropdownMenu>
        </UncontrolledDropdown>
    );
};

const AnonymousDropdown = (props) => {
    return (
        <UncontrolledDropdown nav inNavbar>
            <DropdownToggle nav caret>
                Please sign in
            </DropdownToggle>
            <DropdownMenu right>
                <DropdownItem>
                    <Link href="/login">
                        <a className={"nav-drop-link"}>Sign in</a>
                    </Link>
                </DropdownItem>
                <DropdownItem>
                    <Link href="/signup">
                        <a className={"nav-drop-link"}>Sign up</a>
                    </Link>
                </DropdownItem>
            </DropdownMenu>
        </UncontrolledDropdown>
    );
};

const PizzaNavDropdown = (props) => {
    const [cookies, setCookie, removeCookie] = useCookies(['token']);

    const [name, setName] = useState();

    const firstname = getFirstName(cookies.token);
    useEffect(() => {
        setName(firstname);
    });

    const signOut = () => {
        console.log('sign out');
        removeCookie('token');
        setName(undefined);
        if (default_path === Router.pathname) {
            window.location.href = Router.pathname;
        } else {
            Router.push('/welcome');
        }

    };

    if (name) {
        return (
            <UserDropdown name={name} signOut={signOut}/>
        );
    } else {
        return (
            <AnonymousDropdown/>
        );
    }
};

const PizzaNav = (props) => {
    const [isOpen, setIsOpen] = useState(false);
    const toggle = () => setIsOpen(!isOpen);

    return (
        <div>
            <Navbar color="dark" dark expand="md">
                <NavbarBrand href="/">
                    <FontAwesomeIcon icon={faPizzaSlice}/>
                </NavbarBrand>

                <NavbarToggler onClick={toggle}/>

                <Collapse isOpen={isOpen} navbar>
                    <Nav className="ml-auto" navbar>
                        <NavItem>
                            <Link href="/welcome" passHref>
                                <NavLink>Home</NavLink>
                            </Link>
                        </NavItem>
                        <NavItem>
                            <Link href="/menu" passHref>
                                <NavLink>Menu</NavLink>
                            </Link>
                        </NavItem>
                        <PizzaNavDropdown/>
                    </Nav>
                </Collapse>
            </Navbar>
        </div>
    );
};

export default PizzaNav;