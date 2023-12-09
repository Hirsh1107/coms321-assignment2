import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Disassembler {
    private static int[] branchAddresses = new int[64];
    private static ArrayList<String> printList = new ArrayList<>();
    private static int iCount;
    public static void main(String[] args) {
            

        if (args.length != 1) {
            System.out.println("Usage: java Disassembler <input_file>");
            System.exit(1);
        }

        String inputFileName = args[0];

        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputFileName))) {
            while (dis.available() >= 4) {
                int instruction = dis.readInt();
                disassemble(instruction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String s: printList){
            System.out.println(s);
        }
    }

    private static void disassemble(int instruction) {
        int opcode = instruction >>> 21;
        boolean instructionDefined = false;
        int Rd;
        int Rn;
        int Rm;
        int shamt;
        int imm;
        int addr;
        int Rt;
        String instructionString = "branch";

        //check for r-type and d-type
        Rd = instruction & 0x1F;
        Rn = (instruction >>> 5) & 0x1F;
        Rm = (instruction >>> 16) & 0x1F;
        shamt = (instruction >>> 10) & 0x3F;
        String rd = correctReg(Rd);
        String rn = correctReg(Rn);
        String rm = correctReg(Rm);
        switch (opcode) {
            case 0b10001011000: // ADD
                instructionString = "ADD" + rd + ", " + rn + ", " + rm;
                instructionDefined = true;
                break;

            case 0b10001010000: // AND
                instructionString = "AND " + rd + ", " + rn + ", " + rm;
                instructionDefined = true;
                break;

            case 0b11010110000: // BR
                instructionString = "BR " + rn;
                instructionDefined = true;
                break;
            
            case 0b11001010000: // EOR
                instructionString = "EOR " + rd + ", " + rn + ", " + rm;
                instructionDefined = true;
                break;

            case 0b11010011011: // LSL
                instructionString = "LSL " + rd + ", " + rn + " #" + shamt;
                instructionDefined = true;
                break;

            case 0b11010011010: // LSR
                instructionString = "LSR " + rd + ", " + rn + " #" + shamt;
                instructionDefined = true;
                break;

            case 0b10101010000: // ORR
                instructionString = "OOR " + rd + ", " + rn + ", " + rm;
                instructionDefined = true;
                break;

            case 0b11001011000: // SUB
                instructionString = "SUB " + rd + ", " + rn + ", " + rm;
                instructionDefined = true;
                break;

            case 0b11101011000: // SUBS
                instructionString = "SUBS " + rd + ", " + rn + ", " + rm;
                instructionDefined = true;
                break;

            case 0b10011011000: // MUL
                instructionString = "OOR " + rd + ", " + rn + ", " + rm;
                instructionDefined = true;
                break;

            case 0b11111111101: // PRNT
                instructionString = "PRNT " + rm;
                instructionDefined = true;
                break;

            case 0b11111111100: // PRNL
                instructionString = "PRNL";
                instructionDefined = true;
                break;

            case 0b11111111110: // DUMP
                instructionString = "DUMP";
                instructionDefined = true;
                break;

            case 0b11111111111: // HALT
                instructionString = "HALT";
                instructionDefined = true;
                break;

            //d-type
            //this is not implemented yet, will probably have to do a seperate switch case
            //to calculate the the correct registers and what not
            case 0b11111000010: // LDUR
                instructionString = "LDUR " + rd + ", [" + rn + ", #" + shamt + "]";
                instructionDefined = true;
                break;

            //d-type
            case 0b11111000000: // STUR
                instructionString = "STUR " + rd + ", [" + rn + ", #" + shamt + "]";
                instructionDefined = true;
                break;

            default:
                break;
        }
        if(instructionDefined) {
            printList.add(iCount, instructionString);
            iCount++;
			return;
		}
        //i-type
        opcode = instruction >>> 22;
        Rd = instruction & 0x1F;
        Rn = (instruction >>> 5) & 0x1F;
        imm = (instruction >>> 10) & 0xFFF;
        rd = correctReg(Rd);
        rn = correctReg(Rn);
        switch (opcode) {
            case 0b1001000100: // ADDI
                instructionString = "ADDI " + rd + ", " + rn + ",  #" + imm;
                instructionDefined = true;
                break;

            case 0b1001001000: // ANDI
                instructionString = "ANDI " + rd + ", " + rn + ",  #" + imm;
                instructionDefined = true;
                break;

            case 0b1101001000: // EORI
                instructionString = "EORI " + rd + ", " + rn + ",  #" + imm;
                instructionDefined = true;
                break;

            case 0b1011001000: // ORRI
                instructionString = "ORRI " + rd + ", " + rn + ",  #" + imm;
                instructionDefined = true;
                break;

            case 0b1101000100: // SUBI
                instructionString = "SUBI " + rd + ", " + rn + ",  #" + imm;
                instructionDefined = true;
                break;

            case 0b1111000100: // SUBIS
                instructionString = "SUBIS " + rd + ", " + rn + ",  #" + imm;
                instructionDefined = true;
                break;
            default:
                break;
        }
        if(instructionDefined) {
            printList.add(iCount, instructionString);
            iCount++;
			return;
		}
        //CB type
        opcode = instruction >>> 24;
        addr = (instruction >>> 5) & 0x7FFFFF;
        Rt = instruction & 0x1F;
        String rt = correctReg(Rt);
        String branchString = Integer.toHexString(addr);
    

        switch (opcode) {

            case 0b01010100: // B.COND
            //Conds are 00000 == EQ
            //00001 == NE
            //01011 == LT
            //01101 == LE
            //01100 == GT
            //01010 == GE
                switch(Rt){
                    case 0b00000: 
                        rt = "EQ";
                        break;
                    case 0b00001: 
                        rt = "NE";
                        break;
                    case 0b01011: 
                        rt = "LT";
                        break;
                    case 0b01101: 
                        rt = "LE";
                        break;
                    case 0b01100: 
                        rt = "GT";
                        break;
                    case 0b01010: 
                        rt = "GE";
                        break;
                    default:
                        rt = "cond undefined";
                        return;

                }
                    instructionString = "B." + rt + ", 0x" + branchString;
                    instructionDefined = true;
                    break;
            
            case 0b10110101: // CBNZ
                instructionString = "CBZ " + rt + ", 0x" + branchString;
                instructionDefined = true;
                break;
            
            case 0b10110100: // CBZ
                instructionString = "CBNZ " + rt + ", 0x" + branchString;
                instructionDefined = true;
                break;

            default:
			    break;
        }
        if(instructionDefined) {
            printList.add(iCount, instructionString);
            iCount++;
			return;
		}
        opcode = instruction >>> 26;
        addr = (instruction >>> 6) & 0x3FFFFFFF;
        
        switch (opcode) {

            case 0b000101: // B
                instructionString = "B 0x" + branchString;
                instructionDefined = true;
                break;
            
            case 0b100101: // BL
                instructionString = "BL 0x" + branchString;
                instructionDefined = true;
                break;

            default:
			    break;
        }
        if(instructionDefined) {
            printList.add(iCount, instructionString);
            iCount++;
			return;
		}
        printList.add("Unknown opcode:"+ opcode);
    }

    public static String correctReg(int register) {
    switch (register) {
        case 28:
            return "SP";
        case 29:
            return "FP";
        case 30:
            return "LR";
        case 31:
            return "XZR";
        default:
            return "X" + register;
        }
    }
}
