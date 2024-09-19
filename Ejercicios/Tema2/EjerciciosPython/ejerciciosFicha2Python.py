#EJ1
"""import random
def rnd_word(fitxategi1, fitxategi2):
    file1=open(fitxategi1,'r')
    file2=open(fitxategi2,'w')
    for linea in file1:
        palabras=linea.split()
        palabra_random=random.choice(palabras)
        file2.write(palabra_random+'\n')
    file1.close()
    file2.close()
rnd_word("./archivos/ej1_1.txt","./archivos/ej1_2.txt")"""
#EJ2
"""import random
def nueva_palabra_de_dos(oracion):
    palabras = oracion.split()  # Dividir la oración en palabras
    if len(palabras) < 2:
        return "La oración debe tener al menos dos palabras"
    palabra1, palabra2 =random.sample(palabras, 2)  # Seleccionar dos palabras aleatorias
    max_len = max(len(palabra1), len(palabra2))  # Longitud de la palabra más larga
    nueva_palabra = ''
    for i in range(max_len):
        char1 = palabra1[i] if i < len(palabra1) else ''
        char2 = palabra2[i] if i < len(palabra2) else ''
        if char1 and char2:
            nueva_palabra += random.choice([char1, char2])
        elif char1:
            nueva_palabra += char1
        elif char2:
            nueva_palabra += char2
    return nueva_palabra
print(nueva_palabra_de_dos("No se que decir"))"""
#EJ3
"""import pickle
import random
def generar_y_guardar_listas():
    lista1 = [random.uniform(-100, 100) for _ in range(1000)]
    lista2 = [random.uniform(-100, 100) for _ in range(1000)]

    with open('./archivos/lista1.pkl', 'wb') as f1, open('./archivos/lista2.pkl', 'wb') as f2:
        pickle.dump(lista1, f1)
        pickle.dump(lista2, f2)

    print("Listas generadas y guardadas en archivos.")

def cargar_y_calcular_promedio():
    with open('./archivos/lista1.pkl', 'rb') as f1, open('./archivos/lista2.pkl', 'rb') as f2:
        lista1 = pickle.load(f1)
        lista2 = pickle.load(f2)

    diferencias = [abs(lista1[i] - lista2[i]) for i in range(1000)]
    promedio_diferencias = sum(diferencias) / len(diferencias)

    print(f"El promedio de las diferencias entre los valores es: {promedio_diferencias:.4f}")
generar_y_guardar_listas()
cargar_y_calcular_promedio()"""
#EJ4
"""def introducir_libro():
    lista_libros = []

    while True:
        # Introducir datos del libro
        nombre = input("Introduce el nombre del libro: ")
        autor = input("Introduce el autor del libro: ")
        precio = float(input("Introduce el precio del libro: "))
        precio_descuento = float(input("Introduce el precio con descuento: "))
        num_paginas = int(input("Introduce el número de páginas: "))

        # Guardar los datos del libro en un diccionario
        libro = {
            "nombre": nombre,
            "autor": autor,
            "precio": precio,
            "precio_descuento": precio_descuento,
            "num_paginas": num_paginas
        }

        # Añadir el libro a la lista de libros
        lista_libros.append(libro)

        # Preguntar si quiere introducir otro libro
        continuar = input("¿Quieres añadir otro libro? (si/no): ").strip().lower()
        if continuar != 'si':
            break

    # Guardar los datos en un archivo usando repr()
    with open("./archivos/base_datos_libros.txt", "w") as archivo:
        for libro in lista_libros:
            archivo.write(repr(libro) + "\n")

    print("La base de datos de libros se ha guardado en el archivo.")

# Ejecutar el programa para introducir libros
introducir_libro()
def leer_y_calcular_mayor_descuento():
    libro_mayor_descuento = None
    mayor_descuento = -1

    # Leer el archivo de libros
    with open("./archivos/base_datos_libros.txt", "r") as archivo:
        for linea in archivo:
            # Convertir la línea en un diccionario usando eval()
            libro = eval(linea.strip())

            # Calcular el porcentaje de descuento
            precio = libro["precio"]
            precio_descuento = libro["precio_descuento"]
            descuento = ((precio - precio_descuento) / precio) * 100

            # Encontrar el libro con el mayor descuento
            if descuento > mayor_descuento:
                mayor_descuento = descuento
                libro_mayor_descuento = libro

    # Mostrar el libro con el mayor descuento
    if libro_mayor_descuento:
        print(f"El libro con el mayor descuento es: {libro_mayor_descuento['nombre']}")
        print(f"Porcentaje de descuento: {mayor_descuento:.2f}%")
    else:
        print("No se encontraron libros en la base de datos.")

# Ejecutar el programa para leer los libros y calcular el mayor descuento
leer_y_calcular_mayor_descuento()"""