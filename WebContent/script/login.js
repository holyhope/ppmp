jQuery(document).ready(
		function($) {
			console.log($)
			var $form = $('form');
			$form.append(
					$('<input />').attr('type', 'hidden').attr('name', 'ajax')
							.val('true')).on('submit', function(event) {
				event.preventDefault();

				$.ajax({
					url : $form.attr('action'),
					type : 'POST',
					data : $form.serialize(),
					dataType : 'json'
				}).always(function(response) {
					if (response.code == 0) {
						alert("L'utilisateur n'est pas valide");
					}
				});
			});
		});