# Java Collection Framework Comparison

| **Data Structure**     | **Implements**      | **Ordering**                 | **Allows Duplicates** | **Thread-safe** | **Best Use Case**                                      |
|------------------------|---------------------|------------------------------|------------------------|------------------|---------------------------------------------------------|
| **ArrayList**          | List                | Insertion order              | ✅ Yes                 | ❌ No           | Fast random access, frequent reads                      |
| **LinkedList**         | List, Deque         | Insertion order              | ✅ Yes                 | ❌ No           | Frequent insertions/deletions at both ends             |
| **Vector**             | List                | Insertion order              | ✅ Yes                 | ✅ Yes          | Legacy thread-safe list                                 |
| **Stack**              | Vector              | LIFO                         | ✅ Yes                 | ✅ Yes          | LIFO operations (legacy, use Deque instead)             |
| **HashSet**            | Set                 | No order                     | ❌ No                  | ❌ No           | Unique elements, fast lookup                            |
| **LinkedHashSet**      | Set                 | Insertion order              | ❌ No                  | ❌ No           | Maintain insertion order of unique elements            |
| **TreeSet**            | NavigableSet        | Sorted (natural/custom)      | ❌ No                  | ❌ No           | Sorted unique elements                                  |
| **PriorityQueue**      | Queue               | Priority-based               | ✅ Yes                 | ❌ No           | Efficient min/max queue                                 |
| **ArrayDeque**         | Deque               | Insertion order              | ✅ Yes                 | ❌ No           | Stack/queue replacement (Stack, LinkedList)             |
| **HashMap**            | Map                 | No order                     | Keys: ❌ No            | ❌ No           | Fast key-value access                                   |
| **LinkedHashMap**      | Map                 | Insertion order              | Keys: ❌ No            | ❌ No           | Access in order of insertion                            |
| **TreeMap**            | NavigableMap        | Sorted by keys               | Keys: ❌ No            | ❌ No           | Sorted key-value pairs                                  |
| **Hashtable**          | Map                 | No order                     | Keys: ❌ No            | ✅ Yes          | Legacy thread-safe map                                  |
| **ConcurrentHashMap**  | ConcurrentMap       | No guaranteed order          | Keys: ❌ No            | ✅ Yes (Partial)| Thread-safe, high concurrency                           |
| **EnumMap**            | Map                 | Enum key order               | ❌ (Enum keys)         | ❌ No           | When keys are enums                                     |
| **WeakHashMap**        | Map                 | No order                     | ❌ No                  | ❌ No           | Cache w/ automatic GC of keys                           |
| **IdentityHashMap**    | Map                 | No order                     | ❌ No                  | ❌ No           | Compare keys using `==` not `.equals()`                 |



## Quick Suggestions Based on Use Case

| **Use Case**                            | **Best Structure**                                              |
|----------------------------------------|-----------------------------------------------------------------|
| Fast lookups by key                    | `HashMap`                                                       |
| Maintain insertion order in map        | `LinkedHashMap`                                                 |
| Need sorted keys                       | `TreeMap`                                                       |
| LRU Cache                              | `LinkedHashMap` (with access order)                             |
| Fast unique collection                 | `HashSet`                                                       |
| Keep elements sorted                   | `TreeSet`                                                       |
| Queue or stack replacement             | `ArrayDeque`                                                    |
| Priority-based processing              | `PriorityQueue`                                                 |
| Multi-threaded map access              | `ConcurrentHashMap`                                             |
| Need thread-safe list                  | `Collections.synchronizedList()` or `CopyOnWriteArrayList`     |
| Stream processing with grouping, mapping | `Stream` + `Collectors.groupingBy(...)`                          |
