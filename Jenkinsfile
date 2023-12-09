pipeline {
    agent any

    environment {
        DOCKER_REPO = "harbor.adexassesment.com/"
        IMAGE_NAME = "adex-assesment/nodejs-frontend"
        IMAGE_TAG = 'DEV'.${BUILD_NUMBER}
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
    }

    post {
        always {
            // Clean up - logout from Docker repository
            script {
                sh "docker logout $DOCKER_REPO"
            }
        }
    }
}
