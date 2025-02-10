## DU3 - Exercise 11 - TCP Multithreaded client-server chat

We want to create a client-server chat application.

The client must send the commands entered by the user to the server.

The commands to be used are the following:

NICK <name>. This command indicates that the user wants to use the given nickname.
BCAST <msg>. The client sends a message to all other clients (broadcast).
MSG <name> <msg>. The client sends a message to the client with nickname name.
QUIT. The client informs the server that the user wants to leave.
The mesages from another users must begin with the nickname of the user followed by :. Example: maria: Hello World!

The server manages the messages and informs the clients about the operations.

The server responses are as follows:

OK NICK. Indicates that the nick the client wants to use is valid. The server must inform the other clients about new connected clients.
OK BCAST. Indicates that the broadcast operation was successful
UNKNOWN. Indicates that the command does not exist in the communication protocol.
ERROR. Indicates that the command exists in the communication protocol but its use is incorrect.
BYE. Indicates that the communication with the client is going to be closed. The server must inform the other clients about disconnected clients.
Create a package called client for the client and a package called server for the server.

Create a class ChatClient to launch the Client.

Create a ChatClientUserInput class to create threads to send the user input to the server and to receive its response.

Create a ChatServerMain class to launch the ChatServer.

Create a ChatServer to manage a list of ChatServerWorker.

Create a ChatServerWorker class to communicate to a client.



## DU3 - Ejercicio 11 - Chat cliente-servidor multihilo TCP

Queremos crear una aplicación de chat cliente-servidor.

El cliente debe enviar al servidor los comandos introducidos por el usuario.

Los comandos a utilizar son los siguientes:

NICK <nombre>. Este comando indica que el usuario quiere utilizar el nickname dado.
BCAST <msg>. El cliente envía un mensaje a todos los demás clientes (broadcast).
MSG <nombre> <msg>. El cliente envia un mensaje al cliente con nickname nombre.
QUIT. El cliente informa al servidor de que el usuario quiere salir.
Los mensajes de otros usuarios deben comenzar con el nickname del usuario seguido de :. Ejemplo: maria: ¡Hola Mundo!

El servidor gestiona los mensajes e informa a los clientes de las operaciones.

Las respuestas del servidor son las siguientes:

OK NICK. Indica que el nick que el cliente quiere utilizar es válido. El servidor debe informar a los demás clientes sobre nuevos clientes conectados.
OK BCAST. Indica que la operación de difusión se ha realizado correctamente
UNKNOWN. Indica que el comando no existe en el protocolo de comunicación.
ERROR. Indica que el comando existe en el protocolo de comunicación pero su uso es incorrecto.
BYE. Indica que se va a cerrar la comunicación con el cliente. El servidor debe informar a los demás clientes sobre los clientes desconectados.

Crea un paquete llamado cliente para el cliente y un paquete llamado servidor para el servidor.

Crear una clase ChatClient para lanzar el Cliente.

Crear una clase ChatClientUserInput para crear hilos para enviar la entrada del usuario al servidor y recibir su respuesta.

Crear una clase ChatServerMain para lanzar el ChatServer.

Crear un ChatServer para gestionar una lista de ChatServerWorker.

Crear una clase ChatServerWorker para comunicarse con un cliente.