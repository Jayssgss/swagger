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
					// Use the deployment key to push to the deploy repo
                    withCredentials([sshUserPrivateKey(
                        credentialsId: 'swagger-jenkins-deploy-key',
                        keyFileVariable: 'SSH_KEY'
                    )]) {
						sh '''
                        git clone git@github.com:Jayssgss/swagger-jenkins-deploy.git
                        cd swagger-jenkins-deploy
                        echo "Triggering deployment for commit $(git rev-parse --short HEAD)" > trigger.txt
                        git add trigger.txt
                        git config user.name "Jenkins CI"
                        git config user.email "jenkins@example.com"
                        git commit -m "Trigger deployment for new build"
                        GIT_SSH_COMMAND="ssh -i $SSH_KEY" git push origin main
                        '''
                    }
                }
            }
        }
    }
}