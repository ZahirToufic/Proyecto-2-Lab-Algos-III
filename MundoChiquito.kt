import java.io.*
/*
fun main(ruta: String) {
    Archivo = File(ruta)
    val linea = Archivo.readLines
    linea.forEach { it.split -> CartaMostro(linea[0], linea[1], linea[2], linea[3]) }


}*/
fun main(args: Array<String>) {
    // Nombre del archivo por defecto o pasado como argumento
    val fileName = if (args.isNotEmpty()) args[0] else "deck.csv"
    val lineas = File(fileName).readLines()

    // Saltar encabezado y parsear cada línea
    val cartas = lineas.mapNotNull { linea ->
        val partes = linea.split(",").map { it.trim() }
        if (partes.size < 4) return@mapNotNull null
        try {
            val nombre = partes[0]
            val nivel = partes[1].toInt()
            val atributo = partes[2].uppercase()
            val poder = partes[3].toInt()
            CartaMostro(nombre, nivel, poder, atributo)
        } catch (e: Exception) {
            // Si hay error en alguna línea, se ignora
            null
        }
    }

    val n = cartas.size

    // Construir lista de adyacencia
    val ady = List(n) { mutableListOf<Int>() }

    // Función que determina si dos cartas comparten exactamente una característica
    fun compartenUna(c1: CartaMostro, c2: CartaMostro): Boolean {
        var count = 0
        if (c1.lvl == c2.lvl) count++
        if (c1.att == c2.att) count++
        if (c1.pwr == c2.pwr) count++
        return count == 1
    }

    // Llenar el grafo
    for (i in 0 until n) {
        for (j in i until n) {
            if (compartenUna(cartas[i], cartas[j])) {
                ady[i].add(j)
                ady[j].add(i)
            }
        }
    }



    // Enumerar todos los caminos de longitud 2 (u -> v -> w)
    val resultados = mutableListOf<String>()
    for (u in 0 until n) {
        for (v in ady[u]) {
            for (w in ady[v]) {
                    resultados.add("${cartas[u].name} ${cartas[v].name} ${cartas[w].name}")
            }
        }
    }

    // Imprimir resultados
    resultados.forEach { println(it) }

    println("\n")
    println("Imprimimos el grafo para análisis posteriores: \n")

    imprimirGrafo(cartas, ady)

}

    // Función para imprimir el grafo en el formato: vertice -> vecino1 -> vecino2
fun imprimirGrafo(cartas: List<CartaMostro>, ady: List<List<Int>>) {
    for (i in cartas.indices) {
        val nombreVertice = cartas[i].name
        
        // Si el vértice tiene vecinos, los unimos con " -> "
        if (ady[i].isNotEmpty()) {
            val vecinos = ady[i].joinToString(" -> ") { cartas[it].name }
            println("$nombreVertice -> $vecinos")
        } else {
            // Si es un vértice aislado (sin conexiones), solo imprimimos su nombre
            println(nombreVertice)
        }
    }
}