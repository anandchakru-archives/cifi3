import groovy.json.JsonSlurperClassic
import java.security.*
import java.util.Base64

pipeline {
    agent any
	tools {
        maven 'maven353'
        jdk 'java8172'
    }
    environment{
    	ARTIVER = "v1.${BUILD_NUMBER}"
    }
    stages {
    	stage('init') {
    		steps{
    			sh '''
    				echo "PATH				: ${PATH}"
    				echo "M2_HOME 			: ${M2_HOME}"
    				echo "Build Number		: $BUILD_NUMBER"
    				echo "Artifact Version	: ${ARTIVER}"
    			'''
    		}
    	}
        stage('test') {
        	steps{
	            echo 'Testing..'
	            sh 'mvn clean surefire-report:report site -DgenerateReports=false'
            }
        }
        stage('build') {
        	steps{
	            echo 'Building..'
	            sh 'mvn package -Dcifi.version=${ARTIVER} -DskipTests=true -Dmaven.javadoc.skip=true -B -V'
            }
        }
        stage('tag') {
        	steps{
				echo 'Releasing..'
				script {
					// Create New Release
					// Set @ http://192.168.1.7:8080/configure -> Global properties -> Environment variables -> Add -> GITHUB_OAUTH_TOKEN
					response = sh (
					  script: 'curl -H "Content-Type: application/json" -X POST -d \'{ "tag_name": "\'"${ARTIVER}"\'", "target_commitish": "master", "name": "\'"${ARTIVER}"\'", "body": "Jenkins: \'"${ARTIVER}"\'", "draft": false, "prerelease": false }\' https://api.github.com/repos/anandchakru/cifi3/releases?access_token=${GITHUB_OAUTH_TOKEN}',
					  returnStdout: true
					).trim()
					def rsp = new JsonSlurperClassic().parseText(response)

					//Upload jar
					uploadCmd = 'curl -H "Authorization: token ${GITHUB_OAUTH_TOKEN}" -H "Content-Type: application/zip" -T target/cifi3-1.0.0.jar -X POST https://uploads.github.com/repos/anandchakru/cifi3/releases/' + rsp.id + '/assets?name=cifi3-1.0.0.jar'
					response2 = sh (
					  script: uploadCmd,
					  returnStdout: true
					).trim()
					def rsp2 = new JsonSlurperClassic().parseText(response2)
					println 'rsp2: ' + response2

					commitId = sh (
						script: 'git log -n 1 --pretty=format:%H',
						returnStdout: true
					)
					def payld = $/ '{"assetId":"${rsp2.id}", "assetUrl":"${rsp2.browser_download_url}","commitId":"${commitId}", "status":"BUILT", "version":"${rsp.id}", "tag":"${ARTIVER}","appId":"1"}' /$
					def signature = sh (
						script: 'echo -n '+payld+' | openssl dgst -sha1 -hmac "haPR6C6ltR1ziwritrofuSpo79QI323o8e2Hebltl4l8evlpHisPuf3soNlJlF9Y" | sed "s/(stdin)= /sha1=/" ',
						returnStdout: true
					).trim()
					def whCmd = 'curl -H "Content-Type: application/json" -H "X-Hub-Signature: '+signature+'" -X POST -d '+ payld +' https://api.jrvite.com/webhook/cifi-pipe'
					println whCmd
					response3 = sh (
						script: whCmd,
					  	returnStdout: true
					).trim()
					println 'rsp3: ' + response3
				}
			}
		}
        stage('e2e') {
            steps {
                echo 'TODO: Automated End to end testing..'
            }
        }
        stage('deploy') {
            steps {
                echo 'TODO: Automated deploy to test..'
            }
        }
    }
}
