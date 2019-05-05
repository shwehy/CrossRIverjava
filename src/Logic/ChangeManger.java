package Logic;
import javax.swing.event.ChangeEvent;

public class ChangeManger {
    private Node currentIndex = null;
    private Node parentNode = new Node();
    public ChangeManger(){
        currentIndex = parentNode;

    }
public ChangeManger(ChangeManger manger){
        this();
        currentIndex=manger.currentIndex;
}
    public void clear(){

        currentIndex = parentNode;

    }

    public void addChangeable(Changeable changeable){

        Node node = new Node(changeable);

        currentIndex.right = node;

        node.left = currentIndex;

        currentIndex = node;

    }



    public boolean canUndo(){

        return currentIndex != parentNode;

    }




    public boolean canRedo(){

        return currentIndex.right != null;

    }





    public void undo(){

        //validate

        if ( !canUndo() ){

            throw new IllegalStateException("Cannot undo. Index is out of range.");

        }

        currentIndex = currentIndex.left;
        currentIndex.changeable.undo();


        moveLeft();

    }



    private void moveLeft(){

        if ( currentIndex.left == null ){

            throw new IllegalStateException("Internal index set to null.");

        }

        currentIndex = currentIndex.left;

    }



    private void moveRight(){

        if ( currentIndex.right == null ){

            throw new IllegalStateException("Internal index set to null.");

        }

        currentIndex = currentIndex.right;

    }



    public void redo(){

        //validate

        if ( !canRedo() ){

            throw new IllegalStateException("Cannot redo. Index is out of range.");

        }
        currentIndex = currentIndex.right;
//        currentIndex = currentIndex.right;
        //reset index

        moveRight();

        //redo

        currentIndex.changeable.redo();

    }

    private class Node {

        private Node left = null;

        private Node right = null;


        private final Changeable changeable;


        public Node(Changeable c) {

            changeable = c;

        }


        public Node() {

            changeable = null;

        }
    }
}
