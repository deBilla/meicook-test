FROM openjdk:17-alpine AS BUILD_IMAGE

WORKDIR /usr/src/bankAccount
COPY . .

RUN chmod +x gradlew
RUN apk update && apk add findutils
RUN ./gradlew clean build

FROM openjdk:17-alpine
WORKDIR /usr/
COPY --from=BUILD_IMAGE /usr/src/bankAccount/build/libs/bankAccount-0.0.1-SNAPSHOT.jar .
EXPOSE 8082
CMD ["java","-jar","bankAccount-0.0.1-SNAPSHOT.jar"]