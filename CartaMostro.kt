data class CartaMostro(val name: String, val lvl: Int, val pwr: Int, val att: String) {
    companion object { 
    	//val atributos: Array<String> = ["AGUA", "FUEGO", "VIENTO", "TIERRA", "LUZ", "OSCURIDAD", "DIVINO"]
    	val atributos = arrayOf("AGUA", "FUEGO", "VIENTO", "TIERRA", "LUZ", "OSCURIDAD", "DIVINO")
    	//var names: List<String> = []
    	val names = mutableListOf<String>()
    }
    
    init {
    	require(lvl in 1..12) {"El nivel del Mostro debe estar entre 1 y 12"}
    	require(pwr > 0 && pwr % 50 == 0) {"Poder debe ser un múltiplo de 50"}
    	require(att in atributos) {"No se reconoce el atributo"}
    	require(name !in names) {"Ya hay un Mostro con este nombre"}

    	names.add(name)
    
    }
}

