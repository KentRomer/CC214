package GUI;

import java.util.LinkedList;

class HashTable {
    // Node to store key-value pairs
    private static class Node {
        String key;
        String value;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Array of LinkedLists for separate chaining
    private LinkedList<Node>[] buckets;
    private int capacity;
    private int size;

    // Constructor
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        this.size = 0;

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // Hash function
    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // Insert key-value pair into the hashtable
    public void put(String key, String value) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value; // Update existing key
                return;
            }
        }

        bucket.add(new Node(key, value));
        size++;
    }

    // Retrieve value by key
    public String get(String key) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null; // Key not found
    }

    // Remove key-value pair by key
    public void remove(String key) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                bucket.remove(node);
                size--;
                return;
            }
        }
    }

    // Check if the hashtable contains a key
    public boolean containsKey(String key) {
        int index = hash(key);
        LinkedList<Node> bucket = buckets[index];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    // Get the size of the hashtable
    public int size() {
        return size;
    }
}