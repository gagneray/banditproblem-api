# BanditProblem-API

Banditproblem-api is a rest API exposing a resource to perform a multi-armed bandits testbed as described [here](https://github.com/gagneray/reinforcement-learning/blob/main/bandit-problem/README.md)

## Build and run

Use [Apache Maven](https://maven.apache.org/) and JDK 11 to build the project.

- Build
```bash
mvn clean package
```

- Run
```bash
mvn spring-boot:run
```

## Use Docker

Build a docker image using jib maven plugin
```bash
mvn package jib:dockerBuild
```
or using the Dockerfile
```bash
docker build . -t gagneray/banditproblem-api
```

Run the api in a docker container
```bash
docker run -p 8080:8080 gagneray/banditproblem-api
```

## Usage

Call the only one GET endpoint at the moment (documentation in progress...) to perform a testbed on this url and provide a json configuration as request body :
```
localhost:8080/testbed
```
Example of json configuration : 
```json
{
  "policies" : [ 
    {
    "name" : "E_GREEDY",
    "epsilon" : 0.1
    },
    {
    "name" : "UCB",
    "c" : 2
    }
  ],
  "banditProblemCount" : 2000,
  "k" : 10,
  "totalSteps" : 1000
}
```

Anonymous call is authorized but restricted according to [property values](/src/main/resources/application.properties). Authenticated access allows to perform a testbed without restriction. Use a basic authentication to do so, with ```user``` as login and the security password that can be found in the application logs on startup. 


## License
[MIT](https://choosealicense.com/licenses/mit/)