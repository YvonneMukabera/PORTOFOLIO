@echo off
cd /d "%~dp0"
echo Starting portfolio with direct email sending enabled.
echo Enter your Gmail address and Gmail app password when asked.
echo.
set PORTFOLIO_MAIL_ENABLED=true
if "%PORTFOLIO_MAIL_USERNAME%"=="" set /p PORTFOLIO_MAIL_USERNAME=Enter email username:
if "%PORTFOLIO_MAIL_PASSWORD%"=="" set /p PORTFOLIO_MAIL_PASSWORD=Enter email app password:
echo.
mvn -pl PORTOFOLIO spring-boot:run
