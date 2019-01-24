$(function() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});

var users = {};
$(window).on('load', function() {

    console.log("ready");
    users = getUsers();
    console.log('users', users);
    drawTable(users);

    $('#dynamicTable').DataTable({
        "paging": true
    });



    // var inHTML = "<thead>" +
    //     "<tr>" +
    //         "<th class=\"text-center\">#</th>" +
    //         "<th class=\"text-center\">First Name</th>" +
    //         "<th class=\"text-center\">Last Name</th>" +
    //         "<th class=\"text-center\">Email</th>" +
    //         "<th class=\"text-center\">Password</th>" +
    //         "<th class=\"text-center\">Actions</th>" +
    //     "</tr>" +
    //     "</thead>" +
    //     "<tbody>";
    // $.each(users, function(index, value){
    //     inHTML += "<tr>"+
    //         "<td class=\"text-center\">"+value.userId+"</td>"+
    //         "<td>"+value.userFirstName+"</td>"+
    //         "<td>"+value.userLastName+"</td>"+
    //         "<td>"+value.userEmail+"</td>"+
    //         "<td>"+value.userPassword+"</td>"+
    //         "<td class=\"td-actions text-right\">"+
    //             "<button type=\"button\" rel=\"tooltip\" class=\"btn btn-success btn-link btn-just-icon btn-sm\" data-original-title=\"\" title=\"\">"+
    //                 "<i class=\"material-icons\">edit</i>"+
    //             "</button>"+
    //             "<button type=\"button\" rel=\"tooltip\" class=\"btn btn-danger btn-link btn-just-icon btn-sm\" data-original-title=\"\" title=\"\">"+
    //                 "<i class=\"material-icons\">close</i>"+
    //             "</button>"+
    //         "</td>"+
    //         "</tr>";
    // });
    // $("#dynamicTable").html(inHTML+"</tbody>");
});

function sortUsersByFirstName() {
    users.sort(function(a, b){
        return a.userFirstName.localeCompare(b.userFirstName);
    });
    drawTable(users);
}
function sortUsersByLastName() {
    users.sort(function(a, b){
        return a.userLastName.localeCompare(b.userLastName);
    });
    drawTable(users);
}
function sortUsersByEmail() {
    users.sort(function(a, b){
        return a.userEmail.localeCompare(b.userEmail);
    });
    drawTable(users);
}
function sortUsersById() {
    users.sort(function(a, b){
        return a.userId - b.userId;
    });
    drawTable(users);
}


function drawTable(users){
    var inHTML = "<thead>" +
        "<tr>" +
        "<th class=\"text-center\" onclick=\"sortUsersById();\">#</th>" +
        "<th class=\"text-center\" onclick=\"sortUsersByFirstName();\">First Name</th>" +
        "<th class=\"text-center\" onclick=\"sortUsersByLastName();\">Last Name</th>" +
        "<th class=\"text-center\" onclick=\"sortUsersByEmail();\">Email</th>" +
        "<th class=\"text-center\" >Password</th>" +
        "<th class=\"text-center\">Actions</th>" +
        "</tr>" +
        "</thead>" +
        "<tbody>";
    $.each(users, function(index, value){
        inHTML += "<tr>"+
            "<td class=\"text-center\">"+value.userId+"</td>"+
            "<td>"+value.userFirstName+"</td>"+
            "<td>"+value.userLastName+"</td>"+
            "<td>"+value.userEmail+"</td>"+
            "<td>"+value.userPassword+"</td>"+
            "<td class=\"td-actions text-right\">"+
            "<button type=\"button\" rel=\"tooltip\" onclick=\"editUser(this)\" class=\"btn btn-success btn-link btn-just-icon btn-sm\" data-original-title=\"\" title=\"\">"+
            "<i class=\"material-icons\">edit</i>"+
            "</button>"+
            "<button type=\"button\" data-toggle=\"modal\" data-target=\"#confirmDelete\" onclick=\"confirmUser(this);\" rel=\"tooltip\" class=\"delete btn btn-danger btn-link btn-just-icon btn-sm\" data-original-title=\"\" title=\"\">"+
            "<i class=\"material-icons\">close</i>"+
            "</button>"+
            "</td>"+
            "</tr>";
    });
    $("#dynamicTable").html(inHTML+"</tbody>");
}
// var userInfo = {};
var thisState;
function confirmUser(val) {
    thisState = val;
    console.log($(val).closest('tr').find('td:eq(0)').text()); //id
    console.log($(val).closest('tr').find('td:eq(3)').text()); //mail
    $('#appendTemplate').empty();
    var userInfo = {
            userId: $(val).closest('tr').find('td:eq(0)').text(),
            userEmail: $(val).closest('tr').find('td:eq(3)').text()
        };
    $("#template").tmpl(userInfo).appendTo("#appendTemplate");
}
function deleteUser() {
    // console.log($(val).closest('tr').find('td:eq(0)').text()); //id
    // console.log($(val).closest('tr').find('td:eq(3)').text()); //mail
    var userEmail = $(thisState).closest('tr').find('td:eq(3)').text();
    $.ajax({
        url: 'http://localhost:8080/rest/users/delete/'+userEmail,
        type: 'DELETE',
        success: function(response) {
            console.log('cc', response)
        },
        error: function (response) {
            console.log('err', response);
            $('#confirmDelete').modal('hide');
            $(thisState).closest("tr").remove();
        }
    });
}
var editedItem = false;
function editUser(val) {
    editedItem = true;
    $("#defaultName").val($(val).closest('tr').find('td:eq(1)').text());
    $("#defaultLastName").val($(val).closest('tr').find('td:eq(2)').text());
    $("#defaultEmail").val($(val).closest('tr').find('td:eq(3)').text());
    $("#defaultPass").val($(val).closest('tr').find('td:eq(4)').text());
    $('#addUserModal').modal('show');

}
function getUsers() {
    var response = {};
    $.ajax({
        url: "http://localhost:8080/rest/users/findall",
        type: "GET",
        context: document.body,
        async: false,
        dataType: 'json', // added data type
        success: function(res) {
            response = res;
        }
    });
    return response;
}

$(function() {
    $("#saveBtn").on( "click", function(e) {
        var userPass = $("#defaultPass").val();
        var userName = $("#defaultName").val();
        var userLastName = $("#defaultLastName").val();
        var userEmail = $("#defaultEmail").val();
        var user = {
            userId: Math.floor(Math.random() * 100000000000),
            userFirstName: userName,
            userLastName: userLastName,
            userEmail: userEmail,
            userPassword: userPass
        };
        console.log(user);
        var requestMethod = editedItem ? 'PUT' : 'POST';
        var requestUrl = editedItem ? "http://localhost:8080/rest/users/update" : "http://localhost:8080/rest/users/save";
        $.ajax({
            url: requestUrl,
            type: requestMethod,
            data: JSON.stringify(user),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function(error) {
                alert("User wasn't saved!");
                console.log('err', error.status);
            },
            error: function(success) {
                console.log('cc', success);
                $('#addUserModal').modal('hide');
                users = getUsers();
                console.log('users', users);
                drawTable(users);
                clearModal();
            }
        });
        editedItem = false;
    });
}); //save user

function clearModal() {
    $('.modal-body').find('textarea,input').val('').end();
}


