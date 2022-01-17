# Uses python3
import sys

def gcd_naive(a, b):
    current_gcd = 1
    for d in range(2, min(a, b) + 1):
        if a % d == 0 and b % d == 0:
            if d > current_gcd:
                current_gcd = d

    return current_gcd

def gcd(a, b):
    n1 = max(a, b)
    n2 = min(a, b)
    m = n1 % n2
    while (m > 0):
        n1 = n2
        n2 = m
        m = n1 % n2
    return n2

if __name__ == "__main__":
    input = sys.stdin.read()
    #input = input()
    a, b = map(int, input.split())
    print(gcd(a, b))
