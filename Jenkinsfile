pipeline {
    agent {
        node ('agent001')
    }
    stages {
        stage('Git Checkout') {
            steps {
              git branch: 'main', url: 'https://github.com/MenonSreenath/edu_project1.git'
            }
          }
        stage('install puppet') {
            steps {
                ansiblePlaybook colorized: true, credentialsId: 'smenon', disableHostKeyChecking: true, installation: 'ansible', inventory: 'hosts', playbook: 'install_puppet.yml'
            }
          }
        stage('install docker') {
            steps { 
              ansiblePlaybook colorized: true, credentialsId: 'smenon', disableHostKeyChecking: true, installation: 'ansible', inventory: 'hosts', playbook: 'install_docker.yml'
          }
         }
        stage('build and push docker') {
            steps {
              withDockerRegistry(credentialsId: 'DOCKER_HUB_LOGIN', url: 'https://index.docker.io/v1/') {
                  sh 'cd  $WORKSPACE'
                  sh 'docker build --file dockerfile --tag smenon94/dockertest:$BUILD_NUMBER .'
                  sh 'docker run -d -P -t smenon94/dockertest:$BUILD_NUMBER'
                  sh 'docker push smenon94/dockertest:$BUILD_NUMBER'
                  sh 'docker ps'  
              }
            }
          }
    }
        
         post {
         failure { 
           script {
               sh 'docker kill $(docker ps -l -q)'
      }
         }
         }
}
    
