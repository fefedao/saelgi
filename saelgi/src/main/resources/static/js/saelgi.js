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
	}).when('/orgaos', {
    		templateUrl : 'orgao.html',
    		controller : 'orgao',
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
    self.showAdicionar = false;

	$http.get('/saelgi/licitacoes').then(
        function(response) {
            console.log("licitacoes listadas")
            self.licitacoes = response.data;
        }
	)

	self.btnAdicionar = function() {
        self.showAdicionar = true;
        self.licitacao = null;
        $http.get('/saelgi/modalidades').then(
            function(response) {
                console.log("modalidades listadas")
                self.modalidades = response.data;
            }
        );
        $http.get('/saelgi/orgaos').then(
            function(response) {
                console.log("orgaos listados")
                self.orgaos = response.data;
            }
        );
        console.log("botao adicionar licitacao")
    }


    self.btnEditar = function(codigo) {
        $http.get('/saelgi/licitacao/' + codigo).then(
            function(response) {
                self.showEditar = true;
                self.licitacao = response.data;
                self.licitacao.dataDeAberturaLong = new Date(self.licitacao.dataDeAberturaLong);
                self.licitacao.dataEntregaPropostaLong = new Date(self.licitacao.dataEntregaPropostaLong);
                self.licitacao.dataEntregaDocumentacaoLong = new Date(self.licitacao.dataEntregaDocumentacaoLong);
                $http.get('/saelgi/modalidades').then(
                    function(response) {
                        console.log("modalidades listadas")
                        self.modalidades = response.data;
                    }
                )
                $http.get('/saelgi/orgaos').then(
                    function(response) {
                        console.log("orgaos listados")
                        self.orgaos = response.data;
                    }
                )
                console.log("editar licitacao")
            }
        );
    }

    self.btnExcluir = function(codigo) {
        $http.delete('/saelgi/licitacao/' + codigo).finally(
            function() {
            $http.get('/saelgi/licitacoes').then(
                function(response) {
                    self.licitacoes = response.data;
                }
            )
            console.log("licitacao excluida")
        });
    }

    self.formLicitacao = function() {
        $http.post('/saelgi/criarEditarLicitacao', self.licitacao).then(
            function() {
            self.showEditar = false;
            self.showAdicionar = false;
            console.log("licitacao editada")
            $http.get('/saelgi/licitacoes').then(
                function(response) {
                    console.log("licitacoes listadas")
                    self.licitacoes = response.data;
                }
            )
        });
    }

    self.btnCancelar = function() {
        $http.get('/saelgi/licitacoes').then(
            function(response) {
                console.log("edicao/criacao licitacao cancelada")
                self.showEditar = false;
                self.showAdicionar = false;
                self.licitacoes = response.data;
            }
        )
    }

}).controller('orgao', function($http, $rootScope) {
	var self = this;
	self.showEditar = false;
    self.showAdicionar = false;
    self.showEditarEndereco = false;

	$http.get('/saelgi/orgaos').then(
        function(response) {
            console.log("orgaos listados")
            self.orgaos = response.data;
        }
    )

	self.btnAdicionar = function() {
        self.showAdicionar = true;
        self.orgao = null;
        $http.get('/saelgi/esferas').then(
            function(response) {
                console.log("esferas listadas")
                self.esferas = response.data;
            }
        )
        console.log("botao adicionar orgao")
    }

    self.btnEditar = function(codigo) {
        $http.get('/saelgi/orgao/' + codigo).then(
            function(response) {
                self.showEditar = true;
                self.orgao = response.data;
                $http.get('/saelgi/esferas').then(
                    function(response) {
                        console.log("esferas listadas")
                        self.esferas = response.data;
                    }
                )
                console.log("editar orgao")
            }
        );
    }

    self.btnExcluir = function(codigo) {
        $http.delete('/saelgi/orgao/' + codigo).finally(
            function() {
            $http.get('/saelgi/orgaos').then(
                function(response) {
                    self.orgaos = response.data;
                }
            )
            console.log("orgao excluido")
        });
    }

    self.formOrgao = function() {
        $http.post('/saelgi/criarEditarOrgao', self.orgao).then(
            function() {
            self.showEditar = false;
            self.showAdicionar = false;
            console.log("orgao editado")
            $http.get('/saelgi/orgaos').then(
                function(response) {
                    console.log("orgaos listados")
                    self.orgaos = response.data;
                }
            )
        });
    }

    self.btnCancelar = function() {
        $http.get('/saelgi/orgaos').then(
            function(response) {
                console.log("edicao/criacao orgao cancelada")
                self.showEditar = false;
                self.showAdicionar = false;
                self.orgaos = response.data;
            }
        )
    }

    self.btnEditarEndereco = function(orgao) {
        $http.get('/saelgi/endereco/' + orgao.endereco.codigo).then(
            function(response) {
                self.showEditarEndereco = true;
                console.log("Editando endereco do orgao")
                self.endereco = response.data;
            }
        );
    }

    self.formEndereco = function() {
        $http.post('/saelgi/editarEndereco', self.endereco).then(
            function() {
                self.showEditarEndereco = false;
                console.log("endereco editado")
                $http.get('/saelgi/orgao/' + self.orgao.codigo).then(
                    function(response) {
                        self.showEditar = true;
                        self.orgao = response.data;
                        $http.get('/saelgi/esferas').then(
                            function(response) {
                                console.log("esferas listadas")
                                self.esferas = response.data;
                            }
                        )
                        console.log("editar orgao")
                    }
                );
            }
        );
    }

    self.btnCancelarEdicaoEndereco = function() {
        self.showEditarEndereco = false;
    }
});
