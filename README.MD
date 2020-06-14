![Lingo logo](logo.svg)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=joostlek_lingo&metric=bugs)](https://sonarcloud.io/dashboard?id=joostlek_lingo)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=joostlek_lingo&metric=code_smells)](https://sonarcloud.io/dashboard?id=joostlek_lingo)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=joostlek_lingo&metric=coverage)](https://sonarcloud.io/dashboard?id=joostlek_lingo)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=joostlek_lingo&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=joostlek_lingo)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=joostlek_lingo&metric=ncloc)](https://sonarcloud.io/dashboard?id=joostlek_lingo)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/d3d659fdc430478bbcf816b795ef6ff7)](https://www.codacy.com/manual/joostlek/lingo?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=joostlek/lingo&amp;utm_campaign=Badge_Grade)
[![Snyk Vulnerabilities for GitHub Repo](https://img.shields.io/snyk/vulnerabilities/github/joostlek/lingo)](https://app.snyk.io/org/joostlek/project/fa4acdcd-7cdf-4ba2-a254-bc0820a19653)
[![Gradle build](https://github.com/joostlek/lingo/workflows/Gradle%20build/badge.svg)](https://github.com/joostlek/lingo/actions?query=workflow%3A%22Gradle+build%22)
[![Build and publish docker container](https://github.com/joostlek/lingo/workflows/Build%20and%20publish%20docker%20container/badge.svg)](https://github.com/joostlek/lingo/actions?query=workflow%3A%22Build+and+publish+docker+container%22)
[![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=joostlek/lingo)](https://dependabot.com)

Lingo is a word guessing game powered by Spring boot.

## 🚀 Setup

1. Clone this repo with `git clone https://github.com/joostlek/lingo`.
2. Set the environment variables as displayed below.
3. If you're using Windows do `gradlew.bat clean bootRun` and if you're on a UNIX based system then do `./gradlew clean bootRun`

The project can also be run through Docker. The github package repository stores the image. You can find it [here](https://github.com/joostlek/lingo/packages/252422).

## 📚 Documentation

API documentation is available [here](https://documenter.getpostman.com/view/2998810/SzzheJhN).

## 🔢 Environment variables

| Variable       | Required | Description                                 | Example value                            |
|----------------|----------|---------------------------------------------|------------------------------------------|
| `DB_URL`       | True     | The database url to connect to.             | `jdbc:postgresql://localhost:5423/lingo` |
| `DB_USER`      | True     | The user used to login on the database.     | `postgres`                               |
| `DB_PASS`      | True     | The password used to login on the database. | `hunter2`                                |
| `PORT`         | False    | The port lingo will run on. (default: 8080) | `8080`                                   |
| `STORAGE_TYPE` | False    | The type of storage lingo will use.         | `IN_MEMORY` (default) / `DATABASE`       |

## 🛡 Security

To keep the project secure, we use various measures to protect our code from vulnerabilities. 

 - [Snyk](https://app.snyk.io/org/joostlek/project/fa4acdcd-7cdf-4ba2-a254-bc0820a19653) keeps track of possible CVE's in our application.
 - Dependabot keeps track of possible dependency updates and makes PRs for them.
 