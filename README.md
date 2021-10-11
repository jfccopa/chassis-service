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