rem  Author :Aung Myo
rem  run.bat file for starting University Souvenir Store Application

@ECHO Off
ECHO Running the application...

call setenv.bat
call java -cp classes sg.edu.nus.iss.universitysouvenirstore.login.ApplicationLogin
