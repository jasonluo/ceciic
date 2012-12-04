/* Setting the s_account */
function s_setAccount(){
var s_account="";
var curUrl = location.href;
	
		if(curUrl.indexOf("-stage") != -1 ) {
				s_account = "oracledevall,oracledevforum1";
		}
		else {
				s_account = "oracleglobal,oracleforums";
		}

	return s_account;
}

/ * Pre_plugins */
function s_prePlugins(s){

/* Forum tracking. Most popular pages */

	var mainForumID = "84";   /* default category on main page */
	var topTitle = document.title;  /* grabs page titles */
	var docUrl = document.location.href; /* grabs current url */

/* URLs for manual tag */
	var searchUrl = "http://forums.oracle.com/forums/search!default.jspa";
	var guestsettingsUrl = "http://forums.oracle.com/forums/guestsettings!default.jspa";
	var searchresultUrl = "http://forums.oracle.com/forums/search.jspa?";
	var usersettingsUrl ="http://forums.oracle.com/forums/usersettings!default.jspa";
	var multiUrl = "http://forums.oracle.com/forums/post!post.jspa";
	var editUrl1 = "http://forums.oracle.com/forums/post!";
	var editUrl2 = "http://forums.oracle.com/forums/post.jspa";
	var spellUrl = "http://forums.oracle.com/forums/spell.jspa";
	var newmessageUrl = "http://forums.oracle.com/forums/thread.jspa";
	var loginUrl = "http://forums.oracle.com/forums/login.jspa";
	var logoutUrl = "http://forums.oracle.com/forums/logout.jspa";
	var watchUrl = "http://forums.oracle.com/forums/editwatches!default.jspa";

/* Set pageName based on query string present */
	var categoryID=s.getQueryParam('categoryID');
	if (categoryID) {
    		categoryID=unescape(categoryID).toLowerCase().replace(/\++/g," ");
    		if (categoryID!=mainForumID) {
       			s.pageName= "F: Category: "+categoryID+": "+topTitle;
      		} else {  s.pageName= "F: "+topTitle;
	        } 
	}

/* Captures forums and new posts */
	var forumID=s.getQueryParam('forumID');
	if (forumID) {
    		forumID=unescape(forumID).toLowerCase().replace(/\++/g," ");      
      		s.pageName= "F: Forum: "+forumID+": "+topTitle;
	}

	if (docUrl==multiUrl){
    		s.pageName= "F: "+topTitle;
	}

	if (docUrl==editUrl1 || docUrl==editUrl2){
    		s.pageName= "F: Post Message: Edit";
	}

	if (docUrl==spellUrl){
    		s.pageName= "F: "+topTitle;
	}

	var threadID=s.getQueryParam('threadID');
	if (threadID) {
    		threadID=unescape(threadID).toLowerCase().replace(/\++/g," ");      
      		s.pageName= "F: Thread: "+threadID+": "+topTitle;
	}

	var userID=s.getQueryParam('userID');
	if (userID) {
    		userID=unescape(userID).toLowerCase().replace(/\++/g," ");      
      		s.pageName= "F: Profile: "+userID+": "+topTitle;
	}

	var annID=s.getQueryParam('annID');
	if (annID) {
    		annID=unescape(annID).toLowerCase().replace(/\++/g," ");      
      		s.pageName= "F: Annoucement: "+annID+": "+topTitle;
 
	}

	var msgID=s.getQueryParam('msgID');
	if (msgID) {
    		msgID=unescape(msgID).toLowerCase().replace(/\++/g," ");      
      		s.pageName= "F: "+msgID+": "+topTitle;
 	}

/* Reply to thread post & new thread creation */
	var messageID=s.getQueryParam('messageID');
	if (messageID) {
    		mmessageID=unescape(messageID).toLowerCase().replace(/\++/g," ");      
       
		/* tmID gets hidden value to match messageID with threadID */
		/* Replying to thread and message give same result         */
  
    		if (topTitle =="Post Message: Reply") { 
        		var tmID = document.postform.threadID.value;
      			s.pageName= "F: Message: "+tmID+": "+messageID+": "+topTitle;      	
      		} else {
      			s.pageName= "F: Message: "+messageID+": "+topTitle; 
     	 	}
	}

/* Look for pages without query strings and determine pageName */

	if (docUrl==guestsettingsUrl){
    		s.pageName= "F: Guest: "+topTitle;
	}

	if (docUrl==usersettingsUrl){
    		s.pageName= "F: Member: "+topTitle;
	}

	if (docUrl==loginUrl){
    		s.pageName= "F: Sign In";  
	}

	if (docUrl==logoutUrl){
    		s.pageName= "F: Log Out";  
	}

	if (topTitle=="Your Watches"){
    		s.pageName= "F: Editing Watches"; 
	}
/* Sets search results page name and prop4 with keyword if the q parameter is present */
	var objID=s.getQueryParam('objID'); 
	if (objID) {
    		objID=unescape(objID).toLowerCase().replace(/\++/g," "); 
  		var q=s.getQueryParam('q'); 
      		if (q) {
       			q=unescape(q).toLowerCase().replace(/\++/g," ");      
       			s.pageName="F: Forum Search: Search Results";
			s.prop3=docUrl+": "+q;
			s.prop4=q;
      		} 
            
    	} 
	else if (!objID) {
    		if (docUrl==searchUrl){        
              	s.pageName= "F: "+topTitle+": Default";
    		}
	}

/* Populate eVar with URL of the Page they are on, eVar with name of category and ID and fire event16 */
	s.url=s.linkHandler('forums/rss/','o');
	if(s.url){
		if (s.url.match("rssmessages.jspa"))
		{
			s.eVar17="messages:"
		}
		else if (s.url.match("rssannounce.jspa"))
		{
			s.eVar17="announcements:";
		}
		else if (s.url.match("rsspopularthreads.jspa"))
		{
			s.eVar17="popular threads:";
		}
		else if (s.url.match("rssthreads.jspa"))
		{
			s.eVar17="threads:";
		}
		s.eVar17=s.eVar17+s.url.substring(s.url.lastIndexOf("=")+1,s.url.length);
		s.eVar16=s.url;
		s.events=s.apl(s.events,"event16",",",1)
		s.linkTrackVars="eVar16,eVar17,events"
		s.linkTrackEvents="event16"
	}


}//close function