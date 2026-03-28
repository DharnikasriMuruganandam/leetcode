#include <limits.h>

int max(int a, int b) {
    return (a > b) ? a : b;
}

int solve(struct TreeNode* node, int* maxSum) {
    if (!node) return 0;

    int leftGain = max(solve(node->left, maxSum), 0);
    int rightGain = max(solve(node->right, maxSum), 0);

    int currentPathSum = node->val + leftGain + rightGain;
    *maxSum = max(*maxSum, currentPathSum);

    return node->val + max(leftGain, rightGain);
}

int maxPathSum(struct TreeNode* root) {
    int maxSum = INT_MIN;
    solve(root, &maxSum);
    return maxSum;
}
