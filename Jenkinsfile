pipeline {
    agent any

    stages {

        stage('build') {
            steps {
              bat '''
                 cd customer-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              bat '''
                 cd customer-service
                     ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              bat '''
                 cd customer-service
                     ./mvnw -DskipTests install
              '''
            }
        }
                stage('build') {
            steps {
              bat '''
                 cd proudct-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              bat '''
                 cd proudct-service
                     ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              bat '''
                 cd proudct-service
                     ./mvnw -DskipTests install
              '''
            }
        }
                stage('build') {
            steps {
              bat '''
                 cd admin-edge-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              bat '''
                 cd admin-edge-service
                     ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              bat '''
                 cd admin-edge-service
                     ./mvnw -DskipTests install
              '''
            }
        }
                stage('build') {
            steps {
              bat '''
                 cd invoice-crud-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              bat '''
                 cd invoice-crud-service
                     ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              bat '''
                 cd invoice-crud-service
                     ./mvnw -DskipTests install
              '''
            }
        }
                stage('build') {
            steps {
              bat '''
                 cd levelup-crud-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              bat '''
                 cd levelup-crud-service
                     ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              bat '''
                 cd levelup-crud-service
                     ./mvnw -DskipTests install
              '''
            }
        }
                stage('build') {
            steps {
              bat '''
                 cd retail-edge-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              bat '''
                 cd retail-edge-service
                     ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              bat '''
                 cd retail-edge-service
                     ./mvnw -DskipTests install
              '''
            }
        }

    }
}
