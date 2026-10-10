def hasPairWithSum(nums, target):
    """
    Hash-set solution: O(n) average time and O(n) extra space.
    The set contains only previously visited values, so two distinct
    indices are required even when the list contains duplicates.
    """
    seen = set()
    for number in nums:
        if target - number in seen:
            return True
        seen.add(number)
    return False


def hasPairWithSumBruteForce(nums, target):
    """Alternative: O(n^2) time, O(1) extra space."""
    for i in range(len(nums)):
        for j in range(i + 1, len(nums)):
            if nums[i] + nums[j] == target:
                return True
    return False


if __name__ == "__main__":
    print(hasPairWithSum([2, 7, 11, 15], 9))  # True
    print(hasPairWithSum([3, 4, 6], 20))      # False
    print(hasPairWithSum([3, 3], 6))          # True
