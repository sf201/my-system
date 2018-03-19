/**
 * Created by sf201 on 9/7/2017.
 */
app.controller("loginCtrl", ["$scope", "$http",function ($scope,$http) {
$scope.login = function() {
    $http.post("login.do?action=login").then(function() {

    })
};
}]);
