KC=     kotlinc
KFLAG=  -cp

all:    CartaMostro.class MundoChiquitoKt.class

CartaMostro.class: CartaMostro.kt
	$(KC) $(KFLAG) . CartaMostro.kt

MundoChiquitoKt.class: MundoChiquito.kt CartaMostro.kt
	$(KC) $(KFLAG) . MundoChiquito.kt CartaMostro.kt


clean:
	rm -rf *.class META-INF
