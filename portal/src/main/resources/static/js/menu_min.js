(function($) {
    $.fn.menu = function(b) {
        var c,
            item,
            httpAdress;
        b = jQuery.extend({
                Speed: 220,
                autostart: 1,
                autohide: 1
            },b);
        c = $(this);  // li
        //item = c.children("ul").parent("li").children("a");
        item = $(this).children("a");
        httpAdress = window.location;
        //item.addClass("inactive");
        c.children("ul").parent("li").children("a").addClass("inactive");
        function _item() {
            var a = $(this);
            
            // 切换点击样式
            $(".active-1").each(function(){
            	$(this).attr("class","");
            });
            a.addClass("active-1");
            
            if (b.autohide) {
            	// 收起其他菜单
                a.parent().parent().find(".active,.active-1").parent("li").children("ul").slideUp(b.Speed / 1.1,
                    function() {
                        $(this).parent("li").children("a").removeAttr("class");
                        $(this).parent("li").children("a").attr("class", "inactive");
                    }
                );
            }
            // 显示
            if (a.attr("class") == "inactive") {
            	var ulO = a.parent("li").children("ul");
            	$.each($(ulO).children("li"),function(){
            		$(this).children("ul").hide();
            	});
            	ulO.slideDown(b.Speed,
                    function() {
                        a.removeAttr("class");
                        a.addClass("active");
                	}
                );
            }
            // 折叠
            if (a.attr("class") == "active" ) {
                a.removeAttr("class");
                a.addClass("inactive");
                a.parent("li").children("ul").slideUp(b.Speed);
                
            }
            
        }
        
        item.unbind('click').click(_item);
        
        if (b.autostart) {
            c.children("a").each(function() {
                if (this.href == httpAdress) {
                    $(this).parent("li").parent("ul").slideDown(b.Speed,
                        function() {
                            $(this).parent("li").children(".inactive").removeAttr("class");
                            $(this).parent("li").children("a").addClass("active");
                        });
                }
            });
        }
        
    };
})(jQuery);