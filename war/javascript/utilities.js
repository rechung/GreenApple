function getUrlParameter(urlParameterName) {
	if (urlParameterName = (new RegExp('[?&]'
			+ encodeURIComponent(urlParameterName) + '=([^&]*)'))
			.exec(location.search))
		return decodeURIComponent(urlParameterName[1]);
}

function decimalRound(num, decimals) {
	return Math.round(num * Math.pow(10, decimals)) / Math.pow(10, decimals);
}
