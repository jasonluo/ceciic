#!/bin/sh

if [ ! -d "$OJTS_HOME" ]
then
   OJTS_HOME="`readlink -f $0`"
   OJTS_HOME="`dirname $OJTS_HOME`"
fi

EXTENSIONS=""
if [ -z "$JAVA" ] || ! which $JAVA 
then
    JAVA=java
fi
    
D=":"
case $(/bin/uname) in 
    CYGWIN* )
        D=";" ;;
esac

for JAR in $OJTS_HOME/lib/*.jar; do CLASSPATH=$CLASSPATH:$JAR; done
      
#$JAVA $JAVAOPT -classpath $OJTS_HOME/ojts.jar${D}$CLASSPATH StartJ

export CLASSPATH=./lib/asm-2.0.jar:./lib/bsf.jar:./lib/castor-0.9.5.3.jar:./lib/castor-doclet.jar:./lib/commons-lang-2.0.jar:./lib/commons-logging.jar:./lib/core.jar:./lib/gnujaxp.jar:./lib/hsqldb.jar:./lib/htmlparser.jar:./lib/jcommon-0.9.7.jar:./lib/jCookie.jar:./lib/jdom.jar:./lib/jfreechart-0.9.21.jar:./lib/j.jar:./lib/joda-time-0.98.jar:./lib/jta1.0.1.jar:./lib/junit.jar:./lib/log4j-1.2.8.jar:./lib/servlet.jar:./lib/tm-tool.jar:./lib/wavelet.jar:./lib/xercesImpl.jar:./lib/xerces.jar:./lib/xml-apis.jar:./lib/xmlParserAPIs.jar

export CLASSPATH=.
echo "you need to start the jar with the full path name! no idea why?"
echo $JAVA $JAVAOPT -jar $OJTS_HOME/ojts.jar
$JAVA $JAVAOPT -jar $OJTS_HOME/ojts.jar
