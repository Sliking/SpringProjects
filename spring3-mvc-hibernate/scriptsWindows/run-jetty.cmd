@echo off
set MAVEN_OPTS=-Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m -Xdebug -Xnoagent -Djava.compiler=NONE -agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n
call clear-log.cmd
cls
mvn jetty:run