pipeline {
    agent any
    environment {
        DOCKER_REPO = "harbor.adexassesment.com"
        // adex-assesment-dev --> dev project in harbor
        IMAGE_NAME = "adex-assesment-dev/nodejs-frontend"
        IMAGE_TAG = "DEV.${BUILD_NUMBER}"
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image
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
                    
                    sh "BUILD_NUMBER_ENV=$IMAGE_TAG bash /var/lib/jenkins/argoscript.sh  nodejs/nodejs-dev/deployment.yaml"
                }
            }
        }
    }
}