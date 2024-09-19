class Encriptado:
    CLAVE = "marvolo"
    @staticmethod
    def encriptar_texto(txt):
        txtL,clave=Encriptado.crearTextoLimpio(txt)#Texto limpio y clave limpia
        txtEncr=""
        i=0
        while i < len(txtL):
            num=(ord(txtL[i])-ord('A'))+(ord(clave[i])-ord('A'))#Saco el número que corresponde sumando el del cáracter del texto con el de la clave
            num=num%26#miro que esté en el rango
            txtEncr=txtEncr+chr(num+ord('A'))#lo vuelvo a convertir y lo sumo
            i=i+1
        return txtEncr
    # Creo un método para no hacerlo todo en 1
    @staticmethod
    def crearTextoLimpio(txt):
        clave = Encriptado.CLAVE #Asigno la clave a otra variable para mantener el original
        txtB = txt #Asigno el texto a otra variable para mantener el original
        #Relleno la clave si no tiene la misma longitud que el texto
        if len(txtB) > len(clave):
            i = 0
            while len(clave) < len(txtB):
                clave += clave[i]
                i += 1
        # Cambio los textos a mayusculas y le quito los espacios del principio/final al texto
        clave = clave.upper()
        txtB = txtB.upper().strip()
        # Creo otro texto añadiendo solo letras. Para eliminar cualquier otro caracter
        i=0
        txtN=""
        while i < len(txtB):
            if txtB[i].isalpha():
                txtN=txtN+txtB[i]
            i=i+1
        txtB=txtN
        return txtB,clave

print(Encriptado.encriptar_texto("  a ver si me encrip6ta [mi2s ¡+LeTr it a s%$"))
