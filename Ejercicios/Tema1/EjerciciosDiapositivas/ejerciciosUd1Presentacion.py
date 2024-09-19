#EJ1-->SOLO PALABRAS CON MÁS DE 10 LETRAS
"""pal_10=input("Introduce una palabra: ")
if(len(pal_10)>10):
    print(pal_10)"""
#EJ2-->DE DECIMAL A BINARIO
"""decimal=int(input("Introduce un número para pasarlo a binario: "))
binario=0
cont=1
while(decimal>1):
    binario=binario+((decimal%2)*cont)
    decimal=decimal//2
    cont=cont*10
binario=binario+(decimal*cont)
print(binario)"""
#EJ3-->AÑADIR ESPACIOS
"""pal=input("Introduce una palabra: ")
cont=1
vacios=""
while cont<=len(pal)*2:
    vacios=vacios+" "
    cont=cont+1
pal=vacios+pal
print(pal)"""
#EJ4-->PALABRAS CON A--MODIFICADO PARA USARLO ABAJO
"""def palabras_A(pal):
    #pal=input("Introduce una palabra para ver si contiene 'a': ")
    if(pal.__contains__('a')):
        return(True)
    else:
        return(False)
#EJ5-->PORCENTAJE SIN A
txt=input("Introduce un texto: ")
palabras = txt.split()
cont_pal_totales = len(palabras)
cont_pal_a = 0
for palabra in palabras:
    if palabras_A(palabra):
        cont_pal_a += 1
porc_sin_a = 100 * (cont_pal_totales - cont_pal_a) / cont_pal_totales
print(f'En tu texto hay {cont_pal_totales - cont_pal_a} palabras sin "a" y el porcentaje es de: {porc_sin_a:.2f}%')"""
#EJ6-->Indice de una letra
#Sin usar find:
"""txt=input("Introduce un texto: ")
ltr=input("¿Qué letra quieres buscar?")
i=-1
cont=0
esta=False
while(cont<=len(txt)-1 and esta==False):
    if(txt[cont]==ltr):
        esta=True
        i=cont
    else:
        cont=cont+1
if(i!=-1):
    print("La letra "+ltr+" se encuentra en el índice: "+str(i))
else:
    print("La letra "+ltr+" no se encuentra en el texto.")"""
#Usando find:
"""txt=input("Introduce un texto: ")
ltr=input("¿Qué letra quieres buscar?")
i=txt.find(ltr)
if(i!=-1):
    print("La letra "+ltr+" se encuentra en el índice: "+str(i))
else:
    print("La letra "+ltr+" no se encuentra en el texto.")"""
#DICCIONARIOS
#EJ1-->CREAR FUNCION QUE RECORRA Y BUSQUE
"""def valor_en_diccionario(diccionario, valor_buscado):
    return valor_buscado in diccionario.values()

dicc = {
    'a': 10,
    'b': 20,
    'c': 30
}

v = 20
resultado = valor_en_diccionario(dicc, v)

print("¿El valor está en el diccionario?", resultado)"""
#EJ2-->CARACTERES ASCII Y NUMERICO
dic_asci = {chr(i): i for i in range(32, 127)}
print(dic_asci)
