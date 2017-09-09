
import pandas as pd

books = ['example_dat.xlsx']
df = []

for book in books:
    file = pd.ExcelFile(book) # bookを読む
    for sheet in file.sheet_names:
        df.append(file.parse(sheet)) # シートを順々にデータフレーム化

r=2
c=1
urls = 3
df[0].iloc[r,c]

#query = [
#'data',
#'gjdfkslgdfsgjfdklsgfdjgkldsfgjdflkgfjdglkdfsjgdlfkgjfdlgfjdglkfdgjfldkgjfdlgjfdldata',
#'miss'
#]

f = open('url.dat', 'r')
list = f.readlines()
query = []
for line in list:
    line = line.rstrip('\r\n')
    query.append(line)
    f.close()

url_list = []

for i in range(0,urls):
    url_list.append(df[i].iloc[r,c])
	

for i in range(0,len(query)):
    print(query[i] in url_list,query[i])
    
    
