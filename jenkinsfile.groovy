pipeline {
  agent any
  tools {
    maven 'Maven3' // name of Maven tool in Jenkins
    jdk 'OpenJDK11'
  }
  stages {
    stage('Checkout') {
      steps {
        git branch: 'CaseStudy_Advanced', url: 'https://your.git.repo/your-repo.git'
      }
    }
    stage('Build') {
      steps {
        sh 'mvn -B clean test'
      }
    }
    stage('Publish Allure') {
      when { expression { fileExists('target/allure-results') } }
      steps {
        // if Allure plugin installed in Jenkins:
        allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
      }
    }
    stage('Archive Reports') {
      steps {
        archiveArtifacts artifacts: 'target/screenshots/**/*, target/surefire-reports/**', fingerprint: true
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/**/*.xml'  // if tests produce XMLs
    }
    failure {
      mail to: 'you@example.com', subject: "Build failed: ${currentBuild.fullDisplayName}", body: "See Jenkins details: ${env.BUILD_URL}"
    }
  }
}
