## W sobrescribe
## A añade

#Codifica un programa que pida o nome dun arquivo con extensión
#.txt e a continuación o cree con ese nome.
# Posteriormente pediranos escribir un texto por teclado, que
# deberá gardarse no arquivo creado e o pecharemos.
# O paso seguinte será abrir o arquivo e contar todas as vocais que
# hai no arquivo e lendo o contido arquivo mostralo por pantalla,
# así como o número de vocais e pechamos o arquivo.
# O último paso será escribir outra nome engadindo ao contido
# anterior pero nun liña posterior.
# Valorarase o uso de funcións e un menú que se repita ata unha
# opción Salir ou similar
nome = ''
vocais = ["a", "e", "i", "o", "u"]

print("Escribe o nome do archivo con extensión .txt: ")
print("Escribe 'salir' para terminar")
nome = input()

f = open(nome + ".txt", "a")

print("Escriba el contenido del archivo: ")
contenido = input()
f.write(contenido)
f.close()

with open(nome + ".txt", "r") as f:
    contenido = f.read()
    vocais = ["a", "e", "i", "o", "u"]
    contador_vocais = {vocal: 0 for vocal in vocais} # inicializa contador_vocais como array llave valor  {'a': 0, 'e': 0, 'i': 0, 'o': 0, 'u': 0}

    for char in contenido.lower():
        if char in contador_vocais:
            contador_vocais[char] += 1
        
    print("\nContenido del archivo:")
    print(contenido)
    print("\nNúmero de vocales:")
    for vocal, cantidad in contador_vocais.items():
        print(f"{vocal}: {cantidad}")
    
f.close()

