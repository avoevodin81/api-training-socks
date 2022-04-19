pipeline {
    agent any
    tools {
        maven "MAVEN"
        jdk "JDK"
    }
    stages {
        stage("checkout repo") {
            steps {
                git branch: 'master',
                    credentialsId: 'ddf1c6a3-dc71-4bd8-846b-257e838f73ae',
                    url: 'https://github.com/avoevodin81/api-training-socks.git'
            }
        }

        stage('Initialize') {
            steps {
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
                echo "JAVA_HOME = /usr/java/bin"
            }
        }

        stage("build") {
            steps {
                sh 'mvn compile'
            }
        }

        stage("run api tests") {
            steps {
                sh 'mvn test'
            }
        }
    }

}