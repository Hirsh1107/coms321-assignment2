public class Disassembler {
  
    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }
        String fileName = args[0];
    }

    //idk if this works yet
    private int binaryToDecimal(int[] binaryArr) {
        int result = 0;
        for(int i = 0; i <= binaryArr.length; i++) {
            if(binaryArr[i] == 1) {
                result += Math.pow(2, binaryArr.length - i - 1);
            }
        }
        return result;
    }
}
