import csv
f=open("chandu.csv",'r')
r=csv.reader(f)
for i in r:
    print(i)