<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>

<%


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	<title>Page Title</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js"></script>
	<script type="text/javascript">
		$( document ).delegate("#photo", "pagecreate", function() {
		  alert('A page with an ID of "aboutPage" was just created by jQuery Mobile!');
		});

	
		function slidedown(){
			$.mobile.silentScroll(300);
		}
	</script>
</head>
<body>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
	<!-- Start of second page -->
<div data-role="page" id="bar">

	<div data-role="header">
		<h1>Bar</h1>
	</div><!-- /header -->

	<div data-role="content">
		<p>I'm the seond in the source order so I'm hidden when the page loads. I'm just shown if a link that references my ID is beeing clicked.</p>
		<p><a href="#foo">Back to foo</a></p>
		<p><a href="#photo">View Photo</a></p>
		<p><a href="javascript:slidedown()">Slide Down</a></p>

		<div class="ui-btn-text">
		<a class="ui-link-inherit" href="mailto:jdoe@foo.com">Basic email: mailto:jdoe@foo.com</a>
		
		<a id="twitter_button" href="https://twitter.com/intent/tweet?text=Egyptians%20Vote%20in%20Final%20Round%20of%20Parliamentary%20Elections%3A&url=http%3A%2F%2Fnyti.ms%2FulDhIY">
			<span>Twitter</span>
		</a>
		
			<div class="fb-like" data-send="true" data-width="450" data-show-faces="true"></div>
		</div>
		
	</div><!-- /content -->

	<div data-role="footer">
		<h4>Page Footer</h4>
	</div><!-- /footer -->
</div><!-- /page -->
</body>

</html>