var money = 0;

function loadItems(){



$.ajax({
    url: "http://tsg-vending.herokuapp.com/items",

    success: function (itemArray) {
        $("#itemButtonsDiv").empty();

        var items = $("#itemButtonsDiv");


        $.each(itemArray, function (index, item) {

            var itemDetails = "<button style='width:33%' id='buttonInfo' onclick='getItem(" + item.id + ")'><p>";
            itemDetails += item.id + "<br />";
            itemDetails += item.name + "<br /> ";
            itemDetails += item.price + "$" + "<br />";
            itemDetails += "Quantity: " + item.quantity + "<br />";
            itemDetails += "</p>";
            itemDetails += "</button>";

            items.append(itemDetails);

        });

    },
    error: function (errorThrown) {
        alert("error I guess");
    }
});

}

function makePurchase() {

    $.ajax({
        type: "POST",
        url: "http://tsg-vending.herokuapp.com/money/" + $("#userMoney").val() + "/item/" + $("#item").val(),


        success: function (change) {

            loadItems();


            $("#userMoney").val('');
            $("#item").val('');
            $('#change').val("Quarters: " + change.quarters.toFixed(0) + " Dimes: " + change.dimes + " Nickels: " + change.nickels + " Pennies: " + change.pennies);
            $('#msg').val("Thank You!!");
            money = 0;



        },
        error: function (jqXHR, errorThrown, status) {
            console.log(jqXHR);
            if (statusText = "error") {
                $('#msg').val("Error: Please insert funds and select items!!");
    }
            $('#msg').val(jqXHR.responseJSON.message + "$ ");




}
    });
}


function getItem(id) {
    $('#item').val(id);
}


function addMoney(input) {

    money += input;

    $("#userMoney").val(money.toFixed(2));
}



function returnChange() {

    var total = money;

    var pennies = Math.floor(total * 100);
    var quarters = Math.floor(pennies / 25);
    pennies -= Math.floor(25 * quarters);
    var dimes = Math.floor(pennies / 10);
    pennies -= Math.floor(10 * dimes);
    var nickels = Math.floor(pennies / 5);
    pennies -= Math.floor(5 * nickels);



    $('#change').val("Quarters: " + quarters.toFixed(0) + " Dimes: " + dimes + " Nickels: " + nickels + " Pennies: " + pennies);
    money = 0;
    $('#userMoney').val(money);
    $("#item").val('');
    $("#msg").val("Change Returned");
}

loadItems();




