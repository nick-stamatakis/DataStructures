# This function prints some statements in O(n * log(n)) time.
def test_function(n):

    # Get the range [0, n-1].
    N = xrange(n)
    # Get the range [0, floor(log(n))-1] 
    log_N = xrange(int(math.log(n, 2)))

    # Stack record that loops from 0 to n-1.
    for i in N:

        # Nested stack record that loops from 0 to floor(log(n))-1.
        for j in log_N:

            print("This statement prints n * log(n) times.")

    # All 'while' statements will have a variable go from 'n' to 1.
    k = n
    while k > 1:

        print("But this statement only prints log(n) times.")

        # But you will have to determine the order from the
        # update statement (log(n), in this case).
        k /= 2 

    print("Since n * log(n) is bigger complexity, the whole "
          "funtion is O(n * log(n).")


