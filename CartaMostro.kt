//conviene que sea data o solo class? revisar si las data pueden tener funciones dentro
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

/*    fun share(mostro1, mostro2: CartaMostro): Boolean {
        when {
            mostro1.name == mostro2.name -> true
            mostro1.lvl == mostro2.lvl -> true
            mostro1.pwr == mostro2.pwr -> true
            mostro1.att == mostro2.att -> true
            else -> false
        }

    }

}*/
