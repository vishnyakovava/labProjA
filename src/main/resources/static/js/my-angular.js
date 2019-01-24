    angular.module('myApp',[]).controller('UserController', function requestFunc($scope, $http) {

       $scope.users=[];
        $scope.usersTable={
            userId: 999,
            userFirstName: "",
            userLastName: "",
            userEmail : "",
            userPassword: ""
        };

        $scope.getUsers = function () { //is ok
            $http({
                method: 'GET',
                url: '/rest/users/findall'
            }).then(
                function(res) { //success
                    $scope.users = res.data;
                },
                function(res) {  //error
                    console.log("Error: " + res.status + " : " + res.data)
                }
            );
        };

        $scope.submitUser = function() { //is ok
            var dataParams = angular.toJson($scope.usersTable);
            console.log("data for rest: "+dataParams);
            var url='';
            var method='';
            if($scope.usersTable.userId==0){
                url='/rest/users/update';
                method='PUT';
            }
            else {
                url='/rest/users/save';
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
                    $scope.getUsers();

                    console.log('User was saved', status, response);
                },
                function error(response, status) {
                    console.error('error: ', status, response);
                } );
        };
        
        $scope.createUser = function () { //is ok
          clearFormData();
        };

        $scope.editUser = function(user) { // when email is not changed - make it forbidden to change!!!!!
            $scope.usersTable.userId=0;
            $scope.usersTable.userFirstName = user.userFirstName;
            $scope.usersTable.userLastName = user.userLastName;
            $scope.usersTable.userEmail = user.userEmail;
            $scope.usersTable.userPassword = user.userPassword;
        };

        $scope.deleteUser = function (user) { //is ok
            var userEmail = user.userEmail;
            $http({
                method: 'DELETE',
                url: '/rest/users/delete/' + userEmail,
                transformResponse: [ // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    function (userEmail) {
                        return userEmail;
                    }
                ]
            }).then(
                function success(response, status) {
                console.log('User was deleted', status, response);
                    $scope.getUsers();
                    clearFormData();
            },
               function error(response, status) {
                   console.error('error: ', status, response);
               } );
        };

        function clearFormData() { //is ok
            $scope.usersTable.userId=999;
            $scope.usersTable.userFirstName = "";
            $scope.usersTable.userLastName = "";
            $scope.usersTable.userEmail = "";
            $scope.usersTable.userPassword = ""
        }

    });