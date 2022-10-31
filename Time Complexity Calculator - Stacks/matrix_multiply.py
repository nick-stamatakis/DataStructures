# This function multiplies two n by n matrices in O(n^3) time.
def matrix_multiply(a, b, n):

    # Get the range [0, n-1].
    N = xrange(n)

    # Create an N x N output matrix 'c' full of 0's.
    c = [0 * n] * n

    # For each row 'i' in 'a'...
    for i in N:

        # For each row 'j' in 'b'...
        for j in N:

            # Store dot product of a[i][:] with
            # b[:][j] in c[i][j]. 
            for k in N:

                c[i][j] += a[i][k]*b[k][j]

    # Return the matrix.
    return c







