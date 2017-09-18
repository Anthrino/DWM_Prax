import json
import csv
from pprint import pprint


with open('uefa_country_clubs.json') as json_source:
	source_data = json.load(json_source)

pprint(source_data)

with open('file.csv') as csv_source:
	source_data = csv.reader(csv_source)
	for line in reader:
		print line


