//create to keep track of cart items and how many we need to order. Globally declared.
var cartItems = new Array();

$(document).ready(function(){

    //Login Modal section START*******************************************************
    $(".ajr-login-link").on("click", function(){
        $("#loginmodal").css("display", "block");
    });

    $("#loginbtn").on("click", function(){

        $.ajax({
            url:"http://localhost:8081/customerservice/customerAuthentication",
            type:"POST",
            data:
                JSON.stringify({
                firstName: "Andrew",
                lastName: "Rohrer",
                userName: $("#username").val(),
                password: $("#userpassword").val(),
                street: "",
                aptno: "",
                city: "",
                zipcode: "",
                state:""}),
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
            }
        });
        hideLoginModal();
    });

    $("#cancelbtn").on("click", function(){
        hideLoginModal();
    });

    function hideLoginModal(){
        $("#loginmodal").css("display","none");
    };
    //Login Modal section END*********************************************************

    //Search section START************************************************************
    $("#searchbtn").on("click", function(){
        $.get({url: "http://localhost:8081/productservice/products/searchresults/" + $("#searchterm").val(), success: function(result){
            $("#ecommpanel").html(result);
            for (var i = 0; i < result.length-1;i++){
                var searchresult = result[i];
                console.log(searchresult);
            }
        }});
        $("#searchresults").append(addToSearchResults(5, 5.00))
    });
    //Search section END**************************************************************

    //Page logic section START********************************************************
    $(".ajr-green-btn").on("click", function(){
        var btnitemnum = this.attributes.itemno.value;
        var btnitemprice = this.attributes.itemprice.value;
        var newitem = {number:btnitemnum, price:btnitemprice,qty:1};
        if(addToCartArray(newitem)){
            $("#cartitem" + newitem.number).text(calculateQty(newitem.number));
        } else {
            $("#cartItemsList").append(addCartItem(newitem));
        }
    });
    //Page logic section END**********************************************************
});

function addCartItem(newCartItem) {
    var htmlitem = "";

    htmlitem = `<article class="ajr-item-100">
                    <section class="ajr-container">
                        <article class="ajr-inner-item">` +
                        newCartItem.number +
                        `</article>
                        <article class="ajr-inner-item">` +
                        newCartItem.price +
                        `</article>
                        <article ` + `id="cartitem` + newCartItem.number + `" class="ajr-inner-item">` + 
                            calculateQty(newCartItem.number);
                        `</article>
                    </section>
                </article>`;

    return htmlitem;
}

function addToCartArray(item){
    var alreadyExists = false;
    if (Array.isArray(cartItems) && cartItems.length > 0){
        cartItems.forEach(function(ele){
            if (ele.number == item.number){
                alreadyExists = true;
                ele.qty++;
            }
        });
    }
    if (alreadyExists == false){
        cartItems.push(item);
    }
    return alreadyExists;
}

function calculateQty(itemNo){
    var qty = 1;
    cartItems.forEach(function(ele){
        if (ele.number == itemNo){
            qty = ele.qty;
        }
    });
    return qty; //if the product wasn't found there is a problem.
}

function addToSearchResults(itemNumber, itemPrice){
    var htmlitem = "";

    htmlItem = `<article class="ajr-item-100 container">
                    <section class="ajr-container">

                        <article class="ajr-item">
                            <div>Item Name</div>
                        </article>

                        <article class="ajr-item">
                            <div>Item Price</div>
                        </article>

                        <article class="ajr-item">
                            <section class="ajr-container">
                                <article class="ajr-inner-item">
                                    <a type="button" href="#" data-toggle="collapse" data-target="#desc1">
                                        More info
                                        <span class="glyphicon glyphicon-plus-sign"></span>
                                    </a>
                                </article>
                                <article class="ajr-inner-item">

                                </article>
                                <article class="ajr-inner-item">
                                    <button type="button" class="ajr-green-btn ajr-right" id="btnadd1">Add to Cart >></button>
                                </article>
                            </section>
                        </article>

                    </section>
                    <div id="desc1" class="collapse">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                    </div>
                </article>`;

    return htmlitem;
}