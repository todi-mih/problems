Diverse problems solved in java,the scope is not just solving the problems but to get the best time complexity ,the programs
were tested with a timer (sub 2 s) and the solutions tries to get the best possible time.

Servers:
Used a divide and conquer technique,basiclly binary search
i first get the max of the currents and the minimum for a better time,we
can use it for all numbers but why waste time,i figured since we are subtracting and
using absolute values then the biggest the output can be for an equation was if the 
value in the absolute part produced 0, so we get the value of the currents themselves
so they can be 0,and form here we get the lowest and highest,then i initiliase some variables to 
help me keep track of everything,we enter the while loop,getting the mid and the mid + 0.1 and then we 
use the fn calculateIndividualMinP to get the lowest value of all the equtions applying k(in the first case
mid and mid + 1) then based on this values we see if we should go to the left side or to the right,i also 
implemented a system to check that after 100 tries the value stays the same it means that it converges, so
i could aviod being stuck in an infite loop.

Time complexity : O(N * lg(maxP - minPower))

Compresie:
This program initializes a HashMap indexMap to store the prefix sums of array A(first one,B is the second one) as keys and their coresponding indices as valuess.(HashMaps are awsome and very fast in java).
Compute Prefix Sums for Array A and add them.Compute Prefix Sums for Array B and Matching: It iterates through array B, computing the cumulative sum. If indexMap contains a key equal to sumB, it means there exists a matching prefix sum in array A. In such a case, it increments the sol variable, representing the count of matched segments.

Time complexity : O(lengthof.A + lengthof.B)

Criptate:
The program applies a logic to be solved for each letter of the alpahbet acting like its the dominant char
(since it will only add *27 to out complexity it wasnt overall a very bad choice i think),anyway,the idea
is that for every string it checks how many times it holds the dominant char(we try all of them as 
said before) and then assign the number of times it holds this char (by help from the fn countDomCharTimes,
which just takes a string and a char and it returns how many times the char is in there),then we sort the
hashmap based on the value of how much they hold the dominant char,so we know which string to add first 
and what the rest,and then we add the stings together based on this values keeping in mind the condition if
its not met we stop,and then we get the biggest value of all of the chars we tried.

Time complexity :  O(N * lengthofbiggest.N + N log N)

Oferta:
The idea here was to get all possible pairs and triples,and then make a hashmap with them and the
value they would reduce ,sort them based on this values,start from the biggest and then continue,
but I also made a "blacklist" array for chars that were being used so we only could apply the offer 
once for them.The program doesnt seem to work for most test cases,i made sure to apply the best offers
possible, i must have understood wrong.

Edit:Found a bug,if we have duplicate numebers in the prices,then the blacklist strategy fails,becouse 
it will add the first time this number is there and not use it for the rest.

Edit 2:Fixed the bug,still doesnt work,i just dont get the exercise,no time to solve it

Time complexity : O(N log N)



