# Yvonne Mukabera Portfolio

This is my Spring Boot portfolio application. XAMPP Apache is not needed to run the website. If you use XAMPP, use only the MySQL service for saving contact-form messages.

## Why Two MySQL Properties?

The XAMPP profile uses:

```properties
portfolio.mysql.server-url=jdbc:mysql://localhost:3307/
portfolio.mysql.database=yvonne_portfolio
```

They are separated on purpose. The app first connects to the MySQL server on port `3307` so it can create the database if it does not exist. After that it connects to the specific `yvonne_portfolio` database and stores contact messages in `contact_messages`.

## Run Without Database

Double-click:

```bat
run-without-database.bat
```

The script asks for your email username and Gmail app password so contact-form messages can be sent directly to:

```text
mukabera255@gmail.com
```

Then open:

```text
http://localhost:8080
```

The contact form sends messages to your inbox. Messages are saved only while the app is running unless you use the XAMPP MySQL profile.

## Run With XAMPP MySQL

1. Open XAMPP Control Panel.
2. Start `MySQL`.
3. Double-click:

```bat
run-with-xampp-mysql.bat
```

Then open:

```text
http://localhost:8080
```

The app creates a database named `yvonne_portfolio` and a table named `contact_messages` automatically. You can view them in phpMyAdmin.

## Send Contact Messages To Email

Every contact-form message is addressed to:

```text
mukabera255@gmail.com
```

To send email from Spring Boot, the app needs SMTP login details. For Gmail, create a Gmail app password, then run:

```bat
run-without-database.bat
```

or, if you also want XAMPP MySQL storage:

```bat
run-with-xampp-mysql-and-email.bat
```

The script asks for:

```text
PORTFOLIO_MAIL_USERNAME
PORTFOLIO_MAIL_PASSWORD
```

Use `mukabera255@gmail.com` as the username and the Gmail app password as the password. Do not put the app password directly into Git or public code.

## Run In IntelliJ IDEA

If the green run button is not visible, IntelliJ has not imported the Maven project correctly yet.

1. Open IntelliJ IDEA.
2. Choose `File > Open`.
3. Select this folder, not only the HTML file:

```text
C:\Users\user\Desktop\Portfolio\PORTOFOLIO
```

4. If IntelliJ asks, choose `Trust Project`.
5. Wait for Maven to load dependencies from `pom.xml`.
6. If it still does not show the green run button, open the Maven panel on the right side and click the refresh icon.
7. Make sure the Project SDK is Java 17.
8. Open `src/main/java/com/yvonne/portfolio/PortfolioApplication.java`.
9. Click the green run button beside `main`.

You can also run it from IntelliJ without the green button:

1. Open the Maven panel.
2. Expand `portfolio > Plugins > spring-boot`.
3. Double-click `spring-boot:run`.

Or use the terminal inside IntelliJ:

```bat
mvn -pl PORTOFOLIO spring-boot:run
```

For XAMPP MySQL:

```bat
mvn spring-boot:run "-Dspring-boot.run.profiles=xampp"
```

If IntelliJ gives `ClassNotFoundException: com.yvonne.portfolio.PortfolioApplication`, the run configuration is missing the module classpath. Delete that run configuration and use Maven instead:

1. Open IntelliJ Terminal.
2. Make sure the terminal is in:

```text
C:\Users\user\Desktop\Portfolio
```

3. Run:

```bat
mvn -pl PORTOFOLIO spring-boot:run
```

For XAMPP MySQL:

```bat
mvn -pl PORTOFOLIO spring-boot:run "-Dspring-boot.run.profiles=xampp"
```

You can also double-click these files from the parent folder:

```text
run-portfolio-from-parent.bat
run-portfolio-xampp-from-parent.bat
```

Then open:

```text
http://localhost:8080
```

## Database Connection

The database is active only when you run with the `xampp` profile.

This project currently stores contact messages in MySQL:

```text
contact_messages
```

The portfolio content models, such as `PortfolioProfile`, `SkillCategory`, `Certificate`, `Education`, and `Experience`, are Java data used to render the site. They are not database tables yet.

To check database status in the browser, open:

```text
http://localhost:8080/api/database/status
```

When running without `xampp`, it will say the database is not active. When running with `xampp`, it will show the database name and contact-message count.

For XAMPP MySQL in IntelliJ, edit the run configuration and add this VM option:

```text
-Dspring.profiles.active=xampp
```

Then start XAMPP MySQL before running the app.

For email sending in IntelliJ, add these environment variables to the run configuration:

```text
PORTFOLIO_MAIL_ENABLED=true
PORTFOLIO_MAIL_USERNAME=mukabera255@gmail.com
PORTFOLIO_MAIL_PASSWORD=your Gmail app password
```

## Important

Do not open the old root `index.html` directly and do not serve this project through XAMPP Apache. The real application page is:

```text
src/main/resources/templates/index.html
```

Spring Boot renders that page and powers the backend routes:

```text
/api/profile
/api/contact
```
