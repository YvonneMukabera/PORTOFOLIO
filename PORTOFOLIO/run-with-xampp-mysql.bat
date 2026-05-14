@echo off
cd /d "%~dp0"
echo Make sure XAMPP MySQL is started before running this file.
mvn spring-boot:run "-Dspring-boot.run.profiles=xampp"
