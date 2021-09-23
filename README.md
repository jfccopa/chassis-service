# chassis-service

template to build web-service

# Resources

- java 11
- docker
- docker-compose >=3.1
- make (optional)

# build

## build **3track** java application (mvnw)
```
./mvnw -T 4 clean install -U -DskipTests=true
```

## up **3track** project (docker-compose)
```
docker-compose project-name 3track up -d --build
```

## shutdown **3track** project (docker-compose)
```
docker-compose --project-name 3track down || true
docker-compose --project-name 3track kill || true
docker-compose --project-name 3track rm -f || true
```

## using make
```
make down fast-install up
```