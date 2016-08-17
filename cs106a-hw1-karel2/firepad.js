    function getExampleRef() {
      var ref = new Firebase('https://firepad.firebaseio-demo.com');
      var hash = window.location.hash.replace(/#/g, '');
      if (hash) {
        ref = ref.child(hash);
	return ref;
      } else {
        ref = ref.push(); // generate unique location.
        window.location = window.location + '#' + ref.key(); // add it as a hash to the URL.
	return ref;
      }
    }
    
    function updateUserState(users)
    {
    	var length = Object.keys(users).length;
    	$("#chat-count").text(length-1);
    }

var firepadRef = new Firebase('https://benuku.firebaseio.com/karel'); 

$(function(){
	var socket = io();
	$('form').submit(function(){
        	socket.emit('chat message', $('#chat-input').val());
        	$('#chat-input').val('');
  		return false;
        });
	socket.on('chat message', function(msg){
		$('#messages').append($('<li>').text(msg));
	});
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
	editor.setSize("100%","100%");
	var userId = Math.floor(Math.random() * 9999999999).toString();
	
      var firepad = Firepad.fromCodeMirror(firepadRef, editor, {
        defaultText: '', userId: userId});	
	/*var editor = ace.edit("code");
		editor.setTheme("ace/theme/monokai");
		editor.getSession().setMode("ace/mode/javascript");
	*/
	firepad.on('ready', function() {
		$('.powered-by-firepad').hide();
		$.post("fileopen").done(function(data)
		{
			//editor.getDoc().setValue("");
			//editor.getDoc().setValue(data["data"]);
			console.log("fileopen")
			firepad.setText(data["data"]);
		});
		firepadRef.child("history").on("value",function(s)
		{
			console.log("filewrite");
			$.post("filewrite",{"data":firepad.getText()});
		});	
	});
	firepadRef.child("users").on("value",function(s)
	{
		var users = s.val();
		$(".chat-heading-icon").remove();
		for(var k in users)
		{
			var toAdd = $('<div class="chat-heading-icon" style="background-color:'+users[k].color+'"></div>')
			$("#chat-heading").prepend(toAdd);
		}
		//$("#chat-heading-icon").css("background-color",users[userId].color);
		var length = Object.keys(users).length;
    	$("#chat-count").text(length-1);
    	if(length>1) { 
    		$("#chat-heading-text").text("Chat now..."); 
    		$("#chat-heading").hover( function(){ $(this).css("cursor", "pointer"); });
    	}
    	else { 
    		$("#chat-heading-text").text("You're alone :("); 
    		$("#chat-heading").hover( function(){ $(this).css("cursor", "default"); });
    	}
	});
	$("#chat-heading-text").click(function(){
		$("#chat").css("display","flex");
	});
	/*
	firepadRef.on("child_changed",function(s)
	{
		console.log("change")
		var users = s.val();
		console.log(users);
		//var length = Object.keys(users).length;
		//$("#chat-count").text(length-1);
	});
	*/
	$("#run").click(function(){
		$("#loading").show(); $("#done").hide(); $("#error").hide(); $("#error_output").hide();
		$.post("run").done(function(data){
			if(data["status"] == "error")
			{ 
				$("#loading").hide(); $("#done").hide(); $("#error").show().delay(1000).fadeOut();
				$("#error_output").show()
				$("#error_output").text(data["data"]);
			}
			else
			{
				$("#loading").hide(); $("#done").show().delay(1000).fadeOut(); $("#error").hide();
			}
		});
	});
});
