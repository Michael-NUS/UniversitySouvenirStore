rem  Author :NayLA
rem  compile.bat file

@ECHO Off
ECHO Compilation started......

call setenv.bat

set SRCDIR=src\sg\edu\nus\iss\universitysouvenirstore
call javac -d classes %SRCDIR%\Customer.java %SRCDIR%\CustomException.java %SRCDIR%\Storekeeper.java %SRCDIR%\Member.java %SRCDIR%\Category.java %SRCDIR%\Discount.java %SRCDIR%\Product.java %SRCDIR%\Vendor.java %SRCDIR%\MemberManager.java %SRCDIR%\CategoryUtils.java %SRCDIR%\CategoryVendorMgr.java %SRCDIR%\DiscountManger.java %SRCDIR%\FileManagerUtils.java %SRCDIR%\ProductUtils.java %SRCDIR%\Transaction.java %SRCDIR%\TransactionedItem.java %SRCDIR%\VendorUtils.java %SRCDIR%\util\DateFormattedTextField.java %SRCDIR%\util\DoubleTextField.java %SRCDIR%\util\InfoBox.java %SRCDIR%\util\IntegerTextField.java %SRCDIR%\gui\AddMemberDialog.java %SRCDIR%\gui\AddTransactionedItemDialog.java %SRCDIR%\gui\CategoryInfoDialog.java %SRCDIR%\gui\CategoryManagerDialog.java %SRCDIR%\gui\DiscountDialog.java %SRCDIR%\gui\DiscountInfoMgrDialog.java %SRCDIR%\gui\MemberManagerDialog.java %SRCDIR%\gui\ProductInfoDialog.java %SRCDIR%\gui\ProductManagerDialog.java %SRCDIR%\gui\ProductReorderDialog.java %SRCDIR%\gui\ReportDialog.java %SRCDIR%\gui\TransactionDialog.java %SRCDIR%\gui\TransactionItemDialog.java %SRCDIR%\gui\TransactionMemberDialog.java %SRCDIR%\gui\UtilityManagerDialog.java %SRCDIR%\gui\VendorInfoDialog.java %SRCDIR%\login\AlertDialog.java %SRCDIR%\login\ApplicationLogin.java %SRCDIR%\login\MainMenu.java %SRCDIR%\login\MyKeyListener.java %SRCDIR%\login\package-info.java %SRCDIR%\login\RegistrationStatus.java %SRCDIR%\login\SelectionAlert.java %SRCDIR%\login\StoreKeeperInfoUpdate.java %SRCDIR%\login\UnauthorizedLoginAlert.java %SRCDIR%\login\UserStatusMessage.java

ECHO Compilation finished successfully.
PAUSE 
