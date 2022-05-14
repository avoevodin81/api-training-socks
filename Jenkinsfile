pipeline {
    agent any
    tools {
        maven "MAVEN"
    }
    stages {
        stage("checkout repo") {
            steps {
                git branch: 'master',
                    credentialsId: 'cd25dd89-041f-4d30-a249-d1ec63c92df9',
                    url: 'https://github.com/avoevodin81/api-training-socks.git'
            }
        }

        stage('Initialize') {
            steps {
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
            }
        }

        stage("build") {
            steps {
                sh 'mvn compile'
            }
        }

        stage("run api tests") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh 'mvn -pl api-training test -Dlogging=${LOGGING}'
                }
            }
        }

        stage("run ui tests") {
                    steps {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            sh 'mvn -pl ui-training test -Dlogging=${LOGGING}'
                        }
                    }
                }

        stage("reports") {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'api-training/target/allure-results'],
                                  [path: 'ui-training/target/allure-results']]
                    ])
                }
            }
        }

    }
}