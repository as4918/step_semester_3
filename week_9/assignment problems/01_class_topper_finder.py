def findTopper(marks):
    """
    Return (rowIndex, total) for the highest total.
    Strictly greater keeps the smallest row index on ties.
    Time O(m*n), extra space O(1).
    """
    best_index = 0
    best_total = -1

    for row_index, student_marks in enumerate(marks):
        total = sum(student_marks)
        if total > best_total:
            best_total = total
            best_index = row_index

    return best_index, best_total


if __name__ == "__main__":
    marks = [[78, 85, 90], [88, 92, 79], [65, 70, 95]]
    print(findTopper(marks))  # (1, 259)
