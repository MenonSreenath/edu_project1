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
              ansiblePlaybook colorized: true, credentialsId: 'smenon', disableHostKeyChecking: true, installation: 'ansible', inventory: '$hosts', playbook: 'install_ansible.yml'
            }
        }
    }
}
