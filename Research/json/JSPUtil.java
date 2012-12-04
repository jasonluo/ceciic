/* <!--TOPIC Libraries & Definitions-->
 *
 * Filename:    JSPUtil.java
 * Description: This JSPUtil class has methods that take care of the common
 *              display logic and other miscellaneous jsp functions.  The class
 *              is used by any JSP that needs to perform these functions.
 *
 * $Modtime:   Aug 18 2010 23:20:14  $
 * $Log:   //cmlibrary_01/fafsa/archives/Web Development/Projects/FAFSAWeb/JavaSource/fotw1011/tags/JSPUtil.java-arc  $
 * 
 *    Rev 1.28   Aug 18 2010 23:26:30   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-P09070: CR803 -Post Startup #8 - School Code search updates - IA & Dev - Assigned To = Sam Smith, Last State Changer = Laurie Marlowe
 * Backing out FSC code changes
 * 
 *    Rev 1.26   May 11 2010 17:03:50   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-P09327: Code updates for not including 9070 in 5/16 release - IA & Dev - Sam Smith
 * Reverting FSC changes made for TT 9070 so they don't appear in the 5/16 release
 * 
 *    Rev 1.17   Mar 02 2010 00:14:40   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-P09036: Post Startup #27 - AZ - eligible flag issue - Development - Sam Smith
 * Updated setModel(...) to clear az/snt/ez fields when model changes for FOTW only.
 * 
 *    Rev 1.16   Oct 30 2009 10:47:10   luecbr
 * SCR
 * 
 *    Rev 1.15   Oct 03 2009 14:37:56   lianro
 * Fixed top bar on the confirmation page.
 * 
 *    Rev 1.14   Oct 02 2009 14:28:08   lampst
 * TTuser/items:Steve Lampe(lampst)
 * Resolution for WEB-S00479.IA.17: Module association for 0708 development - Pending IA - Steve Lampe
 * Correct Top Nav Confrimation highlight
 * 
 *    Rev 1.13   Oct 01 2009 21:26:14   lampst
 * TTuser/items:Steve Lampe(lampst)
 * Resolution for WEB-S00479.IA.17: Module association for 0708 development - Pending IA - Steve Lampe
 * Added logic to disable the Parent Demo tab
 * 
 *    Rev 1.12   Sep 29 2009 10:32:44   luoxja
 * TTuser/items:Jason Luo(luoxja)
 * Resolution for WEB-S00479.IA.16: Module association for 0708 development - Pending IA - Jason Luo
 * updated
 * 
 *    Rev 1.11   Sep 16 2009 14:57:28   lampst
 * TTuser/items:Steve Lampe(lampst)
 * Resolution for WEB-S00479.IA.17: Module association for 0708 development - Pending IA - Steve Lampe
 * Added topbar tag
 * 
 *    Rev 1.10   Sep 03 2009 16:46:34   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for success messsages
 * 
 *    Rev 1.9   Sep 03 2009 12:28:34   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for FOTW alert messages
 * 
 *    Rev 1.8   Aug 27 2009 17:09:34   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for confirmation link
 * 
 *    Rev 1.7   Aug 27 2009 14:35:50   luoxja
 * TTuser/items:Jason Luo(luoxja)
 * Resolution for WEB-S00479.IA.16: Module association for 0708 development - Pending IA - Jason Luo
 * updated getErrorAsJson
 * 
 *    Rev 1.6   Aug 25 2009 23:13:24   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Added support for returning validation errors as JSON data
 * 
 *    Rev 1.5   Aug 25 2009 11:10:58   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for confirmation link tag
 * 
 *    Rev 1.4   Aug 24 2009 14:34:00   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for AJAX messaging
 * 
 *    Rev 1.3   Aug 20 2009 17:01:26   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updated so error icon is returned but hidden for FOTW
 * 
 *    Rev 1.2   Aug 11 2009 10:29:22   lampst
 * TTuser/items:Steve Lampe(lampst)
 * Resolution for WEB-S00479.IA.17: Module association for 0708 development - Pending IA - Steve Lampe
 * Resolution for WEB-P00479: Added default overload method for getErrorIcon when using the locale
 * 
 *    Rev 1.1   Aug 11 2009 09:13:38   lianro
 * rollover demo code.
 * 
 *    Rev 1.0   Jul 22 2009 13:44:02   lianro
 * Initial revision.
 *
 *    Rev 1.13   May 15 2009 14:13:50   gangra
 * TTuser/items:Rachana Gangu(gangra)
 * Resolution for WEB-P05862: CR530 - Add new Register for SS question skip logic based on DOB. - Development - Rachana Gangu
 * Updated.
 *
 *    Rev 1.12   May 08 2009 16:34:20   gangra
 * TTuser/items:Rachana Gangu(gangra)
 * Resolution for WEB-S05862.DV.2: CR530 - Add new Register for SS question skip logic based on DOB. - Pending Dev - Rachana Gangu
 * Added method age().
 *
 *    Rev 1.11   Dec 12 2008 12:07:52   lianro
 * Added step number to the step image alt-text.
 *
 *    Rev 1.5   Aug 27 2008 12:25:06   lianro
 * close the reader before opening another one.
 *
 *    Rev 1.0   Apr 17 2008 16:11:54   lianro
 * TTuser/items:Ronghui Liang(LianRo)
 * Resolution for WEB-P00479: Module association for 0708 development - Waiting on Split IA - Configuration Control Board
 *
 * Initial revision.
 *
 *    Rev 1.4   Oct 25 2007 10:08:00   lianro
 * Remove "Open Another Studnet's Record" button from FAA Entry.
 *
 *    Rev 1.3   Sep 15 2007 10:40:54   lianro
 * Updated subheader app images/names.
 *
 *    Rev 1.1   Aug 03 2007 08:04:32   lianro
 * Added optional attribute "default" to checkbox/radio tags.
 * Updated toTitleCase() for the formatName tag
 *
 *    Rev 1.0   Jul 14 2007 22:38:34   lianro
 * TTuser/items:Ronghui Liang(LianRo)
 * Resolution for WEB-P00479: Module association for 0708 development - Waiting on Split IA - Configuration Control Board
 *
 * Initial revision.
 *
 */

package fotw1011.tags;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import java.io.*;
import java.util.*;

import GeneralPackage.*;
import fotw1011.common.*;
import fotw1011.tags.jsputils.ConfirmLinkTag;
import common.*;


/**
 * All JSPUtil methods are public static methods and they can be called
 * without instantiating an object.  The return clValues of these methods
 * are string objects that are placed within a table cell (<td></td>,)
 * unless specified otherwise.
 * <br><br>
 * Usage: For pages with standard entry templates, call methods getXXX()
 * directly.  See JSP template file entrytpl.jsp for samples. If the page
 * has exception buttons other than the ones defined in JSPUtilData,
 * one may define the exception button, and then call method replace.
 * For example:<br>
 *       String continue = "<a href="..."><img src="...."></a>";<br>
 *       String subfooter = replace(SUBFOOTER_EN, "<!--Exception-->", continue);
 *
 * <br><br>
 * Since 2004-2005, most methods in JSPUtil have been wrapped by custom JSP tags.
 * A JSP page should use these JSP tags if they are available.
 *
 * Creation date: (7/10/01 10:15:21 AM)
 */
public class JSPUtil implements JSPUtilData {
	protected static String APP_PROP_PATH;

	// Updated to use the Apache virtual includes
	protected static String SITESTAMP_EN = "";
	protected static String SITESTAMP_SP = "";

	// Load the site stamp include files
	static {

		try {
			APP_PROP_PATH = GlobalConfigValues.get_PropertyPath();
		} catch (Exception e) {
			AFOTWLog.log("JSPUtil failed to locate the property file path.",
			AFOTWLog.INFO, JSPUtil.class.getName());
		}

		try {
			String str;

			// English site stamp
            BufferedReader in = new BufferedReader(new FileReader(APP_PROP_PATH + "/site_timestamp.inc"));

            str = "";
            while ((str = in.readLine()) != null) {
            	if ((str.indexOf("//") < 0) && (str.length() > 0)) {
            		SITESTAMP_EN += str;
            	}
            }
            in.close();

            // Spanish site stamp
            in = new BufferedReader(new FileReader(APP_PROP_PATH + "/sp_site_timestamp.inc"));

            str = "";
            while ((str = in.readLine()) != null) {
              	if ((str.indexOf("//") < 0) && (str.length() > 0)) {
               		SITESTAMP_SP += str;
               	}
            }
            // close it
            in.close();

		} catch (Exception e) {
			AFOTWLog.log("JSPUtil failed to load the site stamp include fiels in the property file folder.",
				AFOTWLog.INFO, JSPUtil.class.getName());
		}
	}


