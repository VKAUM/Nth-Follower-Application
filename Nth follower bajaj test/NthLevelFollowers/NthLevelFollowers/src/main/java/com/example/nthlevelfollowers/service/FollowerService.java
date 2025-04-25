package com.example.nthlevelfollowers.service;

import com.example.nthlevelfollowers.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FollowerService {
    public List<Integer> getNthLevelFollowers(int findId, int n, List<User> users) {
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

        return new ArrayList<>(queue);
    }
}
