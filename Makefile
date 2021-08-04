MVN_CMD				:= mvn -T 4C
PROJECT_NAME		?= $(shell basename `pwd` | tr '[:upper:]' '[:lower:]' | tr -d '[:punct:]')
DOCKER_NETWORK		?= $(PROJECT_NAME)_default
MAVEN_DIR			?= $(HOME)/.m2
WORKING_DIR			?= $(shell pwd)

.DEFAULT_GOAL := help

build: ## Build and create excecutable
	@$(MVN_CMD) clean package -U -DskipTests=true

install: ## Executes mvn clean install to run the full maven lifecycle
	@$(MVN_CMD) clean install -U

fast-install: ## Same as install, but does execute tests or static analysis
	@$(MVN_CMD) clean install -U -DskipTests=true

up: ## Starts applications and dependencies
	@docker-compose --project-name $(PROJECT_NAME) up -d --build --scale app=1

logs: ## Shows applications and dependencies logs
	@docker-compose --project-name $(PROJECT_NAME) logs -f

start: ## start project without build
	@docker-compose --project-name $(PROJECT_NAME) start || true

stop: ## stop
	@docker-compose --project-name $(PROJECT_NAME) stop || true

down: ## Shutsdown applications and dependencies
	@docker-compose --project-name $(PROJECT_NAME) down || true
	@docker-compose --project-name $(PROJECT_NAME) kill || true
	@docker-compose --project-name $(PROJECT_NAME) rm -f || true

integration-test: ## Executes integration-tests from a docker container
	@docker run --rm \
		--network $(DOCKER_NETWORK) \
		-v $(MAVEN_DIR):/root/.m2 \
		-v $(WORKING_DIR):$(WORKING_DIR) \
		-w $(WORKING_DIR) \
		maven:3-jdk-13 \
		mvn -B test-compile failsafe:integration-test failsafe:verify

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: fast-install install dep-multiple-version up logs down integration-test rebuild-and-up help