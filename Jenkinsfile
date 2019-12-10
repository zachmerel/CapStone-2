pipeline {
    agent any

    stages {

        stage('build') {
            steps {
              bat '''
                 cd config-server
                 ./mvnw -DskipTests clean compile
                 cd eureka-service-registry
                 ./mvnw -DskipTests clean compile
                 cd customer-service
                 ./mvnw -DskipTests clean compile
                 cd proudct-service
                 ./mvnw -DskipTests clean compile
                 cd invoice-crud-service
                 ./mvnw -DskipTests clean compile
                 cd levelup-crud-service
                 ./mvnw -DskipTests clean compile
                 cd admin-edge-service
                 ./mvnw -DskipTests clean compile
                  cd retail-edge-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              bat '''
                  cd config-server
                 ./mvnw test
                 cd eureka-service-registry
                 ./mvnw test
                 cd customer-service
                 ./mvnw test
                 cd proudct-service
                 ./mvnw test
                 cd invoice-crud-service
                 ./mvnw test
                 cd levelup-crud-service
                 ./mvnw test
                 cd admin-edge-service
                 ./mvnw test
                  cd retail-edge-service
                ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              bat '''
                 cd config-server
                 ./mvnw -DskipTests install
                 cd eureka-service-registry
                 ./mvnw -DskipTests install
                cd customer-service
                 ./mvnw -DskipTests install
                 cd proudct-service
                 ./mvnw -DskipTests install
                 cd invoice-crud-service
                 ./mvnw -DskipTests install
                 cd levelup-crud-service
                 ./mvnw -DskipTests install
                 cd admin-edge-service
                 ./mvnw -DskipTests install
                  cd retail-edge-service
                ./mvnw -DskipTests install
 
              '''
            }
        }

}
}
