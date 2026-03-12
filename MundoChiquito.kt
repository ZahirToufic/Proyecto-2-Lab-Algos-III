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
    val cartas = lineas.drop(1).mapNotNull { linea ->
        val partes = linea.split(",").map { it.trim() }
        if (partes.size < 4) return@mapNotNull null
        try {
            val nombre = partes[0]
            val nivel = partes[1].toInt()
            val atributo = Atributo.valueOf(partes[2].uppercase())
            val poder = partes[3].toInt()
            CartaMostro(nombre, nivel, atributo, poder)
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
        if (c1.nivel == c2.nivel) count++
        if (c1.atributo == c2.atributo) count++
        if (c1.poder == c2.poder) count++
        return count == 1
    }

    // Llenar el grafo
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (comparenUna(cartas[i], cartas[j])) {
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
                if (w != u) { // w distinto de u; w ya es distinto de v por construcción
                    resultados.add("${cartas[u].nombre} ${cartas[v].nombre} ${cartas[w].nombre}")
                }
            }
        }
    }

    // Imprimir resultados
    resultados.forEach { println(it) }
}
