pipeline {
    agent any

    // 1. Definimos las herramientas que configuramos en Jenkins
    tools {
        maven 'Maven-3'   // Debe coincidir con el nombre que pusiste en "Global Tool Configuration"
        jdk 'Java-Home'   // Debe coincidir con el nombre que pusiste en "Global Tool Configuration"
    }

    // 2. Variables de entorno (Credenciales para Nexus)
    environment {
        NEXUS_CREDS = credentials('nexus-auth') 
        
        // Configuración para que Maven use un settings.xml temporal
        // Esto evita tener que configurar credenciales globales en Jenkins
        MVN_SETTINGS = 'settings-temp.xml' 
    }

    stages {
        // Paso 1: Compilación [cite: 61]
        stage('Compilar') {
            steps {
                echo '>>> Compilando el proyecto...'
                sh 'mvn clean compile'
            }
        }

        // Paso 2: Pruebas Unitarias [cite: 62]
        stage('Tests Unitarios') {
            steps {
                echo '>>> Ejecutando pruebas...'
                sh 'mvn test'
            }
        }

        // Paso 3: Análisis de Calidad con SonarQube [cite: 63]
        stage('Análisis SonarQube') {
            steps {
                echo '>>> Analizando código con SonarQube...'
                // "sonarqube-server" debe coincidir con el nombre que diste en Configuración del Sistema
                withSonarQubeEnv('sonarqube-server') {
                    // Usamos el nombre del servicio docker "sonarqube"
                    sh 'mvn sonar:sonar -Dsonar.projectKey=EjerPOO -Dsonar.projectName="EjerPOO" -Dsonar.host.url=http://sonarqube:9000'
                }
            }
        }

        // Paso 4: Publicación en Nexus [cite: 64]
        // Solo se ejecuta si los pasos anteriores (incluido los tests) fueron exitosos
        stage('Publicar en Nexus') {
            steps {
                echo '>>> Generando archivo de configuración para Nexus...'
                // Creamos un archivo settings.xml al vuelo con las credenciales
                writeFile file: "${MVN_SETTINGS}", text: """
                    <settings>
                        <servers>
                            <server>
                                <id>nexus-releases</id>
                                <username>${NEXUS_CREDS_USR}</username>
                                <password>${NEXUS_CREDS_PSW}</password>
                            </server>
                            <server>
                                <id>nexus-snapshots</id>
                                <username>${NEXUS_CREDS_USR}</username>
                                <password>${NEXUS_CREDS_PSW}</password>
                            </server>
                        </servers>
                    </settings>
                """

                echo '>>> Publicando artefacto en Nexus...'
                // Ejecutamos deploy usando ese archivo de configuración
                sh "mvn deploy -s ${MVN_SETTINGS} -DskipTests"
            }
        }
    }

    // Limpieza final
    post {
        always {
            // Borramos el archivo de configuración temporal por seguridad
            sh "rm -f ${MVN_SETTINGS}"
            cleanWs()
        }
        success {
            echo '>>> ¡Integración Continua Exitosa! Artefacto subido a Nexus.'
        }
        failure {
            echo '>>> El Pipeline ha fallado. Revisa los logs.'
        }
    }
}
