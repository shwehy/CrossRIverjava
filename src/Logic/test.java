package Logic;

import java.util.List;

public class test {

    public static List<ICrosser> rightval;
    private static List<ICrosser> leftval;
    private static List<ICrosser> boatval;

    public static class CommandLineChanger implements Changeable {


        private final List<ICrosser> rval;
        private final List<ICrosser> lval;
        private final List<ICrosser> bval;

        public CommandLineChanger(List<ICrosser> right,List<ICrosser> left,List<ICrosser> boat){
            super();
                this.rval = right;
                this.lval = left;
                this.bval = boat;

        }
        test x = new test();
        public void undo(){

          //  s.Set_Lists(this.rval,this.lval,this.bval);
            System.out.println(rval + " undone    --  "+lval+"....."+bval);
            x.setRightFinal(rval);
            x.setLeftFinal(lval);
            x.setBoatFinal(bval);
        }

        public void redo(){
            System.out.println(rval + " redone    --- "+lval+"......."+bval);
            x.setRightFinal(rval);
            x.setLeftFinal(lval);
            x.setBoatFinal(bval);
        }
    }

    public static List<ICrosser> getRightFinal(){
        return rightval;
    }
    public void setLeftFinal(List<ICrosser> left){
        leftval = left;
    }

    public static List<ICrosser> getLeftFinal(){
        return leftval;
    }

    public void setBoatFinal(List<ICrosser> boat){
        boatval = boat;
    }
    public static List<ICrosser> getBoatFinal(){
        return boatval;
    }
    public void setRightFinal(List<ICrosser> right){
        rightval = right;
    }

}