# coding: utf-8

f = open('url.dat', 'r')
list = f.readlines()
ur = []
for line in list:
    line = line.rstrip('\r\n')
    ur.append(line)
f.close()


sp = [[91,100]]

for i in range(1):
    for num in range(sp[i][0],sp[i][1]+1):
        number_padded = '{0:04d}'.format(num)
        print(number_padded+','+ur[num])


