package fotw1011;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GeneralPackage.AData;
import GeneralPackage.AException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import common.AFOTWLog;
import common.AFOTWSessionValues;

import fotw1011.common.AFOTWData;
import fotw1011.common.AFOTWList;
import fotw1011.common.AFOTWModule;
import fotw1011.common.AFOTWValid;
import fotw1011.common.AFOTWValidException;
import fotw1011.tags.BundleUtil;
import fotw1011.tags.JSPUtil;
import fotw1011.tags.SUBJSPUtil;
import fotw1011.tags.TagUtil;
import fotw1011.tags.URLBuilder;

/**
 * @author luecbr
 *
 */

/**
 * @author luecbr
 *
 */
/**
 * @author luecbr
 *
 */
public class AJAXMethods {
    
	protected          AFOTWValid		m_clValid         = null;
	protected          AFOTWList		m_clFieldsList    = null;
	protected          boolean		m_bDebug          = false;

	protected          Vector<?>           m_clModules       = null;
	protected          Vector<?>           m_clModuleSkipIdx = null;
	protected          Vector <?>         m_clModuleSecIdx  = null;
	
	public static final String CONTENT_HEADER_TEXT = "text/html;charset=UTF-8";
	public static final String CONTENT_HEADER_JSON = "application/json;charset=UTF-8";



    /**
     * @param valid
     * @param fieldsList
     * @param debug
     * @param modules
     * @param moduleSkipIdx
     * @param moduleSecIdx
     */
    public AJAXMethods(AFOTWValid valid, AFOTWList fieldsList,
		boolean debug, Vector<?> modules, Vector<?> moduleSkipIdx,
		Vector<?> moduleSecIdx) {
	    m_clValid = valid;
	    m_clFieldsList = fieldsList;
	    m_bDebug = debug;
	    m_clModules = modules;
	    m_clModuleSkipIdx = moduleSkipIdx;
	    m_clModuleSecIdx = moduleSecIdx;
	}


    //------------------------------------------
    /**
     * runAJAX
     *
     * @param clRequest
     * @param clResponse
     * @param values
     * @throws IOException
     * @throws AException
     *
     * @return void
     */
    //------------------------------------------
    public void runAJAX(HttpServletRequest clRequest, HttpServletResponse clResponse, AFOTWSessionValues values) throws IOException, AException

    {
    	TagUtil.setDataForJSP(true,clRequest,clResponse, m_clFieldsList,values);
    	
		if(clRequest.getParameter("ajax").equals("azsntedit"))
		    {
				runAZSNTEdit(clRequest,clResponse, values); 
		    }
		    else if(clRequest.getParameter("ajax").equals("searchSchool"))
		    {
		    	runSearchSchool(clRequest,clResponse, values); 
		    }
		    else if(clRequest.getParameter("ajax").equals("addSchool"))
		    {	
		    	runAddSchool(clRequest,clResponse, values); 
		    }
		    else if(clRequest.getParameter("ajax").equals("acgprog"))
		    {
		    	runACGProg(clRequest,clResponse, values); 
		    }
		    else if(clRequest.getParameter("ajax").equals("drugResult"))
		    {
		    	runDrugResult(clRequest,clResponse, values); 
		    }
		    else if(clRequest.getParameter("ajax").equals("signStudent"))
		    {
		    	runSignStudent(clRequest,clResponse, values); 
		    }
		    else if(clRequest.getParameter("ajax").equals("signParent"))
		    {
		    	runSignParent(clRequest,clResponse, values); 
		    }
		    else if(clRequest.getParameter("ajax").equals("fatal"))
		    {
		    	throw new AException("ajax fatal error",AException.SEVERITY_FATAL);
		    }
		    else
		    {
		    	AFOTWLog.log("other stuff - AJAX Call() responded", AFOTWLog.ERROR, this.getClass().getName());
		    	setFatalErrorResponse(clResponse);
		    }
    }
   
