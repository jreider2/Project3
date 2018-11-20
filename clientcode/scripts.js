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
    })
});