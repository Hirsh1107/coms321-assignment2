public class Disassembler {
  
    public static void main(String[] args) {
       int[] binary = {1,0,0,0,1,0,1,1,0,0,0};
       int decimal = binaryToDecimal(binary);
       System.out.println(decimal);
       System.out.print(new OpCode(decimal));

        // if (args.length != 1) {
        //     System.exit(1);
        // }
        // String fileName = args[0];   
    }

    
    //idk if this works yet
    private static int binaryToDecimal(int[] binaryArr) {
        int result = 0;
        for(int i = 0; i < binaryArr.length; i++) {
            if(binaryArr[i] == 1) {
                result += Math.pow(2, binaryArr.length - i - 1);
            }
        }
        return result;
    }
}
