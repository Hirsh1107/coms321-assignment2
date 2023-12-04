/**
 * Tim Kuehn and Drew Hirsh
 */
public class OpCode {
    private int opCodeValue;
    private int opWidth;
    private String name;
    private format format; 
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
    
    public OpCode(int opCode){
        switch (opCode){
        
            case 1112: 
                opCodeValue = 1112;
                opWidth = 11;
                name = "ADD";
                format = format.R;
                break;

            case 580:
                opCodeValue = 580;
                opWidth = 10;
                name = "ADDI";
                format = format.I;
                break;

           case 1104:
                opCodeValue = 1104;
                opWidth = 11;
                name = "AND";
                format = format.R;
                break;

            case 5:
                opCodeValue = 5;
                opWidth = 6;
                name = "B";
                format = format.B;
                break;

            case 84:
                opCodeValue = 84;
                opWidth = 8;
                name = "B.cond";
                format = format.CB;
                break;

            case 37:
                opCodeValue = 37;
                opWidth = 6;
                name = "BL";
                format = format.B;
                break;

            case 1712:
                opCodeValue = 1712;
                opWidth = 11;
                name = "BR";
                format = format.R;
                break;

            case 181:
                opCodeValue = 181;
                opWidth = 8;
                name = "CBNZ";
                format = format.CB;
                break;

            case 180:
                opCodeValue = 180;
                opWidth = 8;
                name = "CBZ";
                format = format.CB;
                break;
            
            case 1616:
                opCodeValue = 1616;
                opWidth = 11;
                name = "EOR";
                format = format.R;
                break;

            case 840:
                opCodeValue = 840;
                opWidth = 10;
                name = "EORI";
                format = format.I;
                break;
            
            case 1986:
                opCodeValue = 1986;
                opWidth = 11;
                name = "LDUR";
                format = format.D;
                break;

            case 1691:
                opCodeValue = 1691;
                opWidth = 11;
                name = "LSL";
                format = format.R;
                break;
            
            case 1690:
                opCodeValue = 1690;
                opWidth = 11;
                name = "LSR";
                format = format.R;
                break;

            case 1360:
                opCodeValue = 1360;
                opWidth = 11;
                name = "ORR";
                format = format.R;
                break;
                
            case 712:
                opCodeValue = 712;
                opWidth = 10;
                name = "ORRI";
                format = format.I;
                break;
            
            case 1984:
                opCodeValue = 1984;
                opWidth = 11;
                name = "STUR";
                format = format.D;
                break;
            
            case 1624:
                opCodeValue = 1624;
                opWidth = 11;
                name = "SUB";
                format = format.R;
                break;
            
            case 836:
                opCodeValue = 836;
                opWidth = 10;
                name = "SUBI";
                format = format.I;
                break;

            case 964:
                opCodeValue = 964;
                opWidth = 10;
                name = "SUBIS";
                format = format.I;
                break;
            
            case 1880:
                opCodeValue = 1880;
                opWidth = 11;
                name = "SUBS";
                format = format.R;
                break;
            
            case 1240:
                opCodeValue = 1240;
                opWidth = 11;
                name = "MUL";
                format = format.R;
                break;
            
            case 2045:
                opCodeValue = 2045;
                opWidth = 11;
                name = "PRNT";
                format = format.R;
                break;
            
            case 2044:
                opCodeValue = 2044;
                opWidth = 11;
                name = "PRNL";
                format = format.R;
                break;

            case 2046:
                opCodeValue = 2046;
                opWidth = 11;
                name = "DUMP";
                format = format.R;
                break;

            case 2047:
                opCodeValue = 2047;
                opWidth = 11;
                name = "HALT";
                format = format.R;
                break;

            default:
                opCodeValue = -1;
                opWidth = -1;
                name = "oopsieWoopsies uWU";
                format = null;
                break;
        }

        }


}




