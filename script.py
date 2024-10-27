# Example 1: Matrix Search
def search_matrix(matrix, target):
    if not matrix or not matrix[0]:
        return False
        
    rows, cols = len(matrix), len(matrix[0])
    left, right = 0, rows * cols - 1
    
    while left <= right:
        mid = (left + right) // 2
        row, col = mid // cols, mid % cols
        value = matrix[row][col]
        
        if value == target:
            return True
        elif value < target:
            left = mid + 1
        else:
            right = mid - 1
            
    return False

"""
Analysis:
1. Time Complexity: O(log(m*n)) where m is rows and n is cols
   - Binary search on flattened matrix
   - Each operation in the while loop is O(1)
   - Loop runs log(m*n) times

2. Space Complexity: O(1)
   - Only using a constant number of variables
   - No additional data structures
   - No recursion
"""

# Example 2: Level Order Traversal
from collections import deque

def levelOrder(root):
    if not root:
        return []
        
    result = []
    queue = deque([root])
    
    while queue:
        level = []
        level_size = len(queue)
        
        for _ in range(level_size):
            node = queue.popleft()
            level.append(node.val)
            
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
                
        result.append(level)
        
    return result

"""
Analysis:
1. Time Complexity: O(n)
   - Each node is processed exactly once
   - Queue operations (append/popleft) are O(1)
   - The inner loop total iterations across all levels = number of nodes

2. Space Complexity: O(w) where w is maximum width of tree
   - Queue stores at most one level of nodes at a time
   - Maximum queue size = width of widest level
   - Result list isn't counted in auxiliary space complexity
"""

# Example 3: LRU Cache
class Node:
    def __init__(self, key=None, value=None):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None

class LRUCache:
    def __init__(self, capacity):
        self.capacity = capacity
        self.cache = {}
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key):
        if key in self.cache:
            node = self.cache[key]
            self._remove(node)
            self._add(node)
            return node.value
        return -1

    def put(self, key, value):
        if key in self.cache:
            self._remove(self.cache[key])
        node = Node(key, value)
        self._add(node)
        self.cache[key] = node
        if len(self.cache) > self.capacity:
            lru = self.head.next
            self._remove(lru)
            del self.cache[lru.key]

    def _remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def _add(self, node):
        prev = self.tail.prev
        prev.next = node
        node.prev = prev
        node.next = self.tail
        self.tail.prev = node

"""
Analysis:
1. Time Complexity:
   - get: O(1)
     * Hash table lookup
     * Doubly linked list operations
   - put: O(1)
     * Hash table operations
     * Doubly linked list operations
   All operations (remove, add) are constant time

2. Space Complexity: O(capacity)
   - Hash table stores at most 'capacity' entries
   - Doubly linked list stores at most 'capacity' nodes
   - Each node stores constant extra pointers
"""