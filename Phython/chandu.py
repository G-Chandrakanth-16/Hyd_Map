from scipy.stats import poisson as p
import numpy as np
import matplotlib.pyplot as plt
x=np.arrange(0,100,0.5)
y=p.pmf(x,40,10)
plt.plot(x,y)
plt.show()
