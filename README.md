# coder-heroes-be-a

### Basic Java API Scaffold

Welcome to your `Coder Heroes`. Use this to start your own Greenfield Project using java, spring, and common industry standards.

This repository assumes a handful of industry practices and standards. We strive to keep you on the bleeding edge of the industry and as a result, we have made some opinions for you so that you don't have to; you're welcome.

## Requirements

Labs teams must follow all [Labs Engineering Standards](https://bloomtechlabs.gitbook.io).


## Getting Started

### Enviornment Variables

- `awsaccesskey` - key to access aws instance local/deployed
- `awssecretkey` - authorization token for aws instance (eg. SUPERSECRET)

See application-dev.properties for example values

### Running Locally

#### Prerequisites

- Java 11
- Docker [installation instructions](https://docs.docker.com/compose/install/) (preferred)
- Postgres [installation instructions](https://www.postgresql.org/download/) (optional)

#### Running

- Create a `secrets.properties` file in the src/main/java/resources directory. This file should contain the following environment variables:

```
db.username=postgres 
db.password=postgres 
auth0.id=
```
*Note*: You can find the `auth0 id` in the pinned tab in the slack channel called Product Resources.
auth0.id is the id part of `auth0.domain` which will look something like this: `dev-{id goes here}.auth0.com`.
So if id is `123456789`, then auth0.domain will be `dev-123456789.auth0.com`.
- Make sure docker service is running
    - On mac, run `open -a Docker.app` to open the docker app and start the service
    - On windows, **TODO**
- Run the command `docker-compose up` to start the database (make sure you are in the directory with the docker-compose.yml file)
- Run the main method in the `CoderHeroesBeAApplication` class or run `./gradlew bootRun` from the command line

### Troubleshooting

- If you are having issues with the database, try running `docker-compose down` and then `docker-compose up` to restart the database.
- Check to see docker is running by running `docker ps` in the command line. If you get an error, then docker is not running.
- Check to see if your `secrets.properties` file is in the correct directory and has the correct environment variables.
- Check to see if your `application-dev.properties` file has the correct environment variables.



