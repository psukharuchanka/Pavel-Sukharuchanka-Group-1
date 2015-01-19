@echo off
echo.
echo ------------------------------------------------
echo  Simple identity client 1.0.0-SNAPSHOT
echo  Usage: runClient.bat 
echo ------------------------------------------------
echo.
pushd .
cd /d %~dp0%
set CLASSWORLDS_JAR=lib\classworlds-1.1.jar
rem set JPDA=-Xdebug -Xrunjdwp:transport=dt_socket,address=8181,server=y,suspend=y
java -cp %CLASSWORLDS_JAR% -Xms128m -Xmx512m -ea -Dclassworlds.conf=conf/assembly.conf org.codehaus.classworlds.Launcher %*
popd

