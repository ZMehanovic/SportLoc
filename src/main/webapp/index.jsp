<html>
<body>
	<h2>SportLoc</h2>
	<h3>
		Current options: 
		<br />-login -Requires: params(username, password)  -Returns success(boolean)
		<br />-register -Requires: json(UserBean) -Returns success(boolean)
		<br />-resetPassword -Requires: params(email) -Returns message(String)
		<br />-createEvent -Requires: json(EventBean) -Returns success(boolean), message(String)
		<br />-getSports -Returns list(id, title)
		<br />-getCities -Returns list(id, title)
		<br />-getEvents -Returns list(EventBean)
		<br />-memberSettings -Requires: params(email, eventId, status) -Returns success(boolean)
	</h3>
</body>
</html>
