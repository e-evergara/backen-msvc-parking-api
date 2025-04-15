pipeline{

		agent {
		    label 'Slave_Induccion'
		}


		triggers {
           pollSCM('@hourly')
		}

		tools {
            jdk 'JDK8_Centos'
            gradle 'Gradle5.0_Centos'
		}

		options {
			buildDiscarder(logRotator(numToKeepStr: '5'))
			disableConcurrentBuilds()
		}

		environment {
            PROJECT_PATH_BACK = './'
		}

		stages{

			stage('Checkout') {
				steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                checkout([$class: 'GitSCM', branches: [[name: 'master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'BackendParking']], gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'Github_evergara', url: 'https://github.com/e-evergara/backen-msvc-parking-api.git']]])
				}
			}


			stage('Compile'){
				parallel {
					stage('Compile backend'){
						steps{
							echo "------------>Compilación backend<------------"
							dir("${PROJECT_PATH_BACK}"){
								sh 'gradle build -x test'
							}
						}

					}
				}
			}
			stage('Test Unitarios -Cobertura'){
				parallel {
					stage('Test- Cobertura backend'){
						steps {
							echo '------------>test backend<------------'
							dir("${PROJECT_PATH_BACK}"){
								sh 'gradle --stacktrace test'

							}
						}
					}
				}
			}

			stage('Sonar Analysis'){
				steps{
					echo '------------>Analisis de código estático<------------'
					  withSonarQubeEnv('Sonar') {
                        sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=./sonar-project.properties"
                     }
				}
			}



		}
		post {
			failure {
				mail(to: 'emerson.vergara@gmail.com.co',
				body:"Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/nPlease go to ${env.BUILD_URL} and verify the build",
				subject: "ERROR CI: ${env.JOB_NAME}")
			}
		}

}