import java.util.PriorityQueue;

class Solution {
    public int strongPasswordChecker(String password) {
        int hasLower = 0, hasUpper = 0, hasDigit = 0;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = 1;
            if (Character.isUpperCase(c)) hasUpper = 1;
            if (Character.isDigit(c)) hasDigit = 1;
        }
        int missingTypes = 3 - (hasLower + hasUpper + hasDigit);

        int n = password.length();
        if (n < 6) {
            return Math.max(6 - n, missingTypes);
        }

        int replace = 0;
        int onerm = 0, tworm = 0;

        for (int i = 0; i < n; ) {
            int len = 1;
            while (i + len < n && password.charAt(i + len) == password.charAt(i)) {
                len++;
            }
            if (len >= 3) {
                replace += len / 3;
                if (len % 3 == 0) onerm++;
                else if (len % 3 == 1) tworm += 2;
            }
            i += len;
        }

        if (n <= 20) {
            return Math.max(replace, missingTypes);
        }

        int delete = n - 20;
        replace -= Math.min(delete, onerm);
        replace -= Math.min(Math.max(delete - onerm, 0), tworm) / 2;
        replace -= Math.max(delete - onerm - tworm, 0) / 3;

        return delete + Math.max(replace, missingTypes);
    }
}
