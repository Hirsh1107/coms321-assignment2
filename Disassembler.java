import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class Disassembler {
  
    public static void main(String[] args) {
       int[] binary = {1,1,0,1,0,0,1,1,0,1,1,   0,1,1,1,0,  0,0,0,0,0,0,    0,0,0,1,0, 0,0,0,0,1};
       System.out.println(binary.length);
       printArray(binary);
       System.out.println();
       OpCode test = findOpCode(binary);
       rType(test, binary);
    }


    private static OpCode findOpCode(int[] binary){
       int[] firstEleven = Arrays.copyOfRange(binary,0, 11);
       int decimal = binaryToDecimal(firstEleven);
       OpCode returnCode = new OpCode(decimal);
       return returnCode;
    }



    
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(",");
            }
        }
    }
    

    
    private static int binaryToDecimal(int[] binaryArr) {
        int result = 0;
        for(int i = 0; i < binaryArr.length; i++) {
            if(binaryArr[i] == 1) {
                result += Math.pow(2, binaryArr.length - i - 1);
            }
        }
        return result;
    }

    private static int[] decimalToBinary(int decimalNum) {
       
        int numBits = (int) (Math.log(decimalNum) / Math.log(2)) + 1;
        int[] binaryArr = new int[numBits];
        for (int i = numBits - 1; i >= 0; i--) {
            binaryArr[i] = decimalNum % 2;
            decimalNum /= 2;
        }
        return binaryArr;
    }

    private static int bitShift(int decimal, int shiftBits){
        if (shiftBits >= 0) {
            return decimal << shiftBits;
        } else {
            return decimal >> Math.abs(shiftBits);
        }
    }
    public static String findRegister(int memorysize){
        return "X" + memorysize;
    }
    

    private static int rType(OpCode opcode, int[] rawBinary){
        
        int rmInt = binaryToDecimal(Arrays.copyOfRange(rawBinary, 11,16));
        printArray(Arrays.copyOfRange(rawBinary, 12,17));
        System.out.println();
        int shamt = binaryToDecimal(Arrays.copyOfRange(rawBinary, 16,22));
        if(shamt != opcode.getShamt()){
            throw new RuntimeErrorException(null, "The shamt calculate by OpCode and the one provided in binary do not match");
        }
        int rnInt = binaryToDecimal(Arrays.copyOfRange(rawBinary, 22,27));
        int rdInt = binaryToDecimal(Arrays.copyOfRange(rawBinary, 27,32));
        
        String name = opcode.getName();
        String rm = findRegister(rmInt);
        String rn = findRegister(rnInt);
        String rd = findRegister(rdInt);
        System.out.print(name+ " " + rm + ", " + rn + ", " + rd);


        return 0;
    }


}
