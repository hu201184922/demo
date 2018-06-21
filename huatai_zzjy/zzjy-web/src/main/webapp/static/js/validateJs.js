//字符串方法
function $StringUtil(){};
$StringUtil.constructor = function(){};

/* 取得用户输入的字符串的长度 */
$StringUtil.prototype.getStrLength = function(ui) {
	var i, sum = 0;
	for (i = 0; i < ui.length; i++) {
		if ((ui.charCodeAt(i) >= 0) && (ui.charCodeAt(i) <= 255))
			sum++;
		else
			sum += 2;
	}
	return sum;
};

/* 判断用户输入是否为空 */
$StringUtil.prototype.isEmpty = function(ui) {
	return (ui == null || ui == "");
};

/* 删除两侧空格 */
$StringUtil.prototype.trim = function(ui) {
	var notValid = /(^\s)|(\s$)/;
	while (notValid.test(ui)) {
		ui = ui.replace(notValid, "");
	}
	return ui;
};

/* 删除前导空格 */
$StringUtil.prototype.leftTrim = function(ui) {
	var notValid = /^\s/;
	while (notValid.test(ui)) {
		ui = ui.replace(notValid, "");
	}
	return ui;
};

/* 删除后置空格 */
$StringUtil.prototype.rightTrim = function(ui) {
	var notValid = /\s$/;
	while (notValid.test(ui)) {
		ui = ui.replace(notValid, "");
	}
	return ui;
};

/* 删除所有空格 */
$StringUtil.prototype.allTrim = function(ui) {
	var notValid = /\s/;
	while (notValid.test(ui)) {
		ui = ui.replace(notValid, "");
	}
	return ui;
};

/* 是否包含前导空格 */
$StringUtil.prototype.isLeftSpace = function(ui) {
	var valid = /^\s/;
	return (valid.test(ui));
};

/* 是否包含后置空格 */
$StringUtil.prototype.isRightSpace = function(ui) {
	var valid = /\s$/;
	return (valid.test(ui));
};

/* 是否任何一侧包含空格 */
$StringUtil.prototype.isBothSpace = function(ui) {
	var valid = /(^\s)|(\s$)/;
	return (valid.test(ui));
};

/* 是否包含空格 */
$StringUtil.prototype.isIncSpace = function(ui) {
	var valid = /\s/;
	return (valid.test(ui));
};

/* 是否包含系统禁用的字符 */
$StringUtil.prototype.isIncSym = function(ui) {
	var valid = /[\'\"\,\<\>\+\-\*\/\%\^\=\\\!\&\|\(\)\[\]\{\}\:\;\~\`\#\$]+/;
	return (valid.test(ui));
};

/* 用户输入字符串长度是否等于指定值 */
$StringUtil.prototype.isLenEquals = function(ui, ud) {
	return (ui == ud);
};

/* 用户输入字符串长度是否在两值之间 */
$StringUtil.prototype.isLenBetween = function(ui, minl, maxl) {
	return (ui >= minl && ui <= maxl);
};

/** ********************************************************** */

function $ValidateUtil(){};
$ValidateUtil.constructor =function(){};
// 校验是否全由数字组成
$ValidateUtil.prototype.isNumber = function(s) {
	var patrn = /(^[1-9]\d*$)|(^\d{1}$)/;
	if (!patrn.exec(s))
		return false;
	return true;
};
// 校验是否小数
$ValidateUtil.prototype.isFloat = function(s) {
	var patrn = /(^[1-9]{1}\d*(\.\d+)?$)|(^[0]{1}(\.\d+)?$)/;
	if (!patrn.exec(s))
		return false;
	return true;
};
//校验是整数或小数
$ValidateUtil.prototype.isNumberOrFloat = function(s) {
	var patrn = /^[1-9]+([.]{1}[0-9]+){0,2}$/;
	if (!patrn.exec(s))
		return false;
	return true;
};
//英文加数字
$ValidateUtil.prototype.isLetterOrNumber = function(s) {
	var patrn = /^[a-zA-Z0-9]*$/;
	if (!patrn.exec(s))
		return false;
	return true;
};
// 校验登录名：只能输入5-20个以字母开头、可带数字、“_”、“.”的字串
$ValidateUtil.prototype.isRegisterUserName = function(s) {
	var patrn = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
	if (!patrn.exec(s))
		return false;
	return true;
};

// 校验用户姓名：只能输入1-30个以字母开头的字串
$ValidateUtil.prototype.isTrueName = function(s) {
	var patrn = /^[a-zA-Z]{1,30}$/;
	if (!patrn.exec(s))
		return false;
	return true;
};

// 校验密码：只能输入6-20个字母、数字、下划线
$ValidateUtil.prototype.isPasswd = function(s) {
	var patrn = /^(\w){6,20}$/;
	if (!patrn.exec(s))
		return false;
	return true;
};

//校验月份
$ValidateUtil.prototype.isMonth = function(s) {
	var patrn = /(^[1]{1}[0-2]{1}$)|(^[0]{0,1}[1-9]{1}$)/;
	if (!patrn.exec(s))
		return false;
	return true;
};

// 校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
$ValidateUtil.prototype.isTel = function(s) {
	//var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?(\d){1,12})+$/;
	var patrn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	//var patrn = /^(\d{3,4})[-]?(\d){7,8}+$/;
	if (!patrn.exec(s))
		return false;
	return true;
};

// 校验手机号码：必须以数字开头，除数字外，可含有“-”
$ValidateUtil.prototype.isMobil = function(s) {
	//var patrn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	var patrn = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	if (!patrn.exec(s))
		return false;
	return true;
};

// 校验邮政编码
$ValidateUtil.prototype.isPostalCode = function(s) {
	// var patrn=/^[a-zA-Z0-9]{3,12}$/;
	var patrn = /^[a-zA-Z0-9 ]{3,12}$/;
	if (!patrn.exec(s))
		return false;
	return true;
};

// 校验搜索关键字
$ValidateUtil.prototype.isSearch = function(s) {
	var patrn = /^[^`~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>\/?！”‘“’；：？、。，》《]{1}[^`~!@$%^&()+=|\\\][\]\{\}:;'\,.<>?！”‘“’；：？、。，》《]{0,19}$/;
	if (!patrn.exec(s))
		return false;
	return true;
};
// 检验ip地址
$ValidateUtil.prototype.isIp = function(s) // by zergling
{
	var patrn = /^[0-9.]{1,20}$/;
	if (!patrn.exec(s))
		return false;
	return true;
};
//校验邮箱
$ValidateUtil.prototype.isEmail = function(s) {
	var patrn = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	if (!patrn.exec(s))
		return false;
	return true;
};
//校验身份证
$ValidateUtil.prototype.isIdCard = function(s) {
	var patrn = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	if (!patrn.exec(s))
		return false;
	return true;
};

var stringUtils = new $StringUtil();
var validateUtils = new $ValidateUtil();
