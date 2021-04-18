# Uses python3
def calc_fib(n):
    if (n <= 1):
        return n
    elif (n == 2):
        return 1
    else:
        f1 = 1
        f2 = 1
        fs = 0
        for i in range(2, n):
            fs = f1 + f2
            f2 = f1
            f1 = fs
    return fs

n = int(input())
print(calc_fib(n))
