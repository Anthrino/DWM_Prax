# Program to fire queries on MongoDb Data Warehouse
from pymongo import MongoClient
from pprint import pprint

mdb_client = MongoClient()
db = mdb_client.uefa.playerTransfers

# OLAP Queries executed on FACT

# Slicing Query
print("\n> Slicing: Total Transfers per agent :- \n")
data = db.aggregate([{"$group" : {"_id" : "$agent.id", "total_transfer_amt" : {"$sum" : "$transfer_fee"}}}])

for x in data:
	print(x)

# Dicing Query
print("\n> Dicing: Total Transfers per agent per club :- \n")

data = db.aggregate([{"$group" : {"_id" : {"$concat": ["$agent.id","-", "$to_club.id"]}, "total_transfer_amt" : {"$sum" : "$transfer_fee"}}}])

for x in data:
	print(x)


# Analytical Queries using Join operations between dimension tables.

db = mdb_client.uefa

print("\n> Club information for each player :-\n")

data = db.playerprofiles.aggregate([{ "$lookup": { "from": "clubs", "localField": "club.id", "foreignField": "_id", "as": "club_info"}}, { "$project": { "name":1, "club_info":1 , "_id":0} }])

for x in data:
	print(x)

print("\n> League information for each club :-\n")

data = db.clubs.aggregate([{ "$lookup": { "from": "leagues", "localField": "league.id", "foreignField": "_id", "as": "league_info"}}, { "$project": { "name":1, "league_info":1 , "global_ranking":1, "_id":0} }])

for x in data:
	print(x)
