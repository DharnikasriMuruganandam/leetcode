class Solution:
    def solveSudoku(self, board: list[list[str]]) -> None:
        self.backtrack(board)

    def isValid(self, board, r, c, char):
        for i in range(9):
            if board[r][i] == char: return False
            if board[i][c] == char: return False
            if board[3 * (r // 3) + i // 3][3 * (c // 3) + i % 3] == char: return False
        return True

    def backtrack(self, board):
        for r in range(9):
            for c in range(9):
                if board[r][c] == ".":
                    for char in "123456789":
                        if self.isValid(board, r, c, char):
                            board[r][c] = char
                            if self.backtrack(board):
                                return True
                            board[r][c] = "."
                    return False
        return True
