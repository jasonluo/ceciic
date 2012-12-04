/* <!--TOPIC Libraries & Definitions-->
 *
 * Filename:    JSPUtilData.java
 * Description: This interface defines the HTML data for JSPUtil class, which
 *              has methods that take care of the common display logic and
 *              other miscellaneous jsp functions.
 *
 * $Modtime:   Aug 18 2010 23:20:20  $
 * $Log:   //cmlibrary_01/fafsa/archives/Web Development/Projects/FAFSAWeb/JavaSource/fotw1011/tags/JSPUtilData.java-arc  $
 * 
 *    Rev 1.20   Aug 18 2010 23:26:36   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-P09070: CR803 -Post Startup #8 - School Code search updates - IA & Dev - Assigned To = Sam Smith, Last State Changer = Laurie Marlowe
 * Backing out FSC code changes
 * 
 *    Rev 1.18   May 11 2010 17:55:24   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-P09327: Code updates for not including 9070 in 5/16 release - IA & Dev - Sam Smith
 * Reverting FSC changes made for TT 9070 so they don't appear in the 5/16 release
 * 
 *    Rev 1.14   Oct 23 2009 14:43:04   dukeda
 * TTuser/items:Dani Duke(dukeda)
 * Resolution for WEB-P07962: Field looses focus (232F_SR) - IA - Dani Duke
 * Add an id to the checkforerrors, prev, and next button anchors in order to programatically set focus.
 * 
 *    Rev 1.13   Oct 03 2009 14:38:02   lianro
 * Fixed top bar on the confirmation page.
 * 
 *    Rev 1.12   Oct 02 2009 14:28:10   lampst
 * TTuser/items:Steve Lampe(lampst)
 * Resolution for WEB-S00479.IA.17: Module association for 0708 development - Pending IA - Steve Lampe
 * Correct Top Nav Confrimation highlight
 * 
 *    Rev 1.11   Sep 16 2009 14:57:30   lampst
 * TTuser/items:Steve Lampe(lampst)
 * Resolution for WEB-S00479.IA.17: Module association for 0708 development - Pending IA - Steve Lampe
 * Added topbar tag
 * 
 *    Rev 1.10   Sep 03 2009 16:46:36   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for success messsages
 * 
 *    Rev 1.9   Sep 03 2009 12:28:36   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for FOTW alert messages
 * 
 *    Rev 1.8   Aug 31 2009 16:26:48   lianro
 * Updated step constants.
 * 
 *    Rev 1.7   Aug 27 2009 17:09:36   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for confirmation link
 * 
 *    Rev 1.6   Aug 25 2009 23:13:30   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Added support for returning validation errors as JSON data
 * 
 *    Rev 1.5   Aug 25 2009 11:11:02   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for confirmation link tag
 * 
 *    Rev 1.4   Aug 24 2009 15:14:06   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * remove visibility: hidden
 * 
 *    Rev 1.3   Aug 24 2009 14:33:58   smitsa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updates for AJAX messaging
 * 
 *    Rev 1.2   Aug 20 2009 17:01:30   SmitSa
 * TTuser/items:Sam Smith(smitsa)
 * Resolution for WEB-S00479.IA.15: Module association for 0708 development - Pending IA - Sam Smith
 * Updated so error icon is returned but hidden for FOTW
 * 
 *    Rev 1.1   Aug 11 2009 09:13:42   lianro
 * rollover demo code.
 * 
 *    Rev 1.0   Jul 22 2009 13:44:10   lianro
 * Initial revision.
 *
 *    Rev 1.5   Nov 18 2008 13:22:50   luecbr
 * SCR
 *
 *    Rev 1.4   Oct 31 2008 22:57:28   lianro
 * Get state name from bundles for easy state name conversion.
 *
 *    Rev 1.1   Jul 22 2008 16:15:28   lianro
 * Updated with 0910 logic
 *
 *    Rev 1.0   Apr 17 2008 16:11:58   lianro
 * TTuser/items:Ronghui Liang(LianRo)
 * Resolution for WEB-P00479: Module association for 0708 development - Waiting on Split IA - Configuration Control Board
 *
 * Initial revision.
 *
 *    Rev 1.16   Jan 18 2008 15:10:22   lianro
 * Fixed the alt-text key for "Return to FAA Main Menu"
 *
 *    Rev 1.1   Jul 17 2007 09:28:02   lianro
 * TTuser/items:Ronghui Liang(LianRo)
 * Resolution for WEB-P00479: Module association for 0708 development - Waiting on Split IA - Configuration Control Board
 * Placed #prevnext outside of #copy and inside the #content region.
 *
 *    Rev 1.0   Jul 14 2007 22:38:38   lianro
 * TTuser/items:Ronghui Liang(LianRo)
 * Resolution for WEB-P00479: Module association for 0708 development - Waiting on Split IA - Configuration Control Board
 *
 * Initial revision.
 *
 *
 */
package fotw1011.tags;
import common.*;

/**
 * This interface defines the HTML data for JSPUtil class.
 * Creation date: (7/12/01 11:12:40 AM)
 */
public interface JSPUtilData {

	/////////////////////////////////////////////////
	//
	//  FAA Mode
	//
	/** FAA mode constant - Normal mode */
	public final static String FAA_MODE_NORMAL = "1";

	/** FAA mode constant - Expert mode */
	public final static String FAA_MODE_EXPERT = "2";

	/** FAA mode constant - ED mode */
	public final static String FAA_MODE_ED = "3";

	/** FAA mode constant - EDExpress mode */
	public final static String FAA_MODE_EDE = "4";

	////////////////////////////////////////////////////
	//
	// For FAA only
	//
    /** Use to indicate that there are no exception buttons */
    public final static int NO_EXCEPTIONS = 0;

	/////////////////////////////////////////////////////
	//
	// Language
	//
	/** language - English
	 * @deprecated */
	public final static int ENGLISH = 1;

	/** language - Spanish
	 * @deprecated */
	public final static int SPANISH = 2;

	/////////////////////////////////////////////////////////
	//
	// Labels for attributes set in the request object
	//
	/** label for the AFOTWList object in the request */
	public final static String LABEL_LIST = "LIST";

	/** label for the AFOTWSessionValues object in the request */
	public final static String LABEL_VALUES = "VALUES";

	/** label for the FOTW alert messages */
	public static String LABEL_FOTW_ALERTS = "LABEL_ALERTS";

	/** label for the FOTW success messages */
	public static String LABEL_FOTW_SUCCESS = "LABEL_SUCCESS";

	/** label for the validation error message */
	public static String LABEL_ERRORS = "LABEL_ERRORS";

	/** label for the validation error fields */
	public static String LABEL_ERRORS_FIELDS = "LABEL_ERRORS_FIELDS";
	
	/** label for the validation error message */
	public static String LABEL_ERROR_MSG = "ERRMSG";

	/** label for the validation error field name */
	public static String LABEL_ERROR_FIELD = "ERRFIELD";

	/** label for the validation error type (reject or confirm) */
	public static String LABEL_ERROR_TYPE = "ERRINDEX";

	/** label for the validation confirm value index (confirm type error only) */
	public static String LABEL_ERROR_INDEX = "ERRCONF";

	/** label for the name of the servlet's session data  */
	public static String LABEL_VALUELABEL = "VALUELABEL";

	/** label for the name of AException severity  */
	public static String LABEL_SEVERITY = "SEVERITY";

    /** label for the System  */
	public static String LABEL_SYSTEM = "SYSTEM";

	/** label for the standalone app  */
	public static String LABEL_STANDALONE_TYPE = "STANDALONE_TYPE";

	/** label for the application mode  */
	public static String LABEL_MODE = AFOTWSessionValues.modeIDLabel;

	/** label for the application type  */
	public static String LABEL_APPTYPE = "APPTYPE";

