def maxContainerArea(heights):
    """
    Two-pointer solution: O(n) time, O(1) extra space.
    Move the pointer at the shorter wall because the shorter wall limits area.
    """
    left, right = 0, len(heights) - 1
    best_area = 0

    while left < right:
        width = right - left
        area = min(heights[left], heights[right]) * width
        best_area = max(best_area, area)

        if heights[left] < heights[right]:
            left += 1
        else:
            right -= 1

    return best_area


def maxContainerAreaBruteForce(heights):
    """Alternative: O(n^2) time, O(1) extra space."""
    best_area = 0
    for i in range(len(heights)):
        for j in range(i + 1, len(heights)):
            area = min(heights[i], heights[j]) * (j - i)
            best_area = max(best_area, area)
    return best_area


if __name__ == "__main__":
    print(maxContainerArea([1, 8, 6, 2, 5, 4, 8, 3, 7]))  # 49
