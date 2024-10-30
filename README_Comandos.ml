Comando especializado para extraer datos del excel al .feature:
##@externaldata@./src/main/resources/data/DatosExcel.xlsx@DatosFormularioContacto

Comprimir el reporte de serenity:
zip -r Reporte-serenity.zip target/site/serenity

Validar objetos obsoletos en gradle:
gradle build --warning-mode all

Ejecutar el reporte gerencial de serenity:
En la consola que tiene gradle en la parte superior derecha, la abrimos y colocamos gradle reports y listo.

Comando ejecutar analisis SonarQube maven:
mvn clean verify sonar:sonar -Dsonar.projectKey=Analisis-del-proyecto -Dsonar.projectName="Analisis del proyecto" -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_afb2b179e40ece22acbdb5da5382c26601f73c00