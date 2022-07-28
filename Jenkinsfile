pipeline {
    agent any
    stages {
        stage('install puppet') {
            steps {
              ansiblePlaybook colorized: true, credentialsId: 'smenon', disableHostKeyChecking: true, installation: 'ansible', inventory: '/etc/ansible/hosts', playbook: '/etc/ansible/install_puppet.yml'
            }
          }
        }
      }
