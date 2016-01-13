var lang = {};

function addTranslation(bundle, key, value) {
	if ('undefined' == typeof lang[bundle]) {
		lang[bundle] = {};
	}
	lang[bundle][key] = value;
}

function getTranslation(bundle, key, defaultValue) {
	if ('undefined' == typeof lang[bundle]
			|| 'undefined' == typeof lang[bundle][key]) {
		return defaultValue;
	}
	return lang[bundle][key];
}