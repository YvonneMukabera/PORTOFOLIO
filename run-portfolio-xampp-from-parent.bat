@echo off
cd /d "%~dp0"
mvn -pl PORTOFOLIO spring-boot:run "-Dspring-boot.run.profiles=xampp"
