$(document).ready(function () {
  $('a').click(function (e) {
    if($(this).hasClass("invock-gif")) {
      $("#divLoading").addClass("loading");
    }
    if($(this).is('.not-invock-gif')) {
      $("#divLoading").removeClass("loading");
    }
  });

  $("button[type='submit']").click(function (e) {
    if (!$("form").get(0).checkValidity()) {
      $("#divLoading").removeClass("loading");
    } else if($(this).hasClass("not-invock-gif")) {  
      $("#divLoading").removeClass("loading");
    } else {
      $("#divLoading").addClass("loading");
    }
  });
  
});