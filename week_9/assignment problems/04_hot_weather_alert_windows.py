def countAlerts(readings, k, threshold):
    """
    Count size-k windows whose average >= threshold.
    Compare sum >= k*threshold to avoid division.
    Time O(n), extra space O(1).
    """
    window_sum = sum(readings[:k])
    alerts = 1 if window_sum >= k * threshold else 0

    for right in range(k, len(readings)):
        window_sum += readings[right] - readings[right - k]
        if window_sum >= k * threshold:
            alerts += 1

    return alerts


if __name__ == "__main__":
    readings = [2, 2, 2, 2, 5, 5, 5, 8]
    print(countAlerts(readings, 3, 4))  # 3
