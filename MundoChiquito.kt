import java.io.*
//
fun main(ruta: String) {
    Archivo = File(ruta)
    val linea = Archivo.readLines
    linea.forEach { it.split -> CartaMostro(linea[0], linea[1], linea[2], linea[3]) }

}