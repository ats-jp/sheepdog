function decodeURIForJavaEncode(javaEncoded) {
	return decodeURIComponent(javaEncoded).replace(/\++/g, ' ');
};

function eventCallback(html) {
	jQuery("#eventArea").html(decodeURIForJavaEncode(html));
	jQuery(".fwindow").fwindow();
}

function topicCallback(html) {
	jQuery("#topicArea").html(decodeURIForJavaEncode(html));
	jQuery(".fwindow").fwindow();
}

function commentCallback(html) {
	jQuery("#commentArea").html(decodeURIForJavaEncode(html));
	jQuery(".fwindow").fwindow();
}

function loadNotices(mode) {
	jQuery.ajax({
		type: "GET",
		url: "notice.act?mode=" + mode,
		dataType: "script"
	});
}

function deleteNotice(id, mode) {
	jQuery.ajax({
		type: "GET",
		url: "notice.act?mode=" + mode + "&deleteID=" + id,
		dataType: "script"
	});
}

function startNewNoticeCheck() {
	var myFunction = function() {
		jQuery.ajax({
			type: "GET",
			url: "new.act",
			dataType: "script"
		});
	};

	myFunction();
	setInterval(myFunction, 1000 * 60);
}

var intervalID;
var title;

function newNotice() {
	jQuery("#newNoticeArea").html(
		"<a class=\"functionalLink newNoticeMessage\" onclick=\"location.href='./';\">新しい未読情報があります。&nbsp;&nbsp;(ホームで確認)</a> <div id=\"newNoticeDeleteLink\"><a class=\"functionalLink\" href=\"javascript:void(0);\" onclick=\"deleteNewNotice();\">[通知を消去]</a></div>"
	).addClass("noticeAvailable");
	var next = "■■■■■■■■■■■■■■■";
	title = document.title;
	intervalID = setInterval(function() {
		var current = document.title;
		document.title = next;
		next = current;
	}, 1000);
}

function deleteNewNotice() {
	clearInterval(intervalID);
	jQuery("title").text(title);
	jQuery("#newNoticeArea").html("").removeClass("noticeAvailable");
}

function getScrollbarWidth() {
	var scrollDiv = document.createElement("div");
	jQuery(scrollDiv).css({
		width: "100px",
		height: "100px",
		overflow: "scroll",
		position: "absolute",
		top: "-9999px"});

	document.body.appendChild(scrollDiv);

	var width = jQuery(scrollDiv).get(0).offsetWidth - jQuery(scrollDiv).get(0).clientWidth;

	document.body.removeChild(scrollDiv);

	return width;
}
