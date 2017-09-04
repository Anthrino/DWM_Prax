# Apriori algorithm for assocaition rule mining
from collections import Counter

dataset_size = input("Enter size of dataset : ")
# item_count = input("Enter no of items available : ")
dataset = []

for i in range(0, dataset_size):
    items = set(input("Enter itemset"+str(i+1)+" list:"))
    dataset.append(items)

c = Counter(frozenset(x) for x in dataset)
print(dict(c)) # Counter({frozenset([1, 2, 3]): 2, frozenset([1, 2]): 1})
print(dataset)

# while itemset_size > 1:
