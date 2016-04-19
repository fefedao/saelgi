angular.module('saelgi', [ 'ngRoute' ]).config(

function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home',
		controllerAs: 'controller'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation',
		controllerAs: 'controller'
	}).when('/licitacoes', {
		templateUrl : 'licitacao.html',
		controller : 'licitacao',
		controllerAs: 'controller'
	}).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',

function($rootScope, $http, $location, $route) {

	var self = this;

	self.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	var authenticate = function(credentials, callback) {

		var headers = credentials ? {
			authorization : "Basic " + btoa(credentials.username + ":" + credentials.password)
		} : {};
		$http.get('user', {
			headers : headers
		}).then(function(response) {
			if (response.data.name) {
				$rootScope.authenticated = true;
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback($rootScope.authenticated);
		}, function() {
			$rootScope.authenticated = false;
			callback && callback(false);
		});

	}

	authenticate();

	self.credentials = {};
	self.login = function() {
		authenticate(self.credentials, function(authenticated) {
			if (authenticated) {
				console.log("Login succeeded")
				$location.path("/");
				self.error = false;
				$rootScope.authenticated = true;
			} else {
				console.log("Login failed")
				$location.path("/login");
				self.error = true;
				$rootScope.authenticated = false;
			}
		})
	};

	self.logout = function() {
		$http.post('logout', {}).finally(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		});
	}

}).controller('home', function($http) {
	var self = this;
	$http.get('/saelgi/resource').then(
        function(response) {
            self.greeting = response.data;
        }
	)
}).controller('licitacao', function($http, $rootScope) {
	var self = this;
	self.showEditar = false;

	self.btnExcluir = function(codigo) {
    	$http.delete('/saelgi/licitacoes/' + codigo, {}).finally(
    	    function() {
            console.log("licitacao excluida")
    		$http.get('/saelgi/licitacoes').then(
                function(response) {
                    self.licitacoes = response.data;
                }
            )
    	});
    }

    self.btnEditar = function(codigo) {
        $http.get('/saelgi/licitacoes/' + codigo, {}).then(
            function(response) {
            self.showEditar = true;
            self.licitacao = response.data;
            $http.get('/saelgi/licitacoes').then(
                function(response) {
                    console.log("editar licitacao")
                }
            )
        });
    }

    self.btnCancelar = function() {
        $http.get('/saelgi/licitacoes').then(
            function(response) {
                console.log("edicao licitacao cancelada")
                self.showEditar = false;
                self.licitacoes = response.data;
            }
        )
    }

    self.formEditar = function() {
        $http.post('/saelgi/licitacoes/', {}).finally(
            function(response) {
            self.showEditar = false;
            $rootScope.licitacao = self.licitacao;
            $http.get('/saelgi/licitacoes').then(
              function(response) {
                  console.log("licitacao editada")
              }
          )
        });
    }

	$http.get('/saelgi/licitacoes').then(
        function(response) {
            console.log("licitacoes listadas")
            self.licitacoes = response.data;
        }
	)

});
