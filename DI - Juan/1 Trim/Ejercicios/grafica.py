import matplotlib.pyplot as plt
import numpy as np
import random
import time

#plt.pcolor(np.random.rand(20, 20), cmap='hot')
#plot_color_gradients('Sequential (2)',
#                     ['binary', 'gist_yarg', 'gist_gray', 'gray', 'bone',
#                      'pink', 'spring', 'summer', 'autumn', 'winter', 'cool',
#                      'Wistia', 'hot', 'afmhot', 'gist_heat', 'copper'])

cmap=['binary', 'gist_yarg', 'gist_gray', 'gray', 'bone', 'pink', 'spring', 'summer', 'autumn', 'winter', 'cool', 'Wistia', 'hot', 'afmhot', 'gist_heat', 'copper']
plt.ion()
plt.figure()

while True:
    plt.pcolor(np.random.rand(20, 20), cmap=random.choice(cmap))
    #plt.pcolormesh(np.random.rand(20, 20), cmap='cool')
    plt.show()
    time.sleep(2)