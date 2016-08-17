$(function(){
	var editor = CodeMirror.fromTextArea(document.getElementById("code2"), {
		lineNumbers: true,
		styleActiveLine: true,
		matchBrackets: true,
		mode: "text/x-java",
		theme: "monokai",
		autoCloseBrackets: "()[]{}",
		cursorScrollMargin: 60,
		//lineWrapping: true,
	});
	editor.setSize("100%","50%");
	/*var editor = ace.edit("code");
		editor.setTheme("ace/theme/monokai");
		editor.getSession().setMode("ace/mode/javascript");
	*/
	$.post("fileopen")
	.done(function(data)
	{
		editor.getDoc().setValue(data["data"]);
	});
	$("#run").click(function(){
		$("#loading").show(); $("#done").hide(); $("#error").hide(); $("#error_output").hide();
		$.post("run",{"data":editor.getValue()}).done(function(data){
			if(data["status"] == "error")
			{ 
				$("#loading").hide(); $("#done").hide(); $("#error").show();
				$("#error_output").show()
				$("#error_output").text(data["data"]);
			}
			else
			{
				$("#loading").hide(); $("#done").show(); $("#error").hide();
			}
		});
	});
});
