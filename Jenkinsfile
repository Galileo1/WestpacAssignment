#!/usr/bin/env groovy
pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '30'))
        timeout(time: 1, unit: 'HOURS')
    }

    environment {
        TAG = "e2e-tests_${env.BUILD_NUMBER}"
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean up') {
            steps {
                //clean up
                sh('docker ps -q --filter "name=test-scripts" | grep -q . && docker stop test-scripts && docker rm test-scripts')
                sh('docker ps -q --filter "name=selenium-hub" | grep -q . && docker stop selenium-hub && docker rm selenium-hub')
                sh('docker ps -q --filter "name=chrome-node" | grep -q . && docker stop chrome-node && docker rm chrome-node')
            }
        }

        stage('Run Tests') {
            steps {
        
                //Build, create and start containers in a background.project can be found using TAG
                sh("docker-compose -p ${TAG} up -d --build")
                sh("docker-compose -p ${TAG} run")
            
                //Stop and remove the containers
                sh("docker-compose -p ${TAG} down")
                
            }
        }
    }
}

