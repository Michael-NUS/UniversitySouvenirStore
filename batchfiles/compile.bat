rem  Author :NayLA
rem  compile.bat file

@ECHO Off
ECHO Compilation started......

call setenv.bat
set CLASSPATH=".\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Customer.java"

call %PATH%\javac  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Customer.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Storekeeper.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Member.java

call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Category.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Discount.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Product.java"
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Vendor.java


call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\MemberManager.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\CategoryUtils.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\CategoryVendorMgr.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\DiscountManager.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\FileManagerUtils.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\ProductUtils.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\Transaction.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\TransactionItem.java
call %PATH%\javac %CLASSPATH%  .\..\..\UniversitySouvenirStore\src\sg\edu\nus\iss\universitysouvenirstore\VendorUtils.java

ECHO Compilation finished successfully.
PAUSE
