//create to keep track of cart items and how many we need to order. Globally declared.
var cartItems = new Array();
var isSignedIn = false;
var signedInCustomerNo = "1";
var host = "http://localhost:8081/";

var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //jan is 0
var yyyy = today.getFullYear();

var date = dd + "/" + mm + "/" + yyyy;

$(document).ready(function(){

    // $.getJSON(host + "productservice/products/searchresults/goggles", function (results) {
    //     console.log(results);
    // });
        

    //Login Modal section START*******************************************************
    $(".ajr-login-link").on("click", function(){
        $("#loginmodal").css("display", "block");
    });

    $("#loginbtn").on("click", function(){
        $.ajax({
            url: host + "customerservice/customerAuthentication",
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
            accept: "application/json",
            dataType:"json",
            success: function(data, status){
            	console.log("hello. This is the status: " + status);
            	console.log(data);
                if(data == null){
                    alert("Incorrect username or password");
                } else {
                    isSignedIn = true;
                    hideLoginModal();
                    alert("Login Success!");
                    var parsedResponse = data //JSON.parse(data);
                    var id = parsedResponse.id;
                    signedInCustomerNo = id;// update signedInCustomerNo 
                    var myOrderURL = data.link[0].url;
                    console.log("CustomerID: " + id);
                    console.log("URI: " + myOrderURL);
                    // insert returned URL into menu (links user to their own orders)
                    $("#customerorders").attr('custordurl', myOrderURL);
                }
            }
        });
    });

    $("#cancelbtn").on("click", function(){
        hideLoginModal();
    });

    function hideLoginModal(){
        $("#loginmodal").css("display","none");
    };
    //Login Modal section END*********************************************************

    //Order section START*************************************************************
    $("#customerorders").on("click", function(){
    	console.log("The URL " + $("#customerorders").attr("custordurl"));
    	$.getJSON($("#customerorders").attr("custordurl"), function(result){
    		console.log("result: ");
    		console.log(result);
            event.preventDefault();
            
            //empty myOrderResults and then append headings
            $("#columnheading1").empty();
            $("#columnheading2").empty();
            $("#columnheading3").empty();
            $("#myOrderResults").empty();
            $("#myOrderResults").append(orderResultHeadingsHTML);
            
            //myOrderResults append to
            //orderTotal = 0; //price of all the items on this order
            result.forEach(element => {
            	//get each item and add up their prices
            	let orderTotal = 0;
            	console.log(orderTotal)
            	arrayOfItems = element.productrepresentation;
            	arrayOfItems.forEach(item => {
            		orderTotal += item.price;
            	});
            	//grab cancel URI
            	console.log("The cancel link for HATEOAS:");
            	console.log(host + element.link[0].url);//cancel link
            	//append new order results to DOM
                $("#myOrderResults").append(addToOrderList(element.orderNo, element.orderStatus, date, orderTotal, host + element.link[0].url));// addToOrderList(orderNumber, orderStatus, orderDate, orderTotal, cancelURI)
                //orderTotal = 0; // reset to zero before moving onto next order
            });
        });
    	event.preventDefault();
        setEcomPanel("order");
        hideResultsPlaceHolder();
    });


    $("#placeorder").on("click", function(){
        //Add cart items to a json string to pass to the server.
        var itemsJson = "";
        cartItems.forEach((element, key, cartItmes) => {
            itemsJson += `{"id":"` + element.number + `",`
                    + `"price":"` + element.price + `",`
                    + `"quantityOnOrder":"` + element.qty + `",`
                    + `"description":"",`
                    + `"productOwnerID":""}`;
            if (!Object.is(cartItems.length -1 , key)){
                itemsJson += ",";
            }
        });

        itemsJson = `{"items": [` + itemsJson + `], "customerId":"` + signedInCustomerNo + `",` +
                    `"ccNo": "6547984532156541"}`;

        console.log("This is the URL: " +  host + "order/orderService/order/neworder");
        $.ajax({
            url: host + "order/orderService/order/neworder",
            type:"POST",
            data: itemsJson,
            
            contentType:"application/json; charset=utf-8",
            accept: "application/json",
            dataType:"json",
            success: function(data, status){
                alert("Order Placed!");
            }
        });
       
    });

    $(".ajr-cancel-btn").on("click", function(){
        
    });
    //Order section END***************************************************************

    //Search section START************************************************************
    $("#searchbtn").on("click", function(){
        //$("#searchresults").empty(); -- add after debugging button press
        $.getJSON(host + "productservice/products/searchresults/" + $("#searchterm").val(), function (results) {
            console.log(results);
            event.preventDefault();
            var i = 0;
            results.forEach(element => {
                $("#searchresults").append(addToSearchResults(element.description, element.price, element.name, element.id, i));
                i++;
            });
            
        });
        console.log("test2");
        setEcomPanel("search");
        hideResultsPlaceHolder();
        
    });

    function setEcomPanel(orderOrSearch){
        if (orderOrSearch == "search"){
            $("#columnheading1").text("Item Name");
            $("#columnheading2").text("Item Price");
            $("#columnheading3").text("Other Information");
            $("#myOrdersPanel").css("display", "none");
            $("#searchResultsPanel").css("display", "flex");
        } else {
            $("#columnheading1").text("Order Number");
            $("#columnheading2").text("Order Status");
            $("#columnheading3").text("Order Date");
            $("#searchResultsPanel").css("display", "none");
            $("#myOrdersPanel").css("display", "flex");
        }
    }

    function hideResultsPlaceHolder(){
        $("#resultsPlaceHolder").css("display", "none");
    }
    //Search section END**************************************************************

    
});