	/** label for the application locale  */
	public static String LABEL_LOCALE = BundleUtil.LABEL_LOCALE;

	///////////////////////////////////////////////////
	//
	// Servlet URL & path
	//
	/** default servlet URL - noncycle */
	public static String SERVLET_URL_NONCYCLE = "/FOTWWebApp/";

	/** default servlet URL - current cycle */
	public static String SERVLET_URL_CURRCYCLE = "/FOTWWebApp/fotw1011/";

	/** default servlet URL - previous cycle */
	public static String SERVLET_URL_PREVCYCLE = "/FOTWWebApp/fotw0910/";

	/** current cycle path */
	public static String PATH_CURRCYCLE = "fotw1011";

	/** prev cycle path */
	public static String PATH_PREVCYCLE = "fotw0910";


	//////////////////////////////////////////////////////
	//
	// bottom bar choice
	//
	/** bottom bar type - no buttons */
	public final static int FOOTER_BLANK = 0;

	/** bottom bar type - exit only */
	public final static int FOOTER_EXIT = 1;

	/** bottom bar type - save and exit */
	public final static int FOOTER_SAVE_EXIT = 2;

	/** bottom bar type - save, view and exit */
	public final static int FOOTER_SAVE_VIEW_EXIT = 3;

	/** bottom bar type - view and exit */
	public final static int FOOTER_VIEW_EXIT = 4;

	/** FOTW site flag for the global footer*/
	public final static String SITE_FOTW = "fotw";

	/** PIN Web site flag for the global footer*/
	public final static String SITE_PIN = "pin";


	////////////////////////////////////////////////////
	//
	// Steps
	//
	/** step number for Personal Questions */
	public final static int STEP_PERSONAL = 1;

	/** step number for School Questions */
	public final static int STEP_SCHOOL = 2;

	/** step number for Status Questions */
	public final static int STEP_STATUS = 3;

	/** step number for Parent Demographics Questions */
	public final static int STEP_PARENTAL = 4;

	/** step number for Financial Questions */
	public final static int STEP_FINANCIAL = 5;

	//** step number for Preparer's Questions */
	public final static int STEP_PREPARER = 6;

	//** step number for Final Check */
	public final static int STEP_REVIEW = STEP_PREPARER;

	//** step number for Signature - same as review*/
	public final static int STEP_SIGN = STEP_REVIEW;

	//** step number for Submit*/
	public final static int STEP_SUBMIT = 7;

	/** step number for Finish - same as submit*/
	public final static int STEP_FINISH = STEP_SUBMIT;

	//////////////////////////////////////////////////////////
	//
	//  AppType Index
	//
	/** apptype index - Form FAFSA */
	public final static int FFOTW = 0;

	/** apptype index - Spanish FAFSA */
	public final static int SFOTW = 1;

	/** apptype index - Renewal FAFSA */
	public final static int RFOTW = 2;

	/** apptype index - FAFSA Corrections */
	public final static int COTW = 3;

	/** apptype index - App Status Check */
	public final static int APPSTAT = 4;

	/** apptype index - Electroic Signagure */
	public final static int ESIG= 5;

	/** apptype index - Student Access */
	public final static int SA = 6;

	/** apptype index - FAA Access */
	public final static int FAA = 7;

	/** apptype index - Standalone worksheets */
	public final static int WKSHT = 8;

	/** apptype index - Standalone federal school code lookup*/
	public final static int FSC = 9;

	/** apptype index - Standalone drug worksheet */
	public final static int WSDRUG = 10;

	/** apptype index - Standalone ACG */
	public final static int ACG = 11;

	/** apptype index - FAA FFOTW Entry */
	public final static int FAA_FFOTW = 12;

	/** apptype index - FAA RFOTW Entry */
	public final static int FAA_RFOTW = 13;

	/** apptype index - FAA COTW Entry */
	public final static int FAA_COTW = 14;

	/** apptype index - FSC Corrections */
	public final static int FSCADD = 15;

	/** apptype index - FAA Return of Title IV Funds */
	public final static int FAA_R2T4 = 16;

	/** apptype index - FAA ISIR Request */
	public final static int FAA_ISIRREQ = 17;

	/** apptype index - Standalone Spanish worksheets */
	public final static int SWKSHT = 18;

	/** apptype index - Standalone Spanish drug worksheet */
	public final static int SWSDRUG = 19;

	/** apptype index - FAA ISIR Analysis Tool */
	public final static int FAA_IATOOL = 20;

	/** apptype index - Spanish Renewal FAFSA */
	public final static int SROTW = 21;

	/** apptype index - FAA Student Inquiry */
	public final static int FAA_SA = 22;

	/** apptype index - FAA Verification Tool */
	public final static int FAA_VERIFWS = 23;
	
	/** apptype index - HS Completion*/
	public final static int HS_COMPLETION = 24;

	/////////////////////////////////////////////////////////////
	//
	// Form Actions based on AppType Index
	//
	/** form action programs*/
	public static String[] FORM_ACTIONS = {
		// AppStat, SA, and FAA Access modules (except for FAA Entry)
		// should use the noncyclic path
		// also the standalone FSC
		SERVLET_URL_CURRCYCLE + "FFOTWServlet",	// FFOTW
		SERVLET_URL_CURRCYCLE + "FFOTWServlet", 	// SFOTW
		SERVLET_URL_CURRCYCLE + "FFTWServlet",  	// RFOTW
		SERVLET_URL_CURRCYCLE + "COTWServlet",	  	// COTW
		SERVLET_URL_NONCYCLE + "AppStatServlet",			// APPSTAT
		SERVLET_URL_CURRCYCLE + "SigServlet",		// ESIG
		SERVLET_URL_NONCYCLE + "StudentAccessServlet",		// SA
		SERVLET_URL_NONCYCLE + "FaaAccessServlet",			// FAA
		SERVLET_URL_CURRCYCLE + "WorksheetServlet",// WKSHT
		SERVLET_URL_NONCYCLE + "FSLookupServlet",			// FSC
		SERVLET_URL_CURRCYCLE + "WorksheetServlet",// WSDRUG
		SERVLET_URL_CURRCYCLE + "ACGServlet",   	// ACG
		SERVLET_URL_CURRCYCLE + "FaaEntryServlet", // FAA_FFOTW
		SERVLET_URL_CURRCYCLE + "FaaEntryServlet", // FAA_RFOTW
		SERVLET_URL_CURRCYCLE + "FaaEntryServlet", // FAA_COTW
		SERVLET_URL_CURRCYCLE + "FSCorrectionServlet",// FSC Corrections
		"/R2T4Web/R2T4Servlet",    		       // FAA_R2T4
		SERVLET_URL_CURRCYCLE + "ISIRRequestServlet", // FAA_ISIRREQ
		SERVLET_URL_CURRCYCLE + "WorksheetServlet",  // SWKSHT
		SERVLET_URL_CURRCYCLE + "WorksheetServlet",  // SWSDRUG
		SERVLET_URL_CURRCYCLE + "IAToolServlet",      // FAA_IATOOL
		SERVLET_URL_CURRCYCLE + "FFOTWServlet",  	   // SROTW
		SERVLET_URL_NONCYCLE + "StudentAccessServlet",		   // FAA SA
		SERVLET_URL_CURRCYCLE + "FaaEntryServlet", // FAA_VERIFWS
		SERVLET_URL_CURRCYCLE + "HSCompletionServlet", // 

	};

