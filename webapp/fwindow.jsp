<%@ page pageEncoding="UTF-8" %>
<div id="floatingWindowOutfield"></div>
<div id="floatingWindow">
	<div id="floatingWindowHeader" ondblclick="jQuery.fwindow.adjust(); return false;" title="ダブルクリックでサイズ調整">
		<h1 id="floatingWindowTitle"></h1>
		<div id="floatingWindowAction" class="link">
			<a href="javascript:void(0);" onclick="jQuery.fwindow.close();" title="閉じる">[閉じる]</a>
		</div>
	</div>
	<iframe id="floatingWindowBody" frameborder="0"></iframe>
	<div id="floatingWindowFooter"><img src="fwindowcorner.png" id="floatingWindowCorner" /></div>
</div>
