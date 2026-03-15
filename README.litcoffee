# Proyecto II: Mundo Chiquito 🃏

**Curso:** CI-2693 - Laboratorio de Algoritmos y Estructuras III (Enero-Marzo 2026)  
**Universidad:** Universidad Simón Bolívar  
**Autores:** Bueno, Santiago 20-10168
             Saad, Zahir 21-10600

## Descripción del Proyecto

Este proyecto implementa una solución en Kotlin para modelar las reglas de la carta conjuro "Mundo Chiquito" del popular juego de cartas coleccionables. El programa recibe un mazo de cartas (en formato `.csv`) y construye un **grafo** donde cada carta es un vértice. Existe una arista entre dos cartas si y solo si comparten **exactamente una característica** (Nivel, Atributo o Poder).

El objetivo principal del algoritmo es:
1. Validar las propiedades de cada carta (restricciones de nivel, múltiplos de poder, atributos válidos y nombres únicos).
2. Encontrar y listar todas las ternas de cartas ($u \rightarrow v \rightarrow w$) que representan "puentes" válidos para activar el efecto de Mundo Chiquito.
3. Adicionalmente imprime una representación del grafo generado para hacer verificaciones manuales, etc.

## Archivos del Proyecto

* `CartaMostro.kt`: Definición de la clase base con las propiedades privadas y las validaciones correspondientes en el bloque `init`.
* `MundoChiquito.kt`: Archivo principal que se encarga de la lectura del archivo `.csv`, la construcción del grafo, la impresión de la lista de adyacencia y la búsqueda de los caminos de longitud 2.
* `deck.csv`: Archivo de prueba por defecto que contiene el mazo de cartas.
* `Makefile`: Script para automatizar la compilación y ejecución del código.

## Análisis de Complejidad (Eficiencia)

El algoritmo está diseñado para procesar un mazo de cartas (típicamente entre 40 y 60 cartas en un escenario real) de manera muy eficiente. A continuación, se detalla la complejidad de tiempo y espacio por cada etapa, denotando $V$ como el número de cartas (vértices) y $E$ como el número de conexiones (aristas):

### 1. Lectura y Parseo del Archivo
* **Tiempo:** $O(V)$
* **Descripción:** Se recorre el archivo línea por línea una sola vez. Las operaciones de limpieza de texto (`trim`, `uppercase`), conversión de tipos de datos y la instanciación de la clase `CartaMostro` toman un tiempo constante $O(1)$ por carta.

### 2. Construcción del Grafo
* **Tiempo:** $O(V^2)$
* **Espacio:** $O(V + E)$
* **Descripción:** Para construir la lista de adyacencia, se utilizan dos ciclos anidados que comparan cada par de cartas único (combinatoria). La función `compartenUna` realiza exactamente 3 comparaciones directas, por lo que es $O(1)$. El espacio utilizado corresponde a la estructura clásica de una lista de adyacencia.

### 3. Búsqueda de Puentes (Caminos $u \rightarrow v \rightarrow w$)
* **Tiempo:** $O(V \cdot D^2)$ en promedio, o $O(V^3)$ en el peor de los casos (grafo denso).
* **Descripción:** Se itera sobre cada vértice $u$, luego sobre sus vecinos $v$, y finalmente sobre los vecinos $w$ de $v$ (donde $D$ es el grado máximo de un vértice). Aunque existen tres ciclos anidados, no se itera ciegamente sobre todos los vértices, sino únicamente sobre las **aristas existentes** (vecinos directos), lo que reduce drásticamente las operaciones en grafos dispersos.

## Cómo compilar y ejecutar

Este proyecto utiliza un `Makefile` con comandos personalizados para facilitar la ejecución. Abre tu terminal en la carpeta del proyecto y utiliza los siguientes comandos:

### 1. Compilar el proyecto
Para compilar los archivos `.kt` y generar el ejecutable `MundoChiquito.jar`:
`make`

### 2. Ejecutar
Escriba en la terminal `java -jar MundoChiquito.jar <archivo>`, donde <archivo> es un archivo .csv
con el formato especificado para crear las cartas.