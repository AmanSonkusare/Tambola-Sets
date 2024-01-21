### 1) Create “N” New Tambola Sets:
   1 Tambola set consists of 6 tickets.

   Take number of sets (N) as input and generate N new sets of Tambola tickets (i.e. 6N tickets),
   according to the below rules, and save them in the database.
   Tambula Ticket Generator Rules:

   ● The numbers 1 to 90 are used once only.

   ● In the first column are the numbers 1 to 9, the second column has numbers 10 to
   19, etc, all the way to the 9th column which has numbers 80 to 90 in it.

   ● Every row must have exactly 5 numbers in it.

   ● In a specific column, numbers must be arranged in ascending order from top to
      bottom.

   ● Each column must have at least 1 number.

   ● All the numbers 1 to 90 are used only once in each set of 6 tickets.

   ● Blank Cell fill by zero.

   ● Each ticket must be unique and not exist in the database.

   Response:

   Return the newly created tickets, with table primary-key as JSON key (ex: “12”,”13”,...).
   Sample Response:
```
   {
   "tickets": {
   "11": [
   [0, 19, 0, 31, 0, 0, 61, 74, 85],
   [4, 14, 0, 0, 40, 0, 0, 77, 86],
   [0, 18, 25, 37, 0, 54, 64, 0, 0]
   ],
   "12": [
   [2, 0, 23, 38, 0, 53, 0, 0, 83],
   [0, 13, 0, 0, 46, 59, 70, 79, 0],
   [6, 0, 29, 41, 49, 0, 68, 0, 0]
   ],
   "13": [
   [0, 15, 0, 33, 45, 0, 0, 76, 81],
   [7, 12, 0, 0, 0, 52, 0, 75, 82],
   [0, 0, 22, 39, 0, 57, 66, 0, 89]
   ],
   "14": [
   [8, 11, 20, 0, 0, 0, 0, 73, 84],
   [3, 0, 0, 34, 42, 51, 65, 0, 0],
   [0, 0, 0, 36, 48, 50, 0, 72, 88]
   ],
   "15": [
   [1, 16, 27, 35, 0, 56,0, 0, 0],
   [0, 17, 0, 32, 0, 55, 67, 78, 0],
   [5, 0, 28, 0, 43, 0, 69, 0, 87]
   ],
   "16": [
   [0, 10, 21, 0, 44, 0, 60, 0, 80],
   [9, 0, 26, 30, 0, 58, 63, 0, 0],
   [0, 0, 24, 0, 47, 0, 62, 71, 90]
   ],
   }
   }
   ```
### 2) Fetch Saved Tambola Tickets:
   Make a GET URL to return the tambola tickets saved in database, with pagination.
