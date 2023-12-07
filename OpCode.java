/**
 * Tim Kuehn and Drew Hirsh
 */
public class OpCode {
    private int opCodeValue;
    private int opWidth;
    private String name;
    private format opFormat; 
    private int shamt;

    enum format {
        R,
        I,
        D,
        B,
        CB,
        IM
      }


    public OpCode(){
    }


    public int getOpCodeValue() {
        return opCodeValue;
    }
    
    public void setOpCodeValue(int opCodeValue) {
        this.opCodeValue = opCodeValue;
    }
    
    public int getOpWidth() {
        return opWidth;
    }
    
    public void setOpWidth(int opWidth) {
        this.opWidth = opWidth;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public format getOpFormat() {
        return opFormat;
    }
    
    public void setOpFormat(format opFormat) {
        this.opFormat = opFormat;
    }
    
    public int getShamt() {
        return shamt;
    }
    
    public void setShamt(int shamt) {
        this.shamt = shamt;
    }


    

    @Override
    public String toString() {
    return "OpCode{" +
            "opCodeValue=" + opCodeValue +
            ", opWidth=" + opWidth +
            ", name='" + name + '\'' +
            ", opFormat=" + opFormat +
            ", shamt=" + shamt +
            '}';
    }

    public OpCode(int opCode){
        if(opCode==1160){
            opCode = 1161;
        }
        else if(opCode==1168){
            opCode = 1169;
        }
        else if(opCode>=160 && opCode<=191){
            opCode = 5;
        }
        else if(opCode>=672 && opCode<=679){
            opCode = 84;
        }
        else if(opCode>=1184 && opCode<=1215){
            opCode = 37;
        }
        else if(opCode>=1448 && opCode<=1455){
            opCode = 181;
        }
        else if(opCode>=1440 && opCode<=1447){
            opCode = 180;
        }
        else if(opCode>=1680 && opCode<=1681){
            opCode = 840;
        }
        else if(opCode>=1424 && opCode<=1425){
            opCode = 712;
        }
        else if(opCode>=1672 && opCode<=1673){
            opCode = 836;
        }
        else if(opCode>=1928 && opCode<=1929){
            opCode = 964;
        }
        

        shamt = 0;
        switch (opCode){
        
            case 1112: 
                opCodeValue = 1112;
                opWidth = 11;
                name = "ADD";
                opFormat = format.R;
                break;

            case 580:
                opCodeValue = 580;
                opWidth = 10;
                name = "ADDI";
                opFormat = format.I;
                break;

           case 1104:
                opCodeValue = 1104;
                opWidth = 11;
                name = "AND";
                opFormat = format.R;
                break;

            case 5:
                opCodeValue = 5;
                opWidth = 6;
                name = "B";
                opFormat = format.B;
                break;

            case 84:
                opCodeValue = 84;
                opWidth = 8;
                name = "B.cond";
                opFormat = format.CB;
                break;

            case 37:
                opCodeValue = 37;
                opWidth = 6;
                name = "BL";
                opFormat = format.B;
                break;

            case 1712:
                opCodeValue = 1712;
                opWidth = 11;
                name = "BR";
                opFormat = format.R;
                break;

            case 181:
                opCodeValue = 181;
                opWidth = 8;
                name = "CBNZ";
                opFormat = format.CB;
                break;

            case 180:
                opCodeValue = 180;
                opWidth = 8;
                name = "CBZ";
                opFormat = format.CB;
                break;
            
            case 1616:
                opCodeValue = 1616;
                opWidth = 11;
                name = "EOR";
                opFormat = format.R;
                break;

            case 840:
                opCodeValue = 840;
                opWidth = 10;
                name = "EORI";
                opFormat = format.I;
                break;
            
            case 1986:
                opCodeValue = 1986;
                opWidth = 11;
                name = "LDUR";
                opFormat = format.D;
                break;

            case 1691:
                opCodeValue = 1691;
                opWidth = 11;
                name = "LSL";
                opFormat = format.R;
                break;
            
            case 1690:
                opCodeValue = 1690;
                opWidth = 11;
                name = "LSR";
                opFormat = format.R;
                break;

            case 1360:
                opCodeValue = 1360;
                opWidth = 11;
                name = "ORR";
                opFormat = format.R;
                break;
                
            case 712:
                opCodeValue = 712;
                opWidth = 10;
                name = "ORRI";
                opFormat = format.I;
                break;
            
            case 1984:
                opCodeValue = 1984;
                opWidth = 11;
                name = "STUR";
                opFormat = format.D;
                break;
            
            case 1624:
                opCodeValue = 1624;
                opWidth = 11;
                name = "SUB";
                opFormat = format.R;
                break;
            
            case 836:
                opCodeValue = 836;
                opWidth = 10;
                name = "SUBI";
                opFormat = format.I;
                break;

            case 964:
                opCodeValue = 964;
                opWidth = 10;
                name = "SUBIS";
                opFormat = format.I;
                break;
            
            case 1880:
                opCodeValue = 1880;
                opWidth = 11;
                name = "SUBS";
                opFormat = format.R;
                break;
            
            case 1240:
                opCodeValue = 1240;
                opWidth = 11;
                name = "MUL";
                opFormat = format.R;
                shamt = 31;
                break;
            
            case 2045:
                opCodeValue = 2045;
                opWidth = 11;
                name = "PRNT";
                opFormat = format.R;
                break;
            
            case 2044:
                opCodeValue = 2044;
                opWidth = 11;
                name = "PRNL";
                opFormat = format.R;
                break;

            case 2046:
                opCodeValue = 2046;
                opWidth = 11;
                name = "DUMP";
                opFormat = format.R;
                break;

            case 2047:
                opCodeValue = 2047;
                opWidth = 11;
                name = "HALT";
                opFormat = format.R;
                break;

            default:
            
                opCodeValue = -1;
                opWidth = -1;
                name = "oopsieWoopsies uWU";
                opFormat = null;
                break;
        }

        }



}

