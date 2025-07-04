# requisitos previos 
 *Docker lasted
 *Git lasted
 *Gradle lasted
 *postman
# Instalación
A continuacion se describe el proceso por el cual se descarga e inicializa los contenedores necesarios para la ejecucion de la aplicación
## Descargar repositorio
En una ruta del equipo local, ejecutar el siguiente comando `git clone https://github.com/CarlosAQCode/PruebaTecnica.git`
## Ejecucion contenedores
Una vez descargado el codigo, se debe ejecutar el archivo  ubicado en la carpeta deployment del proyecto
`cd <Ruta-del-codigo>\deployment` una vez estando en la ruta se ejecuta el siguiente comando `docker-compose up` esto ejecutara los contenedores de dynamoBD local y del rabbitMQ local Nota: ya las instancias de los dos estan configuradas no es necesario realizar alguna configuracion externa.
Esta pantalla de CMD o PowerShell no debe ser cerrada, si por error se cierra, se debe ir a la consola de docker local e inciar el contenedor de nombre 'deployment' de forma manual.
## Ejecución aplicacion
Una vez ejecutado los contenedores se debe por ir a la ruta raiz del proyecto y ejecutar el siguiente comando
`cd <Ruta-del-codigo>\`  y luego  `gradle wrapper` posteriormente ejecutar  `./gradlew bootRun`
# Pruebas
Desde postman, importar el siguiente curl: 
curl --location 'http://localhost:8080/api/Stats/Savestats' \
--header 'Content-Type: application/json' \
--data '{
    "totalContactoClientes": 250,
    "motivoReclamo": 25,
    "motivoGarantia": 10,
    "motivoDuda": 100,
    "motivoCompra": 100,
    "motivoFelicitaciones": 7,
    "motivoCambio": 8,
    "hash": "5484062a4be1ce5645eb414663e14f59"
}'

Y realizar los excenarios de pruebas requeridos

