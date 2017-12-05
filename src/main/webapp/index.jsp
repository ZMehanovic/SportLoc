<html>
<body>
	<h2>SportLoc</h2>
	<h3>
		Current options: 
		<br />-login -Requires: params(username, password)  -Returns success(boolean)
		<br />-register -Requires: json(UserBean) -Returns success(boolean)
		<br />-resetPassword -Requires: params(email) -Returns message(String)
		<br />-eventSettings -Requires: json(EventBean-needs to contain option(create,delete,update)) -Returns success(boolean)
		<br />-getSports -Returns list(id, title)
		<br />-getCities -Returns list(id, title)
		<br />-getEvents -Returns list(EventBean)
		<br />-memberSettings -Requires: params(email, eventId, status) -Returns success(boolean)
		<br />-getEventMembers -Requires: params(eventId) -Returns list(EventMembersBean)
	</h3>
</body>
</html>
