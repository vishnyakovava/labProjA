// var productName = 0;
// var productQuantity = 0;
// $(function(){
//     $('#addButton').click(function(){
//         productName++; productQuantity++;
//         console.log(productQuantity);
//         var div = $('<div class="row" id="addingTab">\n' +
//             '                    <div class="col-sm-8">\n' +
//             '                        <div class="inputBox ">\n' +
//             '                            <div class="inputText">Product Name</div>\n' +
//             '                            <input type="text" name="add" id="productName-' + productName + '" class="input-field">\n' +
//             '                        </div>\n' +
//             '                    </div>\n' +
//             '\n' +
//             '                    <div class="col-sm-4">\n' +
//             '                        <div class="inputBox">\n' +
//             '                            <div class="inputText">Quantity</div>\n' +
//             '                            <input type="text" name="" id="productName-' + productQuantity + '" class="input-field">\n' +
//             '                        </div>\n' +
//             '                    </div>\n' +
//             '                </div>');
//         div.appendTo('#addingTab');
//     });
// });

// $(function(){
//     $(".input-field").focus(function() {
//         $(this).parent().addClass("focus");
//     });
// });

$(document).on('focus','.input-field', function () {
    $(this).parent().addClass("focus");
});