	////////////////////////////////////////////////////////////
	//
	//  Application name based on AppType Index
	//
	public static String[][] APP_NAMES = {
		{"FAFSA on the Web", 			"FAFSA en la Web"},  // FFOTW
		{"FAFSA en la Web",  			"FAFSA en la Web"}, // SFOTW
		{"FAFSA on the Web",  			"FAFSA en la Web"}, // RFOTW
		{"Corrections on the Web",  	"Corrección de datos en la Web"},	// COTW
		{"Application Status Check",	"Consulta del Estado de la Solicitud"}, // APPSTAT
		{"Electronic Signatures",	 	"Firmas Electrónicas"},	// ESIG
		{"Student Access Online",  		"Acceso para Estudiantes"}, 	// SA
		{"FAA Access to CPS Online",	"FAA Access to CPS Online"},	// FAA
		{"Worksheet",  					"Hojas de Trabajo"},	        	// WKSHT
		{"Federal School Code Search",  "Búsqueda de Códigos Federales de Instituciones Postsecundarias"},// FSC
		{"Worksheet",  					"Hojas de Trabajo"},	// WSDRUG
		{"ACG Eligibility Data",  		"Datos para determinar derecho a la Beca Competitividad Académica"}, // ACG
		{"FAFSA/Renewal Application",  	"FAFSA/Renewal Application"},      // FAA_FFOTW
		{"FAFSA/Renewal Application",  	"FAFSA/Renewal Application"},    // FAA_RFOTW
		{"Corrections",  				"Corrections"},      // FAA_COTW
		{"Add or Delete a School Code", "Añadir o Borrar Códigos de Instituciones Educativas"}, // FSC Corrections
		{"Return of Title IV Funds on the Web",  "Return of Title IV Funds on the Web"},    // FAA_R2T4
		{"ISIR Request",  				"ISIR Request"},	// FAA_ISIRREQ
		{"Hojas de Trabajo",   			"Hojas de Trabajo"},// SWKSHT
		{"Hojas de Trabajo",  			"Hojas de Trabajo"},// SWSDRUG
		{"ISIR Analysis Tool",  		"ISIR Analysis Tool"}, // FAA_IATOOL
		{"FAFSA en la Web",  			"FAFSA en la Web"}, // SROTW
		{"Student Inquiry",	 			"Student Inquiry"},// FAA SA
		{"Verification Tool",  			"Verification Tool"},// FAA VERIFWS
	};

