"use strict";

async function callPage(pageref) {
    let response = await fetch("html/"+pageref, {
        method: 'GET',
    });
    let result = await response.text();
    $("#variable-content").html(result);
}

$(document).ready(function() {

    $('a').on('click', function (e) {debugger
        e.preventDefault();
        $(this).parent().parent().children().each(function(){
            $(this).removeClass("active");
        });
        $(this).parent().addClass("active");
        var pageRef = $(this).attr('href');
        callPage(pageRef);
    });
    debugger;
    if (!sessionStorage.getItem('loggedin_empid')) {
        location.href = "html/login.html";
        return;
    }
    callPage("home.html");

    $("#logout-btn").click(function(){
        sessionStorage.removeItem("loggedin_empid");
        sessionStorage.removeItem("loggedin_empname");
        location.href = "html/login.html";
        return;
    })
    history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
    };


});