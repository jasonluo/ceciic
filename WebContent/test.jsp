<!DOCTYPE html>
<html>
	<head>
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

<!-- Start of first page -->
<div data-role="page" id="foo">

	<div data-role="header">
		<h1>Foo</h1>
	</div><!-- /header -->

	<div data-role="content">
		<p>I'm first in the source order so I'm shown as the page.</p>
		<p>View internal page called <a href="#bar">bar</a></p>
		
		<form  method="get" action="result.jsp" data-ajax="false">
			<fieldset>
			<div data-role="fieldcontain">
			<label for="shipping">Shipping method:</label>
			<div class="ui-select">
			<div data-theme="a">
			<span>
			</span>
			<select id="shipping" name="shipping">
				<option value="Standard shipping">Standard: 7 day</option>
				<option value="Rush shipping">Rush: 3 days</option>
				<option value="Express shipping">Express: next day</option>
				<option value="Overnight shipping">Overnight</option>
			</select>
			</div>
			</div>
			</div>
			<div  data-theme="b" aria-disabled="false">
			<span>
			</span>
			<button value="submit-value" name="submit" data-theme="b" type="submit" aria-disabled="false">Submit</button>
			</div>
			</fieldset>
		</form>
	</div><!-- /content -->

	<div data-role="footer">
		<h4>Page Footer</h4>
	</div><!-- /footer -->
</div><!-- /page -->


<!-- Start of second page -->
<div data-role="page" id="bar">

	<div data-role="header">
		<h1>Bar</h1>
	</div><!-- /header -->

	<div data-role="content">
		<p>I'm the first in the source order so I'm hidden when the page loads. I'm just shown if a link that references my ID is beeing clicked.</p>
		<p><a href="#foo">Back to foo</a></p>
		<p><a href="#photo">View Photo</a></p>
		<p><a href="javascript:slidedown()">Slide Down</a></p>

		<div class="ui-btn-text">
		<a class="ui-link-inherit" href="mailto:jdoe@foo.com">Basic email: mailto:jdoe@foo.com</a>
		</div>
		
	</div><!-- /content -->

	<div data-role="footer">
		<h4>Page Footer</h4>
	</div><!-- /footer -->
</div><!-- /page -->

<!-- Start of third page -->
<div id="photo"  data-fullscreen="true" data-role="page" >
	<div data-position="fixed" data-role="header" role="banner">
		<a data-icon="arrow-l" data-rel="back" href="#" data-theme="a">
			<span>
				<span>Back</span>
				<span/>
			</span>
		</a>
		<h1 role="heading" aria-level="1">Fullscreen fixed header</h1>
		<a data-direction="reverse" data-iconpos="notext" data-icon="home" href="../../" title="Home" data-theme="a">
		</a>
	</div>
	<div data-role="content">
		<img height="480" width="640" alt="Photo Run" src="images/photo-run.jpeg">
		<p>This page demonstrates the "fullscreen" toolbar mode. This toolbar treatment is used in special cases where you want the content to fill the whole screen, and you want the header and footer toolbars to appear and disappear when the page is clicked responsively &mdash; a common scenario for photo, image or video viewers.</p>
		<p/>
		<p/>
	</div>
	<div data-position="fixed" data-role="footer" role="contentinfo">
		<h1 tabindex="0" role="heading" aria-level="1">Fullscreen fixed footer</h1>
	</div>
</div>
</html>
