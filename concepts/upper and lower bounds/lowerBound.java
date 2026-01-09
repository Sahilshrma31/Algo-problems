// First index where value ≥ target
import java.util.*;
class Solution{
private int lowerBound(List<Integer> list, int target) {
    int l = 0, r = list.size();   // [l, r)
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (list.get(mid) < target)
            l = mid + 1;
        else
            r = mid;
    }
    return l;
}
}

