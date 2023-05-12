$(function () {
  $(document).ajaxSend(function (event, jqxhr, settings) {
  });
  $(document).ajaxComplete(function (event, jqxhr, settings) {
    $("#divLoading").removeClass("loading");
  });
});