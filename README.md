# Audsat Insurance company API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/h2-%23316192.svg?style=for-the-badge&logo=h2&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

This project is an API built using **Java, Java Spring, H2 as the database and Spring Security and JWT for authentication control.**
## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)

## Installation

```bash
  git clone https://github.com/Eduardo-Silva-dev/audsat-seguradora

  docker build -t audsat-seguradora-api.jar .

  cd docker

  docker-compose up -d
```

## Usage

1. Start the application with Docker
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

| URL   | Method       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `http://localhost:8080/swagger-ui/index.html#/` | `GET` | URL to API Documentation |

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
USER -> Default user role for logged in users.
ADMIN -> Administrator role for managers.
```
To access protected endpoints, provide the appropriate authentication credentials in the request header.
## Database
The project utilizes [H2](https://www.h2database.com/html/main.html) as the database. 