pipeline {
	agent any

    stages {
		stage("Checkout") {
			steps {
				checkout scm  // Forces Git checkout explicitly
            }
        }
		stage("Build") {
			steps {
				echo "building the application"  // Fixed quotes
            }
        }

        stage("Test") {
			steps {
				echo "testing the application"  // Fixed quotes
            }
        }

        stage("Deploy") {
			steps {
				echo "deploying the application"  // Fixed quotes
            }
        }
    }
}