	////////////////////////////////////////////////////////////
	//
	//  Application images/names based on AppType Index for the page subheader
	//
	public static String[][] SH_IMAGES_NAMES = {
		{"fafsa_header_09.gif",
			BundleUtil.getImgText("fafsa_header_09", BundleUtil.ENGLISH),
			BundleUtil.getImgText("fafsa_header_09", BundleUtil.SPANISH)}, // FFOTW
		{"fafsa_header_09.gif",
			BundleUtil.getImgText("fafsa_header_09", BundleUtil.ENGLISH),
			BundleUtil.getImgText("fafsa_header_09", BundleUtil.SPANISH)}, // SFOTW
		{"fafsa_header_09.gif",
			BundleUtil.getImgText("fafsa_header_09", BundleUtil.ENGLISH),
			BundleUtil.getImgText("fafsa_header_09", BundleUtil.SPANISH)}, // RFOTW
		{"corrections_fafsa_header_lg.gif",
			BundleUtil.getImgText("corrections_fafsa_header", BundleUtil.ENGLISH),
			BundleUtil.getImgText("corrections_fafsa_header", BundleUtil.SPANISH)}, // COTW
		{"check_status_submitted_lg.gif",
			BundleUtil.getImgText("check_status_submitted", BundleUtil.ENGLISH),
			BundleUtil.getImgText("check_status_submitted", BundleUtil.SPANISH)},	// APPSTAT
		{"electronic_signatures_lg.gif",
			BundleUtil.getImgText("electronic_signatures", BundleUtil.ENGLISH),
			BundleUtil.getImgText("electronic_signatures", BundleUtil.SPANISH)}, // ESIG
		{"student_access.gif",
			BundleUtil.getImgText("student_access", BundleUtil.ENGLISH),
			BundleUtil.getImgText("student_access", BundleUtil.SPANISH)},	// SA
		{"faa_access_lg.gif",
			BundleUtil.getImgText("faa_access", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_access", BundleUtil.SPANISH)},// FAA
		{"worksheets_lg.gif",
			BundleUtil.getImgText("worksheets", BundleUtil.ENGLISH),
			BundleUtil.getImgText("worksheets", BundleUtil.SPANISH)},  // WKSHT
		{"federal_school_search_lg.gif",
			BundleUtil.getImgText("federal_school_search", BundleUtil.ENGLISH),
			BundleUtil.getImgText("federal_school_search", BundleUtil.SPANISH)}, // FSC
		{"worksheets_lg.gif",
			BundleUtil.getImgText("worksheets", BundleUtil.ENGLISH),
			BundleUtil.getImgText("worksheets", BundleUtil.SPANISH)}, // WSDRUG
		{"worksheets_lg.gif",
			BundleUtil.getImgText("worksheets", BundleUtil.ENGLISH),
			BundleUtil.getImgText("worksheets", BundleUtil.SPANISH)}, // ACG
		{"faa_fill_out_lg.gif",
			BundleUtil.getImgText("faa_fill_out", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_fill_out", BundleUtil.SPANISH)},  // FAA_FFOTW
		{"faa_fill_out_lg.gif",
			BundleUtil.getImgText("faa_fill_out", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_fill_out", BundleUtil.SPANISH)},     // FAA_RFOTW
		{"faa_corrections_lg.gif",
			BundleUtil.getImgText("faa_corrections", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_corrections", BundleUtil.SPANISH)},       // FAA_COTW
		{"adddelete_schoolcode_lg.gif",
			BundleUtil.getImgText("adddelete_schoolcode", BundleUtil.ENGLISH),
			BundleUtil.getImgText("adddelete_schoolcode", BundleUtil.SPANISH)},  // FSC Corrections
		{"faa_access.gif",
			BundleUtil.getImgText("faa_access", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_access", BundleUtil.SPANISH)}, // FAA_R2T4
		{"faa_isirrequest_lg.gif",
			BundleUtil.getImgText("faa_isirrequest", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_isirrequest", BundleUtil.SPANISH)},// FAA_ISIRREQ
		{"worksheets_lg.gif",
			BundleUtil.getImgText("worksheets", BundleUtil.ENGLISH),
			BundleUtil.getImgText("worksheets", BundleUtil.SPANISH)},	// SWKSHT
		{"worksheets_lg.gif",
			BundleUtil.getImgText("worksheets", BundleUtil.ENGLISH),
			BundleUtil.getImgText("worksheets", BundleUtil.SPANISH)},	// SWSDRUG
		{"faa_iatool.gif",
			BundleUtil.getImgText("faa_iatool", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_iatool", BundleUtil.SPANISH)},  // FAA_IATOOL
		{"fafsa_header_09.gif",
			BundleUtil.getImgText("fafsa_header_09", BundleUtil.ENGLISH),
			BundleUtil.getImgText("fafsa_header_09", BundleUtil.SPANISH)},  // SROTW
		{"faa_student_inquiry.gif",
			BundleUtil.getImgText("faa_student_inquiry", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_student_inquiry", BundleUtil.SPANISH)}, // FAA SA
		{"faa_verificationtool_lg.gif",
			BundleUtil.getImgText("faa_verificationtool", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_verificationtool", BundleUtil.SPANISH)}, // FAA VERIFWS
		{"faa_access_cps_online_lg.gif",
			BundleUtil.getImgText("faa_hscompletion", BundleUtil.ENGLISH),
			BundleUtil.getImgText("faa_hscompletion", BundleUtil.SPANISH)},// HS_COMPLETION
	};


	////////////////////////////////////////////////////////////
	//
	//  Application Type
	//
	/** apptype - Form FAFSA */
	public final static String APP_TYPE_FFOTW = "8";

	/** apptype - Spanish FAFSA  */
	public final static String APP_TYPE_SFOTW = "6";

	/** apptype - Renewal FAFSA */
	public final static String APP_TYPE_RFOTW = "7";

	/** apptype - FAFSA Corrections */
	public final static String APP_TYPE_COTW = "C";

	/** apptype - App Status Check */
	public final static String APP_TYPE_APPSTAT = "A";

	/** apptype - Electroic Signagure */
	public final static String APP_TYPE_ESIG= "E";

	/** apptype - Student Access */
	public final static String APP_TYPE_SA = "S";

	/** apptype - FAA Access */
	public final static String APP_TYPE_FAA = "F";

	/** apptype - Standalone worksheets */
	public final static String APP_TYPE_WKSHT = "W";

	/** apptype - Standalone federal school code lookup */
	public final static String APP_TYPE_FSC = "I";

	/** apptype - Standalone drug worksheet */
	public final static String APP_TYPE_WSDRUG = "U";

	/** apptype - Standalone ACG */
	public final static String APP_TYPE_ACG = "G";

	/** apptype - FAA FFOTW Entry, for internal use only */
	public final static String APP_TYPE_FAA_FFOTW = "f";

	/** apptype - FAA RFOTW Entry, for internal use only */
	public final static String APP_TYPE_FAA_RFOTW = "r";

	/** apptype - FAA COTW Entry, for internal use only */
	public final static String APP_TYPE_FAA_COTW = "c";

	/** apptype - FSC Corrections */
	public final static String APP_TYPE_FSCADD = "D";

	/** apptype - FAA Return of Title IV Funds */
	public final static String APP_TYPE_FAA_R2T4 = "T";

	/** apptype - FAA ISIR Request */
	public final static String APP_TYPE_FAA_ISIRREQ = "R";

	/** apptype - Standalone Spanish worksheet */
	public final static String APP_TYPE_SWKSHT = "X";

	/** apptype - Standalone Spanish drug worksheet */
	public final static String APP_TYPE_SWSDRUG = "u";

	/** apptype - FAA ISIR Analysis Tool */
	public final static String APP_TYPE_FAA_IATOOL  = "L";

	/** apptype - Spanish Renewal FAFSA */
	public final static String APP_TYPE_SROTW = "9";

	/** apptype - FAA Student Inquiry, for internal use only */
	public final static String APP_TYPE_FAA_SA = "s";

	/** apptype - FAA Verification Tool */
	public final static String APP_TYPE_FAA_VERIFWS = "V";

	/** apptype - HS Completion*/
	public final static String APP_TYPE_HS_COMPLETION = "Z";

	///////////////////////////////////////////////////
	//
	// HTML tags & page layouts
	//
	/** default HTML body tag*/
    public static String BODY_TAG =
        "<body onload=\"CheckBrowser();InitForm(null, '', '', '');\" id=\"<!--BODY_STYLE-->\">";

	/** anchor - skiplink */
	public static String ANCHOR_SKIPLINK = "<a name=\"skiplinks\"></a>";

	/** main header */
	public static String MAIN_HEADER = new StringBuffer()
	// fsa logo and skip link
	.append("<div id=\"logo\">")
	.append("<map name=\"logomap\">\n")
    .append("<area shape=\"rect\" coords=\"0,0,1,50\" href=\"#skiplinks\" alt=\"<!--MH_SKIPLINK-->\">\n")
    .append("</map>\n")
	.append("<!--MH_LOGO-->")
	.append("</div>\n")
	// page title (site name)
	.append("<div id=\"title\">")
	.append("<!--MH_TITLE-->")
	.append("</div>\n")
	// primary menu
	.append("<div id=\"menu1\">")
    .append("<div><!--MH_HOME--></div>")
	.append("<div><!--MH_HELP--></div>")
	.append("<div><!--MH_CONTACT--></div>")
	.append("<div><!--MH_FAQ--></div>")
	.append("<div><!--MH_CHAT--></div>")
	.append("</div>\n")
	.toString();

    /** main header app name */
    public static String MH_NAME = "";
    //new StringBuffer()
	//.append("<div class=\"appname\">\n")
	//.append("  <!--MH_NAME-->\n")
	//.append("</div>\n")
	//.toString();

	/** main header FSA log - English */
	public static String MH_LOGO_EN =
		"<img src=\"/fotw1011/images/fsa_logo.gif\" usemap=\"#logomap\" alt=\"" + BundleUtil.getImgText("fsa_logo", BundleUtil.ENGLISH) + "\" border=\"0\" />";

	/** main header FSA log - FAA */
	public static String MH_LOGO_FAA =
		"<img src=\"/fotw1011/images/fsa_logo.gif\" usemap=\"#logomap\" alt=\"" + BundleUtil.getImgText("fsa_logo", BundleUtil.ENGLISH) + "\" border=\"0\" />";

	/** main header FSA log - Spanish */
	public static String MH_LOGO_SP =
		"<img src=\"/fotw1011/images/fsa_logo.gif\" usemap=\"#logomap\" alt=\"" + BundleUtil.getImgText("fsa_logo", BundleUtil.SPANISH) + "\" border=\"0\" />";

	/** main header title/site name - English */
	public static String MH_TITLE_EN =
		"<img src=\"/fotw1011/images/fafsa_header.gif\" alt=\"" + BundleUtil.getImgText("fafsa_header", BundleUtil.ENGLISH) + "\" />";

	/** main header title/site name - FAA */
	public static String MH_TITLE_FAA =
		"<img src=\"/fotw1011/images/faaaccess_header.gif\" alt=\"" + BundleUtil.getImgText("faaaccess_header", BundleUtil.ENGLISH) + "\" />";

	/** main header title/site name - Spanish */
	public static String MH_TITLE_SP =
		"<img src=\"/es_ES/fotw1011/images/fafsa_header.gif\" alt=\"" + BundleUtil.getImgText("fafsa_header", BundleUtil.SPANISH) + "\" />";

    /** main header home - Some FAA applications */
    public static String MH_HOME_FAA = "<a href=\"JavaScript:GotoHome()\">Home</a>";

    /** main header help - English */
    public static String MH_HELP_EN = "<a href=\"JavaScript:IndexPage('help.htm?szCYCLE=2010', 'en_US')\">Help</a>";

    /** main header help - FAA */
    public static String MH_HELP_FAA = "<a href=\"JavaScript:Help('faatoc00.htm', 'en_US')\">Help</a>";

	/** main header help - Spanish */
    public static String MH_HELP_SP = "<a href=\"JavaScript:IndexPage('help.htm?szCYCLE=2010', 'es_ES')\">Ayuda</a>";

	/** main header contact - English */
	public static String MH_CONTACT_EN = "<a href=\"JavaScript:IndexPage('contact.htm', 'en_US')\">Contact Us</a>";

	/** main header contact - Spanish */
	public static String MH_CONTACT_SP = "<a href=\"JavaScript:IndexPage('contact.htm', 'es_ES')\">Contáctenos</a>";

	/** main header FAQ - English */
	public static String MH_FAQ_EN = "<a href=\"JavaScript:Help('fotwfaq00.htm', 'en_US')\">FAQs</a>";

	/** main header FAQ - FAA */
	public static String MH_FAQ_FAA = "<a href=\"JavaScript:Help('faafaq00.htm', 'en_US')\">FAQs</a>";

	/** main header FAQ - Sanish */
	public static String MH_FAQ_SP = "<a href=\"JavaScript:Help('fotwfaq00.htm', 'es_ES')\">Preguntas Frecuentes</a>";

	/** main header Chat - English */
	public static String MH_CHAT_EN = "<a href=\"JavaScript:OpenCust('en_US')\">Live Help</a>";

	/** main header Chat - Spanish */
	public static String MH_CHAT_SP = "<a href=\"JavaScript:OpenCust('es_ES')\">Apoyo al Usuario en Vivo</a>";

	/** main header ED Seal - English */
	public static String MH_SEAL_EN = "";

	/** main header ED Seal - Spanish */
	public static String MH_SEAL_SP = "";

	/** alt text for skiplink usemap - English */
	public static String MH_SKIPLINK_EN = "Skip the navigation bars";

	/** alt text for skiplink usemap - Spanish */
	public static String MH_SKIPLINK_SP = "Saltar las barras de navegación";

	/** side navigation bar */
	public static String SIDE_BAR = new StringBuffer()
	.append("<div id=\"mmenu-fafsa\">\n")
    .append("<!--SB_STEP1-->\n")
    .append("<!--SB_STEP2-->\n")
    .append("<!--SB_STEP3-->\n")
    .append("<!--SB_STEP4-->\n")
    .append("<!--SB_STEP5-->\n")
    .append("<!--SB_STEP6-->\n")
    .append("<!--SB_STEP7-->\n")
    .append("<!--SB_STEP8-->\n")
	.append("</div>\n")
	.toString();
	
	/** currently visiting menu */
	public static String SB_MENU_ON =
		"<div><a class=\"menuitem-on\" href=\"javascript:Step(<!--SB_STEP#-->);\"><div class=\"noprint\"><!--SB_STEP_NOPRINT--></div><span><strong><!--SB_STEP#--></strong><!--SB_STEP_LABEL--></span></a></div>";
	/** not visited menu */
	public static String SB_MENU_NA =
		"<div class=\"menuitem-dis\"><span><strong><div class=\"noprint\"><!--SB_STEP_NOPRINT--></div><!--SB_STEP#--></strong><!--SB_STEP_LABEL--></span></div>";
	/** visited menu */
	public static String SB_MENU_OFF =
		"<div><a class=\"menuitem-off\" href=\"javascript:Step(<!--SB_STEP#-->)\"><div class=\"noprint\"><!--SB_STEP_NOPRINT--></div><span><strong><!--SB_STEP#--></strong><!--SB_STEP_LABEL--></span></a></div>";
	
	/** index of side bar image alt text */
	public final static int SB_ALT = 0;

	/** index of side bar image currently visiting */
	public final static int SB_VISITING = 1;

	/** index of side bar image not visited */
	public final static int SB_NOT_VISITED = 2;

	/** index of side bar image visited */
	public final static int SB_VISITED = 3;
	
	/** side bar image file names & alt-text - English */
	public static String[] SB_STEP_LABELS_EN =
	{
		"0",
		BundleUtil.getImgText("fafsa_step1_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step2_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step3_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step4_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step5_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step6_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step7_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step8_header", BundleUtil.ENGLISH),
	};

	/** side bar image file names & alt-text - Spanish */
	public static String[] SB_STEP_LABELS_SP =
	{
		"0",
		BundleUtil.getImgText("fafsa_step1_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step2_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step3_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step4_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step5_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step6_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step7_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step8_header", BundleUtil.SPANISH),
	};
	
	/** Side bar images alt text prefix - English */
	public static String[] SB_STEP_NOPRINT_EN = {
	  "", 				   // SB_ALT
	  "Current Step ",     // SB_VISITING
	  "Not Visited Step ", // SB_NOT_VISITED
	  "Visited Step "      // SB_VISITED
	};

	/** Side bar images alt text prefix - Spanish */
	public static String[] SB_STEP_NOPRINT_SP = {
	  "", 				       // SB_ALT
	  "Actual Paso ",          // SB_VISITING
	  "Aún No Visitado Paso ", // SB_NOT_VISITED
	  "Ya Visitado Paso "      // SB_VISITED
	};

	/** top navigation bar */
	/*
		<div id="menu">
			<!-- Left nav...err now on top -->
			<div id="navcontainer2">
				<ul id="navlist2">	
	*/
	public static String TOP_BAR = new StringBuffer()
	.append("<div id=\"menu\">\n")
	.append("<div id=\"navcontainer2\">\n")
	.append("<ul id=\"navlist2\">\n")
	.append("<!--TB_STEP1-->\n")
	.append("<!--TB_STEP2-->\n")
	.append("<!--TB_STEP3-->\n")
	.append("<!--TB_STEP4-->\n")
	.append("<!--TB_STEP5-->\n")
	.append("<!--TB_STEP6-->\n")
	.append("<!--TB_STEP7-->\n")
	.append("</ul>\n")
	.append("</div>\n")
	.append("</div>\n")
	.toString();
	
	/** currently visiting menu */
	public static String TB_MENU_ON =
		"<li><a class=\"active\" href=\"javascript:Step(<!--TB_STEP#-->)\"><!--TB_STEP_LABEL--></a></li>";
	/** not visited menu */
	public static String TB_MENU_NA =
		"<li><span><!--TB_STEP_LABEL--></span></li>";
	/** visited menu */
	public static String TB_MENU_OFF =
		"<li><a class=\"accessed\" href=\"javascript:Step(<!--TB_STEP#-->)\"><!--TB_STEP_LABEL--></a></li>";

	/** finish page */
	public static String TB_MENU_FINISH_ON =
		"<li><a class=\"active\"><!--TB_STEP_LABEL--></a></li>";
	public static String TB_MENU_FINISH_OFF =
		"<li><a class=\"accessed\"><!--TB_STEP_LABEL--></a></li>";
	
	/** index of top bar image alt text */
	public final static int TB_ALT = 0;

	/** index of top bar image currently visiting */
	public final static int TB_VISITING = 1;

	/** index of top bar image not visited */
	public final static int TB_NOT_VISITED = 2;

	/** index of top bar image visited */
	public final static int TB_VISITED = 3;
	
	/** Top bar image file names & alt-text - English */
	public static String[] TB_STEP_LABELS_EN =
	{
		"0",
		BundleUtil.getImgText("fafsa_step1_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step2_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step3_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step4_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step5_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step6_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step7_header", BundleUtil.ENGLISH),
		BundleUtil.getImgText("fafsa_step8_header", BundleUtil.ENGLISH),
	};

	/** Top bar image file names & alt-text - Spanish */
	public static String[] TB_STEP_LABELS_SP =
	{
		"0",
		BundleUtil.getImgText("fafsa_step1_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step2_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step3_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step4_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step5_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step6_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step7_header", BundleUtil.SPANISH),
		BundleUtil.getImgText("fafsa_step8_header", BundleUtil.SPANISH),
	};

	/** Top bar images alt text prefix - English */
	public static String[] TB_STEP_NOPRINT_EN = {
	  "", 				   // TB_ALT
	  "Current Step ",     // TB_VISITING
	  "Not Visited Step ", // TB_NOT_VISITED
	  "Visited Step "      // TB_VISITED
	};

	/** Top bar images alt text prefix - Spanish */
	public static String[] TB_STEP_NOPRINT_SP = {
	  "", 				       // TB_ALT
	  "Actual Paso ",          // TB_VISITING
	  "Aún No Visitado Paso ", // TB_NOT_VISITED
	  "Ya Visitado Paso "      // TB_VISITED
	};
	
	////////////////////////////////////////////////////////////
	//
	//  FOTW subsection images/names based on step number
	//
	/* FAFSA step images/names */
	public static String[][] SH_STEP_IMAGES_NAMES_EN = {
		{SH_IMAGES_NAMES[FFOTW][0], SH_IMAGES_NAMES[FFOTW][1]}, // step 0
		{"fafsa_step1_header.gif", SB_STEP_LABELS_EN[1]}, // step 1
		{"fafsa_step2_header.gif", SB_STEP_LABELS_EN[2]}, // step 2
		{"fafsa_step3_header.gif", SB_STEP_LABELS_EN[3]}, // step 3
		{"fafsa_step4_header.gif", SB_STEP_LABELS_EN[4]}, // step 4
		{"fafsa_step5_header.gif", SB_STEP_LABELS_EN[5]}, // step 5
		{"fafsa_step6_header.gif", SB_STEP_LABELS_EN[6]}, // step 6
		{"fafsa_step7_header.gif", SB_STEP_LABELS_EN[7]}, // step 7
		{"fafsa_step8_header.gif", SB_STEP_LABELS_EN[8]}, // step 8
	};

	/* Spanish FAFSA step images/names */
	public static String[][] SH_STEP_IMAGES_NAMES_SP = {
		{SH_IMAGES_NAMES[SFOTW][0], SH_IMAGES_NAMES[SFOTW][1]}, // step 0
		{"fafsa_step1_header.gif", SB_STEP_LABELS_SP[1]}, // step 1
		{"fafsa_step2_header.gif", SB_STEP_LABELS_SP[2]}, // step 2
		{"fafsa_step3_header.gif", SB_STEP_LABELS_SP[3]}, // step 3
		{"fafsa_step4_header.gif", SB_STEP_LABELS_SP[4]}, // step 4
		{"fafsa_step5_header.gif", SB_STEP_LABELS_SP[5]}, // step 5
		{"fafsa_step6_header.gif", SB_STEP_LABELS_SP[6]}, // step 6
		{"fafsa_step7_header.gif", SB_STEP_LABELS_SP[7]}, // step 7
		{"fafsa_step8_header.gif", SB_STEP_LABELS_SP[8]}, // step 8
	};

	/** subheader template*/
	public static String SUBHEADER = new StringBuffer()
	.append("<div id=\"<!--SH_SIDE-->\">\n")
	.append("<div id=\"content\">\n\n")
	.append(ANCHOR_SKIPLINK)
	.append("<h1 align=\"left\">\n")
	.append("<img src=\"<!--SH_HEADER_IMG-->\"  border=\"0\" alt=\"<!--SH_HEADER_ALT-->\" />")
	.append("</h1>\n")
	.append("<div align=\"left\">")
	.append("<!--SH_HELP-->\n") // help button for FAA only
	.append("<span class=\"redalert\"><!--SH_SAVEMSG--></span>")
	.append("</div>\n")
	.append("<div id=\"copy\">\n\n")
	.toString();

	/** subheader - Help - FAA*/
	public static String SH_HELP_FAA = new StringBuffer()
	.append("<a href=\"JavaScript:Help('").append("<!--SH_HELP_FILE-->").append("');\">")
	.append("<img style=\"padding-top:10px;\" onmouseover=\"imgIn(this);\" onmouseout=\"imgOut(this);\" id=\"needhelp\" ")
	.append("src=\"")
	.append(BundleUtil.getButtonSrc("btn_needhelp", "off", "1", BundleUtil.ENGLISH))
	.append("\" alt=\"")
	.append(BundleUtil.getImgText("btn_needhelp", BundleUtil.ENGLISH))
	.append("\" /></a>&nbsp;&nbsp; ")
	.toString();

    /** subfooter template */
    public static String SUBFOOTER = new StringBuffer()
	.append("</div><!-- end copy -->\n\n")    // moved up here to place prev/next inside #content
	.append("<div id=\"prevnext\">\n")
    .append("<!--SF_EXCEPTION-->\n")
    .append("<!--SF_PREVIOUS-->\n")
    .append("<!--SF_NEXT-->\n")
    .append("</div>")
	.toString();

	public static String SF_PREVIOUS_EN =
		"<a href=\"JavaScript:PrevPage();\" id=\"anchorprev\"><img onmouseover=\"imgIn(this, 'off');\" onmouseout=\"imgOut(this, 'off');\" id=\"prev\" " +
			"src=\"" + BundleUtil.getButtonSrc("btn_prev", "off", "", BundleUtil.ENGLISH) +
			"\" alt=\"" + BundleUtil.getImgText("btn_prev", BundleUtil.ENGLISH) + "\" /></a>";

	/** subfooter Next button - English */
	public static String SF_NEXT_EN =
		"<a href=\"JavaScript:NextPage();\" id=\"anchornext\"><img onmouseover=\"imgIn(this, 'on');\" onmouseout=\"imgOut(this, 'on');\" id=\"next\" " +
		"src=\"" + BundleUtil.getButtonSrc("btn_next", "on", "", BundleUtil.ENGLISH) +
		"\" alt=\"" + BundleUtil.getImgText("btn_next", BundleUtil.ENGLISH) + "\" /></a>";

	/** subfooter Run Final Check button - English */
	public static String SF_FINALCHK_EN =
		"<a href=\"JavaScript:FinalChk();\" id=\"anchorcheckforerrors\"><img onmouseover=\"imgIn(this);\" onmouseout=\"imgOut(this);\" id=\"checkforerrors\" " +
		"src=\"" + BundleUtil.getButtonSrc("btn_checkforerrors", "off", "", BundleUtil.ENGLISH) +
		"\" alt=\"" + BundleUtil.getImgText("btn_checkforerrors", BundleUtil.ENGLISH) + "\" /></a>";

    /** subfooter Previous button - FAA */
	public static String SF_PREVIOUS_FAA =
		"<a href=\"JavaScript:PrevPage();\"><img onmouseover=\"imgIn(this, 'off');\" onmouseout=\"imgOut(this, 'off');\" id=\"prev\" " +
		"src=\"" + BundleUtil.getButtonSrc("btn_prev", "off", "1", BundleUtil.ENGLISH) +
		"\" alt=\"" + BundleUtil.getImgText("btn_prev", BundleUtil.ENGLISH) + "\" /></a>";

	/** subfooter Next button - FAA */
	public static String SF_NEXT_FAA =
		"<a href=\"JavaScript:NextPage();\"><img onmouseover=\"imgIn(this, 'on');\" onmouseout=\"imgOut(this, 'on');\" id=\"next\" " +
		"src=\"" + BundleUtil.getButtonSrc("btn_next", "on", "1", BundleUtil.ENGLISH) +
		"\" alt=\"" + BundleUtil.getImgText("btn_next", BundleUtil.ENGLISH) + "\" /></a>";

	/** subfooter Run Final Check button - FAA */
	public static String SF_FINALCHK_FAA =
		"<a href=\"JavaScript:FinalChk();\"><img onmouseover=\"imgIn(this);\" onmouseout=\"imgOut(this);\" id=\"calculatefc\" " +
		"src=\"" + BundleUtil.getButtonSrc("btn_calculatefc", "off", "1", BundleUtil.ENGLISH) +
		"\" alt=\"" + BundleUtil.getImgText("btn_calculatefc", BundleUtil.ENGLISH) + "\" /></a>";

	/** subfooter Previous button - Spanish */
	public static String SF_PREVIOUS_SP =
		"<a href=\"JavaScript:PrevPage();\" id=\"anchorprev\"><img onmouseover=\"imgIn(this, 'off');\" onmouseout=\"imgOut(this, 'off');\" id=\"prev\" " +
		"src=\"" + BundleUtil.getButtonSrc("btn_prev", "off", "", BundleUtil.SPANISH) +
		"\" alt=\"" + BundleUtil.getImgText("btn_prev", BundleUtil.SPANISH) + "\" /></a>";

	/** subfooter Next button - Spanish */
	public static String SF_NEXT_SP =
		"<a href=\"JavaScript:NextPage();\" id=\"anchornext\"><img onmouseover=\"imgIn(this, 'on');\" onmouseout=\"imgOut(this, 'on');\" id=\"next\" " +
		"src=\"" + BundleUtil.getButtonSrc("btn_next", "on", "", BundleUtil.SPANISH) +
		"\" alt=\"" + BundleUtil.getImgText("btn_next", BundleUtil.SPANISH) + "\" /></a>";

	/** subfooter Run Final Check button - Spanish */
	public static String SF_FINALCHK_SP =
		"<a href=\"JavaScript:FinalChk();\" id=\"anchorcheckforerrors\"><img onmouseover=\"imgIn(this);\" onmouseout=\"imgOut(this);\" id=\"checkforerrors\" " +
		"src=\"" + BundleUtil.getButtonSrc("btn_checkforerrors", "off", "", BundleUtil.SPANISH) +
		"\" alt=\"" + BundleUtil.getImgText("btn_checkforerrors", BundleUtil.SPANISH) + "\" /></a>";

	/** bottom bar */
	public static String BOTTOMBAR = new StringBuffer()
	//.append("</div><!-- end copy -->\n")
	.append("<div id=\"<!--BB_STYLE-->\">\n")
	.append("<div class=\"sublinks_left\">\n")
	.append("  <!--BB_HELP-->\n")
	.append("</div>")
	.append("<div class=\"sublinks_right\">")
	.append("  <!--BB_EXCEPTION-->\n")
	.append("  <!--BB_SAVE-->\n")
	.append("  <!--BB_SUMMARY-->\n")
	.append("  <!--BB_FSAPORTAL-->\n")
	.append("  <!--BB_RETURNTOLOGIN-->\n")
	.append("  <!--BB_FAAMENU-->\n")
	.append("  <!--BB_EXIT-->\n")
	.append("</div>\n")
	.append("</div>\n")
	.append("</div><!-- end content -->\n")
	//.append("</div><!-- end rightside or leftside -->\n")
	.toString();

	/** exception button template */
	public static String BUTTON_TPL =
		"<a href=\"javascript:<!--BB_JS_FUNC-->;\"><img onmouseover=\"imgIn(this, '<!--BB_STATE-->');\" onmouseout=\"imgOut(this, '<!--BB_STATE-->');\" id=\"<!--BB_ID-->\" src=\"<!--BB_IMG-->\" alt=\"<!--BB_ALT-->\" /></a>";

	/** index of sublink button*/
    public static int BB_SAVE_EN = 0;
	/** index of sublink button*/
    public static int BB_SUMMARY_EN = 1;
	/** index of sublink button*/
    public static int BB_EXIT_EN = 2;
	/** index of sublink button*/
    public static int BB_EXIT_NO_SAVE_EN = 3;
	/** index of sublink button*/
    public static int BB_EXIT_NO_WARNING_EN = 4;

	/** index of sublink button*/
    public static int BB_SAVE_SP = 5;
	/** index of sublink button*/
    public static int BB_SUMMARY_SP = 6;
	/** index of sublink button*/
    public static int BB_EXIT_SP = 7;
	/** index of sublink button*/
    public static int BB_EXIT_NO_SAVE_SP = 8;
	/** index of sublink button*/
    public static int BB_EXIT_NO_WARNING_SP = 9;

	/** index of sublink button*/
    public static int BB_SAVE_FAA = 10;
	/** index of sublink button*/
    public static int BB_SUMMARY_FAA = 11;
	/** index of sublink button*/
    public static int BB_EXIT_FAA = 12;
	/** index of sublink button*/
    public static int BB_EXIT_NO_SAVE_FAA = 13;
	/** index of sublink button*/
    public static int BB_EXIT_NO_WARNING_FAA = 14;

    /** index of sublink button*/
    public static int BB_FAAMENU = 15;
	/** index of sublink button*/
    public static int BB_FAAMENU_NO_SAVE = 16;

	/** index of sublink button*/
    public static int BB_HELP_EN = 17;
	/** index of sublink button*/
    public static int BB_HELP_SP = 18;
	/** index of sublink button*/
    public static int BB_HELP_FAA = 19;

    /** index of sublink button*/
    public static int BB_RETURNTOLOGIN_FAA = 20;
    /** index of sublink button*/
    public static int BB_RETURNTOLOGIN_NO_SAVE_FAA = 21;

    /** Information of the Sublink Buttons */
    public static String[][] SUBLINK_BUTTONS =
    {
    	// 0 - 4: sudent English
    	{"Save()", "save", BundleUtil.getButtonSrc("btn_save", "off", "", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_save", BundleUtil.ENGLISH)},
		{"Summary()", "fafsa_summary", BundleUtil.getButtonSrc("btn_fafsa_summary", "off", "", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_fafsa_summary", BundleUtil.ENGLISH)},
		{"ExitForm()", "exit",  BundleUtil.getButtonSrc("btn_exit", "off", "", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_exit", BundleUtil.ENGLISH)},
		{"ExitForm(1)", "exit", BundleUtil.getButtonSrc("btn_exit", "off", "", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_exit", BundleUtil.ENGLISH)},
		{"FafsaHome()", "exit", BundleUtil.getButtonSrc("btn_exit", "off", "", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_exit", BundleUtil.ENGLISH)},

		// 5-9: student Spanish
    	{"Save()", "save", BundleUtil.getButtonSrc("btn_save", "off", "", BundleUtil.SPANISH), BundleUtil.getImgText("btn_save", BundleUtil.SPANISH)},
		{"Summary()", "fafsa_summary", BundleUtil.getButtonSrc("btn_fafsa_summary", "off", "", BundleUtil.SPANISH), BundleUtil.getImgText("btn_fafsa_summary", BundleUtil.SPANISH)},
		{"ExitForm()", "exit",  BundleUtil.getButtonSrc("btn_exit", "off", "", BundleUtil.SPANISH), BundleUtil.getImgText("btn_exit", BundleUtil.SPANISH)},
		{"ExitForm(1)", "exit", BundleUtil.getButtonSrc("btn_exit", "off", "", BundleUtil.SPANISH), BundleUtil.getImgText("btn_exit", BundleUtil.SPANISH)},
		{"FafsaHome()", "exit", BundleUtil.getButtonSrc("btn_exit", "off", "", BundleUtil.SPANISH), BundleUtil.getImgText("btn_exit", BundleUtil.SPANISH)},

		//10-14: FAA
    	{"Save()", "save", BundleUtil.getButtonSrc("btn_save", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_save", BundleUtil.ENGLISH)},
		{"Summary()", "fafsa_summary", BundleUtil.getButtonSrc("btn_fafsa_summary", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_fafsa_summary", BundleUtil.ENGLISH)},
		{"FAAExit(true)", "exit", BundleUtil.getButtonSrc("btn_exit", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_exit", BundleUtil.ENGLISH)},
		{"FAAExit(false)", "exit", BundleUtil.getButtonSrc("btn_exit", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_exit", BundleUtil.ENGLISH)},
		{"FafsaHome()", "exit", BundleUtil.getButtonSrc("btn_exit", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_exit", BundleUtil.ENGLISH)},
		// 15-16: FAA only
    	{"FAAMenu(true)", "sublink_returntofaamenu", BundleUtil.getButtonSrc("btn_sublink_returntofaamenu", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_sublink_returntofaamenu", BundleUtil.ENGLISH)},
		{"FAAMenu(false)", "sublink_returntofaamenu", BundleUtil.getButtonSrc("btn_sublink_returntofaamenu", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_sublink_returntofaamenu", BundleUtil.ENGLISH)},

		// 17-19: Need Help? button
		{"Help('<!--BB_HELP_FILE-->')", "needhelp", BundleUtil.getButtonSrc("btn_needhelp", "off", "", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_needhelp", BundleUtil.ENGLISH)},
		{"Help('<!--BB_HELP_FILE-->')", "needhelp", BundleUtil.getButtonSrc("btn_needhelp", "off", "", BundleUtil.SPANISH), BundleUtil.getImgText("btn_needhelp", BundleUtil.SPANISH)},
		{"Help('<!--BB_HELP_FILE-->')", "needhelp", BundleUtil.getButtonSrc("btn_needhelp", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_needhelp", BundleUtil.ENGLISH)},

		// 20-21: Return to Login, FAA only
    	{"ReturnToLogin(true)", "anotherstudent", BundleUtil.getButtonSrc("btn_anotherstudent", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_anotherstudent", BundleUtil.ENGLISH)},
		{"ReturnToLogin(false)", "anotherstudent", BundleUtil.getButtonSrc("btn_anotherstudent", "off", "1", BundleUtil.ENGLISH), BundleUtil.getImgText("btn_anotherstudent", BundleUtil.ENGLISH)},

    };

	/** main footer for application pages */
	public static String FOOTER_APP = new StringBuffer()
	.append("</div><!-- end rightside or leftside -->\n\n")
	.append("<div id=\"footer\">\n")
	.append("<div class=\"sideleft\">\n")
	.append("<a href=\"javascript:popWindow('<!--MF_PRIVACY_URL-->',800,600,1);\"><!--MF_PRIVACY--></a>")
	.append("<!--MF_EXCEPTION-->")
	.append("</div>\n")
	// CR 3902 - Add WebTrend tagging - R.L. 7/16/08
	.append("<script type=\"text/javascript\">\n")
	.append("//<!--\n")
	.append("	AddWTBaseTag();\n")
  	.append("//-->\n")
	.append("</script>\n")
	.append("</div>\n")
	.toString();

	/** main footer for index pages */
	public static String FOOTER_INDEX = new StringBuffer()
	.append("</div><!-- end rightside or leftside -->\n\n")
	.append("<div id=\"footer\">\n")
	.append("<div class=\"sideleft\">\n")
	.append("<a href=\"javascript:popWindow('http://www.ed.gov/policy/gen/leg/foia/foiatoc.html',800,600,1);\">FOIA</a>")
	.append(" | ")
	.append("<a href=\"javascript:popWindow('<!--MF_PRIVACY_URL-->',800,600,1);\"><!--MF_PRIVACY--></a>")
	.append(" | ")
	.append("<a href=\"javascript:popWindow('<!--MF_SECURITY_URL-->',800,600,1);\"><!--MF_SECURITY--></a>")
	.append(" | ")
	.append("<a href=\"javascript:popWindow('<!--MF_NOTICES_URL-->',800,600,1);\"><!--MF_NOTICES--></a>")
	.append("<!--MF_EXCEPTION-->")
	.append("</div>\n")
	.append("<div class=\"sideright\">\n")
	.append("<a href=\"javascript:popWindow('http://www.whitehouse.gov',800,600,1);\">WhiteHouse.gov</a>")
	.append(" | ")
	.append("<a href=\"javascript:popWindow('<!--MF_USAGOV_URL-->',800,600,1);\"><!--MF_USAGOV--></a>")
	.append(" | ")
	.append("<a href=\"javascript:popWindow('http://www.ed.gov',800,600,1);\">ED.gov</a>")
	.append("</div>\n")

	// CR 3902 - Add WebTrend tagging - R.L. 7/16/08
	.append("<script type=\"text/javascript\">\n")
	.append("//<!--\n")
	.append("	AddWTBaseTag();\n")
  	.append("//-->\n")
	.append("</script>\n")

	.append("</div>\n")
	.toString();


	/** error icon */
	public static String ERROR_ICON =
		"<img src=\"/fotw1011/images/error_icon.gif\" width=\"20\" height=\"17\" alt=\"Error Found\" />";

	/** error icon - English */
	public static String ERROR_ICON_EN =
		"<img src=\"/es_ES/fotw1011/images/error_icon.gif\" width=\"20\" height=\"17\" alt=\"Error Found\" />";

	public static String ERROR_ICON_X_EN =
		"<img src=\"/fotw1011/images/error_icon_x.gif\" width=\"21\" height=\"21\" alt=\"Error Found\" />";

	public static String ERROR_ICON_X_EN_SHOW =
		"<div id=\"errorIcon_%s\" name=\"error_icon\" class=\"error_icon\">" + ERROR_ICON_X_EN + "</div>";

	public static String ERROR_ICON_X_EN_HIDE =
		"<div id=\"errorIcon_%s\" name=\"error_icon\" class=\"error_icon\" style=\"display: none;\">" + ERROR_ICON_X_EN + "</div>";

	/** error icon - English */
	public static String ERROR_ICON_SP =
		"<img src=\"/es_ES/fotw1011/images/error_icon.gif\" width=\"20\" height=\"17\" alt=\"Error Hallado\" />";

	public static String ERROR_ICON_X_SP =
		"<img src=\"/fotw1011/images/error_icon_x.gif\" width=\"21\" height=\"21\" alt=\"Error Hallado\" />";
	
	public static String ERROR_ICON_X_SP_SHOW =
		"<div id=\"errorIcon_%s\" name=\"error_icon\" class=\"error_icon\">" + ERROR_ICON_X_SP + "</div>";

	public static String ERROR_ICON_X_SP_HIDE =
		"<div id=\"errorIcon_%s\" name=\"error_icon\" class=\"error_icon\" style=\"display: none;\">" + ERROR_ICON_X_SP + "</div>";

	public static String CONFIRM_LINK_EN = "<a onclick=\"confirmEditOk('confirmLink_%s', '%s'); return false;\" href=\"#\">OK</a>";
	
	public static String CONFIRM_LINK_EN_SHOW = 
		"<div id=\"confirmLink_%s\" name=\"confirm_link\" class=\"confirm_link\">" + CONFIRM_LINK_EN + "</div>";
	
	public static String CONFIRM_LINK_EN_HIDE = 
		"<div id=\"confirmLink_%s\" name=\"confirm_link\" class=\"confirm_link\" style=\"display: none;\">" + CONFIRM_LINK_EN + "</div>";
	
	public static String CONFIRM_LINK_SP = "<a onclick=\"confirmEditOk('confirmLink_%s', '%s');\" href=\"#\">OK</a>";
	
	public static String CONFIRM_LINK_SP_SHOW = 
		"<div id=\"confirmLink_%s\" name=\"confirm_link\" class=\"confirm_link\">" + CONFIRM_LINK_SP + "</div>";
	
	public static String CONFIRM_LINK_SP_HIDE = 
		"<div id=\"confirmLink_%s\" name=\"confirm_link\" class=\"confirm_link\" style=\"display: none;\">" + CONFIRM_LINK_SP + "</div>";
	
	public static String CONFIRM_HIDDEN_NAME = "szCONFIRMXX";
	
	////////////////////////////////////////////////////////////
	//
	// For some common dropdown options

	/**Yes/No Dropdown - English */
	String[][] DROPDOWN_YES_NO_EN = {
		{"",  BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.ENGLISH)},
		{"1", BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_YESNO + "1", BundleUtil.ENGLISH)},
		{"2", BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_YESNO + "2", BundleUtil.ENGLISH)}
	};

	/**Yes/No Dropdown - Spanish */
	String[][] DROPDOWN_YES_NO_SP = {
		{"",  BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.SPANISH)},
		{"1", BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_YESNO + "1", BundleUtil.SPANISH)},
		{"2", BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_YESNO + "2", BundleUtil.SPANISH)}
	};

	/**Yes/No Dropdown - FAA */
	String[][] DROPDOWN_YES_NO_FAA = {
		{"",  BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_SELECT, BundleUtil.ENGLISH)},
		{"1", "1 - " + BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_YESNO + "1", BundleUtil.ENGLISH)},
		{"2", "2 - " + BundleUtil.getFieldLabel(BundleUtil.OPTION_KEY_YESNO + "2", BundleUtil.ENGLISH)}
	};

	/** State dropdown type - full*/
	public static final int STATE_TYPE_FULL = 1;
	/** State dropdown type - residence*/
	public static final int STATE_TYPE_RESIDENCE = 2;
	/** State dropdown type - driver*/
	public static final int STATE_TYPE_DRIVE = 3;
	/** State dropdown type - school*/
	public static final int STATE_TYPE_SCHOOL = 4;
	/** State dropdown type - ACG*/
	public static final int STATE_TYPE_ACG = 5;
	
	/* JSON error format */
	public static final String JSON_ERRORS_BEGIN = "{\"errorData\":[";
	public static final String JSON_ERRORS_DATA = "[\"%s\",\"%s\"],";
	public static final String JSON_ERRORS_END = "]}";
}
