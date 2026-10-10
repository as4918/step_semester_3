def mostPopular(orders):
    """
    Return (item, count); ties go to the item whose first occurrence is earliest.
    Time O(n) average, extra space O(u), where u is the number of unique items.
    """
    counts = {}
    for item in orders:
        counts[item] = counts.get(item, 0) + 1

    best_item = orders[0]
    best_count = counts[best_item]

    # Iterate in original order; only update on a strictly larger count.
    for item in orders:
        if counts[item] > best_count:
            best_item = item
            best_count = counts[item]

    return best_item, best_count


if __name__ == "__main__":
    print(mostPopular(["dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"]))
    # ('dosa', 3)
    print(mostPopular(["tea", "coffee", "coffee", "tea"]))  # ('tea', 2)
