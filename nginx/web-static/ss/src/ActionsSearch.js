import React, { Component } from 'react';
import './App.css';

class SearchBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
        // This binding is necessary to make `this` work in the callback
        this.onInput = this.onInput.bind(this);
        this.fetchActions = this.fetchActions.bind(this);
    }

    fetchActions(tags) {
        fetch(`http://localhost:8082/actions/search/findByTagsIn?tags=${tags}`)
            .then(response => response.json())
            .then(response => this.props.responseHandler(response._embedded.actions));
    }

    clearActions() {
        this.props.responseHandler();
    }

    onInput(event) {
        var tags = event.target.value;
        clearTimeout(this.state.timeoutId);
        if(tags) {
            this.setState({
                timeoutId: setTimeout(() => this.fetchActions(tags), 500)
            });
        } else {
            this.clearActions();
        }
    }

    render() {
        return (
            <input type="text" title="Action" onInput={this.onInput}/>
        );
    }
}

class ActionList extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <ul>{this.props.actions.map(action => <li key={action.name}>{action.friendlyName}</li>)}            </ul>
        );
    }
}

class ActionsSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {actions: []};
        this.handleResponse = this.handleResponse.bind(this);
    }

    handleResponse(actions) {
        this.setState({actions: actions});
    }

    render() {
        return (
            <div>
                <SearchBox responseHandler={this.handleResponse}/>
                {this.state.actions && <ActionList actions={this.state.actions}/>}
            </div>
        );
    }
}

export default ActionsSearch;
