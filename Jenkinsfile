pipeline {
    agent any
    environment {
        DOCKER_REPO = "harbor.adexassesment.com"
        IMAGE_NAME = "adex-assesment-prod/java-backend"
        IMAGE_TAG = "PROD.${BUILD_NUMBER}"
    }
    stages {
        stage('Build and Test') {
            steps {
                script {
                    // Run Maven clean install
                    sh 'mvn clean install'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t $DOCKER_REPO/$IMAGE_NAME:$IMAGE_TAG ."
                }
            }
        }
        stage('Pushing Docker Image to Harbor Reg') {
            steps {
                script {
                    // Login to Docker repository will be done from config.json file 
                    // Push Docker image
                    sh "docker push $DOCKER_REPO/$IMAGE_NAME:$IMAGE_TAG"
                }
            }

        }
        stage('Updating New Image From ArgoCD') {
                steps {
                    script {
                        sh "BUILD_NUMBER_ENV=$IMAGE_TAG bash /var/lib/jenkins/argoscript.sh  java/java-prod/deployment.yaml"
                    }
                }
            }
    }

}