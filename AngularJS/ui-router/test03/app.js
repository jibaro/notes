'use strict';
// Define `TrialApp` module
var app = angular.module('TrialApp', ['ui.router']);
//Define routers
app.config(function($stateProvider) {
    $stateProvider
        .state('C1', {
            url:'/C1',
            template: '<h4>进入C1状态</h4>',
            controller:'C1Controller'
        })
        .state('C2', {
            url: '/C2',
            templateUrl: 'Htmls/C2.html',
            controller: function() {
                this.test = 'world!';
            },
            controllerAs: 'C2Ctrl'
        })
        .state('C3', { 
            url: '/C3',
            templateProvider: function() {
                return '<h4>进入C3状态</h4><br />' 
                        + '<p>{{t}}</p>';
            },
            controller: function($scope) {
                $scope.t = 'C3Controller is on!';
            }
        })
});
app.controller('mainController', function() {
    return alert('hello!');
});
app.run(function($state) {
    $state.go('C1'); 
});