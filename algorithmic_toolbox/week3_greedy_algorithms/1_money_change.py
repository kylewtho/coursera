# Uses python3
import sys

def get_change(m):
    #write your code here
    coins = [10, 5, 1]
    count = 0

    for coin in coins:
        while m >= coin:
            m = m - coin
            count += 1
    return count

if __name__ == '__main__':
    m = int(sys.stdin.read())
    print(get_change(m))
