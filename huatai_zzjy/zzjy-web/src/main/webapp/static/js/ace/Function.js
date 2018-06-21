Ace.apply(Function, {

	createCallback : function(/* args... */) {
		// 使得传递进来的参数在下面的function中可用。（译者注：这里实际上是返回了
		// 一个闭包函数，然后使用window来调用原来的函数，并把需要的参数传递进去。）
		var args = arguments;
		var method = this;
		return function() {
			return method.apply(window, args);
		};
	},

	createDelegate : function(obj, args, appendArgs) {
		var method = this;
		return function() {
			var callArgs = args || arguments;
			if (appendArgs === true) {
				callArgs = Array.prototype.slice.call(arguments, 0);
				callArgs = callArgs.concat(args);
			} else if (typeof appendArgs == "number") {
				callArgs = Array.prototype.slice.call(arguments, 0); // copy
				// arguments
				// first
				var applyArgs = [ appendArgs, 0 ].concat(args); // create method
				// call params
				Array.prototype.splice.apply(callArgs, applyArgs); // splice
				// them in
			}
			return method.apply(obj || window, callArgs);
		};
	},

	defer : function(millis, obj, args, appendArgs) {
		var fn = this.createDelegate(obj, args, appendArgs);
		if (millis) {

			return setTimeout(fn, millis);
		}
		fn();
		return 0;
	},

	createSequence : function(fcn, scope) {
		if (typeof fcn != "function") {
			return this;
		}
		var method = this;
		return function() {
			var retval = method.apply(this || window, arguments);
			fcn.apply(scope || this || window, arguments);
			return retval;
		};
	},

	createInterceptor : function(fcn, scope) {
		if (typeof fcn != "function") {
			return this;
		}
		var method = this;
		return function() {
			fcn.target = this;
			fcn.method = method;
			if (fcn.apply(scope || this || window, arguments) === false) {
				return;
			}
			return method.apply(this || window, arguments);
		};
	},

	createAfterInterceptor : function(fcn, scope) {
		if (typeof fcn != "function") {
			return this;
		}
		var method = this;
		return function() {
			fcn.target = this;
			fcn.method = method;
			var result = method.apply(this || window, arguments);
			if (result === false)
				return;
			fcn.apply(scope || this || window, arguments);
			return result;
		};
	}
});