@echo off
cd /d "%~dp0"
echo Starting portfolio with direct email sending enabled.
echo Enter your Gmail address and Gmail app password when asked.
echo.
set PORTFOLIO_MAIL_ENABLED=true
set /p PORTFOLIO_MAIL_USERNAME=Enter email username:
set /p PORTFOLIO_MAIL_PASSWORD=Enter email app password:
echo.
mvn spring-boot:run
echo.
echo Portfolio stopped or Maven returned an error.
pause
