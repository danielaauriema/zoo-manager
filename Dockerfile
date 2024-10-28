FROM gradle:8.10-jdk23
COPY . /zoo
WORKDIR /zoo
RUN gradle clean build
CMD gradle -q --console plain run