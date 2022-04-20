node {
    stage("checkout repo") {
        git branch: 'master',
            credentials: 'ddf1c6a3-dc71-4bd8-846b-257e838f73ae',
            url: 'https://github.com/avoevodin81/api-training-socks.git'
    }

    stage("build") {
        sh "mvn compile"
    }

    stage("run api tests") {
        sh "mvn test"
    }

}