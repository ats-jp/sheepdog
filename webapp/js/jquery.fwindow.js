;(function($) {

	var fwindow = {};

	$.fwindow = fwindow;

	var styleID = "floatingWindowStyle";

	var fw;

	var body;

	var closeCallback = null;

	var currentOpenTarget;

	var currentOpenHref;

	var getScrollbarWidth = function() {
		var scrollDiv = document.createElement("div");
		$(scrollDiv).css({
			width: "100px",
			height: "100px",
			overflow: "scroll",
			position: "absolute",
			top: "-9999px"});

		document.body.appendChild(scrollDiv);

		var width = $(scrollDiv).get(0).offsetWidth - $(scrollDiv).get(0).clientWidth;

		document.body.removeChild(scrollDiv);

		return width;
	}

	var getCSSValue = function(target, tagName) {
		$(target).css(tagName).match(/^(\d+)/);
		return parseInt(RegExp.$1);
	}

	var buildStyleTag = function(width, height) {
		return '<style id="' + styleID + '" type="text/css">#floatingWindowBody{width: ' + width + 'px; height: ' + height + 'px;} #floatingWindow{width: ' + (width + 22) + 'px; height: ' + (height + 60) + 'px;}</style>';
	}

	var windowMoveNow = false;

	var prepareWindowMoveFunction = function() {
		var lagX, lagY;

		var mousemoveFunction = function(event) {
			if (windowMoveNow) {
				fw.css({
					top: event.pageY - lagY,
					left: event.pageX - lagX
				});

				return false;
			}
		};

		var documentObject = $(document);

		documentObject.mousemove(mousemoveFunction);
		$("#floatingWindowOutfield")
			.mousedown(fwindow.close)
			.mousemove(mousemoveFunction);

		var floatingWindowHeader = $("#floatingWindowHeader");

		floatingWindowHeader.mousedown(function(event) {
			windowMoveNow = true;
			fw.addClass("floatingWindowDragging");
			var offset = floatingWindowHeader.parent().offset();
			lagX = event.pageX - offset.left + documentObject.scrollLeft();
			lagY = event.pageY - offset.top + documentObject.scrollTop();

			fw.css({
				top: event.pageY - lagY,
				left: event.pageX - lagX
			});

			//これがないとChoromeでカーソルがテキスト選択になってしまう
			return false;
		}).mouseup(function(event) {
			windowMoveNow = false;
			fw.removeClass("floatingWindowDragging");
			fw.css({
				top: event.pageY - lagY,
				left: event.pageX - lagX
			})
		});
	}

	var windowResizeNow = false;

	var windowResizeFunction;

	var prepareWindowResizeFunction = function() {
		var bodyWidthBase, bodyHeightBase, mouseDownX, mouseDownY;

		var bodyTag = $("body");
		windowResizeFunction = function(event) {
			if (windowResizeNow) {
				fw.hide();

				$("style#" + styleID).remove();

				var width = bodyWidthBase + event.pageX - mouseDownX;
				var height = bodyHeightBase + event.pageY - mouseDownY;

				bodyTag.before(buildStyleTag(width, height));

				fw.show();

				return false;
			}
		};

		var documentObject = $(document);

		documentObject.mousemove(windowResizeFunction);
		$("#floatingWindowOutfield").mousemove(windowResizeFunction);

		var floatingWindowCorner = $("#floatingWindowCorner");

		floatingWindowCorner.mousedown(function(event) {
			windowResizeNow = true;
			bodyWidthBase = getCSSValue(body, "width");
			bodyHeightBase = getCSSValue(body, "height");
			mouseDownX = event.pageX;
			mouseDownY = event.pageY;
			return false;
		}).mouseup(function(event) {
			windowResizeNow = false;
		});
	}

	$(document).ready(function() {
		fw = $("#floatingWindow");
		body = $("#floatingWindowBody");

		prepareWindowMoveFunction();
		prepareWindowResizeFunction();
	});

	$.fn.fwindow = function() {
		$(this).unbind("click", fwindow.open);
		$(this).click(fwindow.open);
	}

	fwindow.open = function() {
		var href = $(this).attr("href");
		$(this).attr("href", "javascript:void(0);");

		var titleArea = $("#floatingWindowTitle");
		body.unbind("load");
		body.load(function() {
			var contents = body.contents();

			titleArea.html(contents.find("title").html());

			//ウインドウを一瞬一番下までよけて、カーソルをoutfield上にし、元の位置に戻す
			contents.mouseover(function(event) {
				if (windowMoveNow)
					fw.css({top: $(document).height()});

				windowResizeFunction(event);
			});

			$("#floatingWindowOutfield").show();
			fw.show();

			fwindow.adjust();

			fw.css({
				left: ($("#floatingWindowOutfield").width() - fw.width()) / 2,
				top: 80
			});
		});

		body.attr({"src": href});

		currentOpenTarget = $(this);
		currentOpenHref = href;
	};

	fwindow.adjust = function() {
		$("style#" + styleID).remove();

		var contentsMargin = 5;

		var contents = body.contents();
		contents.find("body").css({margin: contentsMargin + "px"});

		var width = contents.width() + contentsMargin;
		var height = contents.height() + contentsMargin;

		var documentWidth = $(document).width();
		var documentHeight = $(document).height();

		var scrollbarWidth = getScrollbarWidth();

		if (width >= documentWidth - 200) {
			width = documentWidth - 200;
			height += scrollbarWidth;
		}

		if (height >= documentHeight - 200) {
			height = documentHeight - 200;
			width += scrollbarWidth;
		}

		//一度セットしたサイズを上書きできるように、styleタグでサイズ指定し
		//close時にタグごと削除する
		$("body").before(buildStyleTag(width, height));
	};

	fwindow.close = function() {
		if (closeCallback) closeCallback();

		fw.hide();

		$("style#" + styleID).remove();

		$("#floatingWindowOutfield").hide();

		currentOpenTarget.attr("href", currentOpenHref);
	};

	fwindow.registCloseCallback = function(callback) {
		closeCallback = callback;
	};
})(jQuery);
