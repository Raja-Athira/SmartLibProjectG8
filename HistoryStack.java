public class HistoryStack {

    private Book top;

    public HistoryStack(){
        top = null;
    }
    
    public void push(Book book){

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

}
