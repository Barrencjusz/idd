import React, { Component } from 'react';
import FacebookLogin from 'react-facebook-login';
import { FormControl, ListGroup, ListGroupItem } from 'react-bootstrap';
import NewAction from './actions/NewAction';
import './App.css';

class SearchBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
        // This binding is necessary to make `this` work in the callback
        this.onInput = this.onInput.bind(this);
        this.fetchActions = this.fetchActions.bind(this);
    }

    fetchActions = (tags) =>
        fetch(`http://localhost:8082/actions/search/findByTagsIn?tags=${tags}`)
            .then(response => response.json())
            .then(response => this.props.responseHandler(response._embedded.actions));

    clearActions = () => this.props.responseHandler();

    onInput = (event) => {
        const tags = event.target.value;
        clearTimeout(this.state.timeoutId);
        if(tags) {
            this.setState({
                timeoutId: setTimeout(() => this.fetchActions(tags), 500)
            });
        } else {
            this.clearActions();
        }
    };

    render = () =>
        <FormControl
            type="text"
            placeholder="Enter text"
            onChange={this.onInput}
        />
}

class ActionList extends React.Component {

    render = () =>
        <ListGroup>
            {this.props.actions.map(action =>  <ListGroupItem key={action.name} href="#link1">{action.friendlyName}</ListGroupItem>)}
        </ListGroup>
}

class ActionsSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.handleResponse = this.handleResponse.bind(this);
    }

    handleResponse = (actions) => this.setState({actions: actions});

    render = () =>
        <div>
            <FacebookLogin
                appId="257080801368732"
                autoLoad={true}
                fields="name,email,picture"
                onClick={event => console.log(event)}
                callback={event => console.log(event)} />
            <SearchBox responseHandler={this.handleResponse}/>
            {this.state.actions && <ActionList actions={this.state.actions}/>}
            <NewAction/>
        </div>
}

export default ActionsSearch;
