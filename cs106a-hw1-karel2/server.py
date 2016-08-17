from werkzeug.wrappers import Response, Request
from werkzeug.serving import run_simple
import eventlet, socketio
import posixpath, urllib, os, mimetypes, json, sqlite3, signal, subprocess
from operator import attrgetter
import time, subprocess

proc = None

sio = socketio.Server()

@sio.on('connect')
def connect(sid, environ):
    print("connect ", sid)

@sio.on('chat message')
def message(sid, data):
    print "hey there"
    print("message ", data)
    sio.emit('chat message', data)

@sio.on('disconnect')
def disconnect(sid):
    print('disconnect ', sid)

def translate_path(path):
	# abandon query parameters
	path = path.split('?',1)[0]
	path = path.split('#',1)[0]
	path = posixpath.normpath(urllib.unquote(path))
	words = path.split('/')
	words = filter(None, words)
	path = os.getcwd()
	for word in words:
		drive, word = os.path.splitdrive(word)
		head, word = os.path.split(word)
		if word in (os.curdir, os.pardir): continue
		path = os.path.join(path, word)
	return path

def application(environ, start_response):
	request = Request(environ)
	path = translate_path(request.path)
	try:
		print "opeining path",path 
		f = open(path,'rb')
	except IOError:
		if "fileopen" in request.path: 
			f2 = open("src/AnythingGoes.java")
			inp_text = f2.read()
			f2.close()
			response = Response(json.dumps({"data":inp_text}), mimetype="application/json")
			return response(environ, start_response)
		if "filewrite" in request.path:
			inp_text = request.form["data"]
			f2 = open("src/AnythingGoes.java", 'w')
			f2.write(inp_text)
			f2.close()
			response = Response(json.dumps({"status":"success"}), mimetype="application/json")
			return response(environ, start_response)
		if "run" in request.path:
			global proc
			if proc:
				os.killpg(os.getpgid(proc.pid), signal.SIGTERM)
			#inp_text = request.form["data"]
			#f2 = open("src/AnythingGoes.java", 'w')
			#f2.write(inp_text)
			#f2.close()
			out, err = subprocess.Popen("javac -d bin/ -cp lib/karel.jar:bin/:src/ src/*", shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE).communicate()
			if len(err):
				response = Response(json.dumps({"status":"error","data":err}), mimetype="application/json")
				return response(environ, start_response)
			proc = subprocess.Popen("DISPLAY=:5555 java -cp lib/karel.jar:bin/ AnythingGoes &", shell=True, preexec_fn=os.setsid, stdout=subprocess.PIPE, stderr=subprocess.PIPE) 
			response = Response(json.dumps({"status":"success"}), mimetype="application/json")
			return response(environ, start_response)
		# if not, 404 this sucker
		response = Response()
		response.status_code = 404
		return response(environ, start_response)
	response = Response(f.read(), mimetype=mimetypes.guess_type(request.path)[0])
	return response(environ, start_response)


if __name__ == '__main__':
	application = socketio.Middleware(sio, application)
	eventlet.wsgi.server(eventlet.listen(('', 80)), application)
	#run_simple('0.0.0.0', 80, application, use_debugger=True, use_reloader=True)
