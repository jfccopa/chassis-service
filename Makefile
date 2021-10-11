PROJECT_NAME ?= threetrack
DOCKER_NETWORK		?= $(PROJECT_NAME)_default
MAVEN_DIR			?= $(HOME)/.m2
WORKING_DIR			?= $(shell pwd)

.DEFAULT_GOAL := help

run: ## Run the jar locally, without using docker
	@java -jar target/app.jar
	
build: ## Build and create executable
	@./mvnw -T 4 clean package -U -DskipTests=true

install: ## Executes mvn clean install to run the full maven lifecycle
	@./mvnw -T 4 clean install -U

fast-install: ## Same as install, but does execute tests or static analysis
	@./mvnw -T 4 clean install -U -DskipTests=true

up: ## Starts applications and dependencies
	@docker-compose --project-name $(PROJECT_NAME) up -d --build

logs: ## Shows applications and dependencies logs
	@docker-compose --project-name $(PROJECT_NAME) logs -f

start: ## Start project without build
	@docker-compose --project-name $(PROJECT_NAME) start

stop: ## Stop
	@docker-compose --project-name $(PROJECT_NAME) stop

down: ## Shutdown applications and dependencies
	@docker-compose --project-name $(PROJECT_NAME) down
	@docker-compose --project-name $(PROJECT_NAME) kill
	@docker-compose --project-name $(PROJECT_NAME) rm -f


integration-test: ## Executes integration-tests from a docker container
	@docker run --rm \
		--network $(DOCKER_NETWORK) \
		-v $(MAVEN_DIR):/root/.m2 \
		-v $(WORKING_DIR):$(WORKING_DIR) \
		-w $(WORKING_DIR) \
		maven:3-jdk-13 \
		./mvnw -B test-compile failsafe:integration-test failsafe:verify

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: fast-install install dep-multiple-version up logs down integration-test rebuild-and-up help