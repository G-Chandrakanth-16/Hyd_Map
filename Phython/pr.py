words=0
sen=0
c=0
f=open("chandu.txt",'r')
for l in f:
    sen=sen+1
print(sen)
f.close()
f=open("chandu.txt",'r')
li=f.read()
v=li.split()
print(len(v))
for i in v:
    c=c+len(i)
print(c)
f.close()
f=open("chandu.txt",'w')
f.write("chand")
f.close()
