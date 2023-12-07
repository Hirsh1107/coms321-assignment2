import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Disassembler {
    private static int[] branchAddresses = new int[64];
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
    }

    private static void disassemble(int instruction) {
        int opcode = instruction >>> 21;
        int offset = 0;
        boolean instructionDefined = false;
        int Rd;
        int Rn;
        int Rm;
        int shamt;
        int imm;
        int addr;
        int Rt;

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
                System.out.println("ADD " + rd + ", " + rn + ", " + rm);
                instructionDefined = true;
                break;

            case 0b10001010000: // AND
                System.out.println("AND " + rd + ", " + rn + ", " + rm);
                instructionDefined = true;
                break;

            case 0b11010110000: // BR
                System.out.println("BR " + rn);
                instructionDefined = true;
                break;
            
            case 0b11001010000: // EOR
                System.out.println("EOR " + rd + ", " + rn + ", " + rm);
                instructionDefined = true;
                break;

            case 0b11010011011: // LSL
                System.out.println("LSL " + rd + ", " + rn + " #" + shamt);
                instructionDefined = true;
                break;

            case 0b11010011010: // LSR
                System.out.println("LSR " + rd + ", " + rn + " #" + shamt);
                instructionDefined = true;
                break;

            case 0b10101010000: // ORR
                System.out.println("OOR " + rd + ", " + rn + ", " + rm);
                instructionDefined = true;
                break;

            case 0b11001011000: // SUB
                System.out.println("SUB " + rd + ", " + rn + ", " + rm);
                instructionDefined = true;
                break;

            case 0b11101011000: // SUBS
                System.out.println("SUBS " + rd + ", " + rn + ", " + rm);
                instructionDefined = true;
                break;

            case 0b10011011000: // MUL
                System.out.println("OOR " + rd + ", " + rn + ", " + rm);
                instructionDefined = true;
                break;

            case 0b11111111101: // PRNT
                System.out.println("PRNT " + rm);
                instructionDefined = true;
                break;

            case 0b11111111100: // PRNL
                System.out.println("PRNL");
                instructionDefined = true;
                break;

            case 0b11111111110: // DUMP
                System.out.println("DUMP");
                instructionDefined = true;
                break;

            case 0b11111111111: // HALT
                System.out.println("HALT");
                instructionDefined = true;
                break;

            //d-type
            //this is not implemented yet, will probably have to do a seperate switch case
            //to calculate the the correct registers and what not
            case 0b11111000010: // LDUR
                System.out.println("LDUR " + rd + ", [" + rn + ", #" + shamt + "]");
                instructionDefined = true;
                break;

            //d-type
            case 0b11111000000: // STUR
                System.out.println("STUR " + rd + ", [" + rn + ", #" + shamt + "]");
                instructionDefined = true;
                break;

            default:
                break;
        }
        if(instructionDefined) {
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
                System.out.println("ADDI " + rd + ", " + rn + ",  #" + imm);
                instructionDefined = true;
                break;

            case 0b1001001000: // ANDI
                System.out.println("ANDI " + rd + ", " + rn + ",  #" + imm);
                instructionDefined = true;
                break;

            case 0b1101001000: // EORI
                System.out.println("EORI " + rd + ", " + rn + ",  #" + imm);
                instructionDefined = true;
                break;

            case 0b1011001000: // ORRI
                System.out.println("ORRI " + rd + ", " + rn + ",  #" + imm);
                instructionDefined = true;
                break;

            case 0b1101000100: // SUBI
                System.out.println("SUBI " + rd + ", " + rn + ",  #" + imm);
                instructionDefined = true;
                break;

            case 0b1111000100: // SUBIS
                System.out.println("SUBIS " + rd + ", " + rn + ",  #" + imm);
                instructionDefined = true;
                break;
            default:
                break;
        }
        if(instructionDefined) {
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
                    System.out.println("B." + rt + ", 0x" + branchString);
                    instructionDefined = true;
                    break;
            
            case 0b10110101: // CBNZ
                System.out.println("CBZ " + rt + ", 0x" + branchString);
                instructionDefined = true;
                break;
            
            case 0b10110100: // CBZ
                System.out.println("CBNZ " + rt + ", 0x" + branchString);
                instructionDefined = true;
                break;

            default:
			    break;
        }
        if(instructionDefined) {
			return;
		}
        opcode = instruction >>> 26;
        addr = (instruction >>> 6) & 0x3FFFFFFF;
        branchString = Integer.toHexString(addr);
        switch (opcode) {

            case 0b000101: // B
                System.out.println("B 0x" + branchString);
                instructionDefined = true;
                break;
            
            case 0b100101: // BL
                System.out.println("BL 0x" + branchString);
                instructionDefined = true;
                break;

            default:
			    break;
        }
        if(instructionDefined) {
			return;
		}
        System.out.printf("Unknown opcode: %d%n", opcode);
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
