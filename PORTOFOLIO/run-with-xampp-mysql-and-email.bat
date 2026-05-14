@echo off
cd /d "%~dp0"
echo Make sure XAMPP MySQL is started before running this file.
echo.
set PORTFOLIO_MAIL_ENABLED=true
if "%PORTFOLIO_MAIL_USERNAME%"=="" set /p PORTFOLIO_MAIL_USERNAME=Enter email username:
if "%PORTFOLIO_MAIL_PASSWORD%"=="" set /p PORTFOLIO_MAIL_PASSWORD=Enter email app password:
echo.
mvn spring-boot:run "-Dspring-boot.run.profiles=xampp"