//Page logic section START********************************************************
//This must be a function rather than using the "on.("click"...jquery function because the buttons using this function
//were created after the document.ready event.
function addToCartButtonClick(clickedbutton){
    var btnitemnum = clickedbutton.attributes.itemno.value;
    var btnitemprice = clickedbutton.attributes.itemprice.value;
    var newitem = {number:btnitemnum, price:btnitemprice,qty:1, description:"", productOwnerID:""};
    if(addToCartArray(newitem)){
        $("#cartitem" + newitem.number).text(calculateQty(newitem.number));
    } else {
        $("#cartItemsList").append(addCartItem(newitem));
    }
}

function cancelOrderButtonClicked(clickedbutton){
    var btnOrderNumber = clickedbutton.attributes.orderid.value;

    $.ajax({
        url: clickedbutton.attributes.cancelUri.value,
        type:'DELETE',
        success: function(result){
            alert("Order " + btnOrderNumber + "has been cancelled");
            clickedbutton.parentElement.parentElement.parentElement.parentElement.children[1].children[0].innerHTML = "cancelled"
            //console.log(clickedbutton.parentElement.parentElement.parentElement.parentElement.children[1].children[0].innerHTML = "canceled");
        }
    });
    
}
//Page logic section END**********************************************************

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

function addToSearchResults(itemDesc, itemPrice, itemName, itemNo, listNo){
    var htmlitem = "";

    htmlItem = `<article class="ajr-item-100 container">
                    <section class="ajr-container">

                        <article class="ajr-item">
                            <div>` + itemName + `</div>
                        </article>

                        <article class="ajr-item">
                            <div>` + itemPrice + `</div>
                        </article>

                        <article class="ajr-item">
                            <section class="ajr-container">
                                <article class="ajr-inner-item">
                                    <a type="button" href="#" data-toggle="collapse" data-target="#desc` + listNo + `">
                                        More info
                                        <span class="glyphicon glyphicon-plus-sign"></span>
                                    </a>
                                </article>
                                <article class="ajr-inner-item">

                                </article>
                                <article class="ajr-inner-item">
                                    <button itemno="`+ itemNo + `" itemprice="` + itemPrice + `" type="button" class="ajr-green-btn ajr-right" id="btnadd` + listNo +`" onClick="addToCartButtonClick(this)">Add to Cart</button>
                                </article>
                            </section>
                        </article>

                    </section>
                    <div id="desc` + listNo + `" class="collapse">` + itemDesc + `</div>
                </article>`;

    return htmlItem;
}

function addToOrderList(orderNumber, orderStatus, orderDate, orderTotal, cancelURI){
    var htmlitem = "";

    htmlitem = `<article class="ajr-item-100 container">
                    <section class="ajr-container">

                        <article class="ajr-item-25">
                            <div>` + orderNumber + `</div>
                        </article>

                        <article id="orderStatusArticle" class="ajr-item-25">
                            <div>` + orderStatus + `</div>
                        </article>

                        <article class="ajr-item-25">
                        <section class="ajr-container">
                            <article class="ajr-item-50">
                                <div>` + orderDate + `</div>
                            </article>
                            <article class="ajr-item-50">
                                <button onClick="cancelOrderButtonClicked(this)" cancelUri="` + cancelURI + `" class="ajr-cancel-btn" type="button" orderid="` + orderNumber + `">Cancel</button>
                            </article>
                        </section>
                            
                        </article>
                        <article class="ajr-item-25">
                            <div>` + orderTotal + `</div>
                        </article>
                    </section>
                </article>`;

    return htmlitem;
}

var orderResultHeadingsHTML = `<article class="ajr-item-100 container">
									<section class="ajr-container">
									
									    <article class="ajr-item-25">
									        <div>Order Number</div>
									    </article>
									
									    <article class="ajr-item-25">
									        <div>Order Status</div>
									    </article>
									
									    <article class="ajr-item-25">
									        <div>Order Date</div>
									    </article>
									    <article class="ajr-item-25">
									        <div>Order Total</div>
									    </article>
									</section>
								</article>`;

