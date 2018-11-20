//create to keep track of cart items and how many we need to order. Globally declared.
var cartItems = new Array();

$(document).ready(function(){

    $("#test").on("click", function(){
        $.ajax({url: "http://localhost:8081/creditcard/customercard/" + $("#creditcardno").val(), success: function(result){
            $("#creditcardinfo").html(result);
        }});
    });

    $("#searchbtn").on("click", function(){
        $.get({url: "http://localhost:8081/productservice/products/searchresults/" + $("#searchterm").val(), success: function(result){
            $("#creditcardinfo").html(result);
            for (var i = 0; i < result.length-1;i++){
                var searchresult = result[i];
                console.log(searchresult);
            }
        }});
    });

    $("#btnadd1").on("click", function(){
        $("#cartItemsList").append(addCartItem(3,3.00));
    });
});

function addCartItem(itemNumber, itemPrice) {
    var htmlitem = "";

    htmlitem = `<article class="ajr-item-100">
                    <section class="ajr-container">
                        <article class="ajr-inner-item">
                            Item Number
                        </article>
                        <article class="ajr-inner-item">
                            Item Price
                        </article>
                        <article class="ajr-inner-item">
                            Qty Ordered
                        </article>
                    </section>
                </article>`;

    return htmlitem;
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