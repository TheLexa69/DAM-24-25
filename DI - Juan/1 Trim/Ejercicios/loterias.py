import random

lista = []

while len(lista) < 6:
    n = random.randint(1, 49)
    if(n not in lista):
        lista.append(n)

comp = random.randint(1, 9)

print("El numero de la loteria es: " + str(lista) + " complementario: " + str(comp) )

