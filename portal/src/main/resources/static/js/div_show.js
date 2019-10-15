/**
 *  弹出div的js
 *  
 *  20161021  sxq  
 *  
 *  
 *  弹出DIV 使用方式：
 *   
 *   弹出： 调用方式分为两种：
 *   	① 默认无参 $(selector).showDiv();
 *   	② 有参调用 $(selector).showDiv({
 *   			url:"#",						// 加载内容url 
				method:"POST",					// 加载方式
				title:"弹出页面",					// 页面标题
				shadow:false,					// 阴影
				fullScreen:true,				// 阴影是否全屏
				width:600,
				height:400,					 
				move:true,						//	拖动
				callback:                       //  回调函数
				data:							// 传输参数
 *   		});
 *  
 *   关闭已弹出div：$(selector).showDiv("close");
 *  
 * @param $
 * 
 */
(function($) {
	
	
	/**
	 *  弹出div
	 */
	$.fn.showDiv = function (config){
		
		// 关闭
		if( "close" == config  ){
			closeDiv(this);
			return false;
		}
		
		
		config = $.extend({
			url:"#",						// 加载内容url 
			method:"POST",					// 加载方式
			title:"弹出页面",					// 页面标题
			shadow:false,					// 阴影
			fullScreen:true,				// 阴影是否全屏
			width:600,
			height:400,					 
			move:true,						//	拖动
			callback:function (){
				return false;
			},
			data:{}							// 传输参数
		},config || {} );
		
		
		
		this.html("");
		this.attr("class","white_content_new");
		this.attr("style","height:" + config.height + "px;width:"+config.width+"px;overflow:hidden;");
		
		// 阴影
		if( config.shadow ){
			// 设置ID
			if( !this.attr("id") ){
				this.attr("id","show_div_"+(new Date()).getTime());
			}
			if( $("#"+(this.attr("id")+"_shadow")).length <= 0 ){
				if( config.fullScreen ){
					$('body').append(initShadow(this));
					$("#"+(this.attr("id")+"_shadow")).css({"z-index":101});
				}else{
					this.after(initShadow(this));
				}
			}
			$("#"+(this.attr("id")+"_shadow")).show();
		}
		// 实例化  阴影
		function initShadow(obj){
			return '<div id='+($(obj).attr("id")+"_shadow")+' class="black_overlay"></div>';
		}
		// 实例化标题
		_initTitle = function initTitle(){
			return  '<div class="close_new" style="width:'+config.width+'px;" ><div class="close_move_div" ><span>'+config.title+'</span></div><a class="popupContactClose_new" ></a></div>';
		};
		$(this).append( _initTitle );
		
		if( config.fullScreen ){
			$('body').append(this);
		}
		
		
		/**
		 *  拖动
		 */ 
		if( config.move  ){
			$(".close_new",this).css("cursor","move");
			$(".close_new",this).bind("mousedown",clickDiv);
			var $clickObject = this ;
			var clickX ; //鼠标到弹出div X轴 距离
			var clickY ; //鼠标到弹出div Y轴 距离
			var minY ;
			var maxY ;
			var minX ;
			var maxX ;
			function clickDiv(ev){
				if( ev ){
					$clickObject = $(ev.target).parent();
					var minDivY = $clickObject.offset().top;
					var minDivX = $clickObject.offset().left;
					clickX = ev.clientX - minDivX  ;
					clickY = ev.clientY - minDivY ;
					// 阴影
					var $div_parent_shadow = $("#"+($clickObject.attr("id"))+"_shadow");
					// 最大移动
					minY = $div_parent_shadow.offset().top;
					maxY = $div_parent_shadow.height();
					minX = $div_parent_shadow.offset().left;  // div 在X轴上向左最大移动量( 弹出div的left - 阴影的left)
					maxX =  $div_parent_shadow.width() ;  // div 在X轴上向右最大移动量
				}
				$(document).bind("mousemove",moveMouse);
			}
			
			function moveMouse(ev){
				// 弹出Div移动的坐标点（左上顶点）
				var moveX = ev.clientX - clickX ;
				var moveY = ev.clientY - clickY;
				
				// 限制宽度和高度
				if(  ( moveX +  config.width ) >= maxX  ){
					moveX = maxX -  config.width ;
				}else if( moveX <= minX ){
					moveX = minX;
				}
				if( moveY <=  minY ){
					moveY = minY;
				}else if( moveY >= ( maxY - config.height  )  ){
					moveY = maxY - config.height ;
				}
				$(".close_move_div", $clickObject ).parent().parent().css({"margin":"0"});
				if( $clickObject ){
					$(".close_move_div", $clickObject ).parent().parent().css({'left':( moveX )+"px",'top':( moveY )+"px"} );
				}else{
					$(".close_move_div").parent().parent().css({'left':( moveX )+"px",'top':( moveY )+"px"} );
				}
				
				$(document).bind("mouseup",upMouse);
			};
			function upMouse(){
				clickX  = 0;
				clickY = 0;
				$(document).unbind("mousemove");
			}
		}
		
		
		
		// 关闭弹出的div
		$(".popupContactClose_new",this).click( closeDiv );
		
		function closeDiv(ev){
			if( ev ){
				var e = ev.target;
				if( e ){
					var show_div = $(e).parent().parent();
					show_div.hide();
					show_div.html("");
					$("#"+($(show_div).attr("id")+"_shadow")).hide();
				}else{
					$(ev).hide();
					$(ev).html("");
					$("#"+($(ev).attr("id")+"_shadow")).hide();
				}
			}
		}
		 
		
		// 加载内容
		function loadHtml(){
			if( "POST" == config.method.toUpperCase()  ){
				return $('<div class = "show_div_content" style="height:'+(config.height -52 )+'px;width:'+config.width+'px;overflow:auto;"></div>').load( config.url , config.data || {} );
			}
			var data = (config.url).indexOf("?") > 0 ? "&":"?";
			$.each(config.data,function (i,val){
				data += (i+"="+val+"&");
			});
			return $('<div class = "show_div_content"></div>').load( config.url+data );
		}
		
		if(  1 < config.url.trim().length ){
			this.append(loadHtml);
		};
		
	};
  
}(jQuery));