class Encriptado:
    CLAVE = "marvolo"
    @staticmethod
    def encriptar_texto(txt):
        txtL,clave=Encriptado.crearTextoLimpio(txt,0)#Texto limpio y clave limpia
        txtEncr=""
        i=0
        while i < len(txtL):
            num=(ord(txtL[i])-ord('A'))+(ord(clave[i])-ord('A'))#Saco el número que corresponde sumando el del cáracter del texto con el de la clave
            num=num%26#miro que esté en el rango
            txtEncr=txtEncr+chr(num+ord('A'))#lo vuelvo a convertir y lo sumo
            i=i+1
        return txtEncr
    def desEncriptar_texto(txt):
        clave=Encriptado.crearTextoLimpio(txt,1)#Para conseguir la clave ajustada, ya que el txt no nos sirve para nada
        txtDE=""
        i=0
        while i < len(txt):
            num=(ord(txt[i])+ord('A'))-(ord(clave[i])+ord('A'))#Saco el número que corresponde restando el del cáracter del texto con el de la clave
            if num<0:
                num=num+26
            #miro que esté en el rango
            txtDE=txtDE+chr(num+ord('A'))#lo vuelvo a convertir y lo sumo
            i=i+1
        return txtDE
    # Creo un método para no hacerlo todo en 1, le meto un número para ahorrarnos ajustar el texto cuando no queremos
    @staticmethod
    def crearTextoLimpio(txt,num):
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
        if num==1:
            return clave
        txtB = txtB.upper().strip()
        if num==0:
        # Creo otro texto añadiendo solo letras. Para eliminar cualquier otro caracter
            i=0
            txtN=""
            while i < len(txtB):
                if txtB[i].isalpha():
                    txtN=txtN+txtB[i]
                i=i+1
        txtB=txtN

        return txtB,clave


print(Encriptado.encriptar_texto("esternocleidomastoideo"))
print(Encriptado.desEncriptar_texto(Encriptado.encriptar_texto("esternocleidomastoideo")))