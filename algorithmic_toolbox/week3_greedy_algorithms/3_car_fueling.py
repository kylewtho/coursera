 # python3
import sys

def compute_min_refills(distance, tank, n, stops):
    #number of refills, index of next stop, travelled distance
    refills, nextStop, limit = 0, 0, tank

    while limit < distance:
        if nextStop >= n or stops[nextStop] > limit:
            return -1

        while nextStop < n-1 and stops[nextStop+1] <= limit:
            nextStop += 1

        refills += 1
        limit = stops[nextStop] + tank
        nextStop += 1

    return refills

if __name__ == '__main__':
    d, m, n, *stops = map(int, sys.stdin.read().split())
    print(compute_min_refills(d, m, n, stops))
