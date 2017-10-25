'use strict';

angular.module('myApp').service('Session', function () {
    this.create = function (data) {
        this.id = data.id;
        this.username = data.username;
        this.firstName = data.firstName;
        this.lastName = data.familyName;
        this.email = data.email;
        this.userRoles = [];
        angular.forEach(data.roles, function (role) {
            this.push(role);
        }, this.userRoles);
    };
    this.invalidate = function () {
        this.id = null;
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.userRoles = null;
    };
    return this;
});


angular.module('myApp').service('AuthSharedService',["$rootScope", "$http", "$resource", "authService", "Session",function ($rootScope, $http, $resource, authService, Session) {
    return {
        login: function (userName, password, rememberMe) {
            var config = {
                ignoreAuthModule: 'ignoreAuthModule',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };
            $http.post('authenticate', $.param({
                username: userName,
                password: password,
                rememberMe: rememberMe
            }), config)
                .success(function (data, status, headers, config) {
                    Session.create(data);
                    $rootScope.account = Session;
                    $rootScope.authenticated = true;
                    authService.loginConfirmed(data);
                    $rootScope.$broadcast("success-login", {});
                })
                .error(function (data, status, headers, config) {
                    $rootScope.authenticationError = true;
                    $rootScope.$broadcast("failed-login", {});
                    Session.invalidate();
                });
        },
        getAccount: function () {
            $rootScope.loadingAccount = true;
            $http.get('security/account',{ ignoreLoadingBar: true})
                .then(function (response) {
                    authService.loginConfirmed(response.data);

                });
        },
        isAuthorized: function (authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
                if (authorizedRoles == '*') {
                    return true;
                }
                authorizedRoles = [authorizedRoles];
            }
            var roles = Session.userRoles;
            if (roles == null)
                roles = [];
            var isAuthorized = false;
            angular.forEach(authorizedRoles, function (authorizedRole) {
                var authorized = (!!Session.username &&
                roles.indexOf(authorizedRole) !== -1);
                if (authorized || authorizedRole == '*') {
                    isAuthorized = true;
                }
            });
            return isAuthorized;
        },
        logout: function () {
            $rootScope.authenticationError = false;
            $rootScope.authenticated = false;
            $rootScope.account = null;
            $http.get('logout');
            Session.invalidate();
            authService.loginCancelled();
        }
    };
}]);

angular.module('myApp').service('TokensService', ["$log", "$resource",function ($log, $resource) {
    return {
        getAll: function () {
            var tokensResource = $resource('security/tokens', {}, {
                query: {method: 'GET', params: {}, isArray: true}
            });
            return tokensResource.query();
        }
    }
}]);


