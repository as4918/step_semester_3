def warehouseSummary(grid):
    """
    Return (total_items, (row, col)) for the first maximum in row-major order.
    Time O(m*n), extra space O(1). Assumes the grid is non-empty.
    """
    total = 0
    max_value = grid[0][0]
    max_coordinate = (0, 0)

    for row in range(len(grid)):
        for col in range(len(grid[row])):
            value = grid[row][col]
            total += value
            # Strictly greater preserves the first maximum when values tie.
            if value > max_value:
                max_value = value
                max_coordinate = (row, col)

    return total, max_coordinate


if __name__ == "__main__":
    grid = [[4, 9, 2], [7, 1, 6], [3, 12, 5]]
    print(warehouseSummary(grid))  # (49, (2, 1))
