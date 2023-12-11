pipeline {
    agent any
    environment {
        DOCKER_REPO = "harbor.adexassesment.com"
        IMAGE_NAME = "adex-assesment-stage/nodejs-frontend"
        IMAGE_TAG = "STAGE.${BUILD_NUMBER}"
    }
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker images
                    sh "docker build -t $DOCKER_REPO/$IMAGE_NAME:$IMAGE_TAG ."
                
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    // Login to Docker repository will be done from config.json file 
                    // Push Docker image
                    sh "docker push $DOCKER_REPO/$IMAGE_NAME:$IMAGE_TAG"
                }
            }
        }
        stage('Updating New Image') {
            steps {
                script {
                    
                    sh "BUILD_NUMBER_ENV=$IMAGE_TAG bash /var/lib/jenkins/argoscript.sh  nodejs/nodejs-stage/deployment.yaml"
                }
            }
        }
    }
}