@echo off
cd /d "%~dp0"
echo This starts the portfolio with direct email sending enabled.
echo Use your Gmail address as the username and your Gmail app password as the password.
echo.
set PORTFOLIO_MAIL_ENABLED=true
set /p PORTFOLIO_MAIL_USERNAME=Enter email username:
set /p PORTFOLIO_MAIL_PASSWORD=Enter email app password:
echo.
mvn spring-boot:run
