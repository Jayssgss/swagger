pipeline {
	agent any

    stages {
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

        stage("Trigger CD") {
			steps {
				script {
					sshagent(['swagger-jenkins-deploy-key']) {
						sh '''
                        git clone git@github.com:Jayssgss/swagger-jenkins-deploy.git
                        cd swagger-jenkins-deploy
                        echo "Triggering deployment for $(git rev-parse --short HEAD)" > trigger.txt
                        git add trigger.txt
                        git config user.name "Jenkins CI"
                        git config user.email "jenkins@example.com"
                        git commit -m "Trigger deployment"
                        git push origin main
                        '''
                    }
                }
            }
        }
    }
}