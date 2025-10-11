# Usamos una imagen oficial de Tomcat 10.1 con Java 17
FROM tomcat:10.1-jdk17

# Borramos las apps por defecto para empezar de cero
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiamos tu archivo .war a la carpeta de despliegue de Tomcat como ROOT.war
# para que sea la aplicación principal.
# Asegúrate de que el nombre del .war coincida con el que genera Maven.
COPY target/blog-mensajes-nuevo.war /usr/local/tomcat/webapps/ROOT.war
