import json
import csv
import openpyxl as xl
from pymongo import MongoClient
from pprint import pprint

mdb_client = MongoClient()

db = mdb_client.uefa.clubs
print("\n> UEFA Clubs collection selected.")
db.remove({})

print("> Inserted PL data into UEFA Clubs Collection : \n")

# Loading clubs data from json file
with open('uefa_country_clubs.json') as json_source: 
	source_data = json.load(json_source)
	# pprint(source_data['tournaments'])

	for x in source_data['tournaments']:
		# pprint(x['name'])
		if x['name'] == "English Premier League 2015/16":
			for y in x['clubs']:
				db.insert_one(y)

for doc in db.find():
	print(doc)

db = mdb_client.uefa.playerprofiles
print("\n> UEFA Player Profiles collection selected.")
db.remove({})

print("> Inserted Player data into UEFA Profiles Collection : \n")

# Loading player prfiles data from excel file
player_profile_sheet = xl.load_workbook('PlayerProfile.xlsx').active

for x in player_profile_sheet:
	# print(x[0].value+str(x[1].value)+x[2].value+x[3].value)
	db.insert_one({"name": x[0].value, "age": x[1].value , "position": x[2].value, "valuation": x[3].value})

for doc in db.find():
	print(doc)

# with open('file.csv') as csv_source:
# 	source_data = csv.reader(csv_source)
# 	for line in reader:
# 		print(line)