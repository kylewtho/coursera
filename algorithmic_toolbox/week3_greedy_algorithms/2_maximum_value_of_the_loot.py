# Uses python3
import sys

def get_optimal_value(n, capacity, weights, values):
    value = 0.
    items = []
    # write your code here
    for i in range(n):
        items.append([values[i], weights[i], (values[i] / weights[i])])
    items.sort(key=lambda x:x[2], reverse=True)

    for i in range(n):
        if capacity >= items[i][1]:
            value += items[i][0]
            capacity -= items[i][1]
        elif capacity == 0:
            break
        else:
            value += items[i][2] * capacity
            capacity = 0

    return value


if __name__ == "__main__":
    data = list(map(int, sys.stdin.read().split()))
    n, capacity = data[0:2]
    values = data[2:(2 * n + 2):2]
    weights = data[3:(2 * n + 2):2]
    opt_value = get_optimal_value(n, capacity, weights, values)
    print("{:.10f}".format(opt_value))
