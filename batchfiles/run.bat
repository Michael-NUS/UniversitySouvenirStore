rem  Author :NayLA
rem  run.bat file for starting University Souvenir Store Application

@ECHO Off
ECHO Running the application...

call setenv.bat

call %JAVA_HOME%\jre1.8.0_77\bin\java   .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\login\ApplicationLogin.class