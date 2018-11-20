$(document).ready(function(){

    $("#test").on("click", function(){
        $.ajax({url: "http://localhost:8081/creditcard/customercard/" + $("#creditcardno").val(), success: function(result){
            $("#creditcardinfo").html(result);
        }});
    })
});