'use strict';
// Define `TrialApp` module
var app = angular.module('TrialApp', ['ui.router']);
// Define `mainController`
app.controller("mainController", function() {
	
});
// Define routers
app.config(function($stateProvider) {
    $stateProvider
        .state('C1', {
            url:'/C1',
            template: '<h1>进入C1状态</h1>'

        })
        .state('C2', {
            url: '/C2',
            templateUrl: 'Htmls/C2.html'

        })
        .state('C3', {
            url: '/C3',
            templateProvider: function() {
                return '<h1>进入C3状态</h1>';
            }
        })
});
app.run(function($state) {
    $state.go('C1');
});