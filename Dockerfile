FROM openjdk:8

# Set MongoDB environment variables
ENV MONGODBHOSTNAME=localhost
ENV MONGODBPORT=27017
ENV MONGODBNAME=mydatabase
ENV MONGOUSERNAME=username
ENV MONGOUPASSWORD=password
ENV MONGOURI=mongodb://username:password@mongodb-service:27017/database

# Add the JAR file to the image
ADD target/*.jar applicationjar.jar

# Expose the port
EXPOSE 8080

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "applicationjar.jar"]