# Cifi3

**Note:** Frozen - reference only

Wires up a client app with jenkins and github without exposing jenkins to github webhooks. 
Manage/automate/customize your deployments based on specific CI/CD needs.

## Run

* Ensure there is a database cifi3, if not, create it using `mysql> create database cifi3;`

* `mvn spring-boot:run -Dspring.profiles.active=local -Dlogback-debug=true -Dport=8080`

* If InspectIt for APM: `-javaagent:C:\Apps\inspectIT\agent\inspectit-agent.jar -Dinspectit.repository=localhost:9070 -Dinspectit.agent.name=Sample`

## Test

* In eclipse, right-click on `com.anandchakru.app.repo.AppRestRepoTest` _Run As_ -> JUnit Test

* Optional: _Run Configurations_ -> _Arguements_ -> Add `-Dspring.profiles.active=test` under _VM arguements_ -> Run

* To generate report: mvn clean surefire-report:report site -DgenerateReports=false

## Usage

* `java -jar cifi.jar --cifi.hostname=<hostname> --spring.profiles.active=prod,master`
* `java -jar cifi.jar --cifi.hostname=<hostname> --spring.profiles.active=prod,slave --cifi.master.server=192.168.1.18 --cifi.master.port=8080 --cifi.master.protocol=http`

## Refs:
 1. https://docs.spring.io/spring-data/rest/docs/current/reference/html/
