@echo off & setlocal enabledelayedexpansion

set LIB_JARS=
for %%i in (%~dp0..\lib\*) do set LIB_JARS=!LIB_JARS!;%%i

set CMD_LINE_ARGS=
for %%j in (%*) do set CMD_LINE_ARGS=!CMD_LINE_ARGS! %%j

set CLASSPATH=%~dp0..\;%~dp0..\conf;%LIB_JARS%

java -classpath %CLASSPATH% com.sinoiov.etims.app.EtimsAppStarter %CMD_LINE_ARGS%

pause