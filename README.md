# MyIntegerBST
    18HT-1DV516
   Assignment 1: implement the ADTs from scratch without reusing data structures from libraries 

Exercise 1

Implement a Binary Search Tree ADT (called MyIntegerBST) for Integer elements that supports the following operations:
– public void insert(Integer value). //Insertion operation seen during the lectures
– public Integer mostSimilarValue(Integer value) 
      If "value" has been inserted in the tree, returns "value"
      Otherwise, returns the value "s0" such that
        s0 has been previously inserted in the tree 
        for any other value "s1" in the tree, | s1-value | >= | s0-value |
        Note: | x-y | means the absolute value of operation x-y 
– public void printByLevels()
        Prints all elements at depth "d" in the tree before elements at depth "d+1". It Starts with the root (depth=0) as "Depth 0:             rootValue". It continues printing the children of the root (depth=1), as "Depth 1: ...". It continues with the root grandchildren (depth=2), etc.
        Next figure shows an example of a tree and the expected output.
        
Exercise 2
Propose the implementation of an ADT "SequenceWithMinimum" that supports the following operations: 
  insertRight(Integer value) 
  removeRight()
  insertLeft(Integer value)
  removeLeft()
  findMinimum()
  
<Worst-case of the 4 insert/remove operations O(1) and worst-case of findMiniumum O( N ). Hint: you can reach amortized O(1) for all the 5 operations.>
