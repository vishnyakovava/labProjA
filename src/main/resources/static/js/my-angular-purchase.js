angular.module('myApp',[]).controller('PurchaseController', function requestFunc($scope, $http) {

    $scope.purchases=[];
    $scope.purchases={
        purchaseId: 999,
        purchaseName: "",
        purchaseAddress: ""
    };

    $scope.getPurchases = function () {
        $http({
            method: 'GET',
            url: '/rest/purchases/findall'
        }).then(
            function(res) { //success
                $scope.purchases = res.data;
            },
            function(res) {  //error
                console.log("Error: " + res.status + " : " + res.data)
            }
        );
    };

    $scope.submitPurchase = function() {
        var dataParams = angular.toJson($scope.purchases);
        console.log(dataParams);
        var url='';
        var method='';
        if($scope.purchases.purchaseId==0){
            url='/rest/purchases/update';
            method='PUT';
        }
        else {
            url='/rest/purchases/save';
            method='POST';
        }
        $http({
            method: method,
            url: url,
            data: dataParams,
            transformResponse: [ // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                function (dataParams) {
                    return dataParams;
                }
            ],
            headers: {'Content-Type': 'application/json'}
        }).then(function success(response, status) {
                clearFormData();
                $scope.getPurchases();

                console.log('Purchase was saved', status, response);
            },
            function error(response, status) {
                console.error('error: ', status, response);
            } );
    };

    $scope.createPurchase = function () {
        clearFormData();
    };

    $scope.editPurchase = function(purchase) {
        $scope.purchases.userId=0;
        $scope.purchases.purchaseName = purchase.purchaseName;
        $scope.purchases.purchaseAddress = purchase.purchaseAddress;
    };

    $scope.deletePurchase = function (purchase) {
        var purchaseAddress = purchase.purchaseAddress;
        $http({
            method: 'DELETE',
            url: '/rest/purchases/delete/' + purchaseAddress,
            transformResponse: [ // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                function (purchaseAddress) {
                    return purchaseAddress;
                }
            ]
        }).then(
            function success(response, status) {
                console.log('Purchase was deleted', status, response);
                $scope.getPurchases();
                clearFormData();
            },
            function error(response, status) {
                console.error('error: ', status, response);
            } );
    };

    function clearFormData() {
        $scope.purchases.purchaseId=999;
        $scope.purchases.purchaseName= "";
        $scope.purchases.purchaseAddress="";
    }

});