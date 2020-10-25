# Item package readme

This is the readme for the item package

## Documentation

### Documentation on the item package


Idea for how to implement the inventory HashMap:
https://stackoverflow.com/questions/14599317/hashmap-holding-different-data-types-as-values-for-instance-integer-string-and/14599451

____

## Problems

### Item Class
Right now their is a couple of problems:

1. Representing a Weapon object as an instance of the Item class does not allow for the use of some weapon methods. This is also inflexible with other types of items. 


### Item Package 

___
## Abstract Functionality

My idea for this package is to create a series of classes for the acess of items and storage in an inventory system.


### Inventory Class
Inventory class can be an objedct class although may have no need to in all actuality because multiple instances of the inventory class should not be needed. The only concievable instance would be that of the player.

The 'Inventory' will contain a Hashmap that stores the Items with a relative index as an integer. So far their are two ways I can see of creating the inventory system:

1. A 2D ArrayList containing an ArrayList in as each value
Option #1 would allow me to reference by an Index already, but would be tricky as things can not be searched through in the same way. But a true index value may not be achievable.
2. A Hashmap with ArrayLists to be the value and ints as keys to act as an index
Option #2 would be more fond personally as items can be looked up accurately by their key rather than an index. The random lookup would be ignored. Only problem with this is the idea of having indexes are still unknown. This option would simulate an index bot not really create one, and the index may need to be tracked over other methods that modify position of items.

### Item Class

The item class will be instnatiated by item obj, item obj only needs 2 fields really which is the name and what it points to. All other variables can be instance variables.

The same goes for it's children classes where all will be direct superconstructors.






