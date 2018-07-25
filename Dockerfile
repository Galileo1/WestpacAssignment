FROM maven:3.5.4-jdk-9-slim
LABEL name="varun"

RUN  apt-get update && \
    (echo "deb http://http.debian.net/debian jessie-backports main" > /etc/apt/sources.list.d/backports.list) && \
    mkdir -p /usr/share/man/man1 && \
    apt-get install -y xvfb wget ca-certificates-java -t jessie-backports openjdk-8-jre && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    dpkg --unpack google-chrome-stable_current_amd64.deb && \
    apt-get install -f -y && \
    apt-get clean && \
    rm google-chrome-stable_current_amd64.deb && \
    mkdir /frontend-tests
    
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

WORKDIR /frontend-tests
ADD . / ./

ENV DBUS_SESSION_BUS_ADDRESS=/dev/null
RUN ["chmod", "+x", "/frontend-tests"]
RUN ["mvn", "install"]
