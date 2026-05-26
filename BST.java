public class BST {

    private Book root;                   // Root node of the tree
    
    public BST() {                        //creates empty tree
        this.root = null;
    }

   
    public void insert(int isbn, String title, String author) {                              // Public insert method 
        root = insertRec(root, isbn, title, author);
    }

   
    private Book insertRec(Book root, int isbn, String title, String author) {               // Private recursive insert method

        
        if (root == null) {                                                                //base case: if empty spot found, create a new book
            return new Book(isbn, title, author);
        }

        if (isbn < root.getIsbn()) {                                                       // if it is smaller, go left
            root.setLeft(insertRec(root.getLeft(), isbn, title, author));
        }
        else if (isbn > root.getIsbn()) {                                                   // if it is larger, go right
            root.setRight(insertRec(root.getRight(), isbn, title, author));
        }
        

        return root;                                                                         // if it is equal, duplicate it and do nothing
    }

    // Getter for root (for testing)
    public Book getRoot() {
        return root;
    }
}
