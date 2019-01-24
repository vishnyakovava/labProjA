var module = angular.module('myApp', ['ui.select', 'ngSanitize']);

module.controller('MainController', function requestFunc($scope, $http) {

    $scope.product = {};
    $scope.productQuantity = '';
    $scope.listObjects = [];
    $scope.saveValues = function () {
        $scope.listObjects.push({
            product: $scope.product,
            quantity: $scope.productQuantity
        });
        console.log('saved', $scope.listObjects);
    };

    $scope.onProductSelect = function (value) {
        $scope.product = value;
    };
    $scope.onQuantityChange = function (value) {
        $scope.productQuantity = value;
    };
    $scope.getProducts = function () {
        $http({
            method: 'GET',
            url: '/rest/products/findall'
        }).then(
            function(res) { //success
                $scope.products = res.data;
                console.log($scope.products);
            },
            function(res) {  //error
                console.log("Error: " + res.status + " : " + res.data)
            }
        );

    };
    $scope.saveProductList = function(){
        console.log("data");
        var dataParams = angular.toJson($scope.listObjects);
        console.log("data for rest: "+dataParams);
        $http({
            method: 'POST',
            url: '/rest/products/save',
            data: dataParams,
            transformResponse: [ // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                function (dataParams) {
                    return dataParams;
                }
            ],
            headers: {'Content-Type': 'application/json'}
        }).then(function success(response, status) {
                console.log('was saved', status, response);
            },
            function error(response, status) {
                console.error('error: ', status, response);
            }
        );
    };
    ///////////
    // before adding items in list - create shopping list by clicking on NEW SHOPPING LIST or smth like that
    //get list of products to ui, select in selector, send one by one for saving in db
    // profit!!!!!!!
    ///////////





});