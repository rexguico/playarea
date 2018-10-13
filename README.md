# playarea

Play framework api documentation
https://www.playframework.com/documentation/2.6.x/api/java/overview-summary.html

Akka
https://akka.io

Mockito -> mocking
https://site.mockito.org

Google Guice -> DI
https://github.com/google/guice/wiki/GettingStarted

PREPARE BUILD

sbt dist
set -x
unzip -d svc target/universal/*-1.0-SNAPSHOT.zip
mv svc/*/* svc/
rm svc/bin/*.bat
mv svc/bin/* svc/bin/start

docker build -t playarea .
docker run -it -p 9000:9000 -p 9443:9443 --rm playarea

