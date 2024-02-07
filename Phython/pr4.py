import pandas as pd
x=pd.Series(range(1,20))
y=pd.Series(range(21,40))
print(x.rank())
print(y.rank())
print(y.corr(x))