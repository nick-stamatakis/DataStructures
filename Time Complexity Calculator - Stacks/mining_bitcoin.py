#worthwhile_bitcoins contains while!
import math

def mining_bitcoin(n): 
    N = xrange(n)
    log_N = xrange(int(math.log(n, 2)))
    certificateThreshold = 10

    for worthwhile_bitcoin in log_N:
        print("Bitcoin mined!")
        if (N > certificateThreshold):
            print("congratulations! You're qualified as a professional bitcoin miner")
        else:
            print("get to the grind!!")
        worthwhile_bitcoin += 1

    for worthwhile_bitcoin in N:
        print("Bitcoin mined!")
        if (N > certificateThreshold):
            print("congratulations! You're qualified as a professional bitcoin miner")
        else:
            print("get to the grind!!")
        worthwhile_bitcoin += 1