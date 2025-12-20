# SIMULADOR CINE

Repositorio donde se guarda la aplicación del simulador de cine.
Entregable para PSP en un proyecto Maven.

## Cómo ejecutar la aplicación web

1. Ubicate en tu workspace de Eclipse para clonar esta carpeta.
2. Clona el repositorio: 
    ```bash
    git clone https://github.com/Sergiodlf/SimulacionCine.git
    ```
3. Abre el proyecto desde tu workspace y abre las Class.java ubicadas en:
   ```bash
   src/java/org/cuatrovientos/dam/psp/tamagotchisSergioDLF
   ```
4. Ejecuta "Run" para ver la aplicación en la consola.

## Composición del proyecto

- Proyecto Maven: SimulacionCine
- 5 clases java
  - Clase **`Cliente.java`**: Clase simple que define los atributos de un cliente.
  - Clase **`ColaCine.java`**: Clase con listas y métodos para gestionar los estados de los clientes.
  - Clase **`Taquilla.java`**: Clase Runnable, la cual mientras está abierta comprueba cada cola para recuperar el siguiente cliente ( si hay ) y venderle la entrada.
  - Clase **`GeneradorClientes.java`**: Clase Runnable, la cual mientras está encendido crea un cliente cada 4seg e intenta agregarlo a la siguiente cola posible.
  - Clase **`CineMain.java`**: Clase que contiene el main, donde está el programa principal, con un generador de clientes y datos configurables para:
    - Número de taquillas
    - Número de colas
    - Asientos disponibles en el cine
    - Capacidad máxima de las colas
    - Tiempo mínimo de venta a un cliente
    - Tiempo máximo de venta a un cliente
    - Intervalo de monitorización para saber el estado de cada cola mientras el cine está abierto
 
