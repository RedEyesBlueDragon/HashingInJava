package ceng351.labdb;

public class Evaluate {

    public static void main(String[] args) {
        LabDB labDB = new LabDB(4);
        try {

    /*        labDB.enter("e4");
            labDB.enter("e12");
            labDB.enter("e32");
            labDB.enter("e16");
            labDB.enter("e1");
            labDB.enter("e5");
            labDB.enter("e21");
            labDB.enter("e10");
            labDB.enter("e15");
            labDB.enter("e7");
            labDB.enter("e19");
            labDB.enter("e13");
            labDB.enter("e20");
            labDB.enter("e9");
            labDB.leave("e32");
            labDB.leave("e16");
            labDB.leave("e10");
            labDB.leave("e9");
            labDB.leave("e1");
            labDB.leave("e4");
            labDB.leave("e12");
            labDB.leave("e20");
            labDB.leave("e5");
            labDB.leave("e21");
            labDB.leave("e13");
            labDB.printLab();
*/
        	
        	
        	
        	LabDB labdb = new LabDB(1);
            labdb.enter("e0");
            labdb.printLab();
            labdb.enter("e1");
            labdb.printLab();
            labdb.enter("e8");
            labdb.printLab();
            labdb.enter("e5");
            labdb.printLab();
           
        	
        	
        	
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
