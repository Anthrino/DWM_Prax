# Apriori algorithm for association rule mining
from collections import Counter
import itertools
import json

dataset_size = int(input("Enter size of dataset : "))
# itemset_size = int(input("Enter no of items available : "))
dataset = []
itemset = {}
iter_no = 1

# dataset_size = 4
# itemset_size = 5
# dataset = [(1,2,3),(1,2),(1,3,5),(2,4)]
#
# for i in range(1, dataset_size+1):
# 	itemset[json.dumps(i)] = 0

for i in range(0, dataset_size):
	# trans = [0] * itemset_size
	print("Enter transaction " + str(i + 1) + " items : ", end="")
	items = tuple(int(i) for i in input().split())
	for x in items:
		# trans[int(x) - 1] = 1
		itemset[json.dumps(int(x))] = 1
	dataset.append(items)

# print(dataset)
support = int(input("Enter min. support for Apriori Algorithm: "))

while len(itemset) > 1:
	l = []
	for i in itemset.keys():
		if iter_no > 1:
			for j in json.loads(i):
				l.append(int(j))
		else:
			l.append(json.loads(i))

	c = [[j for j in i ] for i in itertools.combinations(set(l), iter_no)]
	l = {}
	c2 = {}
	# print("\nC" + str(iter_no) + " : " + str(c))
	for subset in c:
		counter = 0
		for i in dataset:
			if tuple(set(subset).intersection(set(i))) == tuple(subset):
				counter += 1
		c2[json.dumps(subset)] = counter
		if counter >= support:
				l[json.dumps(subset)] = counter

	print("\nC" + str(iter_no) + " : " + str(c2))
	print("L" + str(iter_no) + " : " + str(l))
	iter_no += 1
	itemset = l

# c = Counter(frozenset(x) for x in dataset )
