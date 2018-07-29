FROM maven:3.5.4-jdk-9-slim
LABEL name="varun"

# Get the utility tools and xvfb
RUN apt-get update && apt-get install -y \
    software-properties-common \
    unzip \
    curl \
    xvfb \
    wget \
    gnupg \
    libgconf-2-4

# Install Chrome, etc
RUN mkdir -p /usr/share/man/man1 && \
    (echo "deb http://http.debian.net/debian jessie-backports main" > /etc/apt/sources.list.d/backports.list) && \
    apt-get update && \
    apt-get install -y xvfb wget ca-certificates-java -t jessie-backports openjdk-8-jre-headless openjdk-8-jdk-headless && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - &&\
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    dpkg --unpack google-chrome-stable_current_amd64.deb && \
    apt-get install -f -y && \
    apt-get clean && \
    rm google-chrome-stable_current_amd64.deb && \
    mkdir /frontend-tests



# RUN rm /etc/apt/sources.list.d/google-chrome.list &&\
#     rm -rf /var/lib/apt/lists/* /var/cache/apt/*

# Install Chrome WebDriver
ENV CHROME_DRIVER_VERSION=2.40
RUN wget --no-verbose http://chromedriver.storage.googleapis.com/${CHROME_DRIVER_VERSION}/chromedriver_linux64.zip -P ~/ && \
    unzip ~/chromedriver_linux64.zip -d ~/ && \
    rm ~/chromedriver_linux64.zip && \
    mv -f ~/chromedriver /usr/local/bin/chromedriver && \
    chown root:root /usr/local/bin/chromedriver && \
    chmod 0755 /usr/local/bin/chromedriver && \
    ln -s /usr/local/bin/chromedriver /usr/bin/chromedriver


# Firefox browser to run the tests
RUN apt-get install -y firefox
 
# Gecko Driver
ENV GECKODRIVER_VERSION=0.21.0
RUN wget --no-verbose -O /tmp/geckodriver.tar.gz https://github.com/mozilla/geckodriver/releases/download/v$GECKODRIVER_VERSION/geckodriver-v$GECKODRIVER_VERSION-linux64.tar.gz \
    && rm -rf /opt/geckodriver \
    && tar -C /opt -zxf /tmp/geckodriver.tar.gz \
    && rm /tmp/geckodriver.tar.gz \
    && mv /opt/geckodriver /opt/geckodriver-$GECKODRIVER_VERSION \
    && chmod 755 /opt/geckodriver-$GECKODRIVER_VERSION \
    && ln -fs /opt/geckodriver-$GECKODRIVER_VERSION /usr/bin/geckodriver \
    && ln -fs /opt/geckodriver-$GECKODRIVER_VERSION /usr/bin/wires
 
 
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

WORKDIR /frontend-tests
ADD . / ./

ENV DBUS_SESSION_BUS_ADDRESS=/dev/null
RUN ["chmod", "+x", "/frontend-tests"]
CMD ["mvn", "install"]
