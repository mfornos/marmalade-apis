
$(function () {
	
  var ApiApp = function() { this.init(); }

  ApiApp.prototype = {
    init: function() {
	    var that = this;
        
	    $(".messages").hide().fadeIn("slow");
        $(".messages > .close").click( function(e) { 
        	e.stopPropagation();
            $(this).parent().fadeOut("slow");
        });
        
        hljs.tabReplace = '    ';
        hljs.initHighlightingOnLoad();
    }
  };

  var App = new ApiApp();

});
