;(function($) {

	var initialize = function() {
		var minipopupOutfield = document.createElement("div");
		document.body.appendChild(minipopupOutfield);

		var minipopup = document.createElement("div");
		document.body.appendChild(minipopup);

		minipopup = $(minipopup);
		minipopup.attr("id", "minipopup");
		minipopup.css({
			display: "none",
			position: "absolute"
		});

		minipopupOutfield = $(minipopupOutfield);
		minipopupOutfield.attr("id", "minipopupOutfield");
		minipopupOutfield.css({
			display: "none",
			position: "fixed",
			top: 0,
			left: 0,
			right: 0,
			bottom: 0
		});

		minipopupOutfield.mousedown(function() {
			minipopup.hide();
			minipopupOutfield.hide();
		});
	};

	$.fn.minipopup = function(urlFunction, indicator) {
		if ($("#minipopup").length == 0) initialize();

		$(this).click(function(event) {
			jQuery("#minipopupOutfield").show();

			$("#minipopup").html(indicator).css({
				top: event.pageY - 15,
				left: event.pageX + $(event.target).width() + 10}).show();

			$.ajax({
				type: "GET",
				url: urlFunction(event),
				dataType: "script"
			});
		});
	};
})(jQuery);

function minipopupCallback(html) {
	jQuery("#minipopup").html(decodeURIComponent(html).replace(/\++/g, ' '));
}
