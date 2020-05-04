# ElaniinTest

Este proyecto fue realizado como una prueba para la plaza de backend developer para Elannin

El proyecto que se encuentra alojado en este repositorio consta de una API Rest para realizar operaciones básicas o CRUD sobre Productos y Usuarios. 

## Pasos para la instalación del código

- Clonar el repositorio EllaninTest
- Abrir con Spring Tool Suite 3.9.10 o superior el proyecto **ProductsBackend**
- Hacer clic derecho sobre el proyecto y seleccinar Run as... :arrow_right: Maven install
- Al finzalizarse la compilación, hacemos clic derecho sobre el proyecto y seleccionamos Show In :arrow_right: System Explorer
- Dentro de la carpeta del proyecto, buscamos la carpeta _target_ y buscamos el archivo con extensión _.war_ generado
- Renombramos el war a _ProductsBackend.war_
- Tomamos el archivo war y lo trasladamos a un servidor donde se tenga instalado Apache Tomcat 9 (o una versión que soporte aplicaciones construidas con Java 8)
- Depositar el archivo war en la carpeta _webapps_ de la instalación de Apache Tomcat, y esperamos a que el archivo se despliegue correctamente
- Una vez desplegado el archivo, buscamos dentro de la carpeta ProductsBackend (carpeta resultante del despliegue) el archivo _application.properties_ y buscar la sección **Oracle settings**. Ahí deberá modificarse en caso de ser requerido la cadena de conexión al esquema de la base de datos, el usuario y la contraseña. 
- Dentro del mismo archivo modificar la propiedad _tomcat_server_ip_ por la IP, puerto y nombre de la carpeta desplegada.
Ejemplo: **tomcat_server_ip=192.168.0.250:8080/ProductsBackend**. Esta propiedad es utilizada para que, al recibir un correo de restablecimiento de contraseña, el link funcione correctamente y el token recibido pueda ser validado.
- También, dentro de la carpeta ProductsBackend, buscar el archivo _application.yml_ y cambiar las propiedades _host_ y _password_ para usar la cuenta de Google deseada para conectarse al servidor SMTP y poder enviar correos desde el aplicativo
- Reiniciar el servicio de Apache Tomcat

**La instalación de la aplicación ha sido finalizada** :thumbsup:
