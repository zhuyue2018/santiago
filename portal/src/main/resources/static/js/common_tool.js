/**
 *  此 JS 是公共工具js 主要是解决js的兼容性问题
 *  @author sxq
 *  @date 20161208
 */
if( !( "trim" in ( new String ) ) ){
	String.prototype.trim = function(){
		return this.replace(/^\s*/g, "").replace(/\s*$/g, '');
	};
}

if (!Array.isArray) {
	Array.isArray = function(arg) {
		return Object.prototype.toString.call(arg) === '[object Array]';
	};
}

if (!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(elt ) {
		var len = this.length >>> 0;
		var from = Number(arguments[1]) || 0;
		from = (from < 0) ? Math.ceil(from) : Math.floor(from);
		if (from < 0)from += len;
		for (; from < len; from++) {
			if (from in this && this[from] === elt)return from;
		}
		return -1;
	};
}

