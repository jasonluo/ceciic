1. configure tomcat
http://forum.springsource.org/showthread.php?74369-STS-Spring-MVC-deployment-on-Tomcat

Right click on server -> Open ... -> "Open launch configuration" -> "Arguments" tab -> look for "-Dwtp.deploy" setting in "VM Arguments" text box
Set value of this variable to your Apache Tomcat installation of choice and from now on, Eclipse (and STS) will deploy to that folder.

2.
javax.faces.validator.DISABLE _DEFAULT_BEAN_VALIDATOR 
javax.faces.VALIDATE_EMPTY_FIELDS
