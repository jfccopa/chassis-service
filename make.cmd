@echo off
goto :init

REM command list
:help
	echo run                 Run the jar locally, without using docker
	echo build               Build and create executable
	echo install             Executes mvn clean install to run the full maven lifecycle
	echo fast-install        Same as install, but does execute tests or static analysis
	echo up                  Starts applications and dependencies
	echo logs                Shows applications and dependencies logs
	echo start               Start project without build
	echo stop                Stops applications
	echo down                Shutdown applications and dependencies
	echo integration-test    Executes integration-tests from a docker container
	goto :eof

:run
	call java -jar target/app.jar
	goto :eof
	
:build
	call ./mvnw -T 4 clean package -U -DskipTests=true
	goto :eof

:install
	call ./mvnw -T 4 clean install -U
	goto :eof

:fast-install
	call ./mvnw -T 4 clean install -U -DskipTests=true
	goto :eof

:up 
	call docker-compose --project-name %PROJECT_NAME% up -d --build
	goto :eof

:logs
	call docker-compose --project-name %PROJECT_NAME% logs -f
	goto :eof

:start
	call docker-compose --project-name %PROJECT_NAME% start
	goto :eof

:stop
	call docker-compose --project-name %PROJECT_NAME% stop
	goto :eof

:down
	call docker-compose --project-name %PROJECT_NAME% down
	call docker-compose --project-name %PROJECT_NAME% kill
	call docker-compose --project-name %PROJECT_NAME% rm -f
	goto :eof

:integration-test:
	call docker run --rm \
		--network %DOCKER_NETWORK% \
		-v %MAVEN_DIR%:/root/.m2 \
		-v %WORKING_DIR%:%WORKING_DIR% \
		-w %WORKING_DIR% \
		maven:3-jdk-13 \
		./mvnw -B test-compile failsafe:integration-test failsafe:verify
	goto :eof
REM end - command list

REM main program
:init
	set "PROJECT_NAME=threetrack"
	set "DOCKER_NETWORK=%PROJECT_NAME%_default"
	set "MAVEN_DIR= %HOME%/.m2"
	set "WORKING_DIR=%~dp0"
	set "Commands[0]=1"
	set /A "CommandIndex=0"

:parse
	if "%~1"=="" goto :validate

	set "Commands[%CommandIndex%]=%~1"
	set /A "CommandIndex = %CommandIndex% + 1"

	shift
	goto :parse

:validate
	if %CommandIndex%==0 call :help & goto :end

:main
	setlocal enabledelayedexpansion

	set /A "length = %CommandIndex% - 1"

	for /l %%n in (0,1,%length%) do (
		call :!Commands[%%n]!
	)

:end
	call :cleanup
	exit /B

:cleanup
	set "__PROJECT_NAME="
	set "Commands[0]="
	set "CommandsIndex="

	goto :eof	
REM end - main program
