public class HistoryStack {

    private Book top;

    public HistoryStack(){
        top = null;
    }
    
    public void push(Book book){

        if(book == null){
            System.out.println("Error! No book is added to history");
            return;
        }

        Book newBook = new Book(book.getIsbn(), book.getTitle(), book.getAuthor());
        newBook.setRight(top);
        top = newBook;

        System.out.println(book.getTitle() + " added");
    }

    public Book pop(){
        
        if(isEmpty()){
            return null;
        }
        Book temp = top;
        top = top.getRight();

        return temp;
    }

    public Book peek(){
        
        if(isEmpty()){
            return null;
        }
        return top;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public void displayHistory(){

        if(isEmpty()){
            System.out.println("No history found");
            return;
        }
        Book current = top;
        System.out.println("\nBorrowing History");

        while (current != null) {
            System.out.println("ISBN  : " + current.getIsbn());
            System.out.println("Title : " + current.getTitle());
            System.out.println("Author: " + current.getAuthor());
            System.out.println("--------------------");

            current = current.getRight();
        }
    }
}
