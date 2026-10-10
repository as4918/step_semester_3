def hasPairWithSum(nums, target):
    """
    Hash-based solution for an unsorted array.
    Average time O(n), extra space O(n).
    Brute-force alternative: compare every pair, O(n^2) time and O(1) space.
    """
    seen = set()
    for value in nums:
        complement = target - value
        if complement in seen:
            return True
        seen.add(value)
    return False


if __name__ == "__main__":
    print(hasPairWithSum([2, 7, 11, 15], 9))  # True
    print(hasPairWithSum([3, 4, 6], 20))      # False