	/** State Dropdown - Full State - English */
	// All
	public static String[][] DROPDOWN_STATE_FULL_EN = {
		{"" ,  BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.ENGLISH)},
		{"AL", ""},
		{"AK", ""},
		{"AB", ""},
		{"AS", ""},
		{"AZ", ""},
		{"AR", ""},
		{"BC", ""},
		{"CA", ""},
		{"CN", ""},
		{"CO", ""},
		{"CT", ""},
		{"DE", ""},
		{"DC", ""},
		{"FM", ""},
		{"FL", ""},
		{"FC", ""},
		{"GA", ""},
		{"GU", ""},
		{"HI", ""},
		{"ID", ""},
		{"IL", ""},
		{"IN", ""},
		{"IA", ""},
		{"KS", ""},
		{"KY", ""},
		{"LA", ""},
		{"ME", ""},
		{"MB", ""},
		{"MH", ""},
		{"MD", ""},
		{"MA", ""},
		{"MX", ""},
		{"MI", ""},
		{"AA", ""},
		{"AE", ""},
		{"AP", ""},
		{"MN", ""},
		{"MS", ""},
		{"MO", ""},
		{"MT", ""},
		{"NE", ""},
		{"NV", ""},
		{"NB", ""},
		{"NH", ""},
		{"NJ", ""},
		{"NM", ""},
		{"NY", ""},
		{"NF", ""},
		{"NL", ""},
		{"NC", ""},
		{"ND", ""},
		{"MP", ""},
		{"NT", ""},
		{"NS", ""},
		{"NU", ""},
		{"OH", ""},
		{"OK", ""},
		{"ON", ""},
		{"OR", ""},
		{"PW", ""},
		{"PA", ""},
		{"PE", ""},
		{"PR", ""},
		{"PQ", ""},
		{"QC", ""},
		{"RI", ""},
		{"SK", ""},
		{"SC", ""},
		{"SD", ""},
		{"TN", ""},
		{"TX", ""},
		{"VI", ""},
		{"UT", ""},
		{"VT", ""},
		{"VA", ""},
		{"WA", ""},
		{"WV", ""},
		{"WI", ""},
		{"WY", ""},
		{"YT", ""},
	};

	/** State Dropdown - Residence State - English */
	// Minus three millitary codes
	public static String[][] DROPDOWN_STATE_RESIDENCE_EN = {
		{"" , BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.ENGLISH)},
		{"AL", ""},
		{"AK", ""},
		{"AB", ""},
		{"AS", ""},
		{"AZ", ""},
		{"AR", ""},
		{"BC", ""},
		{"CA", ""},
		{"CN", ""},
		{"CO", ""},
		{"CT", ""},
		{"DE", ""},
		{"DC", ""},
		{"FM", ""},
		{"FL", ""},
		{"FC", ""},
		{"GA", ""},
		{"GU", ""},
		{"HI", ""},
		{"ID", ""},
		{"IL", ""},
		{"IN", ""},
		{"IA", ""},
		{"KS", ""},
		{"KY", ""},
		{"LA", ""},
		{"ME", ""},
		{"MB", ""},
		{"MH", ""},
		{"MD", ""},
		{"MA", ""},
		{"MX", ""},
		{"MI", ""},
		{"MN", ""},
		{"MS", ""},
		{"MO", ""},
		{"MT", ""},
		{"NE", ""},
		{"NV", ""},
		{"NB", ""},
		{"NH", ""},
		{"NJ", ""},
		{"NM", ""},
		{"NY", ""},
		{"NF", ""},
		{"NL", ""},
		{"NC", ""},
		{"ND", ""},
		{"MP", ""},
		{"NT", ""},
		{"NS", ""},
		{"NU", ""},
		{"OH", ""},
		{"OK", ""},
		{"ON", ""},
		{"OR", ""},
		{"PW", ""},
		{"PA", ""},
		{"PE", ""},
		{"PR", ""},
		{"PQ", ""},
		{"QC", ""},
		{"RI", ""},
		{"SK", ""},
		{"SC", ""},
		{"SD", ""},
		{"TN", ""},
		{"TX", ""},
		{"VI", ""},
		{"UT", ""},
		{"VT", ""},
		{"VA", ""},
		{"WA", ""},
		{"WV", ""},
		{"WI", ""},
		{"WY", ""},
		{"YT", ""},
	};

	/** State Dropdown - Federal School Code State - English */
	// Minus Canada provincial codes.
	public static String[][] DROPDOWN_STATE_SCHOOL_EN =
	/** CM 5011: add back CN and MX to FSC state
	 *  R.L. 1/6/04
	 */ {
		{"" , BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.ENGLISH)},
		{"AL", ""},
		{"AK", ""},
		{"AS", ""},
		{"AZ", ""},
		{"AR", ""},
		{"CA", ""},
		{"CN", ""},
		{"CO", ""},
		{"CT", ""},
		{"DE", ""},
		{"DC", ""},
		{"FM", ""},
		{"FL", ""},
		{"FC", ""},
		{"GA", ""},
		{"GU", ""},
		{"HI", ""},
		{"ID", ""},
		{"IL", ""},
		{"IN", ""},
		{"IA", ""},
		{"KS", ""},
		{"KY", ""},
		{"LA", ""},
		{"ME", ""},
		{"MH", ""},
		{"MD", ""},
		{"MA", ""},
		{"MX", ""},
		{"MI", ""},
		{"MN", ""},
		{"MS", ""},
		{"MO", ""},
		{"MT", ""},
		{"NE", ""},
		{"NV", ""},
		{"NH", ""},
		{"NJ", ""},
		{"NM", ""},
		{"NY", ""},
		{"NC", ""},
		{"ND", ""},
		{"MP", ""},
		{"OH", ""},
		{"OK", ""},
		{"OR", ""},
		{"PW", ""},
		{"PA", ""},
		{"PR", ""},
		{"RI", ""},
		{"SC", ""},
		{"SD", ""},
		{"TN", ""},
		{"TX", ""},
		{"VI", ""},
		{"UT", ""},
		{"VT", ""},
		{"VA", ""},
		{"WA", ""},
		{"WV", ""},
		{"WI", ""},
		{"WY", ""},
	};

	/** State Dropdown - ACG State - English */
	// Minus Canada provincial codes, Canada, and Mexico.
	public static String[][] DROPDOWN_STATE_ACG_EN = {
		{"" , BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.ENGLISH)},
		{"AL", ""},
		{"AK", ""},
		{"AS", ""},
		{"AZ", ""},
		{"AR", ""},
		{"CA", ""},
		{"CO", ""},
		{"CT", ""},
		{"DE", ""},
		{"DC", ""},
		{"FM", ""},
		{"FL", ""},
		{"FC", ""},
		{"GA", ""},
		{"GU", ""},
		{"HI", ""},
		{"ID", ""},
		{"IL", ""},
		{"IN", ""},
		{"IA", ""},
		{"KS", ""},
		{"KY", ""},
		{"LA", ""},
		{"ME", ""},
		{"MH", ""},
		{"MD", ""},
		{"MA", ""},
		{"MI", ""},
		{"MN", ""},
		{"MS", ""},
		{"MO", ""},
		{"MT", ""},
		{"NE", ""},
		{"NV", ""},
		{"NH", ""},
		{"NJ", ""},
		{"NM", ""},
		{"NY", ""},
		{"NC", ""},
		{"ND", ""},
		{"OH", ""},
		{"OK", ""},
		{"OR", ""},
		{"PW", ""},
		{"PA", ""},
		{"PR", ""},
		{"RI", ""},
		{"SC", ""},
		{"SD", ""},
		{"TN", ""},
		{"TX", ""},
		{"VI", ""},
		{"UT", ""},
		{"VT", ""},
		{"VA", ""},
		{"WA", ""},
		{"WV", ""},
		{"WI", ""},
		{"WY", ""},
	};

	/** State Dropdown - Full State - Spanish */
	public static String[][] DROPDOWN_STATE_FULL_SP = {
		{"",  BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.SPANISH)},
		{"AL", ""},
		{"AK", ""},
		{"AB", ""},
		{"AZ", ""},
		{"AR", ""},
		{"CA", ""},
		{"CN", ""},
		{"NC", ""},
		{"SC", ""},
		{"CO", ""},
		{"BC", ""},
		{"CT", ""},
		{"ND", ""},
		{"SD", ""},
		{"DE", ""},
		{"DC", ""},
		{"FM", ""},
		{"FL", ""},
		{"GA", ""},
		{"GU", ""},
		{"HI", ""},
		{"ID", ""},
		{"IL", ""},
		{"IN", ""},
		{"IA", ""},
		{"PE", ""},
		{"MP", ""},
		{"MH", ""},
		{"VI", ""},
		{"KS", ""},
		{"KY", ""},
		{"LA", ""},
		{"ME", ""},
		{"MB", ""},
		{"MD", ""},
		{"MA", ""},
		{"MX", ""},
		{"MI", ""},
		{"AA", ""},
		{"AE", ""},
		{"AP", ""},
		{"MN", ""},
		{"MS", ""},
		{"MO", ""},
		{"MT", ""},
		{"NE", ""},
		{"NV", ""},
		{"NB", ""},
		{"NS", ""},
		{"NJ", ""},
		{"NY", ""},
		{"NH", ""},
		{"NM", ""},
		{"NU", ""},
		{"OH", ""},
		{"OK", ""},
		{"ON", ""},
		{"OR", ""},
		{"FC", ""},
		{"PW", ""},
		{"PA", ""},
		{"PR", ""},
		{"PQ", ""},
		{"QC", ""},
		{"RI", ""},
		{"AS", ""},
		{"SK", ""},
		{"TN", ""},
		{"NF", ""},
		{"NL", ""},
		{"NT", ""},
		{"TX", ""},
		{"UT", ""},
		{"VT", ""},
		{"VA", ""},
		{"WV", ""},
		{"WA", ""},
		{"WI", ""},
		{"WY", ""},
		{"YT", ""},
	};

	/** State Dropdown - Residence State - Spanish */
	public static String[][] DROPDOWN_STATE_RESIDENCE_SP = {
		{"",  BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.SPANISH)},
		{"AL", ""},
		{"AK", ""},
		{"AB", ""},
		{"AZ", ""},
		{"AR", ""},
		{"CA", ""},
		{"CN", ""},
		{"NC", ""},
		{"SC", ""},
		{"CO", ""},
		{"BC", ""},
		{"CT", ""},
		{"ND", ""},
		{"SD", ""},
		{"DE", ""},
		{"DC", ""},
		{"FM", ""},
		{"FL", ""},
		{"GA", ""},
		{"GU", ""},
		{"HI", ""},
		{"ID", ""},
		{"IL", ""},
		{"IN", ""},
		{"IA", ""},
		{"PE", ""},
		{"MP", ""},
		{"MH", ""},
		{"VI", ""},
		{"KS", ""},
		{"KY", ""},
		{"LA", ""},
		{"ME", ""},
		{"MB", ""},
		{"MD", ""},
		{"MA", ""},
		{"MX", ""},
		{"MI", ""},
		{"MN", ""},
		{"MS", ""},
		{"MO", ""},
		{"MT", ""},
		{"NE", ""},
		{"NV", ""},
		{"NB", ""},
		{"NS", ""},
		{"NJ", ""},
		{"NY", ""},
		{"NH", ""},
		{"NM", ""},
		{"NU", ""},
		{"OH", ""},
		{"OK", ""},
		{"ON", ""},
		{"OR", ""},
		{"FC", ""},
		{"PW", ""},
		{"PA", ""},
		{"PR", ""},
		{"PQ", ""},
		{"QC", ""},
		{"RI", ""},
		{"AS", ""},
		{"SK", ""},
		{"TN", ""},
		{"NF", ""},
		{"NL", ""},
		{"NT", ""},
		{"TX", ""},
		{"UT", ""},
		{"VT", ""},
		{"VA", ""},
		{"WV", ""},
		{"WA", ""},
		{"WI", ""},
		{"WY", ""},
		{"YT", ""},
	};

	/** State Dropdown - Federal School Code State - Spanish */
	public static String[][] DROPDOWN_STATE_SCHOOL_SP =
	/** CM 5011: add back CN and MX to FSC state
	 *  R.L. 1/6/04
	 */ {
		{"",  BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.SPANISH)},
		{"AL", ""},
		{"AK", ""},
		{"AZ", ""},
		{"AR", ""},
		{"CA", ""},
		{"CN", ""},
		{"NC", ""},
		{"SC", ""},
		{"CO", ""},
		{"CT", ""},
		{"ND", ""},
		{"SD", ""},
		{"DE", ""},
		{"DC", ""},
		{"FM", ""},
		{"FL", ""},
		{"GA", ""},
		{"GU", ""},
		{"HI", ""},
		{"ID", ""},
		{"IL", ""},
		{"IN", ""},
		{"IA", ""},
		{"MP", ""},
		{"MH", ""},
		{"VI", ""},
		{"KS", ""},
		{"KY", ""},
		{"LA", ""},
		{"ME", ""},
		{"MD", ""},
		{"MA", ""},
		{"MX", ""},
		{"MI", ""},
		{"MN", ""},
		{"MS", ""},
		{"MO", ""},
		{"MT", ""},
		{"NE", ""},
		{"NV", ""},
		{"NJ", ""},
		{"NY", ""},
		{"NH", ""},
		{"NM", ""},
		{"OH", ""},
		{"OK", ""},
		{"OR", ""},
		{"FC", ""},
		{"PW", ""},
		{"PA", ""},
		{"PR", ""},
		{"RI", ""},
		{"AS", ""},
		{"TN", ""},
		{"TX", ""},
		{"UT", ""},
		{"VT", ""},
		{"VA", ""},
		{"WV", ""},
		{"WA", ""},
		{"WI", ""},
		{"WY", ""},
	};

	/** State Dropdown - ACG - Spanish */
	public static String[][] DROPDOWN_STATE_ACG_SP = {
		{"",  BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.SPANISH)},
		{"AL", ""},
		{"AK", ""},
		{"AZ", ""},
		{"AR", ""},
		{"CA", ""},
		{"NC", ""},
		{"SC", ""},
		{"CO", ""},
		{"CT", ""},
		{"ND", ""},
		{"SD", ""},
		{"DE", ""},
		{"DC", ""},
		{"FM", ""},
		{"FL", ""},
		{"GA", ""},
		{"GU", ""},
		{"HI", ""},
		{"ID", ""},
		{"IL", ""},
		{"IN", ""},
		{"IA", ""},
		{"MH", ""},
		{"VI", ""},
		{"KS", ""},
		{"KY", ""},
		{"LA", ""},
		{"ME", ""},
		{"MD", ""},
		{"MA", ""},
		{"MI", ""},
		{"MN", ""},
		{"MS", ""},
		{"MO", ""},
		{"MT", ""},
		{"NE", ""},
		{"NV", ""},
		{"NJ", ""},
		{"NY", ""},
		{"NH", ""},
		{"NM", ""},
		{"OH", ""},
		{"OK", ""},
		{"OR", ""},
		{"FC", ""},
		{"PW", ""},
		{"PA", ""},
		{"PR", ""},
		{"RI", ""},
		{"AS", ""},
		{"TN", ""},
		{"TX", ""},
		{"UT", ""},
		{"VT", ""},
		{"VA", ""},
		{"WV", ""},
		{"WA", ""},
		{"WI", ""},
		{"WY", ""},
	};


	/*
	 * CR 5046 - Get full state names from the resource bundles
	 */
	static
	{
		// Full mailing state
		for (int i=1; i<DROPDOWN_STATE_FULL_EN.length; i++) {
			DROPDOWN_STATE_FULL_EN[i][1] =
				BundleUtil.getStateName(DROPDOWN_STATE_FULL_EN[i][0], BundleUtil.ENGLISH);

			DROPDOWN_STATE_FULL_SP[i][1] =
				BundleUtil.getStateName(DROPDOWN_STATE_FULL_SP[i][0], BundleUtil.SPANISH);
		}

		// residence and drive states
		for (int i=1; i<DROPDOWN_STATE_RESIDENCE_EN.length; i++) {
			DROPDOWN_STATE_RESIDENCE_EN[i][1] =
				BundleUtil.getStateName(DROPDOWN_STATE_RESIDENCE_EN[i][0], BundleUtil.ENGLISH);

			DROPDOWN_STATE_RESIDENCE_SP[i][1] =
				BundleUtil.getStateName(DROPDOWN_STATE_RESIDENCE_SP[i][0], BundleUtil.SPANISH);
		}

		// FSC states
		for (int i=1; i<DROPDOWN_STATE_SCHOOL_EN.length; i++) {
			DROPDOWN_STATE_SCHOOL_EN[i][1] =
				BundleUtil.getStateName(DROPDOWN_STATE_SCHOOL_EN[i][0], BundleUtil.ENGLISH);

			DROPDOWN_STATE_SCHOOL_SP[i][1] =
				BundleUtil.getStateName(DROPDOWN_STATE_SCHOOL_SP[i][0], BundleUtil.SPANISH);
		}


		// ACG states
		for (int i=1; i<DROPDOWN_STATE_ACG_EN.length; i++) {
			DROPDOWN_STATE_ACG_EN[i][1] =
				BundleUtil.getStateName(DROPDOWN_STATE_ACG_EN[i][0], BundleUtil.ENGLISH);

			DROPDOWN_STATE_ACG_SP[i][1] =
				BundleUtil.getStateName(DROPDOWN_STATE_ACG_SP[i][0], BundleUtil.SPANISH);
		}
	}

    /** HashMap of service option descriptions on the FAA Main Menu
     *  by service option number */
    protected final static HashMap		m_clSrvOptDesc = new HashMap();

    static
    {
		m_clSrvOptDesc.put("1",  "Student Inquiry");
		//m_clSrvOptDesc.put("2",  "Batch Status");
		//m_clSrvOptDesc.put("3",  "Signature Hold File Request");
		//m_clSrvOptDesc.put("4",  "Paper Renewal Application Request");
		m_clSrvOptDesc.put("5",  "FAFSA/Renewal Application Entry");
		m_clSrvOptDesc.put("6",  "Renewal FAFSA Entry");
		m_clSrvOptDesc.put("7",  "Corrections Entry");
		m_clSrvOptDesc.put("8",  "FAFSA/Renewal Application Restore");
		m_clSrvOptDesc.put("9",  "Renewal FAFSA Restore");
		m_clSrvOptDesc.put("10", "Corrections Restore");
		m_clSrvOptDesc.put("11", "Verification Tool");
		m_clSrvOptDesc.put("12", "ISIR Request");
		m_clSrvOptDesc.put("13", "Return of Title IV Funds on the Web");
		m_clSrvOptDesc.put("14", "ISIR Analysis Tool");
		m_clSrvOptDesc.put("15", "FAFSA Completion");
	}


    //------------------------------------------------------------------------
    /**
     * Default constructor
     * @param  None
     * @return None
     * @throws None
     */
    //------------------------------------------------------------------------
    public JSPUtil() {}
	//------------------------------------------------------------------------
	/**
	 * This method formats data mmddccyy or ccyymmdd into mm/dd/ccyy, and
	 * mmccyy or ccyymm into mm/ccyy.
	 * The method may be used to display static date on the JSP pages.
	 * Creation date: (7/18/01 3:35:08 PM)
	 * @return java.lang.String date with slashes
	 * @param date java.lang.String date mmddccyy or mmccyy
	 */
	//------------------------------------------------------------------------
	public static String formatDate(String szDate) {
		String szFormated = "";

		try {
			szDate = szDate.trim();
			int iLen = szDate.length();
			StringBuffer clBuf = new StringBuffer();
			boolean bCCYYMM = false;

			if (iLen == 8 || iLen == 6) {
				if (Integer.parseInt(szDate.substring(0, 4)) > 1800) {
					// ccyymmdd or ccyymm fomrat
					bCCYYMM = true;
				} else {
					bCCYYMM = false;
				}
			}

			if (iLen == 8) {
				if (bCCYYMM) {
					// ccyymmdd
					clBuf.append(szDate.substring(4, 6))
					.append("/")
					.append(szDate.substring(6, 8))
					.append("/")
					.append(szDate.substring(0,4));
				} else {
					// mmddccyy
					clBuf.append(szDate.substring(0,2))
					.append("/")
					.append(szDate.substring(2, 4))
					.append("/")
					.append(szDate.substring(4, 8));
				}
				szFormated = clBuf.toString();
			} else if (iLen == 6) {
				if (bCCYYMM) {
					//ccyymm
					clBuf.append(szDate.substring(4, 6))
					.append("/")
					.append(szDate.substring(0,4));
				} else {
					// mmccyy
					clBuf.append(szDate.substring(0,2))
					.append("/")
					.append(szDate.substring(2, 6));
				}
				szFormated = clBuf.toString();
			} else {
				szFormated = szDate;
			}
		} catch (Exception e) {
			szFormated = szDate;
		}

		return szFormated;
	}
	//------------------------------------------------------------------------
	/**
	 * This method formats SSN with dashes like 123-45-6789.  This may be used to
	 * display a static SSN on the JSP page.
	 * Creation date: (7/18/01 3:36:23 PM)
	 * @return java.lang.String SSN with dashes as 123-45-6789
	 * @param szSSN java.lang.String SSN as 123456789
	 */
	//------------------------------------------------------------------------
	public static String formatSSN(String szSSN) {
		try {
			szSSN = szSSN.trim();
			if (szSSN.length() == 9) {
				return szSSN.substring(0,3) + "-" + szSSN.substring(3,5) + "-" +
					   szSSN.substring(5,9);
			} else {
				return szSSN;
			}
		} catch (Exception e) {
			return szSSN;
		}
	}
	//------------------------------------------------------------------------
	/**
	 * This method formats SSN with dashes like 123-45-6789.  This may be used to
	 * display a static SSN on the JSP page.  This version can mask the first 5 digits
	 * Creation date: (7/18/01 3:36:23 PM)
	 * @return java.lang.String SSN with dashes as 123-45-6789
	 * @param bMask boolean true = Mask the ssn as XXX-XX-6789,false = Do not mask
	 * @param szSSN java.lang.String SSN as 123456789
	 */
	//------------------------------------------------------------------------
	public static String formatSSN(boolean bMask, String szSSN) {
		if (bMask) {
			try {
				szSSN = szSSN.trim();
				if (szSSN.length() == 9) {
        				    if(szSSN.startsWith("888"))
        				    {
        					return formatSSN(szSSN);
        				    }
				    
					return "XXX-XX-" + szSSN.substring(5,9);
				} else {
					return szSSN;
				}
			} catch (Exception e) {
				return szSSN;
			}
		} else {
			return formatSSN(szSSN);
		}
	}
	//---------------------------------------------------------------------
	/**
	 * Returns the index of some JSPUtilData data arrays for the application.
	 * Creation date: (7/12/01 11:51:59 AM)
	 * @return int array index
	 * @param szApptype java.lang.String szApptype
	 */
	 //---------------------------------------------------------------------
	public static int getAppIndex(String szApptype) {
		// default is not to convert app type
		return getAppIndex(szApptype, "");
	}
	//---------------------------------------------------------------------
	/**
	 * Returns the index of some JSPUtilData data arrays for the application.
	 * Creation date: (7/12/01 11:51:59 AM)
	 * @return int array index
	 * @param szApptype java.lang.String szApptype
	 * @param szFaaMode String FAA mode
	 */
	 //---------------------------------------------------------------------
	public static int getAppIndex(String szApptype, String szFaaMode) {
		try {
			// get internal FAA apptype
			szApptype = isFaaModeAny(szFaaMode)? getFAAAppType(szApptype, szFaaMode) : szApptype;

			if (szApptype.equals(APP_TYPE_FFOTW)) {
				return FFOTW;
			} else if (szApptype.equals(APP_TYPE_RFOTW)) {
				return RFOTW;
			} else if (szApptype.equals(APP_TYPE_SFOTW)) {
				return SFOTW;
			} else if (szApptype.equals(APP_TYPE_COTW)) {
				return COTW;
			} else if (szApptype.equals(APP_TYPE_APPSTAT)) {
				return APPSTAT;
			} else if (szApptype.equals(APP_TYPE_ESIG)) {
				return ESIG;
			} else if (szApptype.equals(APP_TYPE_SA)) {
				return SA;
			} else if (szApptype.equals(APP_TYPE_FAA)) {
				return FAA;
			} else if (szApptype.equals(APP_TYPE_WKSHT)) {
				return WKSHT;
			} else if (szApptype.equals(APP_TYPE_FSC)) {
				return FSC;
			} else if (szApptype.equals(APP_TYPE_WSDRUG)) {
				return WSDRUG;
			} else if (szApptype.equals(APP_TYPE_ACG)) {
				return ACG;
			} else if (szApptype.equals(APP_TYPE_FAA_FFOTW)) {
				return FAA_FFOTW;
			} else if (szApptype.equals(APP_TYPE_FAA_RFOTW)) {
				return FAA_RFOTW;
			} else if (szApptype.equals(APP_TYPE_FAA_COTW)) {
				return FAA_COTW;
			} else if (szApptype.equals(APP_TYPE_FAA_R2T4)) {
				return FAA_R2T4;
			} else if (szApptype.equals(APP_TYPE_FAA_ISIRREQ)) {
				return FAA_ISIRREQ;
			} else if (szApptype.equals(APP_TYPE_SWKSHT)) {
				return SWKSHT;
			} else if (szApptype.equals(APP_TYPE_SWSDRUG)) {
				return SWSDRUG;
			} else if (szApptype.equals(APP_TYPE_FAA_IATOOL)) {
				return FAA_IATOOL;
			} else if (szApptype.equals(APP_TYPE_SROTW)) {
				return SROTW;
			} else if (szApptype.equals(APP_TYPE_FSCADD)) {
				return FSCADD;
			} else if (szApptype.equals(APP_TYPE_FAA_SA)) {
				return FAA_SA;
			} else if (szApptype.equals(APP_TYPE_FAA_VERIFWS)) {
				return FAA_VERIFWS;
			} else if (szApptype.equals(APP_TYPE_HS_COMPLETION)) {
				return HS_COMPLETION;
			} else {
				return FFOTW;
			}

		} catch (Exception e) {
			return FFOTW;
		}
	}
    //------------------------------------------------------------------------
    /**
     * This method constructs the HTML body element with an onload event to
     * handle the error message.  The output is printed on the JSP source file
     * where an HTML body tag is required.  This version supports optional init
     * JS function names and form names
     * @param bRefresh boolean true = Call JavaScript function RefreshSession()
     * to display a warning message after the session is left idle for 25 minutes;
     * false = Do not call.
     * @param bSidebar boolean true = side bar; false = no side bar
     * @param bKeysize boolean true = alert keysize < 128-bit
     * @param szLocale String value of [locale]
     * @param szInit String init JS function name for the onload event
     * @param szForm String docuemnt form name
     * @param szErrField String value of [ERRFIELD]
     * @param szErrMsg String value of [ERRMSG]
     * @param szErrType String value of [ERRCONF]
     * @param szErrIndex String value of [ERRINDEX]
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getBodyTag(
    		    boolean bFOTW,
    			boolean bSidebar,       // side bar?
    			boolean bRefresh,       // refresh session?
    			boolean bKeysize,       // alert keysize?
				String szLocale,        // [locale]
                String szInit,			// init JS function
    			String szForm, 			// document form name
                String szErrField,		// [ERRFIELD]
                String szErrMsg,		// [ERRMSG]
                String szErrType, 		// [ERRCONF]
				String szErrIndex       // [ERRINDEX]
                ) {
        String szBody = getBodyTag(bFOTW, bRefresh, bKeysize, szLocale, szErrField, szErrMsg, szErrType, szErrIndex);

        // Insert body style
        String szStyle = bSidebar? "fafsa" : "fafsa-noside";
        szBody = replace(szBody, "<!--BODY_STYLE-->", szStyle);

        // For those who use their own init JS name
    	if (szInit != null && !szInit.equals("InitForm") && szInit.length() > 0) {
    		szInit = new StringBuffer(szInit).append("(").toString();
    		szBody = replace(szBody, "InitForm(", szInit);
    	}

    	// for those who use their own document form name
    	if (szForm != null && !szForm.equals("FAFSA") && szForm.length() > 0) {
    		szForm = new StringBuffer("document.").append(szForm).toString();
    		szBody = replace(szBody, "document.FAFSA", szForm);
    	}

    	return szBody;
    }
    //------------------------------------------------------------------------
    /**
     * This method constructs the HTML body element with an onload event to
     * handle the error message.  The output is printed on the JSP source file
     * where an HTML body tag is required.
     * This version does not include the session refresh & keysize warning messages
     * @param szErrField String value of [ERRFIELD]
     * @param szErrMsg String value of [ERRMSG]
     * @param szErrType String value of [ERRCONF]
     * @param szErrIndex String value of [ERRINDEX]
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    protected static String getBodyTag(
    		    boolean bFOTW,
                String szErrField,		// [ERRFIELD]
                String szErrMsg,		// [ERRMSG]
                String szErrType, 		// [ERRCONF]
				String szErrIndex
                ) {
		String szInitForm;
		StringBuffer szErrBody;

		try {
			if (szErrMsg == null || szErrMsg.length() == 0 ) {
				// no error
				return BODY_TAG;
			} else {
				if (bFOTW) {
					// No JavaScript error in FOTW
					return BODY_TAG;
				} else {
					// found error - display the Javascript error message
	
					//escape " or '
					szErrMsg = replace(szErrMsg, "\"", "\\&#34;");
					szErrMsg = replace(szErrMsg, "'", "\\&#39;");
	
					// in case the message has been escaped, remove double slashes "\\"
					szErrMsg = replace(szErrMsg, "\\\\&#", "\\&#");
	
					// pass the error information to JavaScript funtion
					StringBuffer clInit = new StringBuffer("InitForm(");
	
					// inlucde error field only if it exists
					if (szErrField == null || szErrField.length() == 0) {
						clInit.append("null, '");
					} else {
						clInit.append("document.FAFSA.")
						.append(szErrField).append(", '");
					}
	
					clInit.append(szErrMsg).append("', '")
					.append(szErrType).append("', '")
					.append(szErrIndex).append("')");
	
					szInitForm = clInit.toString();
	
					return replace(BODY_TAG, "InitForm(null, '', '', '')", szInitForm);
				}
			}
		} catch (Exception e) {
			return BODY_TAG;
		}
	}
   //------------------------------------------------------------------------
    /**
     * This method constructs the HTML body element with an onload event to
     * handle the error message.  The output is printed on the JSP source file
     * where an HTML body tag is required.  This version accepts a flag to indicate
     * if a session refreshing message is displayed or not.
     * @param bRefresh boolean true = Call JavaScript function RefreshSession()
     * to display a warning message after the session is left idle for 25 minutes;
     * false = Do not call.
     * @param bKeysize boolean true = alert keysize < 128-bit
     * @param szLocale String value of [locale]
     * @param szErrField String value of [ERRFIELD]
     * @param szErrMsg String value of [ERRMSG]
     * @param szErrType String value of [ERRCONF]
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getBodyTag(
    		    boolean bFOTW,
                boolean bRefresh,       // refresh session?
                boolean bKeysize,       // alert keysize < 128 bit?
				String szLocale,        // [locale]
                String szErrField,		// [ERRFIELD]
                String szErrMsg,		// [ERRMSG]
                String szErrType,		// [ERRCONF]
				String szErrIndex       // [ERRINDEX]
                ) {
        String szBody = getBodyTag(bFOTW, szErrField, szErrMsg, szErrType, szErrIndex);

        if (bRefresh) {
        	// refresh first
    		szBody = replace(szBody, "CheckBrowser();", "RefreshSession();CheckBrowser();");
    	}

    	if (bKeysize) {
    		// alert keysize after checking the browser
    		szBody = replace(szBody, "CheckBrowser();", "CheckBrowser();AlertKeySize();");
    	}

    	// add the locale parameter
    	return replace(szBody, "CheckBrowser();", "CheckBrowser('"+ szLocale +"');");
    }
	//------------------------------------------------------------------------
	/**
	 * The method returns the concatenation of the correction flag and the
	 * assumption flag, where these flags are set as below:
     * <ul>
	 * <li>The correction flag is "" (blank) if szCorrected = "0" (never corrected);
	 *     "#" if szCorrected = "1" (corrected on current transaction); or
	 *     "@" if szCorrected = "2" (corrected on previous transaction).</li>
	 * <li>The assumption flag is "" (blank) if szAssumed is "" (not assumed); or
	 *     "*" if szAssumed is "1" (assumed)</li>
	 * For example, if getCorrectionsFlag(1, 1) is called, the method returns "#*".
	 * @return java.lang.String
	 * @param szCorrected String indicate if correction made to field
	 * @param szAssumed String indicate if assumption made to the field
	 */
	//------------------------------------------------------------------------
	public static String getCorrectionsFlag(String szCorrected, String szAssumed)
    {
		String szCorrFlag = "";
		String szAsmFlag = "";

		try {
			if (szCorrected.equals("0")) {
				// never corrected
				szCorrFlag = "";
			} else if (szCorrected.equals("1")) {
				// corrected on current transaction
				szCorrFlag = "#";
			} else if (szCorrected.equals("2")) {
				// corrected on previous transaction
				szCorrFlag = "@";
			} else {
				szCorrFlag = "";
			}
		} catch (Exception e) {
			szCorrFlag = "";
		}

		try {
			if (szAssumed.equals("1")) {
				// assumed
				szAsmFlag = "*";
			} else {
				// not assumed
				szAsmFlag = "";
			}
		} catch (Exception e) {
			szAsmFlag = "";
		}

		return new StringBuffer(szCorrFlag).append(szAsmFlag).toString();

	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the dropdown list with proper option selected.
	 * This version does not set control id.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param clList AFOTWList field definition list
	 * @param clValues Vector field clValues in the session
	 * @param aryVals String[][] The field clValues and dropdown options for the control
	 */
	//---------------------------------------------------------------------
	public static String getDropDown(
		String szField,
		AFOTWList clList,
		Vector clValues,
		String [][] aryVals){
		return 	getDropDown(szField, clList, clValues, aryVals, "");
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param clList AFOTWList field definition list
	 * @param clValues Vector field clValues in the session
	 * @param aryVals String[][] The field clValues and dropdown options for the control
	 * @param szID String the id of this control
	 */
	//---------------------------------------------------------------------
	public static String getDropDown(
		String szField,
		AFOTWList clList,
		Vector clValues,
		String [][] aryVals,
		String szID ){
		return getDropDown(szField, clList, clValues, aryVals, szID, "");
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param clList AFOTWList field definition list
	 * @param clValues Vector field clValues in the session
	 * @param aryVals String[][] The field clValues and dropdown options for the control
	 * @param szID String the id of this control
	 * @param szOtherAttr other attribute to the select control, e.g., onChange event.
	 */
	//---------------------------------------------------------------------
	public static String getDropDown(
		String szField,
		AFOTWList clList,
		Vector clValues,
		String [][] aryVals,
		String szID,
		String szOtherAttr){

		String szFieldVal = "";

		try{
			szFieldVal = clList.getFieldValue(szField, clValues);
		} catch (Exception e) {
			szFieldVal = "";
		}

		return getDropDown(szField, szFieldVal, aryVals, szID, szOtherAttr);
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param szFieldVal, String field value in the session
	 * @param aryVals String[][] The field values and dropdown options for the control
	 * @param szID String the id of this control
	 * @param szOtherAttr other attribute to the select control, e.g., onChange event.
	 */
	//---------------------------------------------------------------------
	public static String getDropDown(
		String szField,
		String szFieldVal,
		String [][] aryVals,
		String szID,
		String szOtherAttr){
		try{
			StringBuffer clRet = new StringBuffer();

			// select control and its attributes
			clRet.append("<select name=\""+szField+"\" ");
			if (szID.trim().length() > 0)  {
				clRet.append("id=\""+szID+"\" ");
			}
			clRet.append(szOtherAttr);
			clRet.append(">\n");

			// option lists
			clRet.append(getDropdownOptions(szFieldVal, aryVals));

			clRet.append("</select>\n");

			return clRet.toString();
		} catch (Exception e) {
			return "";
		}
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the option tags <option></option> with proper option selected.
	 * Creation date: (7/01/06 14:11:48 PM)
	 * @return String the options for the dropdown
	 * @param szFieldVal, String field value in the session
	 * @param aryVals String[][] The field values and dropdown options for the control
	 */
	//---------------------------------------------------------------------
	public static String getDropdownOptions(
		String szFieldVal,
		String [][] aryVals){
		String szLabel;
		String szVal;

		StringBuffer clOps = new StringBuffer();

		// option lists
		for (int i=0; i < aryVals.length; i++) {
			szVal   = aryVals[i][0];
			szLabel = aryVals[i][1];

			clOps.append("<option ");

			if (szVal.equals(szFieldVal)) {
				clOps.append("selected ");
			}

			clOps.append("value=\"").append(szVal).append("\">")
			.append(szLabel)
			.append("</option>\n");
		}
		return clOps.toString();
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the checkbox input with "checked" when value = 1.
	 * Creation date: (7/31/06 14:11:48 PM)
	 * @return String the checkbox input
	 * @param bDefault, boolean true = default option (checked if value is blank); false = not the default option
	 * @param szChoice, String the choice value when the field is checked
	 * @param szField, String field name
	 * @param szFieldVal, String field value in the session
	 * @param szId, String control id
	 * @param szOtherAttr, String other attribute for the control
	 */
	//---------------------------------------------------------------------
	public static String getCheckbox(boolean bDefault, String szChoice, String szField, String szFieldVal, String szId, String szOtherAttr)
	{
		StringBuffer clInput = new StringBuffer("<input type=\"checkbox\" ")
		.append("name=\"").append(szField).append("\" ")
		.append("id=\"").append(szId).append("\" ")
		.append("value=\"").append(szChoice).append("\" ");

		if (szFieldVal.equals(szChoice)
			|| (bDefault && szFieldVal.trim().length() == 0) ) {
			clInput.append("checked ");
		}

		clInput.append(szOtherAttr)
		.append(" />\n");

		return clInput.toString();
	}
    //------------------------------------------------------------------------
    /**
     * If the current field name matches the error field name and is a confirm
     * edit, the method returns the confirmation link; if not, return an empty
     * string ("".)  The output is printed at the left side of the field control.
     * The icon, if any, is next to the field control.
     * @param szLocale String [locale]
     * @param szCurrField The name of the current field the icon would apply to
     & @param szErrField String value of [ERRFIELD] (error field name)
     * @param iErrIndex Confirmation edit array index
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
	public static String getConfirmLink(String szLocale, String szCurrField, String szErrField, int iErrIndex)
	{
    	try {
    		String hiddenName = CONFIRM_HIDDEN_NAME + iErrIndex;
			if (szCurrField.equals(szErrField)) {
				if (BundleUtil.isSpanish(szLocale)) {
					return String.format(CONFIRM_LINK_SP_SHOW, szCurrField, szCurrField, hiddenName);
				} else {
					return String.format(CONFIRM_LINK_EN_SHOW, szCurrField, szCurrField, hiddenName);
				}
			} else {
				if (BundleUtil.isSpanish(szLocale)) {
					return String.format(CONFIRM_LINK_SP_HIDE, szCurrField, szCurrField, hiddenName);
				} else {
					return String.format(CONFIRM_LINK_EN_HIDE, szCurrField, szCurrField, hiddenName);
				}
			}
		} catch (Exception e) {
			return "";
		}
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the radio input with "checked" when value = 1.
	 * Creation date: (7/31/06 14:11:48 PM)
	 * @return String the radio type input
	 * @param bDefault, boolean true = default option (checked if value is blank); false = not the default option
	 * @param szChoice, String the choice value when the field is checked
	 * @param szField, String field name
	 * @param szVal, String field value in the session
	 * @param szId, String control id
	 * @param szOtherAttr, String other attribute for the control
	 */
	//---------------------------------------------------------------------
	public static String getRadio(boolean bDefault, String szChoice, String szField, String szFieldVal, String szId, String szOtherAttr)
	{
		StringBuffer clInput = new StringBuffer("<input type=\"radio\" ")
		.append("name=\"").append(szField).append("\" ")
		.append("id=\"").append(szId).append("\" ")
		.append("value=\"").append(szChoice).append("\" ");

		if (szFieldVal.equals(szChoice)
			|| (bDefault && szFieldVal.trim().length() == 0) ) {
			clInput.append("checked ");
		}

		clInput.append(szOtherAttr)
		.append(" />\n");

		return clInput.toString();
	}
    //------------------------------------------------------------------------
    /**
     * If the current field name matches the error field name, the method
     * returns an image tag <IMG></IMG> for the error icon; if not, return an empty
     * string ("".)  The output is printed at the left side of the field control.
     * The icon, if any, is next to the field control.  This version is for English pages.
     * @param szCurrField The name of the current field the icon applies to
     & @param szErrField String value of [ERRFIELD] (error field name)
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getErrorIcon(
                String szCurrField,	// The name of the current field the icon applies to
                String szErrField		// [ERRFIELD]
                ) {
		return getErrorIcon(false, "", szCurrField, szErrField);
    }
    //------------------------------------------------------------------------
    /**
     * If the current field name matches the error field name, the method
     * returns an image tag <IMG></IMG> for the error icon; if not, return an empty
     * string ("".)  The output is printed at the left side of the field control.
     * The icon, if any, is next to the field control.
     * IconX is defaulted to false
     * @param szLocale String [locale]
     * @param szCurrField The name of the current field the icon would apply to
     & @param szErrField String value of [ERRFIELD] (error field name)
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getErrorIcon(
	    		String szLocale,     // [locale]
                String szCurrField,	// The name of the current field the icon applies to
                String szErrField		// [ERRFIELD]
                ) {
		return getErrorIcon(false, szLocale, szCurrField, szErrField);
    }    
    //------------------------------------------------------------------------
    /**
     * Returns validation errors as JSON data.
     * @param request ServletRequest [request]
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getErrorsAsJson(ServletRequest request) {
    	    List <String []> errorMessageList = new ArrayList<String []>();
    	    Locale szLocale = BundleUtil.getLocale(request);
			
    	    ArrayList <AFOTWValidException> errors = (ArrayList <AFOTWValidException>) request.getAttribute(JSPUtil.LABEL_ERRORS);
    	    HashMap <String, AFOTWValidException> errorFields = (HashMap <String, AFOTWValidException>) request.getAttribute(JSPUtil.LABEL_ERRORS_FIELDS);
    		 
    		if (errors != null && errors.size() > 0) {
    			// Get Error messages
    			String szErrMsg;
    			AFOTWValidException clEx;
    			
    			for (int i=0; i<errors.size(); i++)  {
    				clEx = errors.get(i);
    				String field = "";
    				Iterator <String> it = errorFields.keySet().iterator();
    				while (it.hasNext() && field.equals("")){
    					String key = it.next();
    					if(errorFields.get(key).equals(clEx)){
    						field = key;
    					}
    				}
    				
    				szErrMsg = TagUtil.getPageEditMessage(BundleUtil.isSpanish(szLocale), request, clEx);
    				String cleanMsg = szErrMsg.replaceAll("\"", "&#34;").replaceAll("'", "&#39;");
	    			errorMessageList.add(new String []{field,cleanMsg});
    			}
    		} 
    		else if (errorFields != null && errorFields.keySet() != null) {
    			Iterator<String> keys = errorFields.keySet().iterator();
	    		while(keys.hasNext()) {
	    			String field = keys.next();
	    			String msg = TagUtil.getPageEditMessage(BundleUtil.isSpanish(szLocale), request, (AFOTWValidException) errorFields.get(field));
	    			String cleanMsg = msg.replaceAll("\"", "&#34;").replaceAll("'", "&#39;");
	    			errorMessageList.add(new String []{field,cleanMsg});
	    		}
    		}
    	
    		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
    		xstream.alias("errorData", List.class);
    		String result = xstream.toXML(errorMessageList).replaceAll("\\n", "").replaceAll("\\t", "");
    		
    		return result;
    }
    //------------------------------------------------------------------------
    /**
     * Returns alert messages as JSON data.
     * @param request ServletRequest [request]
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getAlertsAsJson(ServletRequest request) {

    	List <String> alertMessageList = (ArrayList<String>) request.getAttribute(JSPUtil.LABEL_FOTW_ALERTS);
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("alertData", List.class);
		String result = xstream.toXML(alertMessageList).replaceAll("\\n", "").replaceAll("\\t", "");
		
		return result;
    }
    
    //------------------------------------------------------------------------
    /**
     * Returns alert messages as JSON data.
     * @param request ServletRequest [request]
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getCustomErrorsAsJson(ServletRequest request) {

    	List <String> alertMessageList = (ArrayList<String>) request.getAttribute(JSPUtil.LABEL_ERRORS);
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("errorData", List.class);
		String result = xstream.toXML(alertMessageList).replaceAll("\\n", "").replaceAll("\\t", "");
		
		return result;
    }
    
    //------------------------------------------------------------------------
    /**
     * If the current field name matches the error field name, the method
     * returns an image tag <IMG></IMG> for the error icon; if not, return an empty
     * string ("".)  The output is printed at the left side of the field control.
     * The icon, if any, is next to the field control.
     * @param szLocale String [locale]
     * @param szCurrField The name of the current field the icon would apply to
     & @param szErrField String value of [ERRFIELD] (error field name)
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getErrorIcon(
    		    boolean bIconX,
	    		String szLocale,     // [locale]
                String szCurrField,	// The name of the current field the icon applies to
                String szErrField		// [ERRFIELD]
                ) {
    	try {
			if (szCurrField.equals(szErrField)) {
				if (BundleUtil.isSpanish(szLocale)) {
					return bIconX? String.format(ERROR_ICON_X_SP_SHOW, szCurrField) : ERROR_ICON_SP;
				} else {
					return bIconX? String.format(ERROR_ICON_X_EN_SHOW, szCurrField) : ERROR_ICON_EN;
				}
			} else {
				if (bIconX) {
					if (BundleUtil.isSpanish(szLocale)) {
						return String.format(ERROR_ICON_X_SP_HIDE, szCurrField);
					} else {
						return String.format(ERROR_ICON_X_EN_HIDE, szCurrField);
					}
				} else {
					return "";
				}
			}
		} catch (Exception e) {
			return "";
		}
    }
    //------------------------------------------------------------------------
    /**
     * This method returns the URL of the servlet program for the form action.
     * The form data is submitted to this program for processing.  The output
     * is assigned to the action attribute of the form "FAFSA".
     * @param  szApptype String value of [APPTYPE]
     * @return String
     * @throws None
     * @deprecated;
     */
    //------------------------------------------------------------------------
    public static String getFormAction(
                String szApptype 		// [APPTYPE]
                ) {
		try {
			return FORM_ACTIONS[getAppIndex(szApptype)];
		} catch (Exception e) {
			return "";
		}
    }
	//------------------------------------------------------------------------
	/**
	 * This method returns the URL of the servlet program for the
	 * form action.  This version is for FAA Entry where one servlet handles
	 * multiple applications.  All FAA Entry pages and the common pages used
	 * in FAA Entry should call this method instead of
	 * getFormAction(szAppType, clResponse).  This version allows the user to set the encode URL flag.
	 * @return java.lang.String URL as the form action
	 * @param bEncodeURL
	 * @param szApptype java.lang.String  [APPTYPE]
	 * @param szFaaMode java.lang.String [faamode]
	 * @param clResponse javax.servlet.http.HttpServletResponse
	 */
	//------------------------------------------------------------------------
	public static String getFormAction(
		boolean bEncodeURL,
		String szApptype,
		String szFaaMode,
		javax.servlet.http.HttpServletResponse clResponse) {
		// Convert FAA Entry app type
		if (isFaaModeAny(szFaaMode)) {
			szApptype = getFAAAppType(szApptype, szFaaMode);
		}

	    return getFormAction(bEncodeURL, szApptype, clResponse);
	}
	//------------------------------------------------------------------------
	/**
	 * This method returns the encoded URL of the servlet program for the
	 * form action.  This version is for FAA Entry where one servlet handles
	 * multiple applications.  All FAA Entry pages and the common pages used
	 * in FAA Entry should call this method instead of
	 * getFormAction(szAppType, clResponse).
	 * @return java.lang.String encoded URL as the form action
	 * @param szApptype java.lang.String  [APPTYPE]
	 * @param szFaaMode java.lang.String [faamode]
	 * @param clResponse javax.servlet.http.HttpServletResponse
	 */
	//------------------------------------------------------------------------
	public static String getFormAction(
		String szApptype,
		String szFaaMode,
		javax.servlet.http.HttpServletResponse clResponse) {

		// Default to encode the URL
		return getFormAction(true, szApptype, szFaaMode, clResponse);
	}

	//------------------------------------------------------------------------
	/**
	 * This method returns the URL of the servlet program for the form
	 * action. The form data is submitted to this program for processing.  The
	 * output is assigned to the action attribute of the form "FAFSA".  This version
	 * allows the caller to set the flag to encode the URL or not.
	 * Creation date: (7/31/01 1:40:44 PM)
	 * @return java.lang.String URL as the form action
	 * @param bEncodeURL,
	 * @param szApptype java.lang.String  [APPTYPE]
	 * @param clResponse javax.servlet.http.HttpServletResponse
	 */
	//------------------------------------------------------------------------
	public static String getFormAction(
		boolean bEncodeURL,
		String szApptype,
		javax.servlet.http.HttpServletResponse clResponse) {
		try {
			if (bEncodeURL) {
				return clResponse.encodeURL(FORM_ACTIONS[getAppIndex(szApptype)]);
			} else {
				return FORM_ACTIONS[getAppIndex(szApptype)];
			}
		} catch (Exception e) {
			return "";
		}
	}
	//------------------------------------------------------------------------
	/**
	 * This method returns the encoded URL of the servlet program for the form
	 * action. The form data is submitted to this program for processing.  The
	 * output is assigned to the action attribute of the form "FAFSA".
	 * Creation date: (7/31/01 1:40:44 PM)
	 * @return java.lang.String encoded URL as the form action
	 * @param szApptype java.lang.String  [APPTYPE]
	 * @param clResponse javax.servlet.http.HttpServletResponse
	 */
	//------------------------------------------------------------------------
	public static String getFormAction(
		String szApptype,
		javax.servlet.http.HttpServletResponse clResponse) {
		// Default to encode the URL
		return getFormAction(true, szApptype, clResponse);
	}

	//------------------------------------------------------------------------
	/**
	 * This method return the hidden HTML form input for the name/value pair:
	 * <input type="hidden" name="name" value="value">
	 * The method may be used to insert a hidden field into the JSP page
	 * Creation date: (7/17/01 9:58:39 AM)
	 * @return java.lang.String hidden input
	 * @param name java.lang.String name
	 * @param value java.lang.String value
	 */
	//------------------------------------------------------------------------
	public static String getHidden(String szName, String szValue) {
		try {
			return new StringBuffer("<input type=\"hidden\" name=\"")
			.append(szName)
			.append("\" value=\"")
			.append(szValue)
			.append("\"/>\n")
			.toString();
		} catch (Exception e) {
			return "";
		}
	}
	//------------------------------------------------------------------------
	/**
	 * This method returns the hidden HTML form input as below:
	 * <input type="hidden" name="name" value="value">
	 * for the common hidden control fields.  The JSP pages should
	 * call this method after the form tag.
	 * Creation date: (7/23/01 10:24:47 AM)
	 * @return java.lang.String
	 * @param list AFOTWList session parameter definition
	 * @param clValues AFOTWSessionValues session data
	 */
	//------------------------------------------------------------------------

	public static String getHiddens(AFOTWList clList, AFOTWSessionValues clValues) {
		try {
			return getHiddens(clList.getIndexByFieldname(), clValues);
		} catch (Exception e) {
			return getHiddens((HashMap)null, clValues);
		}
	}
	//------------------------------------------------------------------------
	/**
	 * This method returns the hidden HTML form input as below:
	 * <input type="hidden" name="name" value="value">
	 * for the common hidden control fields.  The JSP pages should
	 * call this method after the form tag.
	 * Creation date: (7/19/01 10:05:38 AM)
	 * @return java.lang.String
	 * @param clIndexByName HashMap The field indeces with fieldnames as the key
	 *                   given by the AFOTWList
	 * @param clValues AFOTWSessionValues the object with all the session clValues
	 */
	//------------------------------------------------------------------------
	public static String getHiddens(HashMap clIndexByName, AFOTWSessionValues clValues ) {
		int i;
		String szVal;
		StringBuffer clHid;

		Vector clFieldValues = null;

		try {
			clFieldValues = clValues.getFieldValues();
		} catch (Exception e) {
		}

		// dowhat always "next" by default
		clHid = new StringBuffer(getHidden(AFOTWSession.DOWHAT_VITAL, "next"));

		// phase
		try {
			szVal = String.valueOf(clValues.phase);
		} catch (Exception e) {
			szVal = "0";
		}
		clHid.append(getHidden(AFOTWSessionValues.phaseIDLabel, szVal));

		// state
		try {
			szVal = String.valueOf(clValues.state);
		} catch (Exception e) {
			szVal = "0";
		}
		clHid.append(getHidden(AFOTWSessionValues.stateIDLabel, szVal));

		// historyid
		try {
			szVal = String.valueOf(clValues.transaction);
		} catch (Exception e) {
			szVal = "0";
		}
		clHid.append(getHidden(AFOTWSessionValues.transactionIDLabel, szVal));

		// pageid
		try {
			szVal = String.valueOf(clValues.position);
		} catch (Exception e) {
			szVal = "0";
		}
		clHid.append(getHidden(AFOTWSessionValues.positionIDLabel, szVal));

		// faamode
		try {
			i = ((Integer) clIndexByName.get(AFOTWSessionValues.modeIDLabel)).intValue();
			szVal = (String) clFieldValues.elementAt(i);
		} catch (Exception e) {
			szVal = "";
		}
		clHid.append(getHidden(AFOTWSessionValues.modeIDLabel, szVal));

		// confirmErr for the confirmation index
		clHid.append(getHidden("confirmErr", ""));

		// locale
		try {
			i = ((Integer) clIndexByName.get(LABEL_LOCALE)).intValue();
			szVal = (String) clFieldValues.elementAt(i);
		} catch (Exception e) {
			szVal = BundleUtil.EN_US;
		}
		clHid.append(getHidden(LABEL_LOCALE, szVal));

		return clHid.toString();
	}

    //------------------------------------------------------------------------
    /**
     * This method constructs the button image link
     * @param  szFunc JavaScript function of the button
     * @param  szId button id
     * @param szImg button image file name (full path)
     * @param szAlt button alt-text
     * @param szState default button state on, off or "" (blank).
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getBtnLink(String szJSFunc, String szId, String szImg, String szAlt, String szState) {
    	String szBtn = "";
    	try {
    		szBtn = BUTTON_TPL;
    		szBtn = replace(szBtn, "<!--BB_JS_FUNC-->", szJSFunc);
    		szBtn = replace(szBtn, "<!--BB_ID-->", szId);
    		szBtn = replace(szBtn, "<!--BB_IMG-->", szImg);
    		szBtn = replace(szBtn, "<!--BB_ALT-->", szAlt);
    		szBtn = replace(szBtn, "<!--BB_STATE-->", szState);
    	} catch (Exception e) {
    		AFOTWLog.log("JSPUtil.getBtnLink: failed to build sublink button "+szId, AFOTWLog.INFO, JSPUtil.class.getName());
    	}
		return szBtn;
    }
    //------------------------------------------------------------------------
    /**
     * This method constructs the button image link
     * @param  aryBtn String[] of the button JavaScript function, id,
     * 		   image file name, and alt-text.
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getBtnLink(String[] aryBtn) {
    	return getBtnLink(aryBtn[0], aryBtn[1], aryBtn[2], aryBtn[3], "");
    }
    //------------------------------------------------------------------------
    /**
     * This method constructs the bottom bar of the page.  The output is
     * printed on the bottom of the page below the subfooter.
     * The bottom bar types include:
     * <ul>
     * 		<li>FOOTER_BLANK: Only the Need Help? button; no other button
     *      <li>FOOTER_EXIT: "EXIT" button
     *      <li>FOOTER_SAVE_EXIT: "SAVE" and "EXIT" buttons
     *      <li>FOOTER_SAVE_VIEW_EXIT: "SAVE", "VIEW FAFSA SUMMARY", and "EXIT" buttons
     *      <li>FOOTER_VIEW_EXIT: "VIEW FAFSA SUMMARY" and "EXIT" buttons
     * </u>
     * When in FAA mode, add "RETURN TO FAA MENU" button except for FOOTER_BLANK.
     * The buttons and JavaScript messages are in English or Spanish based on iLanguage
     * (ENGLISH or SPANISH).
 	 * @param bSidebar boolean true = has side bar; false = no side bar
 	 * @param szLocale String The application locale code
 	 * @param  szHelpText Help text parameter(s) for the JS funtion Help()
     * @param  iType int footer template type
 	 * @param  szAppType String Application type [APPTYPE]
     * @param  szFAAMode String value of [faamode]
     * @param  bSimplified boolean true = simplied/no message for FAA Menu and Exit
     *          when footer type is FOOTER_EXIT.
     * @param  bCustom boolean indicate whether the mainfooter is custom (true) or per template (flase).
     *          true = extra buttons only; false = extra + template buttons.
     * @param  szExtra String extra buttons to be included in the footer
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getBottomBar(
    	boolean bSidebar,
		String szLocale,
		String szHelpText,
    	int iType,
    	String szAppType,
    	String szFAAMode,
    	boolean bSimplified,
    	boolean bCustom,
    	String szExtra)
    {
    	boolean bSpanish = false;
    	String szMF = "";
		String szSave = "";
		String szSummary = "";
		String szExit = "";
		String szMenu = "";
		String szPortal = "";
		String szHelp = "";
		String szStamp = "";

		String szReturnToLogin = "";

		// TO DO:
		// Update szReturnToLogin
		// Update szLocale

		try {
			bSpanish = BundleUtil.isSpanish(szLocale);
			boolean bFAA = isFaaModeAny(szFAAMode);

			if (!bCustom) {
				// footer per apptype and FAA mode
				// template buttons + any extra buttons

				int iHelp = -1;
				int iMenu = -1;
				int iSave = -1;
				int iSummary = -1;
				int iExit = -1;

				switch(iType) {
					case FOOTER_BLANK:
					// Only the Need Help button; no other buttons
					if (bFAA) {
						iHelp = BB_HELP_FAA;
					} else {
						if (bSpanish) {
							iHelp = BB_HELP_SP;
						} else {
							iHelp = BB_HELP_EN;
						}
					}
					break;

					case FOOTER_EXIT:
					// EXIT button only -
					// No warning message since there is no SAVE FOR LATER button on the page
					// Or display simplified message for FAA
					if (bSimplified) {
						if (bFAA) {
							iHelp = BB_HELP_FAA;
							iMenu = BB_FAAMENU_NO_SAVE;
							iExit = BB_EXIT_NO_SAVE_FAA;
						} else {
							// Added NO_SAVE messge for students - SCR 29451 - 11/23/05, RL
							if (bSpanish) {
								iHelp = BB_HELP_SP;
								iExit = BB_EXIT_NO_SAVE_SP;
							} else {
								iHelp = BB_HELP_EN;
								iExit = BB_EXIT_NO_SAVE_EN;
							}
						}
					} else {
						if (bFAA) {
							iHelp = BB_HELP_FAA;
							iMenu = BB_FAAMENU;
							iExit = BB_EXIT_FAA;
						} else {
							if (bSpanish) {
								iHelp = BB_HELP_SP;
								iExit = BB_EXIT_SP;
							} else {
								iHelp = BB_HELP_EN;
								iExit = BB_EXIT_EN;
							}
						}
					}
					break;

					case FOOTER_SAVE_EXIT:
					if (bFAA) {
						iHelp = BB_HELP_FAA;
						iMenu = BB_FAAMENU;
						iSave = BB_SAVE_FAA;
						iExit = BB_EXIT_FAA;
					} else {
						if (bSpanish) {
							iHelp = BB_HELP_SP;
							iSave = BB_SAVE_SP;
							iExit = BB_EXIT_SP;
						} else {
							iHelp = BB_HELP_EN;
							iSave = BB_SAVE_EN;
							iExit = BB_EXIT_EN;
						}
					}
					break;

					case FOOTER_SAVE_VIEW_EXIT:
					if (bFAA) {
						iHelp = BB_HELP_FAA;
						iMenu = BB_FAAMENU;
						iSave = BB_SAVE_FAA;
						iSummary = BB_SUMMARY_FAA;
						iExit = BB_EXIT_FAA;
					} else {
						if (bSpanish) {
							iHelp = BB_HELP_SP;
							iSave = BB_SAVE_SP;
							iSummary = BB_SUMMARY_SP;
							iExit = BB_EXIT_SP;
						} else {
							iHelp = BB_HELP_EN;
							iSave = BB_SAVE_EN;
							iSummary = BB_SUMMARY_EN;
							iExit = BB_EXIT_EN;
						}
					}
					break;

					case FOOTER_VIEW_EXIT:
					// No warning message since there is no SAVE button on the page
					// Or display simplified message for FAA
					if (bSimplified) {
						if (bFAA) {
							iHelp = BB_HELP_FAA;
							iMenu = BB_FAAMENU_NO_SAVE;
							iSummary = BB_SUMMARY_FAA;
							iExit = BB_EXIT_NO_SAVE_FAA;
						} else {
							// Added NO_SAVE messge for students - SCR 29451 - 11/23/05, RL
							if (bSpanish) {
								iHelp = BB_HELP_SP;
								iSummary = BB_SUMMARY_SP;
								iExit = BB_EXIT_NO_SAVE_SP;
							} else {
								iHelp = BB_HELP_EN;
								iSummary = BB_SUMMARY_EN;
								iExit = BB_EXIT_NO_SAVE_EN;
							}
						}
					} else {
						if (bFAA) {
							iHelp = BB_HELP_FAA;
							iMenu = BB_FAAMENU;
							iSummary = BB_SUMMARY_FAA;
							iExit = BB_EXIT_FAA;
						} else {
							if (bSpanish) {
								iHelp = BB_HELP_SP;
								iSummary = BB_SUMMARY_SP;
								iExit = BB_EXIT_SP;
							} else {
								iHelp = BB_HELP_EN;
								iSummary = BB_SUMMARY_EN;
								iExit = BB_EXIT_EN;
							}
						}
					}
					break;

					default:
					break;
				} // end switch

				// get the buttons

				// For 08-09, insert the help button only when a help file is defined
				if (iHelp > -1 && szHelpText != null && szHelpText.length() > 0) {
					szHelp = getBtnLink(SUBLINK_BUTTONS[iHelp]);
					// also the help text parameter(s)
					szHelp = replace(szHelp, "<!--BB_HELP_FILE-->", szHelpText);
				}
				if (iMenu > -1) {
					szMenu = getBtnLink(SUBLINK_BUTTONS[iMenu]);
				}
				if (iSave > -1) {
					szSave = getBtnLink(SUBLINK_BUTTONS[iSave]);
				}
				if (iSummary > -1) {
					szSummary = getBtnLink(SUBLINK_BUTTONS[iSummary]);
				}
				if (iExit > -1) {
					szExit = getBtnLink(SUBLINK_BUTTONS[iExit]);
				}

			} // end if-bCustom

			// 07-08 - new style
			String szStyle;
			if (bSidebar) {
				szStyle = bFAA? "bottombar_faa" : "bottombar";
			} else {
				szStyle = bFAA? "bottombar_faa_lg" : "bottombar_lg";
			}

			// insert buttons
			szMF = replace(BOTTOMBAR, "<!--BB_STYLE-->", szStyle);
			szMF = replace(szMF, "<!--BB_HELP-->", szHelp);
			szMF = replace(szMF, "<!--BB_SAVE-->", szSave);
			szMF = replace(szMF, "<!--BB_SUMMARY-->", szSummary);
			szMF = replace(szMF, "<!--BB_FSAPORTAL-->", szPortal);
			szMF = replace(szMF, "<!--BB_RETURNTOLOGIN-->", szReturnToLogin);
			szMF = replace(szMF, "<!--BB_FAAMENU-->", szMenu);
			szMF = replace(szMF, "<!--BB_EXIT-->", szExit);

			// Add any extra buttons
			if (szExtra != null && szExtra.trim().length() > 0) {
				szMF = replace(szMF, "<!--BB_EXCEPTION-->", szExtra);
			}
			return szMF;
		} catch (Exception e) {
			AFOTWLog.log("JSPUtil.getBottombar: failed to build bottombar type="+iType,
			AFOTWLog.INFO, JSPUtil.class.getName());
			return BOTTOMBAR;
		}
    }
    //------------------------------------------------------------------------
    /**
     * The bootom bar from this version may be custom and include extra buttons.
     * If not custom, it is per default application/faamode template.
 	 * @param bSidebar boolean true = has side bar; false = no side bar
 	 * @param szLocale String The applicaiton locale code
 	 * @param  szHelpText Help text parameter(s) for the JS funtion Help()
     * @param  AList clList list definition
     * @param  ASessionValues clValues session data
     * @return String
     * @throws None
     *
     */
    //------------------------------------------------------------------------
    public static String getBottomBar(
    			boolean bSidebar,
				String szLocale,
				String szHelpText,
                AList clList,
                ASessionValues clValues,
                boolean bCustom,
                String szExtra
                ) {
		int iType;
		boolean bSimplified = true;

		Vector clFieldValues = null;
		try {
			clFieldValues = clValues.getFieldValues();
		} catch (Exception e) {
		}

        String szAppType; // [APPTYPE]
		try {
			szAppType = clList.getFieldValue(LABEL_APPTYPE, clFieldValues);
		} catch (AException ae) {
			szAppType = "";
		}

        String szFaaMode; // [faamode]
		try {
			szFaaMode = clList.getFieldValue(LABEL_MODE, clFieldValues);
		} catch (AException ae) {
			szFaaMode = "";
		}

		// also handle the post-entry pages
		int iCurr;
    	try {
    		String szCurrSection = clList.getFieldValue("currentsection", clFieldValues);
    		iCurr = Integer.parseInt(szCurrSection);
    	} catch (Exception e) {
    		iCurr = 0;
    	}

    	// CM 5038: Also use page id to check for post-entry pages in case
    	// [currentsection] is not set as in COTW.
    	// R.L. 1/6/04
    	boolean bEntry;
    	int iPos = clValues.position;
		if ( iCurr >= STEP_REVIEW ||
		     (iPos >= 8500 && iPos < 9500) || /* post-entry pages */
			 (iPos >=15000 && iPos <15499) /* ACG pages*/ ) {
			bEntry = false;
		} else {
			bEntry = true;
		}

		try {
			if (bCustom) {
				// custom footer has no template buttons
				iType = -1;
			} else if (isFaaModeAny(szFaaMode)) {
				if (szAppType.equals(APP_TYPE_FFOTW) ||
					szAppType.equals(APP_TYPE_RFOTW) ||
					szAppType.equals(APP_TYPE_COTW)) {
					// FAA entry applications
					if (bEntry) {
						iType = FOOTER_SAVE_EXIT;
					} else {
						iType = FOOTER_EXIT;
					}
				} else {
					// Other FAA applications
					iType = FOOTER_EXIT;
				}
			} else if (szAppType.equals(APP_TYPE_FFOTW) ||
				szAppType.equals(APP_TYPE_RFOTW) ||
				szAppType.equals(APP_TYPE_SFOTW) ||
				szAppType.equals(APP_TYPE_SROTW) ) {
				// FFOTW or RFOTW or SFOTW or SROTW
				// 08-09: Allow save in student post-entry
				//if (bEntry) {
					iType = FOOTER_SAVE_VIEW_EXIT;
				//} else {
				//	iType = FOOTER_VIEW_EXIT;
				//}
			} else if (szAppType.equals(APP_TYPE_COTW)) {
				// COTW - no view summary
				// 08-09: Allow save in student post-entry
				// if (bEntry) {
					iType = FOOTER_SAVE_EXIT;
				// } else {
				//	iType = FOOTER_EXIT;
				//}
			} else {
				// all other apps do not have the "SAVE"/"VIEW FAFSA SUMMARY" button
				iType = FOOTER_EXIT;
			}
		} catch (Exception e) {
			iType = FOOTER_BLANK;
		}

	    return getBottomBar(bSidebar, szLocale, szHelpText, iType, szAppType, szFaaMode, bSimplified, bCustom, szExtra);
    }
    //------------------------------------------------------------------------
    /**
     * This method returns the site date/time stamp
     * @param  szLocale String value of [locale]
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getSiteStamp(String szLocale) {
		// Add site stamp
    	StringBuffer clBuff = new StringBuffer()
		.append("<div id=\"datestamp\">\n");

    	if (BundleUtil.isSpanish(szLocale)) {
    		clBuff.append(SITESTAMP_SP);
		} else {
			clBuff.append(SITESTAMP_EN);
		}
		clBuff.append("\n</div>\n");
		return clBuff.toString();
    }

    //------------------------------------------------------------------------
    /**
     * Returns the global footer.
 	 * @param bApp boolean true = form-based apps; false = index pages
 	 * @param  szSite String fotw = FAFSA or pin for PIN site
     * @param szLocale String The application locale code
 	 * @param szFaaMode [faamode]
     * @param szExtra extra links on the global footer
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getMainFooter(
    		boolean bApp,
			String szSite,
        	String szLocale,
        	String szFaaMode,
        	String szExtra)
    {
    	String szMF = bApp? FOOTER_APP : FOOTER_INDEX;
    	boolean bSpanish = BundleUtil.isSpanish(szLocale);
    	boolean bFAA = isFaaModeAny(szFaaMode);

    	String szPriUrl;
    	if (szSite.equals(SITE_PIN)) {
    		szPriUrl = bSpanish? "/es_ES/priact.htm" : "/priact.htm";
    	} else {
    		szPriUrl = bSpanish? "/es_ES/privact.htm" : "/privact.htm";
    	}

    	String szPri = bSpanish? "Confidencialidad" : "Privacy";

    	szMF = replace(szMF, "<!--MF_PRIVACY_URL-->", szPriUrl);
    	szMF = replace(szMF, "<!--MF_PRIVACY-->", szPri);

    	if (!bApp) {
    		String szSecUrl;
        	String szSec;
    		String szNoticeUrl;
        	String szNotice;

    		if (bSpanish) {
    			szSec = "Seguridad";
    			szNotice = "Avisos";
    		} else {
    			szSec = "Security";
    			szNotice = "Notices";
    		}

        	if (szSite.equals(SITE_PIN)) {
        		if (bSpanish) {
        			szSecUrl  = "/PINWebApp/es_ES/security.jsp";
        			szNoticeUrl  = "/es_ES/pinnotices.htm";
        		} else {
        			szSecUrl  = "/PINWebApp/security.jsp";
        			szNoticeUrl = "/pinnotices.htm";
        		}
        	} else {
        		if (bSpanish) {
        			szSecUrl  = "/es_ES/secpriv.htm";
        			szNoticeUrl  = "/es_ES/secpriv.htm";
        		} else {
        			szSecUrl  = "/secpriv.htm";
        			szNoticeUrl  = "/secpriv.htm";
        		}
        	}

        	String szUSAUrl = "";
        	String szUSA = "";
        	if (bSpanish) {
        		szUSAUrl = "http://www.usa.gov/gobiernousa/index.shtml";
        		szUSA = "GobiernoUSA.gov";
        	} else {
        		szUSAUrl = "http://www.usa.gov/";
        		szUSA = "USA.gov";
        	}

        	szMF = replace(szMF, "<!--MF_SECURITY_URL-->", szSecUrl);
        	szMF = replace(szMF, "<!--MF_SECURITY-->", szSec);
        	szMF = replace(szMF, "<!--MF_NOTICES_URL-->", szNoticeUrl);
        	szMF = replace(szMF, "<!--MF_NOTICES-->", szNotice);
        	szMF = replace(szMF, "<!--MF_USAGOV_URL-->", szUSAUrl);
        	szMF = replace(szMF, "<!--MF_USAGOV-->", szUSA);
    	}

    	if (szExtra != null && szExtra.length() > 0) {
    		szMF = replace(szMF, "<!--MF_EXCEPTION-->", szExtra);
    	}

    	return szMF;
    }

    //------------------------------------------------------------------------
    /**
     * Returns the global footer.
 	 * @param bApp boolean true = form-based apps; false = index pages
 	 * @param  szSite String fotw = FAFSA or pin for PIN site
     * @param  szLocale String The application locale code
 	 * @param  AList clList list definition
     * @param  ASessionValues clValues session data
     * @param szExtra extra links on the global footer
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getMainFooter(
    		boolean bApp,
			String szSite,
			String szLocale,
			AList clList,
            ASessionValues clValues,
            String szExtra
            ) {
		Vector clFieldValues = null;
		try {
			clFieldValues = clValues.getFieldValues();
		} catch (Exception e) {
		}

        String szFaaMode; // [faamode]
		try {
			szFaaMode = clList.getFieldValue(LABEL_MODE, clFieldValues);
		} catch (AException ae) {
			szFaaMode = "";
		}

    	return getMainFooter(bApp, szSite, szLocale, szFaaMode, szExtra);
    }
    //------------------------------------------------------------------------
    /**
     * This method constructs the main header of the page, including the
     * graphics of the application, "Help", "Contact Us", "FAQs", "Chat",
     * and the ED logo.  The "Chat" graphic is available on the primary applications
     * only.  The output is printed on the top the page above the sub header.
     * The FAA Access pages include buttons "Help" and "FAQs" only.
     * @param  szLocale String The application locale code
     * @param  szApptype String value of [APPTYPE]
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getMainHeader(
    		String szLocale,        // [locale]
    		String szApptype		// [APPTYPE]
		) {
        // by default, not in FAA mode
        String szFaaMode = "";

        // by default, do not remove the chat button
        boolean bRemoveChat = false;

		return getMainHeader(szApptype, szFaaMode, bRemoveChat);
    }

    //------------------------------------------------------------------------
    /**
     * This method constructs the main header of the page, including the
     * graphics of the application, "Help", "Contact Us", "FAQs", "Chat",
     * and the ED logo.  The "Chat" graphic is available on the primary applications
     * only.  The output is printed on the top the page above the sub header.
     * The FAA Access pages include buttons "Help" and "FAQs" only.
     * @param  szLocale String The application locale code
     * @param  szApptype String value of [APPTYPE]
     * @param  bRemoveChat true = remove the chat button even if it is required
     *         for the application
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getMainHeader(
    		    String szLocale,        // [locale]
                String szApptype,		// [APPTYPE]
                boolean bRemoveChat
                ) {
        // by default, not in FAA mode
        String szFaaMode = "";

        return getMainHeader(szLocale, szApptype, szFaaMode, bRemoveChat);
    }
    //------------------------------------------------------------------------
    /**
     * This method constructs the main header of the page, including the
     * graphics of the application, "Help", "Contact Us", "FAQs", "Chat",
     * and the ED logo.  The "Chat" graphic is available on the primary applications
     * only.  The output is printed on the top the page above the sub header.
     * The FAA Access pages include buttons "Help" and "FAQs" only.
     * If the FAA mode is "0", return the Student Access main header instead.
     * If in FAA mode, return the FAA Access main header instead.
     * @param  szLocale String The application locale code
     * @param  szApptype String value of [APPTYPE]
     * @param  szFaaMode String value of [faamode] indicates if the app is in FAA mode.
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getMainHeader(
    		String szLocale,        // [locale]
            String szApptype,		// [APPTYPE]
            String szFaaMode		// [faamode]
        ) {
        // by default, do not remove the chat button
        boolean bRemoveChat = false;
    	return getMainHeader(szLocale, szApptype, szFaaMode, bRemoveChat);
    }
    //------------------------------------------------------------------------
    /**
     * This method constructs the main header of the page, including the
     * graphics of the application, "Help", "Contact Us", "FAQs", "Chat",
     * and the ED logo.  The "Chat" graphic is available on the primary applications
     * only.  The output is printed on the top the page above the sub header.
     * The FAA Access pages include buttons "Help" and "FAQs" only.
     * If the FAA mode is "0", return the Student Access main header instead.
     * If in FAA mode, return the FAA Access main header instead.
     * @param  szLocale String The application locale code
     * @param  szApptype String value of [APPTYPE]
     * @param  szFaaMode String value of [faamode] indicates if the app is in FAA mode.
     * @param  bRemoveChat true = remove the chat button even if it is required for
     *         the application
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getMainHeader(
    			String szLocale, 		// [locale]
                String szApptype,		// [APPTYPE]
                String szFaaMode,		// [faamode]
                boolean bRemoveChat
                ) {
        // first we need to reset the apptype for FAA Entry
        boolean bFAA = false;
	    bFAA = isFaaModeAny(szFaaMode);
		if (bFAA) {
			// Convert FAA Access Entry/Inquiry
			szApptype = getFAAAppType(szApptype, szFaaMode);
		}

	    int i = 0;
	    String szLogo = "";
	    String szTitle = "";
	    String szHome = "";
		String szHelp = "";
		String szContact = "";
		String szFaq = "";
		String szChat = "";
		String szSeal = "";
		String szSkip = "";

		String szMH = MAIN_HEADER;

		try {
			// determine what logo is for the main header
			if (bFAA) {
				// FAA apps
				// No Chat and Contact Us
				szLogo = MH_LOGO_FAA;
				szTitle = MH_TITLE_FAA;
				szHelp = MH_HELP_FAA;
				szFaq = MH_FAQ_FAA;
				szSeal = MH_SEAL_EN;
				szSkip = MH_SKIPLINK_EN;

				// special for IA Tool & R2T4
				if (szApptype.equals(APP_TYPE_FAA_IATOOL)) {
					// FAA IA Tool - Home icon plus IA Tool help and FAQs
					szHome = MH_HOME_FAA;
					szHelp = replace(MH_HELP_FAA, "Help('faatoc00.htm', '"+ szLocale +"')", "Help('ia-toc00.htm', '"+ szLocale +"')");
					szFaq = replace(MH_FAQ_FAA, "Help('faafaq00.htm', '"+ szLocale +"')", "Help('ia-faq00.htm', '"+ szLocale +"')");
				} else if (szApptype.equals(APP_TYPE_FAA_R2T4)) {
					// FAA R2T4 - Home icon plus R2T4 help and R2T4 FAQs
					szHome = MH_HOME_FAA;
					szHelp = replace(MH_HELP_FAA, "Help('faatoc00.htm')", "Help('r2t4-toc00.htm', '"+ szLocale +"')");
					szFaq = replace(MH_FAQ_FAA, "Help('faafaq00.htm')", "Help('r2t4-faq00.htm', '"+ szLocale +"')");
				}

			} else {
				// Student apps
				boolean bSpanish = BundleUtil.isSpanish(szLocale);

				if (bRemoveChat) {
					szChat = "";
				} else {
					if (isPrimaryFAFSA(szApptype)) {
						szChat = (bSpanish)? MH_CHAT_SP : MH_CHAT_EN;
					} else {
						szChat = "";
					}
				}

				if (bSpanish) {
					szLogo = MH_LOGO_SP;
					szTitle = MH_TITLE_SP;
					szHelp = MH_HELP_SP;
					szContact = MH_CONTACT_SP;
					szFaq = MH_FAQ_SP;
					szSeal = MH_SEAL_SP;
					szSkip = MH_SKIPLINK_SP;
				} else {
					szLogo = MH_LOGO_EN;
					szTitle = MH_TITLE_EN;
					szHelp = MH_HELP_EN;
					szContact = MH_CONTACT_EN;
					szFaq = MH_FAQ_EN;
					szSeal = MH_SEAL_EN;
					szSkip = MH_SKIPLINK_EN;
				}
			}

			// insert szLogo
			if (szLogo.length() > 0 ) {
				szMH = replace(szMH, "<!--MH_LOGO-->", szLogo);
			}

			// insert page title /site name
			if (szTitle.length() > 0 ) {
				szMH = replace(szMH, "<!--MH_TITLE-->", szTitle);
			}

			// insert home
			if (szHome.length() > 0) {
				szMH = replace(szMH, "<!--MH_HOME-->", szHome);
			}

			// insert help
			if (szHelp.length() > 0) {
				szMH = replace(szMH, "<!--MH_HELP-->", szHelp);
			}

			// insert contact
			if (szContact.length() > 0) {
				szMH = replace(szMH, "<!--MH_CONTACT-->", szContact);
			}

			// insert FAQ
			if (szFaq.length() > 0) {
				szMH = replace(szMH, "<!--MH_FAQ-->", szFaq);
			}

			// insert CHAT
			if (szChat.length() > 0) {
				szMH = replace(szMH, "<!--MH_CHAT-->", szChat);
			}

			// insert SEAL
			szMH = replace(szMH,  "<!--MH_SEAL-->", szSeal);

			// insert skiplink alt text
			szMH = replace(szMH,  "<!--MH_SKIPLINK-->", szSkip);

			return szMH;
		} catch (Exception e) {
			return MAIN_HEADER;
		}

	    //return getMainHeader(szApptype, bRemoveChat);
	}
    //------------------------------------------------------------------------
    /**
     * This method constructs the side navigation bar based on the current
     * progress in the application for FFOTW, SFOTW,  RFOTW, and their
     * embedded modules.  The output is printed on the left side of the page
     * and under the main header.
     *
     * @param  szLocale String The application locale code
     * @param clList AList Session list
     * @param clFieldValues Vector session data
     *
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getSideNavBar (String szLocale, AList clList, Vector clFieldValues)
    {
    	String szFaaMode;   // [faamode]
    	String szApptype;   // [APPTYPE]
    	int iCurr;          // [currentsection]
    	int iLast;	        // [lastsection]
    	String szModel;		// [MODEL]
    	String szSpecial; 	// [confirmspecial]
    	String szEZ;        // [ezflag]

    	try {
    		szFaaMode = clList.getFieldValue(AFOTWSessionValues.modeIDLabel, clFieldValues);
    	} catch (Exception e) {
    		szFaaMode = "";
    	}

    	try {
    		szApptype = clList.getFieldValue(JSPUtil.LABEL_APPTYPE, clFieldValues);
    	} catch (Exception e) {
    		szApptype = "";
    	}

    	try {
    		String szCurrSection = clList.getFieldValue("currentsection", clFieldValues);
    		iCurr = Integer.parseInt(szCurrSection);
    	} catch (Exception e) {
    		iCurr = 0;
    	}

    	try {
    		String szLastSection = clList.getFieldValue("lastsection", clFieldValues);
    		iLast = Integer.parseInt(szLastSection);
    	} catch (Exception e) {
    		iLast = 0;
    	}

    	try {
    		szModel = clList.getFieldValue("MODEL", clFieldValues);
    	} catch (Exception e) {
    		szModel = "";
    	}

    	try {
    		szSpecial = clList.getFieldValue("confirmspecial", clFieldValues);
    	} catch (Exception e) {
    		szSpecial = "";
    	}

    	try {
    		szEZ = clList.getFieldValue("ezflag", clFieldValues);
    	} catch (Exception e) {
    		szEZ = "";
    	}


    	return getSideNavBar(szLocale, szFaaMode,szApptype,iCurr,iLast,szModel,szSpecial,szEZ);

    }
    //------------------------------------------------------------------------
    /**
     * This method constructs the side navigation bar based on the current
     * progress in the application for FFOTW, SFOTW,  RFOTW, and their
     * embedded modules.  The output is printed on the left side of the page
     * and under the main header.
     *
     * @param  szLocale String The application locale code
     * @param szFaaMode String value of [faamode]
     * @param szApptype	String value of [APPTYPE]
     * @param iCurr int value of [currentsection]
     * @param iLast int value of [lastsection]
     * @param szModel String value of [MODEL]
     * @param szSpecial String value of [specialflag]
     *
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    protected static String getSideNavBar (
    	String szLocale, 	// [locale]
    	String szFaaMode,        // [faamode]
    	String szApptype,		// [APPTYPE]
    	int iCurr,           // [currentsection]
    	int iLast,	        // [lastsection]
    	String szModel,		// [MODEL]
    	String szSpecial, 	// [specialflag]
    	String szEZ         // [ezflag]
        )
    {
		try {
			boolean bFinish = false;
			boolean bSkip = false;
			boolean bSpanish = BundleUtil.isSpanish(szLocale);

			int iType = 0; // image type

			int START  = STEP_PERSONAL; // default starting step
			int FINISH = STEP_FINISH;   // default finishing step
			int REVIEW = STEP_REVIEW;   // default review step

			String szSB = SIDE_BAR;  // default side bar

			String[] aryLabels; // step label
			String[] aryNoPrint;  // No print text
			String szMenu;

			// No side bars for FAA Entry
			// side bars are for primay FAFASA only
			if (isFaaModeAny(szFaaMode)) {
				return "";
			} else if (isPrimaryFAFSA(szApptype)) {
				if (bSpanish) {
					aryLabels = SB_STEP_LABELS_SP;
					aryNoPrint = SB_STEP_NOPRINT_SP;
				} else {
					aryLabels = SB_STEP_LABELS_EN;
					aryNoPrint = SB_STEP_NOPRINT_EN;
				}
			} else {
				// all other apps stop here
				return "";
			}

			// reset current section visiting within 0- FINISH
			if (iCurr < 0 ) {
				iCurr = 0;
			} else if (iCurr > FINISH) {
				iCurr = FINISH;
			}

			// reset iLast section visited within iCurr- FINISH
			if (iLast < iCurr) {
				iLast = iCurr;
			} else if (iLast > FINISH) {
				iLast = FINISH;
			}

			// from entry pages can not bypass review step (EOE)
			if (iCurr <= REVIEW && iLast > REVIEW) {
				iLast = REVIEW;
			}

			// finish?
			bFinish = (iCurr == FINISH && iLast == FINISH);

			// insert side bar menu labels
			for (int i=START; i<=FINISH; i++) {
				if (bFinish) {
					// finish - no link
					iType = SB_VISITED;
					szMenu = (i==iCurr)? SB_MENU_ON : SB_MENU_OFF;
				} else {
					// Skipping the Parents' Info step
					bSkip = (i == STEP_PARENTAL && skipPar(szFaaMode, szApptype, szModel, szSpecial));

					if (i == iCurr) {
						// currently visiting
						iType = SB_VISITING;
						szMenu = SB_MENU_ON;
					} else if (i <= iLast && !bSkip){
						// visited and not skipped
						iType = SB_VISITED;
						szMenu = SB_MENU_OFF;
					} else {
						// not visited
						iType = SB_NOT_VISITED;
						szMenu = SB_MENU_NA;
					}
				}

				// insert step label
				szMenu = replace(szMenu, "<!--SB_STEP_LABEL-->", aryLabels[i]);

				// insert step label no print text
				szMenu = replace(szMenu, "<!--SB_STEP_NOPRINT-->", aryNoPrint[iType]);

				// inser step number
				szMenu = replace(szMenu, "<!--SB_STEP#-->", String.valueOf(i));
				if (iType == SB_VISITING || iType == SB_VISITED) {
					szMenu = replace(szMenu, "<!--SB_STEP#-->", String.valueOf(i));
				}

				// Some steps may have two lines
				if ((BundleUtil.isEnglish(szLocale) && (i==5 || i==7)) ||
					(bSpanish && (i==1 || i==3 || i==5 || i==6))) {
					szMenu = replace(szMenu, "menuitem-", "menuitem2-");
				}
				// Update the side bar
				szSB = replace(szSB, "<!--SB_STEP"+i+"-->", szMenu);
			}

			return szSB;
		} catch (Exception e) {
			AFOTWLog.log("JSPUtil.getSideNavBar(): failed.", e, AFOTWLog.INFO, JSPUtil.class.getName());
			return "";
		}
    }

    //------------------------------------------------------------------------
    /**
     * This method constructs the top navigation bar based on the current
     * progress in the application for FFOTW, SFOTW,  RFOTW, and their
     * embedded modules.  The output is printed on the top of the page
     * and under the main header.
     *
     * @param  szLocale String The application locale code
     * @param clList AList Session list
     * @param clFieldValues Vector session data
     *
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getTopNavBar (String szLocale, AList clList, Vector clFieldValues, int iPos)
    {
    	String szFaaMode;   // [faamode]
    	String szApptype;   // [APPTYPE]
    	int iCurr;          // [currentsection]
    	int iLast;	        // [lastsection]
    	String szModel;		// [MODEL]
    	String szSpecial; 	// [confirmspecial]
    	String szEZ;        // [ezflag]
    	String szSkippar;	// [ezSKIPPAR]
    	
    	try {
    		szFaaMode = clList.getFieldValue(AFOTWSessionValues.modeIDLabel, clFieldValues);
    	} catch (Exception e) {
    		szFaaMode = "";
    	}

    	try {
    		szApptype = clList.getFieldValue(JSPUtil.LABEL_APPTYPE, clFieldValues);
    	} catch (Exception e) {
    		szApptype = "";
    	}

    	try {
    		String szCurrSection = clList.getFieldValue("currentsection", clFieldValues);
    		iCurr = Integer.parseInt(szCurrSection);
    	} catch (Exception e) {
    		iCurr = 0;
    	}
   	
    	try {
    		String szLastSection = clList.getFieldValue("lastsection", clFieldValues);
    		iLast = Integer.parseInt(szLastSection);
    	} catch (Exception e) {
    		iLast = 0;
    	}

    	try {
    		szModel = clList.getFieldValue("MODEL", clFieldValues);
    	} catch (Exception e) {
    		szModel = "";
    	}

    	try {
    		szSpecial = clList.getFieldValue("confirmspecial", clFieldValues);
    	} catch (Exception e) {
    		szSpecial = "";
    	}

    	try {
    		szEZ = clList.getFieldValue("ezflag", clFieldValues);
    	} catch (Exception e) {
    		szEZ = "";
    	}
    	try {
        	szSkippar = clList.getFieldValue("szSKIPPAR", clFieldValues);
    	} catch (Exception e) {
    		szSkippar = "";
    	}

    	return getTopNavBar(szLocale, szFaaMode,szApptype,iCurr,iLast,szModel,szSpecial,szEZ,szSkippar);
    }
    //------------------------------------------------------------------------
    /**
     * This method constructs the top navigation bar based on the current
     * progress in the application for FFOTW, SFOTW,  RFOTW, and their
     * embedded modules.  The output is printed on the top of the page
     * and under the main header.
     *
     * @param  szLocale String The application locale code
     * @param szFaaMode String value of [faamode]
     * @param szApptype	String value of [APPTYPE]
     * @param iCurr int value of [currentsection]
     * @param iLast int value of [lastsection]
     * @param szModel String value of [MODEL]
     * @param szSpecial String value of [specialflag]
     * @param szSkippar String value of [szSKIPPAR]
     *
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    protected static String getTopNavBar (
    	String szLocale, 	// [locale]
    	String szFaaMode,        // [faamode]
    	String szApptype,		// [APPTYPE]
    	int iCurr,           // [currentsection]
    	int iLast,	        // [lastsection]
    	String szModel,		// [MODEL]
    	String szSpecial, 	// [specialflag]
    	String szEZ,         // [ezflag]
    	String szSkippar    // [szSKIPPAR]        
    	)
    {
		try {
			boolean bFinish = false;
			boolean bSkip = false;
			boolean bSpanish = BundleUtil.isSpanish(szLocale);

			int iType = 0; // image type

			int START  = STEP_PERSONAL; // default starting step
			int FINISH = STEP_FINISH;   // default finishing step
			int REVIEW = STEP_REVIEW;   // default review step

			String szTB = TOP_BAR;  // default top bar

			String[] aryLabels; // step label
			String[] aryNoPrint;  // No print text
			String szMenu;

			// No top bars for FAA Entry
			// top bars are for primay FAFASA only
			if (isFaaModeAny(szFaaMode)) {
				return "";
			} else if (isPrimaryFAFSA(szApptype)) {
				if (bSpanish) {
					aryLabels = TB_STEP_LABELS_SP;
					aryNoPrint = TB_STEP_NOPRINT_SP;
				} else {
					aryLabels = TB_STEP_LABELS_EN;
					aryNoPrint = TB_STEP_NOPRINT_EN;
				}
			} else {
				// all other apps stop here
				return "";
			}

			// reset current section visiting within 0- FINISH
			if (iCurr < 0 ) {
				iCurr = 0;
			} else if (iCurr > FINISH) {
				iCurr = FINISH;
			}

			// reset iLast section visited within iCurr- FINISH
			if (iLast < iCurr) {
				iLast = iCurr;
			} else if (iLast > FINISH) {
				iLast = FINISH;
			}

			// from entry pages can not bypass review step (EOE)
			if (iCurr <= REVIEW && iLast > REVIEW) {
				iLast = REVIEW;
			}

			// finish?
			bFinish = (iCurr == FINISH && iLast == FINISH);

			// insert top bar menu labels
			for (int i=START; i<=FINISH; i++) {
				if (bFinish) {
					// finish - no link
					iType = TB_VISITED;
					szMenu = (i==iCurr)? TB_MENU_FINISH_ON : TB_MENU_FINISH_OFF;
				} else {
					// Skipping the Parents' Info step
					bSkip = (i == STEP_PARENTAL && 
							skipPar(szFaaMode, szApptype, szModel, szSpecial,szSkippar));
					
					if (i == iCurr) {
						// currently visiting
						iType = TB_VISITING;
						szMenu = TB_MENU_ON;
					} else if (i <= iLast && !bSkip){
						// visited and not skipped
						iType = TB_VISITED;
						szMenu = TB_MENU_OFF;
					} else {
						// not visited
						iType = TB_NOT_VISITED;
						szMenu = TB_MENU_NA;
					}
				}

				// insert step label
				szMenu = replace(szMenu, "<!--TB_STEP_LABEL-->", aryLabels[i]);

				// insert step label no print text
				szMenu = replace(szMenu, "<!--TB_STEP_NOPRINT-->", aryNoPrint[iType]);

				// insert step number
				szMenu = replace(szMenu, "<!--TB_STEP#-->", String.valueOf(i));
				if (iType == TB_VISITING || iType == TB_VISITED) {
					szMenu = replace(szMenu, "<!--TB_STEP#-->", String.valueOf(i));
				}

				// Update the top bar
				szTB = replace(szTB, "<!--TB_STEP"+i+"-->", szMenu);
			}

			return szTB;
		} catch (Exception e) {
			AFOTWLog.log("JSPUtil.getTopNavBar(): failed.", e, AFOTWLog.INFO, JSPUtil.class.getName());
			return "";
		}
    }
    
    //------------------------------------------------------------------------
    /**
     * This method returns the definition of an anchor tag named "skiplinks".
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getSkipAnchor() {
	    return ANCHOR_SKIPLINK;
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the state dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param clList AFOTWList field definition list
	 * @param clValues Vector field clValues in the session
	 * @param szID String the id of this control
	 * @param szLocale String Application locale code
	 */
	//---------------------------------------------------------------------
	public static String getState(
								  int iType,
								  String szField,
								  AFOTWList clList,
								  Vector clValues,
								  String szID,
								  String szLocale) {

		return getState(iType, szField, clList, clValues, szID, "", szLocale);
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the state dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param clList AFOTWList field definition list
	 * @param clValues Vector field clValues in the session
	 * @param szID String the id of this control
	 * @param szOtherAttr String other attribute for the select control, e.g., onChange
	 * @param szLocale String Application locale code
	 */
	//---------------------------------------------------------------------
	public static String getState(
								  int iType,
								  String szField,
								  AFOTWList clList,
								  Vector clValues,
								  String szID,
								  String szOtherAttr,
								  String szLocale) {

   		String szFieldVal = "";
   		try {
	   		szFieldVal = clList.getFieldValue(szField, clValues);
   		} catch (Exception e) {}

		return getState(iType, szField, szFieldVal, szID, szOtherAttr, szLocale);
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the state dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param szFieldVal String field value in the session
	 * @param szID String the id of this control
	 * @param szOtherAttr String other attribute for the select control, e.g., onChange
	 * @param szLocale String Application locale code
	 */
	//---------------------------------------------------------------------
	public static String getState(
								  int iType,
								  String szField,
								  String szFieldVal,
								  String szID,
								  String szOtherAttr,
								  String szLocale) {
		String[][] aryOptions;

		boolean bSpanish = BundleUtil.isSpanish(szLocale);

		if (iType == STATE_TYPE_FULL) {
			// mailing states
			if (bSpanish)  {
				aryOptions = DROPDOWN_STATE_FULL_SP;
			} else {
				aryOptions = DROPDOWN_STATE_FULL_EN;
			}
		} else if (iType == STATE_TYPE_SCHOOL) {
			// FSC states
			if (bSpanish)  {
				aryOptions = DROPDOWN_STATE_SCHOOL_SP;
			} else {
				aryOptions = DROPDOWN_STATE_SCHOOL_EN;
			}
		} else if (iType == STATE_TYPE_ACG) {
			// ACG states
			if (bSpanish)  {
				aryOptions = DROPDOWN_STATE_ACG_SP;
			} else {
				aryOptions = DROPDOWN_STATE_ACG_EN;
			}
		} else {
			// residence and drive states
			if (bSpanish)  {
				aryOptions = DROPDOWN_STATE_RESIDENCE_SP;
			} else {
				aryOptions = DROPDOWN_STATE_RESIDENCE_EN;
			}
		}

		return getDropDown(szField, szFieldVal, aryOptions, szID, szOtherAttr);
	}
    //------------------------------------------------------------------------
    /**
     * This method constructs the subfooter of the page. The output is
     * printed on the bottom of the page below the question texts but
     * above the main footer. This version may create a custom subfooter
     * and/or include any extra buttons.
     * @param bPrev boolean true = show previous button
     * @param bNext boolean true = show next buton
	 * @param szLocale String Application locale code
     * @param  szApptype String value of [APPTYPE]
     * @param szHelpText The file name of the help text for this page
     * * @param iPos int current page id
     * @param curr int value of [currentsection]
     * @param iLast int value of [lastsection]
     * @param bCustom boolean true = help + extra buttons only;
     *                         fale = help + extra buttons + template buttons
     * @param String szExtra Extra buttons inserted to the subfooter
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getSubFooter(
                boolean bPrev,
				boolean bNext,
				String szLocale,  	// [locale]
    			String szApptype, 	// [APPTYPE]
    			String szFaaMode,
    			int iPos,           // current page id
				int iCurr,           // [currentsection]
                int iLast, 			// [lastsection]
                boolean bCustom,   // flag indicating custom subfooter or not
                String szExtra     // extra buttons to the subfooter
                ) {
		try {
			int REVIEW = STEP_REVIEW;

			String szSF;
			String iNext;
			String szPrev;
			String szFinalChk;

			boolean bFAA = isFaaModeAny(szFaaMode);
			boolean bSpanish = BundleUtil.isSpanish(szLocale);

			szSF = SUBFOOTER;

			if (bFAA) {
				// FAA apps
				iNext = SF_NEXT_FAA;
				szPrev = SF_PREVIOUS_FAA;
			} else {
				if (bSpanish) {
					// Spanish apps
					iNext = SF_NEXT_SP;
					szPrev = SF_PREVIOUS_SP;
				} else {
					// English apps
					iNext = SF_NEXT_EN;
					szPrev = SF_PREVIOUS_EN;
				}
			}

			if (!bCustom) {
				// template subfooter: extra buttons + template buttons

				// CR 5313, R.L. - 11/20/08
				// Insert the Check For Errors on the main entry pages only

				// May include Final Check for FFOTW, RFOTW, and SFOTW if going back
				// to entry pages from the pose-entry pages
				szFinalChk = "";
				if (iPos < 6000 && iCurr < REVIEW && iLast >= REVIEW) {
					if (szApptype.equals(APP_TYPE_FFOTW)) {
						if (bFAA) {
							szFinalChk = SF_FINALCHK_FAA;
						} else if (bSpanish) {
							szFinalChk = SF_FINALCHK_SP;
						} else {
							szFinalChk = SF_FINALCHK_EN;
						}
					}
				}

				if (szFinalChk.length() > 0) {
					szExtra = new StringBuffer(szExtra).append(szFinalChk).toString();
				}
				// these are the template buttons
				if (bPrev) {
					szSF = replace(szSF, "<!--SF_PREVIOUS-->", szPrev);
				}
				if (bNext) {
					szSF = replace(szSF, "<!--SF_NEXT-->", iNext);
				}
			}

			// Include the extra buttons
			szSF = replace(szSF, "<!--SF_EXCEPTION-->", szExtra);

			return szSF;
		} catch (Exception e) {
			return SUBFOOTER;
		}
    }

    //------------------------------------------------------------------------
    /**
     * This method constructs the subfooter of the page. The output is
     * printed on the bottom of the page below the question texts but
     * above the main footer. This version may create a custom subfooter
     * and/or include any extra buttons.
     * @param bPrev boolean true = show previous button
     * @param bNext boolean true = show next buton
	 * @param szLocale String Application locale code
     * @param szApptype String value of [APPTYPE]
     * @param szHelpText The file name of the help text for this page
     * @param iPos int current page id
     * @param szCurr String value of [currentsection]
     * @param szLast String value of [lastsection]
     * @param bCustom boolean true = help + extra buttons only;
     *                         fale = help + extra buttons + template buttons
     * @param String szExtra Extra buttons inserted to the subfooter
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getSubFooter(
    			boolean bPrev,
				boolean bNext,
				String szLocale,	// [locale]
                String szApptype, 	// [APPTYPE]
                String szFaaMode,
                int    iPos,        // current position
				String szCurr,        // [currentsection]
                String szLast, 	    // [lastsection]
                boolean bCustom,   // flag indicating custom subfooter or not
                String szExtra     // extra buttons to the subfooter
               ) {
		int iCurr;
		int iLast;

		try {
			iCurr = Integer.valueOf(szCurr).intValue();
		} catch (Exception e) {
			iCurr = 0;
		}

		try {
			iLast = Integer.valueOf(szLast).intValue();
		} catch (Exception e) {
			iLast = 0;
		}

		return getSubFooter(bPrev, bNext, szLocale, szApptype, szFaaMode, iPos, iCurr, iLast, bCustom, szExtra);
	}

    //------------------------------------------------------------------------
    /**
     * This method constructs the subheader of the page, including the help
     * text graphic and the scrolling instruction.  The output is printed on
     * the top of the page below the main header.
     * @param bSidebar true = has left side bar; false = no side bar
     * @param szCurrSec String current section in the application
     * @param szLocale String the application locale code
     * @param szApptype String value of [APPTYPE]
     * @param szFAAMode String value of [faamode]
     * @param szHelpText The file name of the help text for this page
     * @param szSaveCode The save return code
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
    public static String getSubHeader(
    			boolean bSidebar,
				String szCurrSec, // [currentsection]
				String szLocale,    // [locale]
                String szApptype, 	// [APPTYPE]
                String szFAAMode,   // [faamode]
                String szHelpText,		// The file name of the help text for this page
                String szSaveCode       // [SAVERETCODE]
                ) {
		try {
			boolean bFAA = isFaaModeAny(szFAAMode);
			boolean bSpanish = BundleUtil.isSpanish(szLocale);
			int iApp = getAppIndex(szApptype, szFAAMode);

			String szSH = SUBHEADER;

			String szSide;
			String szImg = SH_IMAGES_NAMES[iApp][0];
			String szImgText = bSpanish? SH_IMAGES_NAMES[iApp][2] : SH_IMAGES_NAMES[iApp][1];

			// Use step images/names for student FAFSA
			if (!bFAA && (szApptype.equals(APP_TYPE_FFOTW)) ) {
				int iStep = 0;
				try {
					iStep = Integer.parseInt(szCurrSec);
				} catch (Exception e) {
					iStep = 0;
				}
				if (bSpanish) {
					szImg = SH_STEP_IMAGES_NAMES_SP[iStep][0];
					szImgText = SH_STEP_IMAGES_NAMES_SP[iStep][1];
				} else {
					szImg = SH_STEP_IMAGES_NAMES_EN[iStep][0];
					szImgText = SH_STEP_IMAGES_NAMES_EN[iStep][1];
				}

				// CR 5450, R.L. 12/12/08
				// For better accessibilty, add step number to the subsection image alt-text
				if (iStep > 0) {
					szImgText = szCurrSec + " " + szImgText;
				}
			}

			if (bSidebar) {
				// side style
				szSide = "rightside";
				// rename image if needed
				if (szImg.trim().endsWith("_lg.gif")) {
					szImg = replace(szImg, "_lg.gif", ".gif");
				}
			} else {
				// side style
				szSide = "leftside";
				// rename image if needed
				if (!szImg.trim().endsWith("_lg.gif")) {
					szImg = replace(szImg, ".gif", "_lg.gif");
				}
			}

			// get the full image path
			if (bSpanish) {
				szImg = BundleUtil.getImgPath(BundleUtil.SPANISH) + szImg;
			} else {
				szImg = BundleUtil.getImgPath(BundleUtil.ENGLISH) + szImg;
			}

			// insert side style
			szSH = replace(szSH, "<!--SH_SIDE-->", szSide);

			// insert header image
			szSH = replace(szSH, "<!--SH_HEADER_IMG-->", szImg);

			// insert header image alt-text
			szSH = replace(szSH, "<!--SH_HEADER_ALT-->", szImgText);

			// insert help topic for FAA only
			// and the help topic is not blank
			if (bFAA && szHelpText != null && szHelpText.length() > 0) {
				szSH = replace(szSH, "<!--SH_HELP-->", replace(SH_HELP_FAA, "<!--SH_HELP_FILE-->", szHelpText));
			}

			// insert the save message
			String szSave = getSaveMessage(szSaveCode, bSpanish);
			szSH = replace(szSH, "<!--SH_SAVEMSG-->", szSave);

			return szSH;

		} catch (Exception e) {
			return SUBHEADER;
		}
    }
    public static String getSaveMessage(String szSaveCode, boolean bSpanish) {
		// insert the save message
		String szSave = "";
		if (szSaveCode.equals("000")) {
			// the save successful message
			if (bSpanish) {
				szSave = "La solicitud se ha guardado satisfactoriamente.";
			} else {
				szSave = "Application was successfully saved.";
			}
		} else if (szSaveCode.equals("100")) {
			// the save attempt failed message
			if (bSpanish) {
				szSave = "Falló el intento de guardar los datos de la solicitud.";
			} else {
				szSave = "Application save attempt failed.";
			}
		}
		return szSave;
    }
	//------------------------------------------------------------------------
	/**
	 * This method returns the asterisk "*" if the flag = true; or blank ("") if the
	 * flag = false.  It may be used, for example, to insert a "*" sign next to an
	 * assumed field.
	 * Creation date: (7/17/01 9:57:46 AM)
	 * @return java.lang.String
	 * @param bFlag boolean flag
	 */
	//------------------------------------------------------------------------
	public static String getSymbol(boolean bFlag) {
		return getSymbol(bFlag, "<b>*</b>");
	}
	//------------------------------------------------------------------------
	/**
	 * This method returns the symbol if the flag = true; or blank ("")
	 * if the flag = false.  It may be used, for example, to insert a "#"
	 * sign next to a verified field.  It may also be used to insert the
	 * attribute "CHECKED" or "SELECTED" inside the checkbox or select option.
	 * Creation date: (7/17/01 9:57:07 AM)
	 * @return java.lang.String
	 * @param bFlag boolean
	 * @param symbol java.lang.String
	 */
	//------------------------------------------------------------------------
	public static String getSymbol(boolean bFlag, String symbol) {
		if (bFlag) {
			return symbol;
		} else {
			return "";
		}
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the Yes/No dropdown list with proper option selected.
	 * This version does not set control id.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param clList AFOTWList field definition list
	 * @param clValues Vector field clValues in the session
	 * @param szLocale String Application locale string
	 */
	//---------------------------------------------------------------------
	public static String getYesNo(String szField,
								  AFOTWList clList,
								  Vector clValues,
								  String szLocale){
		return getYesNo(szField, clList, clValues, "", "", szLocale);
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the Yes/No dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param clList AFOTWList field definition list
	 * @param clValues Vector field clValues in the session
	 * @param szID String the id of this control
	 * @param szLocale String Application locale
	 */
	//---------------------------------------------------------------------
	public static String getYesNo(String szField,
								  AFOTWList clList,
								  Vector clValues,
								  String szID,
								  String szLocale){

		return getYesNo(szField, clList, clValues, szID, "", szLocale);
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the Yes/No dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param clList AFOTWList field definition list
	 * @param clValues Vector field clValues in the session
	 * @param szID String the id of this control
	 * @param szOtherAttr String other attribute for the select control, i.e., onChange event.
	 * @param szLocale String application locale string
	 */
	//---------------------------------------------------------------------
	public static String getYesNo(String szField,
								  AFOTWList clList,
								  Vector clValues,
								  String szID,
								  String szOtherAttr,
								  String szLocale){
		String [][] aryYesNo;
		boolean bFAA = false;
		try {
			bFAA = isFaaModeAny(clList, clValues);
		} catch (Exception e) {
			bFAA = false;
		}

		if (bFAA) {
			aryYesNo = DROPDOWN_YES_NO_FAA;
		} else if (BundleUtil.isSpanish(szLocale)) {
			aryYesNo = DROPDOWN_YES_NO_SP;
		} else {
			aryYesNo = DROPDOWN_YES_NO_EN;
		}

		return getDropDown(szField, clList, clValues, aryYesNo, szID, szOtherAttr);
	}
	//---------------------------------------------------------------------
	/**
	 * This method returns the Yes/No dropdown list with proper option selected.
	 * Creation date: (8/15/01 14:11:48 PM)
	 * @return String the <select></select> definition for the dropdown
	 * @param szField java.lang.String field name
	 * @param szFieldVal String field value in the session
	 * @param szID String the id of this control
	 * @param szOtherAttr String other attribute for the select control, i.e., onChange event.
	 * @param szLocale String Application locale string
	 * @param szFaaMode String FAA mode
	 */
	//---------------------------------------------------------------------
	public static String getYesNo(String szField,
								  String szFieldVal,
								  String szID,
								  String szOtherAttr,
								  String szLocale,
								  String szFaaMode){
		String [][] aryYesNo;

		if (isFaaModeAny(szFaaMode)) {
			aryYesNo = DROPDOWN_YES_NO_FAA;
		} else if (BundleUtil.isSpanish(szLocale)) {
			aryYesNo = DROPDOWN_YES_NO_SP;
		} else  {
			aryYesNo = DROPDOWN_YES_NO_EN;
		}

		return getDropDown(szField, szFieldVal, aryYesNo, szID, szOtherAttr);
	}
    //------------------------------------------------------------------------
    /**
     * This method returns true if side bar is needed; false if not needed.
     * @param  szAppType String value of [APPTYPE]
     * @param  szFaaMode String value of [faamode]
     * @return boolean
     * @throws None
     */
    //------------------------------------------------------------------------
    public static boolean isSideBar(String szAppType, String szFaaMode)
    {
	    boolean bSide = false;

	    if (isFaaModeAny(szFaaMode)) {
	    	if (szAppType.equals(APP_TYPE_SA) ||
	    		szAppType.equals(APP_TYPE_FAA_IATOOL) ||
				szAppType.equals(APP_TYPE_FAA_R2T4)) {
	    		bSide = true;
	    	} else {
	    		bSide = false;
	    	}
	    } else if (szAppType.equals(APP_TYPE_FFOTW) ||
	    		 szAppType.equals(APP_TYPE_SA) )  {
		    bSide = true;
	    }

	    return bSide;
    }

    //------------------------------------------------------------------------
    /**
     * This method returns true if side bar is needed; false if not needed.
     * @param  PageContext JSP page context object
     * @return boolean
     * @throws None
     */
    //------------------------------------------------------------------------
	public static boolean isSideBar(PageContext pageContext) {

 		String szAppType;
		try {
			szAppType = TagUtil.getFieldValue(pageContext, JSPUtil.LABEL_APPTYPE);
		} catch (AException ae) {
			szAppType = "";
		}

		String szFaaMode;
		try {
			szFaaMode = TagUtil.getFieldValue(pageContext, JSPUtil.LABEL_MODE);
		} catch (AException ae) {
			szFaaMode = "";
		}

		// check if sidebar is needed
		return JSPUtil.isSideBar(szAppType, szFaaMode);
	}

    /**
     * This method returns true if side bar is needed; false if not needed.
     * @param  szAppType String value of [APPTYPE]
     * @param  szFaaMode String value of [faamode]
     * @return boolean
     * @throws None
     */
    //------------------------------------------------------------------------
    public static boolean isTopBar(String szAppType, String szFaaMode)
    {
	    boolean bSide = false;

	    if (isFaaModeAny(szFaaMode)) {
	    	if (szAppType.equals(APP_TYPE_SA) ||
	    		szAppType.equals(APP_TYPE_FAA_IATOOL) ||
				szAppType.equals(APP_TYPE_FAA_R2T4)) {
	    		bSide = true;
	    	} else {
	    		bSide = false;
	    	}
	    } else if (szAppType.equals(APP_TYPE_FFOTW) ||
	    		 szAppType.equals(APP_TYPE_SA) )  {
		    bSide = true;
	    }

	    return bSide;
    }

    //------------------------------------------------------------------------
    /**
     * This method returns true if side bar is needed; false if not needed.
     * @param  PageContext JSP page context object
     * @return boolean
     * @throws None
     */
    //------------------------------------------------------------------------
	public static boolean isTopBar(PageContext pageContext) {

 		String szAppType;
		try {
			szAppType = TagUtil.getFieldValue(pageContext, JSPUtil.LABEL_APPTYPE);
		} catch (AException ae) {
			szAppType = "";
		}

		String szFaaMode;
		try {
			szFaaMode = TagUtil.getFieldValue(pageContext, JSPUtil.LABEL_MODE);
		} catch (AException ae) {
			szFaaMode = "";
		}

		// check if sidebar is needed
		return JSPUtil.isSideBar(szAppType, szFaaMode);
	}
	
	//---------------------------------------------------------------------
	/**
	 * Replace substrings in a string.
	 * Creation date: (7/12/01 2:55:40 PM)
	 * @return java.lang.String the upated String
	 * @param szStr java.lang.String str to alter
	 * @param szPattern java.lang.String pattern
	 * @param szReplace java.lang.String replace
	 */
	//---------------------------------------------------------------------
	public static String replace(String szStr, String szPattern, String szReplace) {
		int iStart = 0;
		int iEnd = 0;
		StringBuffer clResult = new StringBuffer();

		while ((iEnd = szStr.indexOf(szPattern,iStart)) >= 0) {
			clResult.append(szStr.substring(iStart, iEnd));
			clResult.append(szReplace);
			iStart = iEnd + szPattern.length();
		}
		clResult.append(szStr.substring(iStart));
		return clResult.toString();
	}
	//------------------------------------------------------------------------
    /**
     * Convert to string to Tile Case
     * @param  String
     * @return String
     * @throws None
     */
    //------------------------------------------------------------------------
	public static String toTitleCase (String szStr) {
		int iLen = 0;

		if (szStr == null) {
			iLen = 0;
			szStr = "";
		} else {
			szStr = szStr.trim();
			iLen = szStr.length();
		}

		if (iLen == 1) {
			szStr = szStr.toUpperCase();
		} else if (iLen > 1) {
			int s = 0;
			int e = 0;
			String szUpper ="";
			szStr = szStr.toLowerCase();
			StringBuffer clResult = new StringBuffer();

			// Uppercase the first chracter
			clResult.append(szStr.substring(0, 1).toUpperCase());
			s = 1;

			// Then uppercase the first character after a blank space
			while ((e = szStr.indexOf(" ", s)) >=0 && e+1 < iLen) {
				clResult.append(szStr.substring(s, e+1));
				szUpper = szStr.substring(e+1, e+2);
				if (!szUpper.equals(" ")) {
					clResult.append(szUpper.toUpperCase());
					s = e+2;
				} else {
					s = e+1;
				}
			}
			if (s < iLen) {
				clResult.append(szStr.substring(s, iLen));
			}
			szStr = clResult.toString();

			//
			// Also uppercase the first chracter after a dash (-)
			s = 0;
			e = 0;
			clResult = new StringBuffer();
			clResult.append(szStr.substring(0, 1));
			s = 1;

			// Then uppercase the first character after a blank space
			while ((e = szStr.indexOf("-", s)) >=0 && e+1 < iLen) {
				clResult.append(szStr.substring(s, e+1));
				szUpper = szStr.substring(e+1, e+2);
				if (!szUpper.equals("-") && !szUpper.equals(" ")) {
					clResult.append(szUpper.toUpperCase());
					s = e+2;
				} else {
					s = e+1;
				}
			}
			if (s < iLen) {
				clResult.append(szStr.substring(s, iLen));
			}
			szStr = clResult.toString();
		}

		return szStr;
	}
	//---------------------------------------------------------------------
	/**
	 * Determine whether parental questions should be skipped
	 * Creation date: (7/14/01 7:55:17 PM)
	 * @return boolean true = skip parental questions; false = not skipped
	 * @param szFaaMode String [faamode]
	 * @param szAppType String [APPTYPE]
	 * @param szModel java.lang.String studet's model [MODEL])
	 * @param szSpecial String [confirmspecial]
	 */
	//---------------------------------------------------------------------
	protected static boolean skipPar(
		String szFaaMode,
		String szAppType,
		String szModel,
		String szSpecial)
	{
		return skipPar(szFaaMode, szAppType, szModel, szSpecial, "");
	}
	
	//---------------------------------------------------------------------
	/**
	 * Determine whether parental questions should be skipped
	 * Creation date: (7/14/01 7:55:17 PM)
	 * @return boolean true = skip parental questions; false = not skipped
	 * @param szFaaMode String [faamode]
	 * @param szAppType String [APPTYPE]
	 * @param szModel java.lang.String studet's model [MODEL])
	 * @param szSpecial String [confirmspecial]
	 * @param szSkippar String [szSKIPPAR]
	 */
	//---------------------------------------------------------------------
	protected static boolean skipPar(
		String szFaaMode,
		String szAppType,
		String szModel,
		String szSpecial,
		String szSkippar)
	{
		// Skip parental info if dependent and have special circumstance in FOTW
    	if (!JSPUtil.isFaaModeAny(szFaaMode) && szAppType.equals(JSPUtil.APP_TYPE_FFOTW)) 
    	{
    		if ((szModel.equals("D") && (szSpecial.equals("1")||szSpecial.equals("3")||szSpecial.equals("4"))))
    			return true;
    		
    		else if (szModel.equals("I") && szSkippar.equals("2"))
    			return true;
    	}

    	return false;
	}	//--------------------------------------------------------------------
	/**
	 * Gets the mode value from the session and determines if it
	 * indicates FAA mode.
	 * @param clItemList				Field definitions list
	 * @param clValues					Vector of session values
	 * @return boolean					true if FAA mode
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeAny(AList clItemList, Vector clValues)
	throws AException {
		try
		{
			String szFAAMode =
				clItemList.getFieldValue(AFOTWSessionValues.modeIDLabel, clValues);

			return isFaaModeAny(szFAAMode);
		}
		catch (Exception clE)
		{
			// If the faamode field isn't in the list, assume false.
			return false;
		}
	}
	//--------------------------------------------------------------------
	/**
	 * Determines if the mode value passed indicates any FAA mode.
	 * @param szMode					mode
	 * @return boolean					true if any FAA mode
	 *									false otherwise
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeAny(String szMode)
	{
		return	szMode.equals(FAA_MODE_NORMAL) ||
				szMode.equals(FAA_MODE_EXPERT) ||
				szMode.equals(FAA_MODE_ED) ||
				szMode.equals(FAA_MODE_EDE);
	}
	//--------------------------------------------------------------------
	/**
	 * Gets the mode value from the session and determines if it
	 * indicates FAA Expert mode.
	 * @param clItemList				Field definitions list
	 * @param clValues					Vector of session values
	 * @return boolean					true if FAA Expert mode
	 *									false otherwise
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeExpert(AList clItemList, Vector clValues)
	throws AException
	{
		try {
			return isFaaModeExpert(clItemList.getFieldValue(AFOTWSessionValues.modeIDLabel, clValues));
		} catch (Exception e) {
			return false;
		}
	}
	//--------------------------------------------------------------------
	/**
	 * Determines if the mode passed indicates FAA Expert mode.
	 * @param szMode					Mode
	 * @return boolean					true if FAA Expert mode
	 *									false otherwise
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeExpert(String szMode) throws AException
	{
		return szMode.equals(FAA_MODE_EXPERT);
	}
	//--------------------------------------------------------------------
	/**
	 * Gets the mode value from the session and determines if it
	 * indicates any FAA Expert mode.
	 * @param clItemList				Field definitions list
	 * @param clValues					Vector of session values
	 * @return boolean					true if any FAA Expert mode
	 *									false otherwise
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeExpertAny(AList clItemList, Vector clValues)
	throws AException
	{
		try {
			return isFaaModeExpertAny(clItemList.getFieldValue(AFOTWSessionValues.modeIDLabel, clValues));
		} catch (Exception e) {
			return false;
		}
	}
	//--------------------------------------------------------------------
	/**
	 * Determines if the mode passed indicates any FAA Expert mode.
	 * @param szMode					Mode
	 * @return boolean					true if any FAA Expert mode
	 *									false otherwise
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeExpertAny(String szMode) throws AException
	{
		return szMode.equals(FAA_MODE_EXPERT)
				|| szMode.equals(FAA_MODE_ED);
	}
	//--------------------------------------------------------------------
	/**
	 * Gets the mode value from the session and determines if it
	 * indicates a FAA normal mode.
	 * @param clItemList				Field definitions list
	 * @param clValues					Vector of session values
	 * @return boolean					true if FAA mode
	 *									false otherwise
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeNormal(AList clItemList, Vector clValues)
	throws AException
	{
		try {
			return isFaaModeNormal(clItemList.getFieldValue(AFOTWSessionValues.modeIDLabel, clValues));
		} catch (Exception e) {
			return false;
		}
	}

	//--------------------------------------------------------------------
	/**
	 * Determines if the mode value passed indicates FAA normal mode.
	 * @param szMode					mode
	 * @return boolean					true if FAA normal mode
	 *									false otherwise
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeNormal(String szMode)
	{
		return szMode.equals(FAA_MODE_NORMAL);
	}

	//--------------------------------------------------------------------
	/**
	 * Gets the mode value from the session and determines if it
	 * indicates any FAA normal mode.
	 * @param clItemList				Field definitions list
	 * @param clValues					Vector of session values
	 * @return boolean					true if any FAA mode
	 *									false otherwise
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeNormalAny(AList clItemList, Vector clValues)
	throws AException
	{
		try {
			return isFaaModeNormalAny(clItemList.getFieldValue(AFOTWSessionValues.modeIDLabel, clValues));
		} catch (Exception e) {
			return false;
		}
	}

	//--------------------------------------------------------------------
	/**
	 * Determines if the mode value passed indicates any FAA normal mode.
	 * @param szMode					mode
	 * @return boolean					true if any FAA normal mode
	 *									false otherwise
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeNormalAny(String szMode)
	{
		return szMode.equals(FAA_MODE_NORMAL)
				|| szMode.equals(FAA_MODE_EDE);
	}

	//--------------------------------------------------------------------
	/**
	 * Gets the mode value from the session and determines if it
	 * indicates a FAA ED mode.
	 * @param clItemList				Field definitions list
	 * @param clValues					Vector of session values
	 * @return boolean					true if FAA mode
	 *									false otherwise
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeED(AList clItemList, Vector clValues)
	throws AException
	{
		try {
			return isFaaModeED(clItemList.getFieldValue(AFOTWSessionValues.modeIDLabel, clValues));
		} catch (Exception e) {
			return false;
		}
	}

	//--------------------------------------------------------------------
	/**
	 * Determines if the mode value passed indicates FAA ED mode.
	 * @param szMode					mode
	 * @return boolean					true if FAA normal mode
	 *									false otherwise
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeED(String szMode)
	{
		return szMode.equals(FAA_MODE_ED);
	}

	//--------------------------------------------------------------------
	/**
	 * Gets the mode value from the session and determines if it
	 * indicates a FAA EDExpress mode.
	 * @param clItemList				Field definitions list
	 * @param clValues					Vector of session values
	 * @return boolean					true if FAA mode
	 *									false otherwise
	 * @exception AException
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeEDE(AList clItemList, Vector clValues)
	throws AException
	{
		try {
			return isFaaModeEDE(clItemList.getFieldValue(AFOTWSessionValues.modeIDLabel, clValues));
		} catch (Exception e) {
			return false;
		}
	}

	//--------------------------------------------------------------------
	/**
	 * Determines if the mode value passed indicates FAA EDExpress mode.
	 * @param szMode					mode
	 * @return boolean					true if FAA normal mode
	 *									false otherwise
	 */
	//--------------------------------------------------------------------
	public static boolean isFaaModeEDE(String szMode)
	{
		return szMode.equals(FAA_MODE_EDE);
	}
    //-----------------------------------------------------------------------------
	/**
	 * Returns a text description of the FAA service option number passed.
	 *
	 * @param szServiceOption			Service option
	 *
	 * @return                          text description of the service option
	 */
    //-----------------------------------------------------------------------------
	public static String getServiceAsText(String szServiceOption)
	{
		return (String) m_clSrvOptDesc.get(szServiceOption);
	}
    //-----------------------------------------------------------------------------
	/**
	 * Determins if the FAA menu option passed is an FAA Entry (entry or restore) type.
	 *
	 * @param szMenuOption
	 *
	 * @return    boolena true = FAA Entry; fale = Not an FAA Entry app
	 */
    //-----------------------------------------------------------------------------
	public static boolean isFaaEntryType(String szMenuOption)
	{
		boolean bEntry = false;
		try {
			int iOption = Integer.parseInt(szMenuOption);
			if (iOption >= 5 && iOption <= 10) {
				bEntry = true;
			}
		} catch (NumberFormatException nfe) {
		}
		return bEntry;
	}
	//--------------------------------------------------------------------
	/**
	 * Convert internal apptype for FAA applications.
	 * @param szApptype 				App type
	 * @param szMode					mode
	 * @return String					Internal app type
	 */
	//--------------------------------------------------------------------
    public static String getFAAAppType(String szApptype, String szFaaMode) {
		try {
	    	boolean bFAA = isFaaModeAny(szFaaMode);
			if (bFAA) {
				// FAA Access Entry
				if (szApptype.equals(APP_TYPE_FFOTW)) {
					szApptype = APP_TYPE_FAA_FFOTW;
				} else if (szApptype.equals(APP_TYPE_COTW)) {
					szApptype = APP_TYPE_FAA_COTW;
				} else if (szApptype.equals(APP_TYPE_SA)) {
					szApptype = APP_TYPE_FAA_SA;
				} else if (szApptype.equals(APP_TYPE_RFOTW)) {
					szApptype = APP_TYPE_FAA_RFOTW;
				}
			} else if (szFaaMode.equals("0")) {
				// Student Access
				szApptype = APP_TYPE_SA;
			}
	    } catch (Exception e) {
		    // do nothing
	    }
    	return szApptype;
    }

	//--------------------------------------------------------------------
	/**
	 * Determines if the application is a primary FAFSA application.
	 * @param szAppType String applicaiton type
	 * @return boolean
	 */
	//--------------------------------------------------------------------
	public static boolean isPrimaryFAFSA(String szAppType) {
		if (szAppType == null) {
			return false;
		} else if (
			szAppType.equals(APP_TYPE_FFOTW) ||
			szAppType.equals(APP_TYPE_COTW) ||
			szAppType.equals(APP_TYPE_RFOTW) ||
			szAppType.equals(APP_TYPE_SFOTW) ||
			szAppType.equals(APP_TYPE_SROTW)) {
			return true;
		} else {
			return false;
		}
	}

	//--------------------------------------------------------------------
	/**
	 * Determines what language to display by locale string
	 * @param szLocale String locale string
	 * @return int ENGLISH or SPANISH
	 * @deprecated
	 */
	//--------------------------------------------------------------------
	public static int language(String szLocale)
	{
		if (szLocale == null) {
			return ENGLISH;
		} else if (BundleUtil.isEnglish(szLocale)) {
			return ENGLISH;
		} else if (BundleUtil.isSpanish(szLocale)) {
			return SPANISH;
		// This is for backward compatibility
		} else if (szLocale.equals(APP_TYPE_SFOTW)  ||
		    szLocale.equals(APP_TYPE_SROTW)  ||
			szLocale.equals(APP_TYPE_SWKSHT) ||
			szLocale.equals(APP_TYPE_SWSDRUG) ) {
			return SPANISH;
		} else {
			return ENGLISH;
		}
	}

	//--------------------------------------------------------------------
	/**
	 * Determine student's model based on answers to the Student's Status
	 * questions. Update the session field [MODEL] before returning the
	 * value to the caller.
	 * @param clItemList				Field definitions list
	 * @param clValues					Vector of session values
	 * @return String					I = independent or D = dependent
	 * @throws AException
	 */
	//--------------------------------------------------------------------
	public static String setModel(AList clItemList, Vector clFieldValues)
	throws AException
	{
		boolean bFAA = isFaaModeAny(clItemList, clFieldValues);
		String szAppType = clItemList.getFieldValue(LABEL_APPTYPE, clFieldValues);

        String szDOBPRIOR = clItemList.getFieldValue("szDOBPRIOR", clFieldValues).trim();
        String szSTATMAR = clItemList.getFieldValue("szSTATMAR",  clFieldValues).trim();
        String szSTATGRAD = clItemList.getFieldValue("szSTATGRAD", clFieldValues).trim();
        String szACTIVEDUTY = clItemList.getFieldValue("szACTIVEDUTY", clFieldValues).trim();
        String szVETERAN = clItemList.getFieldValue("szVETERAN",  clFieldValues).trim();

        String szDEPSUPP = clItemList.getFieldValue("szDEPSUPP",  clFieldValues).trim();
        String szDEPLIVE = clItemList.getFieldValue("szDEPLIVE",  clFieldValues).trim();

        String szORPHAN = clItemList.getFieldValue("szORPHAN",   clFieldValues).trim();
        String szMINOR = clItemList.getFieldValue("szMINOR",  clFieldValues).trim();
        String szGUARDIANSHIP = clItemList.getFieldValue("szGUARDIANSHIP",  clFieldValues).trim();
        String szHOMELESS = clItemList.getFieldValue("szHOMELESS",  clFieldValues).trim();
        String szHOMELESSHUD = clItemList.getFieldValue("szHOMELESSHUD",  clFieldValues).trim();
        String szATRISK = clItemList.getFieldValue("szATRISK",  clFieldValues).trim();

        // This field is used to reset the model in FAA or COTW only
        String szDEPOVERRIDE = "";
        if (bFAA || szAppType.equals(APP_TYPE_COTW)) {
        	szDEPOVERRIDE = clItemList.getFieldValue("szDEPOVERRIDE", clFieldValues).trim();
        }

	    // Corrections may use the ISIR values
	    if (szAppType.equals(APP_TYPE_COTW)) {
        	szDOBPRIOR = (szDOBPRIOR.length() != 0)? szDOBPRIOR :
        		clItemList.getFieldValue("szIsirDOBPRIOR", clFieldValues);
        	szSTATGRAD = (szSTATGRAD.length() != 0)? szSTATGRAD :
        		clItemList.getFieldValue("szIsirSTATGRAD", clFieldValues);
        	szSTATMAR = (szSTATMAR.length() != 0)? szSTATMAR :
        		clItemList.getFieldValue("szIsirSTATMAR", clFieldValues);
        	szDEPSUPP = (szDEPSUPP.length() != 0)? szDEPSUPP :
        		clItemList.getFieldValue("szIsirDEPSUPP", clFieldValues);
        	szDEPLIVE = (szDEPLIVE.length() != 0)? szDEPLIVE :
        		clItemList.getFieldValue("szIsirDEPLIVE", clFieldValues);
        	szORPHAN = (szORPHAN.length() != 0)? szORPHAN :
        		clItemList.getFieldValue("szIsirORPHAN", clFieldValues);
        	szACTIVEDUTY = (szACTIVEDUTY.length() != 0)? szACTIVEDUTY :
        		clItemList.getFieldValue("szIsirACTIVEDUTY", clFieldValues);
        	szVETERAN = (szVETERAN.length() != 0)? szVETERAN :
        		clItemList.getFieldValue("szIsirVETERAN", clFieldValues);

        	szMINOR = (szMINOR.length() != 0)? szMINOR :
        		clItemList.getFieldValue("szIsirMINOR", clFieldValues);
        	szGUARDIANSHIP = (szGUARDIANSHIP.length() != 0)? szGUARDIANSHIP :
        		clItemList.getFieldValue("szIsirGUARDIANSHIP", clFieldValues);
        	szHOMELESS = (szHOMELESS.length() != 0)? szHOMELESS :
        		clItemList.getFieldValue("szIsirHOMELESS", clFieldValues);
        	szHOMELESSHUD = (szHOMELESSHUD.length() != 0)? szHOMELESSHUD :
        		clItemList.getFieldValue("szIsirHOMELESSHUD", clFieldValues);
        	szATRISK = (szATRISK.length() != 0)? szATRISK :
        		clItemList.getFieldValue("szIsirATRISK", clFieldValues);

        	szDEPOVERRIDE = (szDEPOVERRIDE.length() != 0)? szDEPOVERRIDE :
        		clItemList.getFieldValue("szIsirDEPOVERRIDE", clFieldValues);
        }

		String szModel =
		   (szDOBPRIOR.equals("1") ||
            szSTATMAR.equals("1") ||
			szSTATGRAD.equals("1") ||
	        szVETERAN.equals("1") ||
	        szACTIVEDUTY.equals("1") ||

	        szDEPSUPP.equals("1") ||
	        szDEPLIVE.equals("1") ||

	        szORPHAN.equals("1") ||
	        szMINOR.equals("1") ||
	        szGUARDIANSHIP.equals("1") ||
	        szHOMELESS.equals("1") ||
	        szHOMELESSHUD.equals("1") ||
	        szATRISK.equals("1")
			) ? "I" : "D";

		// For FAA, check dependecy override (Compute Edit 1021)
		// WEB-P01356 student mode needs to check dependency override in COTW
        if (bFAA || szAppType.equals(APP_TYPE_COTW)) {
			if (szDEPOVERRIDE.equals("1")) {
				szModel = "I";
			}
        }

        //WEB-P09036: Reset az/snt/ez fields when model changes
        if (isFOTW(clItemList, clFieldValues)) {
	        String modelBefore = clItemList.getFieldValue("MODEL", clFieldValues);
			clItemList.setFieldValue("MODEL", szModel, clFieldValues);
	        String modelAfter = clItemList.getFieldValue("MODEL", clFieldValues);
	        if (!modelBefore.equals(modelAfter)) {
	        	clItemList.setFieldValue("autozeroflag", "", clFieldValues);
	        	clItemList.setFieldValue("ezflag", "", clFieldValues);
	        	clItemList.setFieldValue("sntflag", "", clFieldValues);
	        	clItemList.setFieldValue("szAZFLAG", "", clFieldValues);
	        	clItemList.setFieldValue("szPARINCOMEASSET", "", clFieldValues);
	        }
        } else {
        	clItemList.setFieldValue("MODEL", szModel, clFieldValues);
        }
		return szModel;
	}

	//--------------------------------------------------------------------
	/**
	 * Determine if an SSN is masked based on the SSN's match flag and FAA mode
	 * @param String szSSNMatchFlag SSN match flag
	 * @param String szFAAMode FAA mode
	 * @return boolean true = masked; false = not masked
	 * @throws none
	 * @deprecated
	 */
	//--------------------------------------------------------------------
	public static boolean isSSNMasked(String szSSNMatchFlag, String szFAAMode) {
		try {
			// Will mask a good SSN in student mode
			// SCR 27311 - R.L. 11/17/04
			// Will maske regardless of match flag
			if (!isFaaModeAny(szFAAMode)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	//--------------------------------------------------------------------
	/**
	 * Determine if an SSN is masked based on the SSN's match flag and FAA mode
	 * @param String szSSNMatchFlag SSN match flag
	 * @param String szFAAMode FAA mode
	 * @param String szAppType
	 * @return boolean true = masked; false = not masked
	 * @throws none
	 */
	//--------------------------------------------------------------------
	public static boolean isSSNMasked(String szSSNMatchFlag, String szFAAMode, String szAppType) {
		try {
			// Will mask a good SSN in student mode except in Student Access with a match flag = 1
			if (!isFaaModeAny(szFAAMode)) {
				if (szAppType.equals(APP_TYPE_SA) && szSSNMatchFlag.equals("1")) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns the age from the entered DOB in Q#9.
	 * @return int age
	 * @param String szDOB DateofBirth in mmddyyyy format
	 */
	 //---------------------------------------------------------------------
		public static int age(String dob) {
			 String szDOB = dob;
			 int y = Integer.parseInt(szDOB.substring(szDOB.length()-4, szDOB.length()));
			 int d = Integer.parseInt(szDOB.substring(szDOB.length()-6, szDOB.length()-4));
			 int m = Integer.parseInt(szDOB.substring(0, szDOB.length()-6));
		     Calendar cal = new GregorianCalendar(y, m-1, d);
		     Calendar now = new GregorianCalendar();
		     int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		     if((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
		       || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)
		       && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH)))
		     {
		        res--;
		     }
		     return res;
		   }

		//--------------------------------------------------------------------
		/**
		 * Determine student's model based on answers to the Student's Status
		 * questions. Update the session field [MODEL] before returning the
		 * value to the caller.
		 * @param clList				Field definitions list
		 * @param clValues					Vector of session values
		 * @return boolean					true = age 21 or younger; false age = 22 or above
		 * @throws AException
		 */
		//--------------------------------------------------------------------
		public static boolean isYouth(AList clList, Vector clFieldValues) throws AException {
			String szDOB = clList.getFieldValue("szDOB", clFieldValues);

			if (szDOB != null && szDOB.length() == 8) {
				return (age(szDOB) < 22);
			}
			return false;
	    }

		//--------------------------------------------------------------------
		/**
		 * Determine if this is a student FOTW.
		 * @param clList				Field definitions list
		 * @param clValues				Vector of session values
		 * @return boolean				true = student FOTW; false otherwise
		 * @throws None
		 */
		//--------------------------------------------------------------------
		public static boolean isFOTW(AList clList, Vector clFieldValues) {
			
			boolean bFAA = false;
			try {
				bFAA = JSPUtil.isFaaModeAny(clList, clFieldValues);
			} catch (Exception e) {
				bFAA = false;
			}
			
			String szApp = "";
			try {
				szApp = clList.getFieldValue(JSPUtil.LABEL_APPTYPE, 0, clFieldValues);
			} catch (Exception e) {
				szApp = "";
			}
			
			boolean bFOTW = !bFAA && szApp.equals(JSPUtil.APP_TYPE_FFOTW);
			
			return bFOTW;
	    }		

} // end JSPUtil
