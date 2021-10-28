package com.john.utils;

/**
 * @author John
 * @create 2021-10-2721:08
 */
public class CodeRandomUtil {
    public static void main(String[] args) {
        System.out.println(CodeRandomUtil.getRandom(6));
    }
    public static String getRandom(int count){
        String[] letters = new String[] {
                "q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
                "A","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
                "0","1","2","3","4","5","6","7","8","9"};
        String code = "";
        for (int i = 0; i < count; i++) {
            code = code + letters[(int)Math.floor(Math.random()*letters.length)];
        }
        return code;
    }
}