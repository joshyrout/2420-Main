package cePet;


import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdRandom;

public class PetApp {


    private static void addPet(BinarySearchST<Integer, Pet> st, String name, int age, String species){
        int randomNumber = StdRandom.uniform(10,30);
        while(st.contains(randomNumber)){
            randomNumber = StdRandom.uniform(10,30);
        }
        Pet newPet = new Pet(name, age, species);
        st.put(randomNumber, newPet);
    }

    private static void addPet(BinarySearchST<Integer, Pet> st, String name, int age, String species, int key){
        Pet newPet = new Pet(name, age, species);
        st.put(key, newPet);
    }

    private static void changeName(BinarySearchST<Integer, Pet> st, String newName, int key){
        Pet oldPet = st.get(key);
        addPet(st, newName, oldPet.age, oldPet.species, key);
    }

    private static void returnValueAtKey(BinarySearchST<Integer, Pet> st, int key){
        if(st.contains(key)){
            Pet pet = st.get(key);
            System.out.println("The pet at key " + key + " was "+ pet.name + ", an "+  pet.age + " year old " + pet.species);
            return;
        }
        System.out.println("There are no pets with a key of " + key);
    }

    public static void main(String[] args) {
        BinarySearchST<Integer, Pet> st = new BinarySearchST<>();
        addPet(st, "Buddy", 8, "dog");
        addPet(st, "Larry", 1, "cat");
        addPet(st, "Marv", 2, "cat");
        addPet(st, "Bingo", 2, "goat");
        addPet(st, "Bucky", 7, "chicken");
        addPet(st, "Rabunzle", 6, "rabbit");
        addPet(st, "Scout", 8, "dog");
        addPet(st, "Air Bud", 9, "dog");
        addPet(st, "Nibbler", 1, "hamster");
        addPet(st, "Henry", 14, "goose");


        System.out.println("All keys: " + st.keys());
        System.out.print("All pet names: ");
        for(int i = 0; i < st.size(); i++){
            int key = st.select(i);
            String name = st.get(key).name;
            System.out.print(name + " ");
        }

        System.out.println();
        System.out.println();
        returnValueAtKey(st, 10);
        returnValueAtKey(st, 17);
        returnValueAtKey(st, 23);
        System.out.println();
        System.out.println("The smallest pet ID is: " + st.min());
        System.out.println("The smallest pet ID above 17 is: " + st.ceiling(17));
        System.out.println("The number of pets in the table: " + st.size());
        addPet(st, "Waldi", 3, "dog", 30);
        System.out.println("The number of pets with an id smaller then 25 are: " + st.rank(25));
        System.out.println("The second smallest pet id: " + st.select(1));
        changeName(st, "Strolch", 30);

        System.out.printf("\n%-3s  %-20s", "IDs", "Pets");
        System.out.printf("\n%-3s  %-20s", "---", "------------------");

        for(int i = 0; i < st.size(); i++){
            int key = st.select(i);
            Pet pet = st.get(key);
            System.out.printf("\n%-3s  %-20s", key, (pet.species + " " + pet.name + "("+ pet.age+ ")"));
        }


    }



}
