
    /**
 * Public search method - starts recursive search from root.
 * @param isbn - the ISBN number to search for
 * @return the Book if found, or null if not found
 */
public Book search(int isbn) {
    return searchRec(root, isbn);
}


/**
 * Private recursive search method.
 * At each node, compares target ISBN with current node.
 * If equal - book found (base case)
 * If smaller - search left subtree (recursive)
 * If larger - search right subtree (recursive)
 * If null - book does not exist in tree (base case)
 * @param current - the current node being examined
 * @param isbn - the ISBN number to search for
 * @return the Book if found, or null if not found
 */

private Book searchRec(Book current, int isbn){
    //base case 1 : book not found (null)
    if (current == null){
        return null;
    }

    //base case 2 : book is found (matching isbn)
    if ( isbn == current.getIsbn()){
        return current;
    }

    //Recursive case : ISBN smaller( search left subtree)
    if (isbn < current.getIsbn()){
        return searchRec(current.getLeft(), isbn);
    }

    //Recursive case: ISBN is larger, search right subtree
    //no need if option as it is the last option
    return searchRec(current.getRight(), isbn);
     
  }
}
