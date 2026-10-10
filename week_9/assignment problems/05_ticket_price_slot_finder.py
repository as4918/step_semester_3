def findSlot(prices, newPrice):
    """
    Return the index of newPrice, or its insertion position (lower bound).
    Binary search: O(log n) time, O(1) extra space.
    """
    low, high = 0, len(prices)

    while low < high:
        mid = (low + high) // 2
        if prices[mid] < newPrice:
            low = mid + 1
        else:
            high = mid

    return low


if __name__ == "__main__":
    prices = [120, 150, 200, 260]
    print(findSlot(prices, 150))  # 1
    print(findSlot(prices, 210))  # 3
    print(findSlot(prices, 300))  # 4