    //------------------------------------------
    /**
     * runSignParent
     *
     * @param clRequest
     * @param clResponse
     * @param values
     * @throws IOException
     * returns void
     */
    //------------------------------------------
    public void runSignParent(HttpServletRequest clRequest, HttpServletResponse clResponse, AFOTWSessionValues values) throws IOException
    {
		AFOTWLog.log("runSignParent - AJAX Call() recieved", AFOTWLog.DEBUG, this.getClass().getName());
		boolean isParent = true;
		boolean isSignature = true;
		
		FOTWSigPage sigPage = (FOTWSigPage)m_clModules.elementAt(m_clModuleSkipIdx.indexOf("sig"));
		Locale clLocale = BundleUtil.getLocale(clRequest);
		String szPARPIN = (String) clRequest.getParameter("szPARPIN");
		String szPARPINWHO = (String) clRequest.getParameter("szPARPINWHO");
		String szMODTYPE = (String) clRequest.getParameter("szMODTYPE");

		AFOTWLog.log("runSignStudent - szPARPIN: "+szPARPIN, AFOTWLog.DEBUG, this.getClass().getName());
		AFOTWLog.log("runSignStudent - szMODTYPE:  "+szMODTYPE, AFOTWLog.DEBUG, this.getClass().getName());

		if(szMODTYPE!=null && szMODTYPE.equals("IRS"))
		{
			// IS Parent field BLANK??? 
			if(szPARPINWHO ==null || szPARPINWHO.equals(""))
			{
				AFOTWLog.log("runSignParent - Parent type not defined =="+szPARPINWHO, AFOTWLog.DEBUG, this.getClass().getName());
				AFOTWValidException ae1 = new AFOTWValidException(2,AFOTWData.TYPE_OTHER, "szPARINFOWHO", 0);
				setAjaxValidationError(ae1, clRequest, clResponse, "szPARINFOWHO2");
				return;
			}
		}

		// IS BLANK??? Logic
		if(szPARPIN ==null || szPARPIN.equals(""))
		{
			if(szMODTYPE!=null && szMODTYPE.equals("IRS"))
			{
				AFOTWLog.log("runSignParent - Pin is incorrect=="+szPARPIN, AFOTWLog.DEBUG, this.getClass().getName());
				AFOTWValidException ae1 = new AFOTWValidException(1,AFOTWData.TYPE_OTHER, "szPARPIN", 0);
				setAjaxValidationError(ae1, clRequest, clResponse, "szPARPIN");
				return;
			}
			else{
				AFOTWLog.log("runSignParent - no pin on request=="+szPARPIN, AFOTWLog.DEBUG, this.getClass().getName());
				AFOTWValidException ae1 = new AFOTWValidException(2,AFOTWData.TYPE_OTHER, "szPARPIN", 0);
				setAjaxValidationError(ae1, clRequest, clResponse, "szPARPIN");
				return;
			}
		}
		else
		{
			if(szMODTYPE!=null && szMODTYPE.equals("IRS"))
			{
				if (szPARPIN.length() == 4)
				{
					for (int i = 0; i < szPARPIN.length(); i++)
					{
						if (!Character.isDigit(szPARPIN.charAt(i)))
						{
							AFOTWLog.log("runSignParent - Pin is not correct =="+szPARPIN, AFOTWLog.DEBUG, this.getClass().getName());
							AFOTWValidException ae1 = new AFOTWValidException(1,AFOTWData.TYPE_OTHER, "szPARPIN", 0);
							setAjaxValidationError(ae1, clRequest, clResponse, "szPARPIN");
							return;
						}
					}
				}
				else if (szPARPIN.length() == 6)
				{
					for (int i = 0; i < szPARPIN.length(); i++)
					{
						if (!Character.isLetter(szPARPIN.charAt(i)) ||
							!Character.isLowerCase(szPARPIN.charAt(i)))
						{
							AFOTWLog.log("runSignParent - Pin which is 6 digits long has invalid characters =="+szPARPIN, AFOTWLog.DEBUG, this.getClass().getName());
							AFOTWValidException ae1 = new AFOTWValidException(1,AFOTWData.TYPE_OTHER, "szPARPIN", 0);
							setAjaxValidationError(ae1, clRequest, clResponse, "szPIN");
							return;
						}
					}
				}
				else
				{
					AFOTWLog.log("runSignParent - Pin is not 4 or 6 digits long =="+szPARPIN, AFOTWLog.DEBUG, this.getClass().getName());
					AFOTWValidException ae1 = new AFOTWValidException(1,AFOTWData.TYPE_OTHER, "szPARPIN", 0);
					setAjaxValidationError(ae1, clRequest, clResponse, "szPIN");
					return;
				}
			}
		}

		// WEB EDIT Logic	
		try {
			AFOTWLog.log("runSignParent - validate web-edits", AFOTWLog.DEBUG, this.getClass().getName());
			m_clFieldsList.setFieldValue("szPARPIN", szPARPIN, values.getFieldValues());
			sigPage.validatePIN(AFOTWModule.PARENT, values.getFieldValues());
			
		} catch (AFOTWValidException ae2) {
			setAjaxValidationError(ae2, clRequest, clResponse, "szPARPIN");
			return;
			
		} catch (AException e)
		{
			AFOTWLog.log("runSignParent - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
			setFatalErrorResponse(clResponse);
		}
		
		String returnCode = "";	
		// RUN AUTHENTICATION...	
		try {
			m_clFieldsList.setFieldValue("szPARPINWHO", szPARPINWHO, values.getFieldValues());
			AFOTWLog.log("runSignParent - run authentication with PIN service", AFOTWLog.DEBUG, this.getClass().getName());
			boolean bAuthenticated = sigPage.authenPIN(AFOTWModule.PARENT, m_clFieldsList, values.getFieldValues(), clRequest);
			returnCode = m_clFieldsList.getFieldValue("RETURNCODE",  values.getFieldValues());
			if(!bAuthenticated && !returnCode.equals(""))
			{	
				String szValue = "";
				if(szMODTYPE!=null && szMODTYPE.equals("IRS"))
				{
					szValue = getPinMessageForIRS(clLocale, returnCode, isParent, isSignature, false);
				}
				else
				{
					szValue = FOTWSaveRes.getPinMessage(clLocale, returnCode, isParent, isSignature);
				}
				
				setAjaxPinValidationError(szValue, clRequest, clResponse);
				
				return;
			}else if (bAuthenticated)
			{
				ArrayList<String[]> clResults = new ArrayList<String[]>();
				clResults.add(new String[]{"success"});
				setResponse(clResponse, this.getJsonData(clResults), CONTENT_HEADER_JSON);
				return;
			}
		}
		catch (AException e)
		{
			AFOTWLog.log("runSignParent - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
			setFatalErrorResponse(clResponse);
		}
    }
    
    /**
     * @param locale
     * @param returnCode
     * @param isParent
     * @return
     */
    public String getPinMessageForIRS(
	    Locale locale,
	    String returnCode,
	    boolean isParent,
	    boolean isSign,
	    boolean isRenewal)
    {
    	ResourceBundle bundle;
    	if (BundleUtil.isSpanish(locale)) 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("es", "ES"));
        } 
        else 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("en", "US"));
        }
    	
    	String userType = isParent ? bundle.getString("pin.parent") : bundle.getString("pin.student");
	String szLogin = bundle.getString("pin.linkIrs");
	String szAuthText = bundle.getString("pin.transferToIrs");
    	
    	//System.out.println(userType);
	Object[] pinHome = { 
		"<a href=\"JavaScript:PIN('" + 
		URLBuilder.getURL(null, null, "PIN", "secure", "PINWebApp/pinindex.jsp?locale="+ bundle.getLocale().toString(), "")
		+ "')\">",
		"</a>",
		userType
	};

	Object[] pinApply = {
		"<a href=\"JavaScript:PIN('" +
		URLBuilder.getURL(null, null, "PIN", "secure", "PINWebApp/appinstr.jsp?locale="+ bundle.getLocale().toString(), "")
		+ "')\">",
		"</a>",
		userType
	};
	
	 Object[] pinReEstablish = {
		 "<a href=\"JavaScript:PIN('" + 
		URLBuilder.getURL(null, null, "PIN", "secure", "PINWebApp/PINServlet?state=300&locale="+ bundle.getLocale().toString(), "")
		+ "')\">",
		"</a>",
		userType
	};
	 
	 Object[] pinActivate= {
		 "<a href=\"JavaScript:PIN('" + 
		URLBuilder.getURL(null, null, "PIN", "secure", "PINWebApp/PINServlet?state=800&locale="+ bundle.getLocale().toString(), "")
		+ "')\">",
		"</a>",
		userType
	};
	
	Object[] pinEmail = {
		"<a href=\"JavaScript:PIN('" +
		URLBuilder.getURL(null, null, "PIN", "secure", "PINWebApp/PINServlet?state=710&locale="+ bundle.getLocale().toString(), "")
		+ "')\">",
		"</a>",
		userType
	};
	
	Object[] ssaHome = {
		"<a href=\"JavaScript:popWindow('http://www.ssa.gov/',800,575,1)\">",
		"</a>",
		userType
	};
	
	Object[] aryContact = {
		"<a href=\"javascript:Help('cs.htm', '" + bundle.getLocale().toString() +"')\">",
		"</a>"		
	};
	
	
	
	String szReturn = "Unknown PIN Issue - " + returnCode;
	
	// No match found
	if(returnCode.equals("N") || returnCode.equals("P"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(), 
		   bundle.getString("msg.pin.notfound"), 
		   new Object[] {pinApply[0], pinApply[1], userType, aryContact[0], aryContact[1]});

	}
	// Conditional/Temp pin
	else if(returnCode.equals("T"))
	{
		szReturn = BundleUtil.formatText(bundle.getLocale(), 
				   bundle.getString("msg.pin.conditional"), 
				   new Object[] {userType});

	}
	
	else if(returnCode.equals("D"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.disabled"), 
			   new Object[] {szAuthText, 
				szLogin, 
				pinReEstablish[0], 
				pinReEstablish[1], 
				pinHome[0], 
				pinHome[1],
				userType});
	}
	
	else if(returnCode.equals("U"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(), 
		   bundle.getString("msg.pin.temp.deactivated"), 
		   new Object[] {pinReEstablish[0], pinReEstablish[1], pinHome[0], pinHome[1], userType});

	}
	
	else if(returnCode.equals("V"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(), 
		   bundle.getString("msg.pin.temp.challengeLocked"), 
		   new Object[] {pinApply[0], pinApply[1], pinHome[0], pinHome[1], userType});

	}

	else if(returnCode.equals("I"))
	{
	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append( BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.inactive"), 
			   new Object[] {szAuthText,
			     szLogin, userType}))
	    
	    .append("<br /><br />").append(bundle.getString("msg.pin.inactive.2"))
            .append("<ul><li style=\"font-weight: normal\">").append(bundle.getString("msg.pin.inactive.3")).append("</li>");
	    
	    strBuf.append("<li style=\"font-weight: normal\">").append( BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.inactive.4"), 
			   new Object[] {pinEmail[0], pinEmail[1], pinHome[0], pinHome[1]}))
	    .append("</li></ul>");
	    
	    szReturn = strBuf.toString();
	}
	
	else if(returnCode.equals("R"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.deactive"), 
			   new Object[] {pinReEstablish[0], pinReEstablish[1], pinHome[0], pinHome[1], userType});
	    
	}

	else if(returnCode.equals("C"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.challengeLocked"), 
			   new Object[] {pinApply[0], pinApply[1], pinHome[0], pinHome[1], userType});
	}
	
	else if(returnCode.equals("G"))
	{
		
	    
	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append(BundleUtil.formatText(bundle.getLocale(), bundle.getString("msg.pin.badSSAMatch"), new Object[]{userType})).append("<br />");
	    strBuf.append("<ul><li style=\"font-weight: normal\">").append(bundle.getString("msg.pin.badSSAMatch.2")).append("</li>");
	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.pin.badSSAMatch.3")).append("</li></ul>");
	    strBuf.append("<br />").append(BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.badSSAMatch.4"), 
			   ssaHome));
	    strBuf.append("<br /><br />").append(BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.badSSAMatch.5"), 
			   aryContact));
	    szReturn = strBuf.toString();
	}
	
	else if(returnCode.equals("B"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.noSSAMatch"), 
			   new Object[] {szAuthText, 
				szLogin, 
				szLogin,
				userType});
	}
	
	else if(returnCode.equals("X"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(),
	    		bundle.getString("msg.pin.expired"),
	    		new Object[] {userType}) 
	    	 + "<br /><br />"+
		
			BundleUtil.formatText(bundle.getLocale(), 
			   bundle.getString("msg.pin.expired.2"), 
			   new Object[] {pinApply[0], pinApply[1], pinHome[0], pinHome[1]});
	}
	else if(returnCode.equals("Z"))
	{
	    szReturn = BundleUtil.formatText(bundle.getLocale(),
	    		bundle.getString("msg.pin.nochallenge"),
	    		new Object[] {szAuthText, pinActivate[0], pinActivate[1], pinHome[0], pinHome[1], userType}); 
	}
	
	/*if(isSign && (!isRenewal || isParent))
	{
		if (BundleUtil.isSpanish(locale))
		{
			userType = isParent ? "del estudiante" : "del padre o de la madre";
		}
		
		szReturn = szReturn + "<br /><br />"+
		BundleUtil.formatText(bundle.getLocale(),
	    		bundle.getString("msg.pin.signOnly"),
	    		new Object[] {userType}); 
	}*/
	
	return  "<div style=\"font-weight: normal\">" + szReturn + "<br /><br /></div>";
    }
    
    //------------------------------------------
    /**
     * runSignStudent
     *
     * @param clRequest
     * @param clResponse
     * @param values
     * @throws IOException
     *
     * @return void
     */
    //------------------------------------------
    public void runSignStudent(HttpServletRequest clRequest, HttpServletResponse clResponse, AFOTWSessionValues values) throws IOException
    {
		AFOTWLog.log("runSignStudent - AJAX Call() recieved", AFOTWLog.DEBUG, this.getClass().getName());
		boolean isParent = false;
		boolean isSignature = true;
		
		FOTWSigPage sigPage = (FOTWSigPage)m_clModules.elementAt(m_clModuleSkipIdx.indexOf("sig"));
		Locale clLocale = BundleUtil.getLocale(clRequest);
		String szPIN = (String) clRequest.getParameter("szPIN");
		String szMODTYPE = (String) clRequest.getParameter("szMODTYPE");
		boolean isRenewal = SUBJSPUtil.hasSignedData(m_clFieldsList, values.getFieldValues());
		
		AFOTWLog.log("runSignStudent - szPIN: "+szPIN, AFOTWLog.DEBUG, this.getClass().getName());
		AFOTWLog.log("runSignStudent - szMODTYPE:  "+szMODTYPE, AFOTWLog.DEBUG, this.getClass().getName());
		// IS BLANK??? Logic
		if(szPIN ==null || szPIN.equals(""))
		{
			if(szMODTYPE!=null && szMODTYPE.equals("IRS"))
			{
				AFOTWLog.log("runSignStudent - Pin is incorrect=="+szPIN, AFOTWLog.DEBUG, this.getClass().getName());
				AFOTWValidException ae1 = new AFOTWValidException(1,AFOTWData.TYPE_OTHER, "szPIN", 0);
				setAjaxValidationError(ae1, clRequest, clResponse, "szPIN");
				return;
			}
			else
			{
				AFOTWLog.log("runSignStudent - no pin on request=="+szPIN, AFOTWLog.DEBUG, this.getClass().getName());
				AFOTWValidException ae1 = new AFOTWValidException(2,AFOTWData.TYPE_OTHER, "szPIN", 0);
				setAjaxValidationError(ae1, clRequest, clResponse, "szPIN");
				return;
			}
		}
		else
		{
			if(szMODTYPE!=null && szMODTYPE.equals("IRS"))
			{
				if (szPIN.length() == 4)
				{
					for (int i = 0; i < szPIN.length(); i++)
					{
						if (!Character.isDigit(szPIN.charAt(i)))
						{
							AFOTWLog.log("runSignStudent - Pin which is 4 digits long has invalid characters =="+szPIN, AFOTWLog.DEBUG, this.getClass().getName());
							AFOTWValidException ae1 = new AFOTWValidException(1,AFOTWData.TYPE_OTHER, "szPIN", 0);
							setAjaxValidationError(ae1, clRequest, clResponse, "szPIN");
							return;
						}
					}
				}
				else if (szPIN.length() == 6)
				{
					for (int i = 0; i < szPIN.length(); i++)
					{
						if (!Character.isLetter(szPIN.charAt(i)) ||
							!Character.isLowerCase(szPIN.charAt(i)))
						{
							AFOTWLog.log("runSignStudent - Pin which is 6 digits long has invalid characters =="+szPIN, AFOTWLog.DEBUG, this.getClass().getName());
							AFOTWValidException ae1 = new AFOTWValidException(1,AFOTWData.TYPE_OTHER, "szPIN", 0);
							setAjaxValidationError(ae1, clRequest, clResponse, "szPIN");
							return;
						}
					}
				}
				else
				{
					AFOTWLog.log("runSignStudent - Pin is not 4 or 6 digits long =="+szPIN, AFOTWLog.DEBUG, this.getClass().getName());
					AFOTWValidException ae1 = new AFOTWValidException(1,AFOTWData.TYPE_OTHER, "szPIN", 0);
					setAjaxValidationError(ae1, clRequest, clResponse, "szPIN");
					return;
				}
			}
		}

		// WEB EDIT Logic	
		try {
			AFOTWLog.log("runSignStudent - validate web-edits", AFOTWLog.DEBUG, this.getClass().getName());
			m_clFieldsList.setFieldValue("szPIN", szPIN, values.getFieldValues());
			sigPage.validatePIN(AFOTWModule.STUDENT, values.getFieldValues());
			
		} catch (AFOTWValidException ae2) {
			setAjaxValidationError(ae2, clRequest, clResponse, "szPIN");
			return;
			
		} catch (AException e)
		{
			AFOTWLog.log("runSignStudent - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
			setFatalErrorResponse(clResponse);
		}
		String returnCode = "";	
		// RUN AUTHENTICATION...	
		try {
			AFOTWLog.log("runSignStudent - run authentication with PIN service", AFOTWLog.DEBUG, this.getClass().getName());
			boolean bAuthenticated = sigPage.authenPIN(AFOTWModule.STUDENT, m_clFieldsList, values.getFieldValues(), clRequest);
			returnCode = m_clFieldsList.getFieldValue("RETURNCODE",  values.getFieldValues());
			if(!bAuthenticated && !returnCode.equals(""))
			{	
				String szValue = "";
				if(szMODTYPE!=null && szMODTYPE.equals("IRS"))
				{
					szValue = getPinMessageForIRS(clLocale, returnCode, isParent, isSignature, false);
				}
				else
				{
					szValue = FOTWSaveRes.getPinMessage(clLocale, returnCode, isParent, isSignature, isRenewal);
				}
				setAjaxPinValidationError(szValue, clRequest, clResponse);
				
				return;
			} else if (bAuthenticated)
			{
				ArrayList<String[]> clResults = new ArrayList<String[]>();
				clResults.add(new String[]{"success"});
				setResponse(clResponse, this.getJsonData(clResults), CONTENT_HEADER_JSON);
				return;
			}
		}
		catch (AException e)
		{
			AFOTWLog.log("runSignStudent - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
			setFatalErrorResponse(clResponse);
		}

	}


    
    //------------------------------------------
    /**
     * setAjaxValidationError
     *
     * @param clValidE
     * @param clRequest
     * @param clResponse
     * @throws IOException
     *
     * @return void
     */
    //------------------------------------------
    private void setAjaxValidationError(AFOTWValidException clValidE, HttpServletRequest clRequest, HttpServletResponse clResponse, String field)
    throws IOException
    {
    	AFOTWLog.log("returnValidationError - validation error=="+clValidE, AFOTWLog.DEBUG, this.getClass().getName());
		
		Map <String,AFOTWValidException> errors = new HashMap <String,AFOTWValidException>();
		
		errors.put(field, clValidE);
		
		clRequest.setAttribute(JSPUtil.LABEL_ERRORS_FIELDS,errors);
		
		String errorJson = JSPUtil.getErrorsAsJson(clRequest);
		AFOTWLog.log("returnValidationError - errorJson=="+errorJson, AFOTWLog.DEBUG, this.getClass().getName());
		
		setResponse(clResponse, errorJson, CONTENT_HEADER_JSON);
    }
    
    //------------------------------------------
    /**
     * setAjaxValidationError
     *
     * @param clValidE
     * @param clRequest
     * @param clResponse
     * @throws IOException
     *
     * @return void
     */
    //------------------------------------------
    private void setAjaxPinValidationError(String message, HttpServletRequest clRequest, HttpServletResponse clResponse)
    throws IOException
    {
    	AFOTWLog.log("setAjaxPinValidationError - validation error=="+message, AFOTWLog.DEBUG, this.getClass().getName());
		
    	ArrayList<String> errors = new ArrayList<String>();
		errors.add(0, "custom");
		errors.add(message);
		
		clRequest.setAttribute(JSPUtil.LABEL_ERRORS, errors);
		
		String errorJson = JSPUtil.getCustomErrorsAsJson(clRequest);
		AFOTWLog.log("setAjaxPinValidationError - errorJson=="+errorJson, AFOTWLog.DEBUG, this.getClass().getName());
		
		setResponse(clResponse, errorJson, CONTENT_HEADER_JSON);
    }
    
    
    
    //------------------------------------------
    /**
     * runAddSchool
     *
     * @param clRequest
     * @param clResponse
     * @param values
     * @throws IOException
     *
     * @return void
     */
    //------------------------------------------
    public void runAddSchool(HttpServletRequest clRequest, HttpServletResponse clResponse, AFOTWSessionValues values) throws IOException
    {
		AFOTWLog.log("runAddSchool - AJAX Call() recieved", AFOTWLog.DEBUG, this.getClass().getName());
		FscLookup fscLookup = (FscLookup)m_clModules.elementAt(m_clModuleSkipIdx.indexOf("schl"));
		Locale clLocale = BundleUtil.getLocale(clRequest);
		
		FSCode m_clFSCode = fscLookup.m_clFSCode;
		
		String szFSCODE = (String) clRequest.getParameter("szFSCODE");
		
		if(szFSCODE ==null || szFSCODE.equals(""))
		{
			
			List <String> alertMsgs = new ArrayList<String>();
			String alertMsg = getAlertMsgSchoolCodeRequired(clLocale);
			alertMsgs.add(alertMsg);
			clRequest.setAttribute(JSPUtil.LABEL_FOTW_ALERTS, alertMsgs);
			
			AFOTWLog.log("runAddSchool - alertMsg=="+alertMsg, AFOTWLog.DEBUG, this.getClass().getName());
			
			setResponse(clResponse, JSPUtil.getAlertsAsJson(clRequest), CONTENT_HEADER_JSON);
			
			return;
		}
		else
		{
			boolean isValidSchoolCode = false;
			
			AFOTWLog.log("runAddSchool - m_clValid=="+m_clValid, AFOTWLog.DEBUG, this.getClass().getName());
			try {
				isValidSchoolCode = m_clValid.isSchoolCode(szFSCODE);
				
			} catch (AException e) {
				AFOTWLog.log("runAddSchool - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
				setFatalErrorResponse(clResponse);
			}
			
			AFOTWLog.log("runAddSchool - isValidSchoolCode=="+isValidSchoolCode, AFOTWLog.DEBUG, this.getClass().getName());
			if (isValidSchoolCode)
			{
				List <String[]> clResults = new ArrayList <String []> ();
				try {
				    String []  aryCodes = {szFSCODE};
				    String []  aryNames = new String [1];
				    String []  aryAddress = new String [1];
				    String []  aryCity = new String [1];
				    String []  aryGradRate = new String [1];
				    String []  aryRetnRate = new String [1];
				    String []  aryTransRate = new String [1];
					
					m_clFSCode.verifyCode(FSCode.YEAR_CURR, aryCodes, aryNames, aryAddress, aryCity, aryGradRate, aryRetnRate, aryTransRate);
					
					String [] clResult = {aryCodes[0],aryNames[0],aryAddress[0],aryCity[0],aryGradRate[0],aryRetnRate[0],aryTransRate[0]};
					
					if(aryNames[0] == null || aryNames[0].trim().equals(""))
					{
						List <String> alertMsgs = new ArrayList<String>();
						alertMsgs.add(getAlertMsgSchoolNotFoundForAdd(clLocale,szFSCODE));
				        clRequest.setAttribute(JSPUtil.LABEL_FOTW_ALERTS, alertMsgs);
						
						setResponse(clResponse, JSPUtil.getAlertsAsJson(clRequest), CONTENT_HEADER_JSON);
					}
					else
					{
						clResults.add(clResult);
						AFOTWLog.log("runAddSchool - clResults=="+clResults, AFOTWLog.DEBUG, this.getClass().getName());
						setResponse(clResponse, this.getJsonData(clResults), CONTENT_HEADER_JSON);
					}
					
				} catch (AException e) {
				    AFOTWLog.log("runAddSchool - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
				    setFatalErrorResponse(clResponse);
				}
			}
			//input validation errors
			else
			{
				AFOTWValidException clValidE = new AFOTWValidException(19, AData.TYPE_OTHER, "szFSCODE", 0, AFOTWValidException.TYPE_REJECT);
				AFOTWLog.log("runAddSchool - validation error=="+clValidE, AFOTWLog.DEBUG, this.getClass().getName());
				
				Map <String,AFOTWValidException> errors = new HashMap <String,AFOTWValidException>();
				errors.put("szFSCODE", clValidE);
				
				clRequest.setAttribute(JSPUtil.LABEL_ERRORS_FIELDS,errors);
				
				String errorJson = JSPUtil.getErrorsAsJson(clRequest);
				AFOTWLog.log("runAddSchool - errorJson=="+errorJson, AFOTWLog.DEBUG, this.getClass().getName());
				
				setResponse(clResponse, errorJson, CONTENT_HEADER_JSON);
				
			}
		}
    }
    
    //------------------------------------------
    /**
     * runSearchSchool
     *
     * @param clRequest
     * @param clResponse
     * @param values
     * @throws IOException
     *
     * @return void
     */
    //------------------------------------------
    @SuppressWarnings("unchecked")
    public void runSearchSchool(HttpServletRequest clRequest, HttpServletResponse clResponse, AFOTWSessionValues values) throws IOException
    {
		AFOTWLog.log("runSchoolSearch - AJAX Call() recieved", AFOTWLog.DEBUG, this.getClass().getName());
		String szFSNAME = (String) clRequest.getParameter("szFSNAME");
		String szFSCITY = (String) clRequest.getParameter("szFSCITY");
		String szFSSTATE = (String) clRequest.getParameter("szFSSTATE");
		String szNextSet = (String) clRequest.getParameter("szFSSTATE");
		int iNextSet = 1;
		try {
		    Integer.parseInt(szNextSet);
		} catch (Throwable t)
		{}

		AFOTWLog.log("szFSNAME=="+szFSNAME, AFOTWLog.DEBUG, this.getClass().getName());
		AFOTWLog.log("szFSCITY=="+szFSCITY, AFOTWLog.DEBUG, this.getClass().getName());
		AFOTWLog.log("szFSSTATE=="+szFSSTATE, AFOTWLog.DEBUG, this.getClass().getName());
		
		Locale clLocale = BundleUtil.getLocale(clRequest);
		
		Map <String,AFOTWValidException> errors = new LinkedHashMap <String,AFOTWValidException>();
		
		if(szFSSTATE == null || szFSSTATE.equals(""))
		{
			
			List <String> alertMsgs = new ArrayList<String>();
			String alertMsg = getAlertMsgStateRequired(clLocale);
			alertMsgs.add(alertMsg);
			clRequest.setAttribute(JSPUtil.LABEL_FOTW_ALERTS, alertMsgs);
			
			AFOTWLog.log("runSearchSchool - alertMsg=="+alertMsg, AFOTWLog.DEBUG, this.getClass().getName());
			
			setResponse(clResponse, JSPUtil.getAlertsAsJson(clRequest), CONTENT_HEADER_JSON);
			
			return;
		}
	   
		
		if (szFSSTATE.startsWith(" ") || !FSCode.isValidState(szFSSTATE)) 
		{
			AFOTWValidException clValidE = new AFOTWValidException(1, AData.TYPE_OTHER, "szFSSTATE", 0, AFOTWValidException.TYPE_REJECT);
			errors.put("szFSSTATE", clValidE);
		}
		
		if (szFSCITY.startsWith(" ") || !FSCode.isValidCity(szFSCITY)) 
		{
			AFOTWValidException clValidE = new AFOTWValidException(1, AData.TYPE_OTHER, "szFSCITY", 0, AFOTWValidException.TYPE_REJECT);
			errors.put("szFSCITY", clValidE);
		} 
		if (szFSNAME.startsWith(" ") || !FSCode.isValidKeyword(szFSNAME)) 
		{
			AFOTWValidException clValidE = new AFOTWValidException(1, AData.TYPE_OTHER, "szFSNAME", 0, AFOTWValidException.TYPE_REJECT);
			errors.put("szFSNAME", clValidE);
		} 
		

		if(errors.size() == 0)
		{
			FscLookup fscLookup = (FscLookup)m_clModules.elementAt(m_clModuleSkipIdx.indexOf("schl"));
			FSCode m_clFSCode = fscLookup.m_clFSCode;
			
			List <String[]> clResults = null;
			try {
				clResults = m_clFSCode.searchCode(FSCode.YEAR_CURR, szFSNAME, szFSSTATE, szFSCITY, FSCode.SORT_BY_NAME, iNextSet);
				// 0 aryFSCODE
				// 1 aryFSNAME
				// 2 aryFSADDRESS
				// 3 aryFSCITY
				// 4 GRADRATE
				// 5 RETNRATE
				// 6 TRANSRATE
				if(clResults ==null || clResults.size()==0){
					AFOTWLog.log("runSchoolSearch - clResults==000", AFOTWLog.DEBUG, this.getClass().getName());
					
					List <String> alertMsgs = new ArrayList<String>();
					alertMsgs.add(getAlertMsgSchoolNotFoundForSearch(clLocale));
			        clRequest.setAttribute(JSPUtil.LABEL_FOTW_ALERTS, alertMsgs);
					
					setResponse(clResponse, JSPUtil.getAlertsAsJson(clRequest), CONTENT_HEADER_JSON);
					
				}
				else{
					AFOTWLog.log("runSchoolSearch - clResults=="+clResults, AFOTWLog.DEBUG, this.getClass().getName());
					clLocale = new Locale(clLocale.getLanguage(), clLocale.getCountry(), "schoolSelection");
				    ResourceBundle clBundle = BundleUtil.getFieldTextsBundle(clLocale);
				    
					ArrayList <String[]> tableData = new ArrayList <String[]>();
					int iRow = 0;
					
					for(String [] row : clResults)
					{
						iRow++;
						if(iRow <= (FSCode.FSC_MAX_ROW_COUNT))
						{
        					    	String addLink = "<span><a href=javascript:addSchool('"+row[0]+"')>"+BundleUtil.getString(clBundle, "Common.button.add")+"</a></span>";
        						String addLinkDisabled = "<span style=\"display:none\">"+BundleUtil.getString(clBundle, "Common.button.add")+"</span>";
        						
        						String toolTipText ="<span title=\""+row[4] + ";"+row[5] +";"+row[6]+"\" diaplay:none/>";
        						tableData.add(new String [] {addLink+addLinkDisabled,row[1]+toolTipText,row[2],row[3],row[0],row[4],row[5],row[6]});
						} else
						{
						    tableData.add(new String [] {"", "","","","","","",""});
						}
					}
					
					setResponse(clResponse, this.getJsonData(tableData), CONTENT_HEADER_JSON);
				}
				
			} catch (AException e) {
			    AFOTWLog.log("runSearchSchool - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
				setFatalErrorResponse(clResponse);
			}
		}
		//input validation errors
		else
		{
			clRequest.setAttribute(JSPUtil.LABEL_ERRORS_FIELDS,errors);
			
			String errorJson = JSPUtil.getErrorsAsJson(clRequest);
			AFOTWLog.log("runSchoolSearch - errorJson=="+errorJson, AFOTWLog.DEBUG, this.getClass().getName());
			
			setResponse(clResponse, errorJson, CONTENT_HEADER_JSON);
		}
		
		AFOTWLog.log("runSchoolSearch - AJAX Call() responded", AFOTWLog.DEBUG, this.getClass().getName());
		
    }
    
    //------------------------------------------
    /**
     * runAZSNTEdit
     *
     * @param clRequest
     * @param clResponse
     * @param values
     * @throws IOException
     *
     * @return void
     */
    //------------------------------------------
    @SuppressWarnings("unchecked")
	public void runAZSNTEdit(HttpServletRequest clRequest, HttpServletResponse clResponse, AFOTWSessionValues values) throws IOException
    {
		AFOTWLog.log(".runAZSNTEdit(): BEGIN", AFOTWLog.DEBUG, this.getClass().getName());
		
		String flagType = (String) clRequest.getParameter("flagType");
		String ajax = (String) clRequest.getParameter("ajax");
		String dowhat = (String) clRequest.getParameter("dowhat");
		String flag = "";
		
		AFOTWLog.log(".runAZSNTEdit(): flagType=="+flagType, AFOTWLog.DEBUG, this.getClass().getName());
		AFOTWLog.log(".runAZSNTEdit(): ajax=="+ajax, AFOTWLog.DEBUG, this.getClass().getName());
		AFOTWLog.log(".runAZSNTEdit(): dowhat=="+dowhat, AFOTWLog.DEBUG, this.getClass().getName());
		
		Map <String,AFOTWValidException> errors = new LinkedHashMap <String,AFOTWValidException>();
		
		// run validation on entire page here
		int pageid = values.position;
		AFOTWLog.log(".runAZSNTEdit(): pageid="+pageid, AFOTWLog.DEBUG, this.getClass().getName());
		
		try {
			
			m_clValid.validate(m_clFieldsList, values.getFieldValues(), 0, pageid, true, true);

		} catch (AFOTWValidException e) {
			errors = e.getErrorFields();
		} catch (AException e)
		{
		    AFOTWLog.log("runAZSNTEdit - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
		    setFatalErrorResponse(clResponse);
		}
		
		if(errors.size() == 0)
		{
			try {
				((EOECheck) m_clModules.elementAt(m_clModuleSkipIdx.indexOf(EOECheck.EOE_SKIP_KEY))).setAutoZeroFlags(values);
			
				// get the possibly updated value
				flag = m_clFieldsList.getFieldValue(flagType, values.getFieldValues());
			} catch (AFOTWValidException e) {
				errors.put(e.getErrorFieldName(),e);
				AFOTWLog.log(".runAZSNTEdit(): EOECheck.setAutoZeroFlags found error in field " + e.getErrorFieldName(), AFOTWLog.DEBUG, this.getClass().getName());
			} catch (AException e)
			{
			    AFOTWLog.log("runAZSNTEdit - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
			    setFatalErrorResponse(clResponse);
			}
		}
		if(errors.size() == 0)
		{
			AFOTWLog.log(".runAZSNTEdit(): " + flagType + " returned="+flag, AFOTWLog.DEBUG, this.getClass().getName());
			setResponse(clResponse, "{ \"flag\" : \"" + flag + "\" }", CONTENT_HEADER_JSON);
		}		
		else
		{
			clRequest.setAttribute(JSPUtil.LABEL_ERRORS_FIELDS,errors);
			String errorJson = JSPUtil.getErrorsAsJson(clRequest);
			AFOTWLog.log(".runAZSNTEdit(): AFOTWValid errorJson=="+errorJson, AFOTWLog.DEBUG, this.getClass().getName());
			setResponse(clResponse, errorJson, CONTENT_HEADER_JSON);
		}
		AFOTWLog.log(".runAZSNTEdit(): END", AFOTWLog.DEBUG, this.getClass().getName());
    }
    
    //------------------------------------------
    /**
     * runDrugResult
     *
     * @param clRequest
     * @param clResponse
     * @param values
     * @return
     * @throws IOException
     *
     * @return boolean
     */
    //------------------------------------------
    public boolean runDrugResult(HttpServletRequest clRequest, HttpServletResponse clResponse, AFOTWSessionValues values) throws IOException
    {
		boolean bResponse = false;
		String szDrugResult = "";
		AFOTWLog.log("runDrugResult - AJAX Call() received", AFOTWLog.DEBUG, this.getClass().getName());
		WSDrug mod= (WSDrug)m_clModules.elementAt(m_clModuleSkipIdx.indexOf("q31"));
		try {
			Vector<?> clFieldValues = values.getFieldValues();
		    //String szState = m_clFieldsList.getFieldValue("szSTUDSTLEGAL", values.getFieldValues());
		    AFOTWLog.log(
		    		"Drug Values received ... " +
		        	"szFEDAID=["   +m_clFieldsList.getFieldValue("szFEDAID", values.getFieldValues()) +
		        	"] szDRUGCONVICTED=[" +m_clFieldsList.getFieldValue("szDRUGCONVICTED", values.getFieldValues())+
		        	"] szDRUGREMOVED=[" + m_clFieldsList.getFieldValue("szDRUGREMOVED", values.getFieldValues()) +    
		        	"] szDRUGPERIOD=["   +m_clFieldsList.getFieldValue("szDRUGPERIOD", values.getFieldValues()) +   
		        	"] szDRUGREHAB=["   +m_clFieldsList.getFieldValue("szDRUGREHAB", values.getFieldValues()) +
		        	"] szDRUGPOSSESSNBR=[" + m_clFieldsList.getFieldValue("szDRUGPOSSESSNBR", values.getFieldValues()) +
			        "] szDRUGPOSSESSDATEL=[" + m_clFieldsList.getFieldValue("szDRUGPOSSESSDATEL", values.getFieldValues()) +
			        "] szDRUGPOSSESSDATE=["+ m_clFieldsList.getFieldValue("szDRUGPOSSESSDATE", values.getFieldValues()) +
			        "] szDRUGSALENBR=["   + m_clFieldsList.getFieldValue("szDRUGSALENBR", values.getFieldValues()) +		           
			        "] szDRUGSALEDATE=["  +m_clFieldsList.getFieldValue("szDRUGSALEDATE", values.getFieldValues()) + 
			        "] szCITIZEN=["   +m_clFieldsList.getFieldValue("szCITIZEN", values.getFieldValues())
			        , AFOTWLog.DEBUG, this.getClass().getName());
		        
	        // Call to blank out values
		    mod.analyzeResults(values.getFieldValues());

		    // Run validation if necessary
    		Map <String,AFOTWValidException> errors = null;
	        if (m_clFieldsList.getFieldValue("szFEDAID", clFieldValues).equals("1") 
		    		&& m_clFieldsList.getFieldValue("szDRUGCONVICTED", clFieldValues).equals("1")
		    		&& m_clFieldsList.getFieldValue("szDRUGREMOVED", clFieldValues).equals("2")
		    		&& m_clFieldsList.getFieldValue("szDRUGPERIOD", clFieldValues).equals("1")
		    		&& m_clFieldsList.getFieldValue("szDRUGREHAB", clFieldValues).equals("2"))
		    {
	        	errors = new LinkedHashMap <String,AFOTWValidException>();

	        	String possessions = m_clFieldsList.getFieldValue("szDRUGPOSSESSNBR", clFieldValues);
	        	if (possessions.equals("1")) {
	        		validateField(clFieldValues, "szDRUGPOSSESSDATE", errors);
	        	} else if (possessions.equals("2")) {
	        		validateField(clFieldValues, "szDRUGPOSSESSDATEL", errors);
	        	}

	        	String sales = m_clFieldsList.getFieldValue("szDRUGSALENBR", clFieldValues);
	        	if (sales.equals("1")) {
	        		validateField(clFieldValues, "szDRUGSALEDATE", errors);
	        	}
		    }
		    		
	        if (errors != null && errors.size() > 0)
	        {
	        	clRequest.setAttribute(JSPUtil.LABEL_ERRORS_FIELDS, errors);
	        	String errorJson = JSPUtil.getErrorsAsJson(clRequest);
	        	AFOTWLog.log(".runDrugResult(): AFOTWValid errorJson==" + errorJson, AFOTWLog.DEBUG, this.getClass().getName());
	        	setResponse(clResponse, errorJson, CONTENT_HEADER_JSON);
	        }
	        else
	        {
	        	// Call again to calculate results
		        mod.analyzeResults(values.getFieldValues());
		           
		        AFOTWLog.log(
		        	    "Results returned..."+
		        	    "szDRUGCONV=["+m_clFieldsList.getFieldValue("szDRUGCONV",  values.getFieldValues())+ "] "+
		        	    "szDRUGELIGIBLEDATE=["+m_clFieldsList.getFieldValue("szDRUGELIGIBLEDATE",  values.getFieldValues())+ "] "
		        	    , AFOTWLog.DEBUG, this.getClass().getName());
	
			    szDrugResult = m_clFieldsList.getFieldValue("szDRUGCONV",  values.getFieldValues());
			    if(m_clFieldsList.getFieldValue("szDRUGELIGIBLEDATE",  values.getFieldValues()).trim().length() == 8)
			    {
			    	szDrugResult = szDrugResult + m_clFieldsList.getFieldValue("szDRUGELIGIBLEDATE",  values.getFieldValues());
			    }
				setResponse(clResponse, szDrugResult,CONTENT_HEADER_TEXT);
	        }
		} 
		catch (AException e)
		{
		    AFOTWLog.log("runDrugResult - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
			setFatalErrorResponse(clResponse);
		}
		
		bResponse = true;
		AFOTWLog.log("runDrugResult - AJAX Call() responded", AFOTWLog.DEBUG, this.getClass().getName());
		return bResponse;
    }

    //------------------------------------------
    /**
     * runACGProg
     *
     * @param clRequest
     * @param clResponse
     * @param values
     * @return
     * @throws IOException
     *
     * @return boolean
     */
    //------------------------------------------
    public boolean runACGProg(HttpServletRequest clRequest, HttpServletResponse clResponse, AFOTWSessionValues values) throws IOException
    {
    	boolean bResponse = false;
		AFOTWLog.log("runACGProg - AJAX Call() recieved", AFOTWLog.DEBUG, this.getClass().getName());
		
		String szACGState = "";
		String szACGHSProg = "";
		String szHSYear = "";
		Locale clLocale = null;
		try {
			Vector<?> clFieldValues = values.getFieldValues();
			
			szACGState = m_clFieldsList.getFieldValue("szACGHSSTATE", clFieldValues);
			szACGHSProg = m_clFieldsList.getFieldValue("szACGHSPROG", clFieldValues);
			szHSYear = m_clFieldsList.getFieldValue("szACGHSAFTER", clFieldValues);
			clLocale = BundleUtil.getLocale(m_clFieldsList, clFieldValues);
		} catch (AException e)
		{
		    AFOTWLog.log("runACGProg - runtime error=="+e, AFOTWLog.ERROR, this.getClass().getName());
			setFatalErrorResponse(clResponse);
		}
		
		String acgOptions = ACG.getOptions(szACGHSProg, szACGState, szHSYear, clLocale);
		
		setResponse(clResponse, acgOptions,CONTENT_HEADER_TEXT);
		
		bResponse = true;
		AFOTWLog.log("runACGProg - AJAX Call() responded", AFOTWLog.DEBUG, this.getClass().getName());
		return bResponse;
    }    
    
    //------------------------------------------
    /**
     * setResponse
     *
     * @param clResponse
     * @param message
     * @param contentType
     * @throws IOException
     *
     * @return void
     */
    //------------------------------------------
    public void setResponse(HttpServletResponse clResponse, String message, String contentType) throws IOException
    {
    	clResponse.setContentType(contentType);
    	clResponse.setHeader("Cache-Control", "no-cache");
    	clResponse.getWriter().write(message);
    	clResponse.getWriter().close();
    }
    
    //------------------------------------------
    /**
     * setFatalErrorResponse
     *
     * @param clResponse
     * @throws IOException
     *
     * @return void
     */
    //------------------------------------------
    public void setFatalErrorResponse(HttpServletResponse clResponse) throws IOException
    {
    	clResponse.setContentType(CONTENT_HEADER_JSON);
    	clResponse.setHeader("Cache-Control", "no-cache");
    	
    	XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("fatalError", String.class);
    	clResponse.getWriter().write(xstream.toXML("ajax runtime error"));
    	clResponse.getWriter().close();
    }
    
    
    //------------------------------------------
    /**
     * getJsonData
     *
     * @param clResults
     * @return
     *
     * @return String
     */
    //------------------------------------------
    public String getJsonData(List <String[]> clResults) 
    {
    	if(clResults==null)
    	{
    		clResults = new ArrayList <String []>();
    	}
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("aaData", List.class);
		
    	return xstream.toXML(clResults);
    }
    
    
    //------------------------------------------
    /**
     * getAlertMsgStateRequired
     *
     * @param locale
     * @return
     *
     * @return String
     */
    //------------------------------------------
    public String getAlertMsgStateRequired(Locale locale)
    {
    	Locale clLocale = new Locale(locale.getLanguage(), locale.getCountry(), "fotw1011.i18n.FAFSA_AlertMessages");
		ResourceBundle bundle;
	    if (BundleUtil.isSpanish(clLocale)) 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("es", "ES"));
        } 
        else 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("en", "US"));
        }
	   
    	StringBuffer strBuf = new StringBuffer();
 	    strBuf.append(bundle.getString("msg.fstep2_01.State.required"));
 	    
 	    String szReturn = strBuf.toString();
        
 	    
 	    return szReturn;
    	
    }
    
    //------------------------------------------
    /**
     * getAlertMsgSchoolCodeRequired
     *
     * @param locale
     * @return
     *
     * @return String
     */
    //------------------------------------------
    public String getAlertMsgSchoolCodeRequired(Locale locale)
    {
    	Locale clLocale = new Locale(locale.getLanguage(), locale.getCountry(), "fotw1011.i18n.FAFSA_AlertMessages");
		ResourceBundle bundle;
	    if (BundleUtil.isSpanish(clLocale)) 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("es", "ES"));
        } 
        else 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("en", "US"));
        }
	   
    	StringBuffer strBuf = new StringBuffer();
 	    strBuf.append(bundle.getString("msg.fstep2_01.SchoolCode.required"));
 	    
 	    String szReturn = strBuf.toString();
        
 	    
 	    return szReturn;
    	
    }
    
    //------------------------------------------
    /**
     * getAlertMsgSchoolNotFoundForSearch
     *
     * @param locale
     * @return
     *
     * @return String
     */
    //------------------------------------------
    public String getAlertMsgSchoolNotFoundForSearch(Locale locale)
    {
    	Locale clLocale = new Locale(locale.getLanguage(), locale.getCountry(), "fotw1011.i18n.FAFSA_AlertMessages");
		ResourceBundle bundle;
	    if (BundleUtil.isSpanish(clLocale)) 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("es", "ES"));
        } 
        else 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("en", "US"));
        }
	   
    	StringBuffer strBuf = new StringBuffer();
 	    strBuf.append(bundle.getString("msg.fstep2_01.Search.NotFound.Begin")).append("<br /><br />");
 	    strBuf.append("<ul>");
 	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.fstep2_01.Search.NotFound.Bullet1")).append("</li>");
 	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.fstep2_01.Search.NotFound.Bullet2")).append("</li>");
 	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.fstep2_01.Search.NotFound.Bullet3")).append("</li>");
 	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.fstep2_01.Search.NotFound.Bullet4")).append("</li>");
 	    strBuf.append("</ul><br />");
 	    strBuf.append(bundle.getString("msg.fstep2_01.Search.NotFound.End"));
 	   
 	    String szReturn = strBuf.toString();
        
 	    
 	    return szReturn;
    }
    

    //------------------------------------------
    /**
     * getAlertMsgSchoolNotFoundForAdd
     *
     * @param locale
     * @param schoolCode
     * @return
     *
     * @return String
     */
    //------------------------------------------
    public String getAlertMsgSchoolNotFoundForAdd(Locale locale, String schoolCode)
    {
    	Locale clLocale = new Locale(locale.getLanguage(), locale.getCountry(), "fotw1011.i18n.FAFSA_AlertMessages");
		ResourceBundle bundle;
	    if (BundleUtil.isSpanish(clLocale)) 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("es", "ES"));
        } 
        else 
        {
        	bundle = ResourceBundle.getBundle("fotw1011.i18n.FAFSA_AlertMessages", new Locale("en", "US"));
        }
	   
    	StringBuffer strBuf = new StringBuffer();
    	String beginStr = bundle.getString("msg.fstep2_01.Add.NotFound.Begin").replace("{0}", schoolCode);
 	    strBuf.append(beginStr).append("<br />");
 	    strBuf.append("<ul>");
 	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.fstep2_01.Add.NotFound.Bullet1")).append("</li>");
 	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.fstep2_01.Add.NotFound.Bullet2")).append("</li>");
 	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.fstep2_01.Add.NotFound.Bullet3")).append("</li>");
 	    strBuf.append("<li style=\"font-weight: normal\">").append(bundle.getString("msg.fstep2_01.Add.NotFound.Bullet4")).append("</li>");
 	    strBuf.append("</ul>");
 	   
 	    String szReturn = strBuf.toString();
        
 	    
 	    return szReturn;
    }

    //------------------------------------------
    /**
     * validateField - Utility method that can be used when you need to validate some but not all fields on the page. Just help keep code a little more clean.
     *
     * @param clFieldValues
     * @param szField
     * @param errors
     * @throws AException
     *
     * @return void
     */
    //------------------------------------------
    private void validateField(Vector<?> clFieldValues, String szField, Map <String, AFOTWValidException> errors) throws AException
    {
    	try 
    	{
    		m_clValid.validateField(m_clFieldsList, 0, clFieldValues, szField);
    	}
    	catch (AFOTWValidException e) 
    	{
    		errors.put(e.getErrorFieldName(), e);
    	}
   }
}
