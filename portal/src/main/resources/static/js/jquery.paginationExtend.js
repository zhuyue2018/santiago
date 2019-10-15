/**
 *
 * 此插件为分页插件原版（jquery.pagination.js）基础的修改与拓展
 * 
 * 20161020     sxq
 */
 (function($){
	 
	/**
	 * @class Class for calculating pagination values
	 */
	$.PaginationCalculator = function(maxentries, opts) {
		this.maxentries = maxentries;
		this.opts = opts;
	};
	
	$.extend($.PaginationCalculator.prototype, {
		/**
		 * Calculate the maximum number of pages
		 * @method
		 * @returns {Number}
		 */
		numPages:function() {
			return Math.ceil(this.maxentries/this.opts.items_per_page);
		},
		/**
		 * Calculate start and end point of pagination links depending on 
		 * current_page and num_display_entries.
		 * @returns {Array}
		 */
		getInterval:function(current_page)  {
			var ne_half = Math.floor(this.opts.num_display_entries/2);
			var np = this.numPages();
			var upper_limit = np - this.opts.num_display_entries;
			var start = current_page > ne_half ? Math.max( Math.min(current_page - ne_half, upper_limit), 0 ) : 0;
			var end = current_page > ne_half?Math.min(current_page+ne_half + (this.opts.num_display_entries % 2), np):Math.min(this.opts.num_display_entries, np);
			return {start:start, end:end};
		}
	});
	
	// Initialize jQuery object container for pagination renderers
	$.PaginationRenderers = {};
		
	
	$.PaginationRenderers.extendRenderer = function(maxentries, opts) {
		this.maxentries = maxentries;
		this.opts = opts;
		this.pc = new $.PaginationCalculator(maxentries, opts);
	};
	$.extend($.PaginationRenderers.extendRenderer.prototype, {
		/**
		 * Helper function for generating a single link (or a span tag if it's the current page)
		 * @param {Number} page_id The page id for the new item
		 * @param {Number} current_page 
		 * @param {Object} appendopts Options for the new item: text and classes
		 * @returns {jQuery} jQuery object containing the link
		 */
		createLink:function(page_id, current_page, appendopts){
			var lnk, np = this.pc.numPages();
			page_id = page_id<0?0:(page_id<np?page_id:np-1); // Normalize page id to sane value
			appendopts = $.extend({text:page_id+1, classes:""}, appendopts||{});
			if(page_id == current_page){
				lnk = $("<a class='current'>" + appendopts.text + "</a>");
			}
			else
			{
				lnk = $("<a>" + appendopts.text + "</a>")
					.attr('href', this.opts.link_to.replace(/__id__/,page_id));
			}
			if(appendopts.classes){ lnk.addClass(appendopts.classes); }
			lnk.data('page_id', page_id);
			return lnk;
		},
		// Generate a range of numeric links 
		appendRange:function(container, current_page, start, end, opts) {
			var i;
			for(i=start; i<end; i++) {
				this.createLink(i, current_page, opts).appendTo(container);
			}
		},
		//附加总页数及跳转显示
		_total:function( totalPage,current_page ){
			var str = '<div class="searchPage"><span class="page-sum">共<strong class="allPage">'+totalPage+
			'</strong>页</span><span class="page-go">跳转<input id="pagination_go_page" type="text" value='+(current_page+1)+' />页</span>'+
			'<a href="javascript:void(0);" ';
			if( totalPage <=1 ||  totalPage < (current_page+1) ){
				str += 'class="page-btn-g"';
			}else{
				str += 'class="page-btn-b"';
			}
			str += '>GO</a></div>';
			return str;
		},
		getLinks:function(current_page, eventHandler) { 
			var begin, end,
				interval = this.pc.getInterval(current_page),
				np = this.pc.numPages(),
				fragment = $("<div class='pagination'></div>");
			
			// Generate "Previous"-Link
			if(this.opts.prev_text && (current_page > 0 || this.opts.prev_show_always)){
				fragment.append(this.createLink(current_page-1, current_page, {text:this.opts.prev_text, classes:"prev"}));
			}
			// Generate starting points
			if (interval.start > 0 && this.opts.num_edge_entries > 0)
			{
				end = Math.min(this.opts.num_edge_entries, interval.start);
				this.appendRange(fragment, current_page, 0, end, {classes:'sp'});
				if(this.opts.num_edge_entries < interval.start && this.opts.ellipse_text)
				{
					$("<span class='pagination-break'>"+this.opts.ellipse_text+"</span>").appendTo(fragment);
				}
			}
			// Generate interval links
			this.appendRange(fragment, current_page, interval.start, interval.end);
			// Generate ending points
			if (interval.end < np && this.opts.num_edge_entries > 0)
			{
				if(np-this.opts.num_edge_entries > interval.end && this.opts.ellipse_text)
				{
					$("<span class='pagination-break'>"+this.opts.ellipse_text+"</span>").appendTo(fragment);
				}
				begin = Math.max(np-this.opts.num_edge_entries, interval.end);
				this.appendRange(fragment, current_page, begin, np, {classes:'ep'});
				
			}
			// Generate "Next"-Link
			if(this.opts.next_text && (current_page < np-1 || this.opts.next_show_always)){
				fragment.append(this.createLink(current_page+1, current_page, {text:this.opts.next_text, classes:"next"}));
			}
			
			// 附加分页后面部分
			fragment.append(this._total(np, current_page));
			
			$('a', fragment).click(eventHandler);
			
			return fragment;
		}
	});
	
	
	// Extend jQuery
	$.fn.pagination = function(maxentries, opts){
		
		// Initialize options with default values
		opts = $.extend({
			items_per_page:10,
			num_display_entries:4,
			current_page:0,
			num_edge_entries:1,
			link_to:"#",
			prev_text:"<i></i>上一页",
			next_text:"下一页<i></i>",
			ellipse_text:"...",
			prev_show_always:true,
			next_show_always:true,
			renderer:"extendRenderer", // 使用拓展渲染器
			show_if_single_page:true,
			load_first_page:false,
			callback:function(){return false;}
		},opts||{});
		
		var containers = this,
			renderer, links, current_page;

		
		/**
		 * This is the event handling function for the pagination links.
		 * 
		 * @param {int}
		 *            page_id The new page number
		 */
		function paginationClickHandler(evt){
			var links, new_current_page = $(evt.target).data('page_id'),continuePropagation;
				if( $(evt.target).attr("class") &&  $(evt.target).attr("class").indexOf("current") >= 0 ){
					return null;
				}
				if( new_current_page || new_current_page >=0 ){
					continuePropagation = selectPage(new_current_page);
				}else{
					var goPage = $(".searchPage #pagination_go_page").val() ;  // 跳转
					var current = $(".pagination .current");  // 当前页面对象
					var current_page =1;   //  当前页
					if( !isNaN( $(current[0]).html() )){
						current_page = $(current[0]).html();
					}else if( !isNaN( $(current[0]).html() ) ){
						current_page = $(current[1]).html();
					}
					if( goPage && !isNaN(goPage) && parseInt(goPage) <= np && parseInt(goPage) > 0 ){
						if( parseInt(goPage) != parseInt(current_page)){
							continuePropagation = selectPage( parseInt(goPage)-1 );
						}
					}else{
						if(parseInt(goPage) > np ||parseInt(goPage) <= 0  ){
							$(".searchPage #pagination_go_page").val(current_page);
						}else{
							$(".searchPage #pagination_go_page").val("");
						}
						return null;
					}
				}
			if (!continuePropagation) {
				evt.stopPropagation();
			}
			return continuePropagation;
		}
		
		/**
		 * This is a utility function for the internal event handlers. 
		 * It sets the new current page on the pagination container objects, 
		 * generates a new HTMl fragment for the pagination links and calls
		 * the callback function.
		 */
		function selectPage(new_current_page) {
			// update the link display of a all containers
			containers.data('current_page', new_current_page+1);
			links = renderer.getLinks(new_current_page, paginationClickHandler);
			containers.empty();
			links.appendTo(containers);
			// call the callback and propagate the event if it does not return false
			// var continuePropagation = opts.callback(new_current_page, containers);
			
			//$("input[name='page']").val(new_current_page+1);
			//$("input[name='rows']").val(opts.items_per_page);
			
			// 回调函数添加参数  每页显示数
			var continuePropagation = opts.callback(new_current_page+1,opts.items_per_page, containers);
			return continuePropagation;
		}
		
		// -----------------------------------
		// Initialize containers
		// -----------------------------------
		
        current_page = parseInt(opts.current_page);
		containers.data('current_page', current_page);
		// Create a sane value for maxentries and items_per_page
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		
		if(!$.PaginationRenderers[opts.renderer])
		{
			throw new ReferenceError("Pagination renderer '" + opts.renderer + "' was not found in jQuery.PaginationRenderers object.");
		}
		renderer = new $.PaginationRenderers[opts.renderer](maxentries, opts);
		
		// Attach control events to the DOM elements
		var pc = new $.PaginationCalculator(maxentries, opts);
		var np = pc.numPages();
		containers.bind('setPage', {numPages:np}, function(evt, page_id) { 
				if(page_id >= 0 && page_id < evt.data.numPages) {
					selectPage(page_id); return false;
				}
		});
		containers.bind('prevPage', function(evt){
				var current_page = $(this).data('current_page');
				if (current_page > 0) {
					selectPage(current_page - 1);
				}
				return false;
		});
		containers.bind('nextPage', {numPages:np}, function(evt){
				var current_page = $(this).data('current_page');
				if(current_page < evt.data.numPages - 1) {
					selectPage(current_page + 1);
				}
				return false;
		});
		
		
		// When all initialisation is done, draw the links
		links = renderer.getLinks(current_page, paginationClickHandler);
		containers.empty();
		if(np > 1 || opts.show_if_single_page) {
			links.appendTo(containers);
		}
		// call callback function
		if(opts.load_first_page) {
			// 回调函数添加参数  每页显示数
			opts.callback(current_page,opts.items_per_page, containers);
		}
	}; // End of $.fn.pagination block
	
	
})(jQuery);
