# QUICK SORT (O(nlog(n)))
# Python

def quick_sort(list, start, end):
    if start < end:
        partition_index = partition(list, start, end)
        quick_sort(list, start, partition_index - 1)
        quick_sort(list, partition_index + 1, end)

def partition(list, start, end):
    # Last element used as pivot
    pivot = list[end]
    # Index of greater number
    lower_index = start - 1

    for x in range(start, end):
        if list[x] < pivot:
            lower_index += 1

            # Swap
            (list[lower_index], list[x]) = (list[x], list[lower_index])

    (list[lower_index + 1], list[end]) = (list[end], list[lower_index + 1])

    return lower_index + 1


if __name__ == "__main__":
    list = [62, 42, 16, 16, 6, 13, 84, 82, 22, 75, 32, 63, 53, 6, 36]

    # Initial List
    print("Initial List")
    for x in list:
        print(x, end=" ")
    

    quick_sort(list, 0, len(list) - 1)


    # Sorted List
    print("\nSorted List")
    for x in list:
        print(x, end=" ")