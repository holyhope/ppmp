jQuery(document)
	.ready(function($) {
		var $form = $('form');
		$form.append($('<input />')
			.attr('type', 'hidden')
			.attr('name', 'ajax')
			.val('true'))
			.on('submit',function(event) {
				event.preventDefault();
				$.ajax({
					url : $form.attr('action'),
					type : 'POST',
					data : $form.serialize(),
					dataType : 'json'
				}).always(function(response) {
					var translations = getTranslation(
							'login',
							'notifications',
							{});
					if (response.code == 0) {
						notifyUser(
								translations.invalidUser.title,
								translations.invalidUser.content,
								"danger");
					}
					else if(response.code == 1){
						notifyUser(
							translations.badPassword.title,
							translations.badPassword.content,
							"danger");
					}
					});
				});
	});