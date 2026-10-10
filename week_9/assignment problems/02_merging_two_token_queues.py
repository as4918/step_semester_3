def mergeTokens(counterA, counterB):
    """
    Merge two already sorted lists without sorting them again.
    Time O(m+n), extra space O(m+n) for the returned list.
    Duplicates are retained.
    """
    i = j = 0
    merged = []

    while i < len(counterA) and j < len(counterB):
        if counterA[i] <= counterB[j]:
            merged.append(counterA[i])
            i += 1
        else:
            merged.append(counterB[j])
            j += 1

    merged.extend(counterA[i:])
    merged.extend(counterB[j:])
    return merged


if __name__ == "__main__":
    print(mergeTokens([3, 8, 15, 20], [5, 8, 12]))
    # [3, 5, 8, 8, 12, 15, 20]
    print(mergeTokens([], [4, 9]))  # [4, 9]
