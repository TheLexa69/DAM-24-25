import random;

lista = [1, "X", 2];
quiniela = [];
for i in range(15):
    quiniela.append(random.choice(lista));

print(quiniela)

#print(" ".join(str(x) for x in quiniela))