
import java.util.*;

class User {
    int id;
    String name;
    List<Integer> follows;

    User(int id, String name, List<Integer> follows) {
        this.id = id;
        this.name = name;
        this.follows = follows;
    }
}

public class NthLevelFollowerSolver {

    public static List<Integer> getNthLevelFollowers(int findId, int n, List<User> users) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (User user : users) {
            graph.put(user.id, user.follows);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(findId);
        visited.add(findId);

        int level = 0;

        while (!queue.isEmpty() && level < n) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                List<Integer> neighbors = graph.getOrDefault(current, Collections.emptyList());
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            level++;
        }

        List<Integer> result = new ArrayList<>(queue);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", Arrays.asList(2, 3)));
        users.add(new User(2, "Bob", Arrays.asList(4)));
        users.add(new User(3, "Charlie", Arrays.asList(4, 5)));
        users.add(new User(4, "David", Arrays.asList(6)));
        users.add(new User(5, "Eva", Arrays.asList(6)));
        users.add(new User(6, "Frank", new ArrayList<>()));

        int findId = 1;
        int n = 2;

        List<Integer> outcome = getNthLevelFollowers(findId, n, users);

        System.out.println("{");
        System.out.println("  \"regNo\": \"REG564\",");
        System.out.println("  \"outcome\": " + outcome);
        System.out.println("}");
    }
}
