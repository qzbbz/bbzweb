function isNumber(str) {
	var re = /^[0-9]+.?[0-9]*$/;
	if (!re.test(str)) {
		return false;
	}
	return true;
}

function isEmail(email) {
	var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if (!pattern.test(email)) {
		return false;
	}
	return true;
}