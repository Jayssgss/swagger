pipeline {
	agent any

    stages {
		stage("Checkout") {
			steps {
				git url: 'https://github.com/Jayssgss/swagger-jenkins.git', branch: 'main'
            }
        }
		stage("Build") {
			steps {
				echo "building the application"
            }
        }

        stage("Test") {
			steps {
				echo "testing the application"
            }
        }

        stage("Deploy") {
			steps {
				echo "deploying the application"
            }
        }
    }
}