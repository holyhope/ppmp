function notifyUser(title, content, level) {
	var $cross = $('<span></span>').attr('aria-hidden', 'true').html('&times;');
	var $closeButton = $('<button />').attr('type', 'button').addClass('close')
			.attr('data-dismiss', 'alert').attr('aria-label',
					getTranslation('notification', 'close')).append($cross);
	var $alert = $('<div></div>').addClass(
			"alert fade in alert-dismissible alert-" + level).text(content)
			.prepend($closeButton);
	$('#notification').append($alert);
}