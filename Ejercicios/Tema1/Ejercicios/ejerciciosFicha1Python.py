#EJ1-->LISTA DE 10 NUMEROS
"""cont=0
lst=[]
while(cont<=10):
    num=int(input("Introduce un número: "))
    lst.append(num)
    cont=cont+1
print(lst)"""
from operator import truediv

#EJ2-->EJ1+SUMATORIO+MEDIA
"""cont=1
lst=[]
sumatorio=0
while(cont<=10):
    num=int(input("Introduce un número: "))
    sumatorio=sumatorio+num
    lst.append(num)
    cont=cont+1
media=sumatorio/10
print(lst)
print(sumatorio)
print(media)"""
#EJ3-->EJ2 + IMPARES + ASEGURARSE DE QUE SEAN NÚMEROS
"""cont=1
lst=[]
sumatorio=0
while(cont<=10):
    try:
        num = int(input("Introduce un número impar: "))
    except ValueError:
        print("ERROR! SOLO NÚMEROS!.")
    if(num%2==0):
        print("ERROR! SOLO NÚMEROS IMPARES!")
        while(num%2==0):
            try:
                num = int(input("Introduce un número impar: "))
            except ValueError:
                print("ERROR! SOLO NÚMEROS!.")
    sumatorio=sumatorio+num
    lst.append(num)
    cont=cont+1
media=sumatorio/10
print(lst)
print(sumatorio)
print(media)"""
#EJ4-->EJ3 + MENU
"""cont=1
lst=[]
sumatorio=0
max=0
min=9999999
while(cont<=10):
    try:
        num = int(input("Introduce un número impar: "))

    except ValueError:
        print("ERROR! SOLO NÚMEROS!.")
    if(num%2==0):
        print("ERROR! SOLO NÚMEROS IMPARES!")
        while(num%2==0):
            try:
                num = int(input("Introduce un número impar: "))

            except ValueError:
                print("ERROR! SOLO NÚMEROS!.")
    if(num>max):
        max=num
    if(num<min):
        min=num
    sumatorio=sumatorio+num
    lst.append(num)
    cont=cont+1
media=sumatorio/10
resp=int(input("¿Qué desea hacer con su lista?\n\t1.Mostrar\n\t2.Sumatorio\n\t3.Media\n\t4.Máximo\n\t5.Mínimo\n\n\t0.Salir"));
if(resp==1):
    print(lst)
else:
    if(resp==2):
        print(sumatorio)
    else:
        if(resp==3):
            print(media)
        else:
            if(resp==4):
                print(max)
            else:
                if(resp==5):
                    print(min)"""
#EJ5->EJ4 PERO EN FUNCIONES
"""def obtener_numero_impar():
    while True:
        try:
            num = int(input("Introduce un número impar: "))
            if num % 2 != 0:
                return num
            else:
                print("ERROR! SOLO NÚMEROS IMPARES!")
        except ValueError:
            print("ERROR! SOLO NÚMEROS!.")

def mostrar_lista(lst):
    print(lst)

def calcular_sumatorio(lst):
    return sum(lst)

def calcular_media(lst):
    return sum(lst) / len(lst)

def encontrar_maximo(lst):
    return max(lst)

def encontrar_minimo(lst):
    return min(lst)

def main():
    cont = 1
    lst = []
    while cont <= 10:
        num = obtener_numero_impar()
        lst.append(num)
        cont += 1

    while True:
        try:
            resp = int(input("¿Qué desea hacer con su lista?\n\t1. Mostrar\n\t2. Sumatorio\n\t3. Media\n\t4. Máximo\n\t5. Mínimo\n\n\t0. Salir\n"))
            if resp == 0:
                break
            elif resp == 1:
                mostrar_lista(lst)
            elif resp == 2:
                print(calcular_sumatorio(lst))
            elif resp == 3:
                print(calcular_media(lst))
            elif resp == 4:
                print(encontrar_maximo(lst))
            elif resp == 5:
                print(encontrar_minimo(lst))
            else:
                print("Opción no válida. Por favor, elija una opción del 0 al 5.")
        except ValueError:
            print("ERROR! SOLO NÚMEROS!.")

if __name__ == "__main__":
    main()"""
#EJ6->CRIPTOGRAFO
"""
class Criptografo:

    def encriptar(self,txt):
        txtN=""
        for i in range(len(txt)):
            txtN=txtN+chr((ord(txt[i]))+1)
        return txtN
    def desencriptar(self,txt):
        txtN=""
        for i in range(len(txt)):
            txtN=txtN+chr((ord(txt[i]))-1)
        return txtN
cripto=Criptografo()
txt=input("Introduce un texto para encriptarlo: ")
print(cripto.encriptar(txt))
txt=input("Introduce un texto para desencriptarlo: ")
print(cripto.desencriptar(txt))
"""
#EJ7->Persona
import random

class Persona:
      SEXO_HOMBRE='H'

      def __init__(self,nombre="",edad=0,peso=0,altura=0,sexo=SEXO_HOMBRE):
          self.nombre=nombre
          self.edad=edad
          self.sexo=sexo
          self.peso=peso
          self.altura=altura
          self.dni = self.generarDNI()
      def calcular_imc(self):
          if self.altura == 0:
            raise ValueError("La altura no puede ser cero.")
          imc = self.peso / ((self.altura/100) ** 2)
          if imc < 20:
            return 0
          elif 20 <= imc <= 25:
            return -1
          else:
            return 1
      def esMayorDeEdad(self):
        if(self.edad>=18):
            return True
        else:
            return False

      def __str__(self):
        return (f"Nombre: {self.nombre}\n"
              f"Edad: {self.edad}\n"
              f"DNI: {self.dni}\n"
              f"Sexo: {self.sexo}\n"
              f"Peso: {self.peso} kg\n"
              f"Altura: {self.altura} m")
      def generarDNI(self):
          dni=""
          cont=1
          while(cont<=8):
              num=random.randint(0,9)
              dni=dni+str(num)
              cont=cont+1
          letras = "TRWAGMYFPDXBNJZSQVHLCKE"
          dni=dni+letras[(int(dni)%23)]
          return dni
      def set_nombre(self, nombre):
          self.nombre = nombre

      def set_edad(self, edad):
        if edad >= 0:
            self.edad = edad
        else:
            raise ValueError("La edad no puede ser negativa.")

      def set_peso(self, peso):
        if peso >= 0:
            self.peso = peso
        else:
            raise ValueError("El peso no puede ser negativo.")

      def set_altura(self, altura):
        if altura > 0:
            self.altura = altura
        else:
            raise ValueError("La altura debe ser mayor que cero.")

      def set_sexo(self, sexo):
        if sexo in [Persona.SEXO_HOMBRE, Persona.SEXO_MUJER]:
            self.__sexo = sexo
        else:
            raise ValueError("Sexo debe ser 'H' o 'M'.")
"""persona=Persona("Benito",21,115,185)
print(persona)"""
#EJ8->Persona prueba
persona1=Persona("Benito",21,200,185)
persona2=Persona("Sergio",49,90,190)
persona3=Persona("Uxue",25,40,179)
print(persona1.calcular_imc())
print(persona2.calcular_imc())
print(persona3.calcular_imc())
print(persona1.esMayorDeEdad())
print(persona2.esMayorDeEdad())
print(persona3.esMayorDeEdad())
print(persona1)
print(persona2)
print(persona3)
