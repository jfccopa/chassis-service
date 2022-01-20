# chassis-service

template to build web-service

# Resources

- java 11
- docker
- docker-compose >=3.1

# Build

use **make** in linux and **./make** for windows

### only build and run service
```
make fast-install up
```

### init all applications
```
make start
```

### stop all applications
```
make stop
```

### rebuild and run all applications
```
make down fast-install up
```
## Docker commands

### Logs of a docker 
```
docker logs -f <docker_name>
```

### Enter bash container, console 
```
docker exec -it <docker_name> /bin/sh
```

### Enter DB with bash 
```
psql -U <user_name> <db_name> 
psql -U 3track threetrack_db
```
### Query DB example 
```
SELECT * FROM AD_LOGIN;
```

# Api Documentation
```
http://localhost:80/swagger-ui.html
```